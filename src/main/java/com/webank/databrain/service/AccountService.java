package com.webank.databrain.service;

import com.webank.databrain.contracts.AccountModule;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.dao.db.dao.AccountInfoDAO;
import com.webank.databrain.dao.db.dao.CompanyInfoDAO;
import com.webank.databrain.dao.db.dao.PersonInfoDAO;
import com.webank.databrain.enums.CodeEnum;
import com.webank.databrain.model.bo.CompanyInfoBO;
import com.webank.databrain.model.bo.PersonInfoBO;
import com.webank.databrain.dao.db.entity.AccountInfoEntity;
import com.webank.databrain.dao.db.entity.CompanyInfoEntity;
import com.webank.databrain.dao.db.entity.PersonInfoEntity;
import com.webank.databrain.enums.AccountStatus;
import com.webank.databrain.enums.AccountType;
import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.exception.DataBrainException;
import com.webank.databrain.handler.key.ThreadLocalKeyPairHandler;
import com.webank.databrain.handler.token.ITokenHandler;
import com.webank.databrain.utils.AccountUtils;
import com.webank.databrain.utils.BlockchainUtils;
import com.webank.databrain.utils.PagingUtils;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.account.*;
import com.webank.databrain.vo.response.account.*;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.codec.decode.TransactionDecoderInterface;
import org.fisco.bcos.sdk.v3.transaction.tools.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private Client client;

    @Autowired
    private ThreadLocalKeyPairHandler keyPairHandler;

    @Autowired
    private CryptoKeyPair witnessKeyPair;
    @Autowired
    private SysConfig sysConfig;

    @Autowired
    private AccountInfoDAO accountDAO;

    @Autowired
    private PersonInfoDAO personInfoDAO;

    @Autowired
    private CompanyInfoDAO companyInfoDAO;


    @Autowired
    private ITokenHandler tokenHandler;

    @Autowired
    private TransactionDecoderInterface txDecoder;

    @Transactional
    public CommonResponse<RegisterResponse> registerAccount(RegisterRequest request) throws Exception {
        //Generation private key
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        CryptoKeyPair keyPair = cryptoSuite.generateRandomKeyPair();
        //Save to blockchain
        AccountModule accountContract = AccountModule.load(
                sysConfig.getContractConfig().getAccountContract(),
                client,
                keyPair);
        TransactionReceipt txReceipt = accountContract.register(BigInteger.valueOf(request.getAccountType().ordinal()), cryptoSuite.hash(request.getUserName().getBytes()));
        byte[] didBytes = accountContract.getRegisterOutput(txReceipt).getValue1();
        BlockchainUtils.ensureTransactionSuccess(txReceipt, txDecoder);
        log.info("blockchain generate did : {}", AccountUtils.encode(didBytes));
        //Save to database
        String username = request.getUserName();
        String password = request.getPassword();
        int accountType = request.getAccountType().ordinal();
        String did = AccountUtils.encode(didBytes);
        String privateKey = keyPair.getHexPrivateKey();
        String salt = sysConfig.getSalt();
        String pwdHash = AccountUtils.getPwdHash(cryptoSuite, password, salt);
        AccountInfoEntity accountInfoEntity = new AccountInfoEntity();
        accountInfoEntity.setAccountType(accountType);
        accountInfoEntity.setDid(did);
        accountInfoEntity.setPwdHash(pwdHash);
        accountInfoEntity.setSalt(salt);
        accountInfoEntity.setStatus(AccountStatus.Registered.ordinal());
        accountInfoEntity.setPrivateKey(privateKey);
        accountInfoEntity.setUserName(username);

        accountDAO.saveItem(accountInfoEntity);
        long accountPkId = accountInfoEntity.getPkId();
        if (accountType == AccountType.Personal.ordinal()) {
            PersonalDetailRequest personalDetail = JsonUtils.fromJson(request.getDetailJson(), PersonalDetailRequest.class);
            PersonInfoEntity personInfoPo = new PersonInfoEntity();
            personInfoPo.setPersonCertNo(personalDetail.getCertNum() != null? personalDetail.getCertNum() : "");
            personInfoPo.setPersonContact(personalDetail.getContact()!=null?personalDetail.getContact(): "");
            personInfoPo.setPersonEmail(personalDetail.getEmail() != null? personalDetail.getEmail() : "");
            personInfoPo.setPersonName(personalDetail.getName() != null? personalDetail.getName() : "");
            personInfoPo.setAccountId(accountInfoEntity.getPkId());
            personInfoPo.setPersonCertType(personalDetail.getCertType()!=null?personalDetail.getCertType():"");

            personInfoDAO.saveItem(personInfoPo);
        } else if (accountType == AccountType.Company.ordinal()) {
            CompanyDetailRequest companyDetail = JsonUtils.fromJson(request.getDetailJson(), CompanyDetailRequest.class);
            CompanyInfoEntity companyInfoEntity = new CompanyInfoEntity();
            companyInfoEntity.setCompanyContact(companyDetail.getContact()!=null? companyDetail.getContact() : "");
            companyInfoEntity.setCompanyName(companyDetail.getCompanyName());
            companyInfoEntity.setCompanyDesc(companyDetail.getCompanyDesc()!=null?companyDetail.getCompanyDesc():"");
            companyInfoEntity.setAccountId(accountPkId);
            companyInfoEntity.setCompanyCertType(companyDetail.getCertType()!=null?companyDetail.getCertType():"");
            companyInfoEntity.setCompanyCertFileUri(companyDetail.getCertFileUrl()!=null?companyDetail.getCertFileUrl():"");
            companyInfoEntity.setCompanyCertNo(companyDetail.getCertNo()!=null?companyDetail.getCertNo():"");
            companyInfoDAO.saveItem(companyInfoEntity);
        }

        return CommonResponse.success(new RegisterResponse(did));
    }


    public CommonResponse<LoginResponse> login(LoginRequest loginRequest) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        String username = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        AccountInfoEntity accountInfo = accountDAO.selectByUserName(username);
        if (accountInfo == null){
            return CommonResponse.error(CodeEnum.USER_NOT_EXISTS);
        }
        String pwdHash = AccountUtils.getPwdHash(cryptoSuite, password, accountInfo.getSalt());
        if (!Objects.equals(pwdHash, accountInfo.getPwdHash())) {
            return CommonResponse.error(CodeEnum.PWD_NOT_RIGHT);
        }
        String token = tokenHandler.generateToken(accountInfo.getDid());
        LoginResponse result = new LoginResponse();
        result.setToken(token);
        result.setAccountType(accountInfo.getAccountType().intValue());
        result.setDid(accountInfo.getDid());
        return CommonResponse.success(result);
    }

    public CommonResponse<HotCompaniesResponse> listHotCompanies(int topN) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        List<CompanyInfoBO> companyInfoDataObjects = companyInfoDAO.listHotCompany(topN);
        List<CompanyInfoResponse> items = companyInfoDataObjects.stream().map(b->AccountUtils.companyBOToVO(cryptoSuite, b)).collect(Collectors.toList());
        HotCompaniesResponse response = new HotCompaniesResponse(items);
        return CommonResponse.success(response);
    }

    public CommonResponse<PageQueryCompanyResponse> listCompanyByPage(PageQueryCompanyRequest request) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        int itemsCount = companyInfoDAO.totalCount();
        List<CompanyInfoBO> companyInfoDataObjects = companyInfoDAO.listCompany(request.getPageNo(), request.getPageSize());
        List<CompanyInfoResponse> items = companyInfoDataObjects.stream().map(b->AccountUtils.companyBOToVO(cryptoSuite, b)).collect(Collectors.toList());

        PageQueryCompanyResponse response = new PageQueryCompanyResponse();
        response.setTotalCount(itemsCount);
        response.setItemList(items);
        response.setPageCount(PagingUtils.toPageCount(itemsCount, request.getPageSize()));

        return CommonResponse.success(response);
    }

    public CommonResponse<GetPrivateKeyResponse> getPrivateKey(String did) {
        AccountInfoEntity entity = accountDAO.selectByDid(did);
        if (entity == null){
            return CommonResponse.error(CodeEnum.USER_NOT_EXISTS);
        }
        return CommonResponse.success(new GetPrivateKeyResponse(entity.getPrivateKey()));
    }

    public CommonResponse<QueryPersonByUsernameResponse> getPersonByUsername(String username) {

        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();

        PersonInfoBO data = personInfoDAO.queryPersonByUsername(username);
        if (data == null){
            return CommonResponse.error(CodeEnum.USER_NOT_EXISTS);
        }

        PersonInfoResponse voItem = AccountUtils.personBOToVO(cryptoSuite, data);
        QueryPersonByUsernameResponse ret = new QueryPersonByUsernameResponse();
        ret.setItem(voItem);

        return CommonResponse.success(ret);
    }

    public CommonResponse<QueryCompanyByUsernameResponse> getCompanyByUsername(String username) {

        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();

        CompanyInfoBO data = companyInfoDAO.queryCompanyByUsername(username);
        if (data == null){
            return CommonResponse.error(CodeEnum.USER_NOT_EXISTS);
        }

        CompanyInfoResponse voItem = AccountUtils.companyBOToVO(cryptoSuite, data);
        QueryCompanyByUsernameResponse ret = new QueryCompanyByUsernameResponse();
        ret.setItem(voItem);
        return CommonResponse.success(ret);
    }

    public CommonResponse<SearchCompanyResponse> searchCompanies(SearchCompanyRequest request) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        String statusStr = request.getCondition().getAccountStatus();
        AccountStatus status = AccountStatus.valueOf(statusStr);
        int totalCount = companyInfoDAO.totalCountWithStatus(status.ordinal());
        List<CompanyInfoBO> boList = companyInfoDAO.listCompanyWithStatus(status.ordinal(), request.getPageNo(), request.getPageSize());
        List<CompanyInfoResponse> voItems = boList.stream().map(b -> AccountUtils.companyBOToVO(cryptoSuite, b)).collect(Collectors.toList());
        SearchCompanyResponse searchCompanyResponse = new SearchCompanyResponse();
        searchCompanyResponse.setItemList(voItems);
        searchCompanyResponse.setTotalCount(totalCount);
        searchCompanyResponse.setPageCount(PagingUtils.toPageCount(totalCount, request.getPageSize()));
        return CommonResponse.success(searchCompanyResponse);
    }

    public CommonResponse<SearchPersonResponse> searchPersons(SearchPersonRequest request) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        String statusStr = request.getCondition().getAccountStatus();
        AccountStatus status = AccountStatus.valueOf(statusStr);
        int totalCount = personInfoDAO.totalCountWithStatus(status.ordinal());
        int pageCount = PagingUtils.toPageCount(totalCount, request.getPageSize());
        List<PersonInfoBO> boList = personInfoDAO.listPersonWithStatus(status.ordinal(), request.getPageNo(), request.getPageSize());
        List<PersonInfoResponse> voItems = boList.stream().map(b -> AccountUtils.personBOToVO(cryptoSuite, b)).collect(Collectors.toList());

        SearchPersonResponse response = new SearchPersonResponse();
        response.setItemList(voItems);
        response.setPageCount(pageCount);
        response.setTotalCount(totalCount);

        return CommonResponse.success(response);
    }

    public CommonResponse approveAccount(ApproveAccountRequest request) throws Exception{
        String did = request.getDid();
        boolean approve = request.isApproved();
        AccountInfoEntity accountInfoPO = accountDAO.selectByDid(did);
        if (accountInfoPO == null){
            throw new DataBrainException(ErrorEnums.AccountNotExists);
        }

        byte[] didBytes = AccountUtils.decode(did);
        CryptoKeyPair witnessKeyPair = this.witnessKeyPair;
        AccountModule accountModule = AccountModule.load(sysConfig.getContractConfig().getAccountContract(), client, witnessKeyPair);
        TransactionReceipt txReceipt = accountModule.approve(didBytes, approve);
        BlockchainUtils.ensureTransactionSuccess(txReceipt, txDecoder);
        //修改数据库状态
        AccountStatus status = approve?AccountStatus.Approved:AccountStatus.Denied;
        accountDAO.updateAccountStatus(did, status);
        return CommonResponse.success();
    }



}
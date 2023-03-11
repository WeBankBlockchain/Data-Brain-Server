package com.webank.databrain.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.webank.databrain.contracts.AccountModule;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.db.dao.AccountInfoDAO;
import com.webank.databrain.db.dao.CompanyInfoDAO;
import com.webank.databrain.db.dao.PersonInfoDAO;
import com.webank.databrain.model.bo.CompanyInfoBO;
import com.webank.databrain.model.bo.PersonInfoBO;
import com.webank.databrain.model.req.account.*;
import com.webank.databrain.model.resp.PagedResult;
import com.webank.databrain.model.resp.account.*;
import com.webank.databrain.model.po.AccountInfoPO;
import com.webank.databrain.model.po.CompanyInfoPO;
import com.webank.databrain.model.po.PersonInfoPO;
import com.webank.databrain.enums.AccountStatus;
import com.webank.databrain.enums.AccountType;
import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.exception.DataBrainException;
import com.webank.databrain.handler.key.ThreadLocalKeyPairHandler;
import com.webank.databrain.handler.token.ITokenHandler;
import com.webank.databrain.utils.AccountUtils;
import com.webank.databrain.utils.BlockchainUtils;
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
    public RegisterResponse registerAccount(RegisterRequest request) throws Exception {
        //Generation private key
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        CryptoKeyPair keyPair = cryptoSuite.generateRandomKeyPair();
        //Save to blockchain
        AccountModule accountContract = AccountModule.load(
                sysConfig.getContractConfig().getAccountContract(),
                client,
                keyPair);
        TransactionReceipt txReceipt = accountContract.register(BigInteger.valueOf(request.getAccountType().ordinal()), cryptoSuite.hash(request.getUsername().getBytes()));
        byte[] didBytes = accountContract.getRegisterOutput(txReceipt).getValue1();
        BlockchainUtils.ensureTransactionSuccess(txReceipt, txDecoder);
        log.info("blockchain generate did : {}", AccountUtils.encode(didBytes));
        //Save to database
        String username = request.getUsername();
        String password = request.getPassword();
        int accountType = request.getAccountType().ordinal();
        String did = AccountUtils.encode(didBytes);
        String privateKey = keyPair.getHexPrivateKey();
        String salt = sysConfig.getSalt();
        String pwdHash = AccountUtils.getPwdHash(cryptoSuite, password, salt);
        AccountInfoPO accountInfoDo = new AccountInfoPO();
        accountInfoDo.setAccountType(accountType);
        accountInfoDo.setDid(did);
        accountInfoDo.setPwdHash(pwdHash);
        accountInfoDo.setSalt(salt);
        accountInfoDo.setStatus(AccountStatus.Registered.ordinal());
        accountInfoDo.setPrivateKey(privateKey);
        accountInfoDo.setUsername(username);

        accountDAO.save(accountInfoDo);
        long accountPkId = accountInfoDo.getPkId();
        if (accountType == AccountType.Personal.ordinal()) {
            PersonalDetailInput personalDetail = JsonUtils.fromJson(request.getDetailJson(), PersonalDetailInput.class);
            PersonInfoPO personInfoPo = new PersonInfoPO();
            personInfoPo.setPersonCertNo(personalDetail.getCertNum());
            personInfoPo.setPersonContact(personalDetail.getContact());
            personInfoPo.setPersonEmail(personalDetail.getEmail());
            personInfoPo.setPersonName(personalDetail.getName());
            personInfoPo.setAccountId(accountInfoDo.getPkId());
            personInfoPo.setPersonCertType(personalDetail.getCertType());

            personInfoDAO.save(personInfoPo);
        } else if (accountType == AccountType.Company.ordinal()) {
            CompanyDetailInput companyDetail = JsonUtils.fromJson(request.getDetailJson(), CompanyDetailInput.class);
            CompanyInfoPO companyInfoPo = new CompanyInfoPO();
            companyInfoPo.setCompanyContact(companyDetail.getContact());
            companyInfoPo.setCompanyName(companyDetail.getCompanyName());
            companyInfoPo.setCompanyDesc(companyDetail.getCompanyDesc());
            companyInfoPo.setAccountId(accountPkId);
            companyInfoPo.setCompanyCertType(companyDetail.getCertType());
            companyInfoPo.setCompanyCertFileUri(companyDetail.getCertFileUrl());
            companyInfoPo.setCompanyCertNo(companyDetail.getCertNo());
            companyInfoDAO.save(companyInfoPo);
        }

        return new RegisterResponse(did);
    }


    public LoginResponse login(LoginRequest loginRequest) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        AccountInfoPO accountInfo = accountDAO.getOne(Wrappers.<AccountInfoPO>query().eq("user_name", username), false);
        if (accountInfo == null){
            throw new DataBrainException(ErrorEnums.InvalidCredential);
        }
        String pwdHash = AccountUtils.getPwdHash(cryptoSuite, password, accountInfo.getSalt());
        if (!Objects.equals(pwdHash, accountInfo.getPwdHash())) {
            throw new DataBrainException(ErrorEnums.InvalidCredential);
        }
        String token = tokenHandler.generateToken(accountInfo.getPkId());
        LoginResponse result = new LoginResponse();
        result.setToken(token);
        result.setAccountType(accountInfo.getAccountType().intValue());
        result.setDid(accountInfo.getDid());
        return result;
    }

    public HotCompaniesResponse listHotCompanies(int topN) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        List<CompanyInfoBO> companyInfoDataObjects = companyInfoDAO.listHotCompany(topN);
        List<CompanyInfoVO> items = companyInfoDataObjects.stream().map(b->AccountUtils.companyBOToVO(cryptoSuite, b)).collect(Collectors.toList());
        HotCompaniesResponse response = new HotCompaniesResponse(items);
        return response;
    }

    public PageQueryCompanyResponse listCompanyByPage(PageQueryCompanyRequest request) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        List<CompanyInfoBO> companyInfoDataObjects = companyInfoDAO.listCompany(request.getPageNo(), request.getPageSize());
        List<CompanyInfoVO> items = companyInfoDataObjects.stream().map(b->AccountUtils.companyBOToVO(cryptoSuite, b)).collect(Collectors.toList());
        return new PageQueryCompanyResponse(new PagedResult<>(
                items,
                request.getPageNo(),
                request.getPageSize()));
    }

    public String getPrivateKey(String did) {
//        AccountDO accountDO =  accountDAO.getAccountByDid(did);
//        if (accountDO == null){
//            throw new DataBrainException(ErrorEnums.AccountNotExists);
//        }
//        return accountDO.getPrivateKey();
        return null;
    }

    public QueryPersonByUsernameResponse getPersonByUsername(String username) {

        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();

        PersonInfoBO data = personInfoDAO.queryPersonByUsername(username);
        if (data == null){
            throw new DataBrainException(ErrorEnums.AccountNotExists);
        }

        PersonInfoVO voItem = AccountUtils.personBOToVO(cryptoSuite, data);
        QueryPersonByUsernameResponse ret = new QueryPersonByUsernameResponse();
        ret.setItem(voItem);

        return ret;
    }

    public QueryCompanyByUsernameResponse getCompanyByUsername(String username) {

        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();

        CompanyInfoBO data = companyInfoDAO.queryCompanyByUsername(username);
        if (data == null){
            throw new DataBrainException(ErrorEnums.AccountNotExists);
        }

        CompanyInfoVO voItem = AccountUtils.companyBOToVO(cryptoSuite, data);
        QueryCompanyByUsernameResponse ret = new QueryCompanyByUsernameResponse();
        ret.setItem(voItem);
        return ret;
    }

    public SearchCompanyResponse searchCompanies(SearchCompanyRequest request) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        String statusStr = request.getCondition().getAccountStatus();
        AccountStatus status = AccountStatus.valueOf(statusStr);
        List<CompanyInfoBO> boList = companyInfoDAO.listCompanyWithStatus(status.ordinal(), request.getPageNo(), request.getPageSize());
        List<CompanyInfoVO> voItems = boList.stream().map(b -> AccountUtils.companyBOToVO(cryptoSuite, b)).collect(Collectors.toList());
        return new SearchCompanyResponse(new PagedResult<>(
                voItems,
                request.getPageNo(),
                request.getPageSize()
        ));
    }

    public SearchPersonResponse searchPersons(SearchPersonRequest request) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        String statusStr = request.getCondition().getAccountStatus();
        AccountStatus status = AccountStatus.valueOf(statusStr);
        List<PersonInfoBO> boList = personInfoDAO.listPersonWithStatus(status.ordinal(), request.getPageNo(), request.getPageSize());
        List<PersonInfoVO> voItems = boList.stream().map(b -> AccountUtils.personBOToVO(cryptoSuite, b)).collect(Collectors.toList());
        return new SearchPersonResponse(new PagedResult<>(
                voItems,
                request.getPageNo(),
                request.getPageSize()
        ));
    }

    public void approveAccount(ApproveAccountRequest request) throws Exception{
        String did = request.getDid();
        boolean approve = request.isApproved();
        AccountInfoPO accountInfoPO = accountDAO.getOne(Wrappers.<AccountInfoPO>query().eq("did", did));
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
    }
//
//
//    public void auditAccount(String username, boolean agree) throws Exception{
//        //获取did
//        AccountDO accountDO = accountDAO.getAccountByName(username);
//        if (accountDO == null) {
//            throw new DataBrainException(ErrorEnums.AccountNotExists);
//        }
//        byte[] didBytes = AccountUtils.decode(accountDO.getDid());
//        //链上审批
//        CryptoKeyPair witnessKeyPair = this.witnessKeyPair;
//        AccountModule accountModule = AccountModule.load(sysConfig.getContractConfig().getAccountContract(), client, witnessKeyPair);
//        TransactionReceipt txReceipt = accountModule.approve(didBytes, agree);
//        BlockchainUtils.ensureTransactionSuccess(txReceipt, txDecoder);
//        //修改数据库状态
//        accountDAO.updateReviewStatus(accountDO.getDid(), agree?ReviewStatus.Approved:ReviewStatus.Denied, LocalDateTime.now());
//    }
//
//



}
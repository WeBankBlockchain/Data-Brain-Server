package com.webank.databrain.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.webank.databrain.blockchain.AccountModule;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.db.dao.AccountInfoDAO;
import com.webank.databrain.db.dao.CompanyInfoDAO;
import com.webank.databrain.db.dao.PersonInfoDAO;
import com.webank.databrain.db.entity.AccountInfoDataObject;
import com.webank.databrain.db.entity.CompanyInfoDataObject;
import com.webank.databrain.db.entity.PersonInfoDataObject;
import com.webank.databrain.enums.AccountStatus;
import com.webank.databrain.enums.AccountType;
import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.error.DataBrainException;
import com.webank.databrain.handler.key.ThreadLocalKeyPairHandler;
import com.webank.databrain.handler.token.ITokenHandler;
import com.webank.databrain.model.dto.account.CompanyDetailInput;
import com.webank.databrain.model.dto.account.PersonalDetailInput;
import com.webank.databrain.model.request.account.LoginRequest;
import com.webank.databrain.model.request.account.RegisterRequest;
import com.webank.databrain.model.response.account.LoginResponse;
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

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Objects;

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
    public String registerAccount(RegisterRequest request) throws Exception {
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
        AccountInfoDataObject accountInfoDataObject = new AccountInfoDataObject();
        accountInfoDataObject.setAccountType(accountType);
        accountInfoDataObject.setDid(did);
        accountInfoDataObject.setPwdhash(pwdHash);
        accountInfoDataObject.setSalt(salt);
        accountInfoDataObject.setStatus(AccountStatus.Registered.ordinal());
        accountInfoDataObject.setPrivateKey(keyPair.getHexPrivateKey());
        accountInfoDataObject.setUsername(username);

        accountDAO.save(accountInfoDataObject);
        long accountPkId = accountInfoDataObject.getPkId();
        if (accountType == AccountType.Personal.ordinal()) {
            PersonalDetailInput personalDetail = JsonUtils.fromJson(request.getDetailJson(), PersonalDetailInput.class);
            PersonInfoDataObject personInfoDataObject = new PersonInfoDataObject();
            personInfoDataObject.setPersonCertNo(personalDetail.getCertNum());
            personInfoDataObject.setPersonContact(personalDetail.getContact());
            personInfoDataObject.setPersonEmail(personalDetail.getEmail());
            personInfoDataObject.setPersonName(personalDetail.getName());
            personInfoDataObject.setPersonId(accountInfoDataObject.getPkId());
            personInfoDataObject.setPersonCertType(personalDetail.getCertType());

            personInfoDAO.save(personInfoDataObject);
        } else if (accountType == AccountType.Company.ordinal()) {
            CompanyDetailInput companyDetail = JsonUtils.fromJson(request.getDetailJson(), CompanyDetailInput.class);
            CompanyInfoDataObject companyInfoDataObject = new CompanyInfoDataObject();
            companyInfoDataObject.setCompanyContact(companyDetail.getContact());
            companyInfoDataObject.setCompanyName(companyDetail.getCompanyName());
            companyInfoDataObject.setCompanyDesc(companyDetail.getCompanyDesc());
            companyInfoDataObject.setCompanyId(accountPkId);
            companyInfoDataObject.setCompanyCertType(companyDetail.getCertType());
            companyInfoDataObject.setCompanyCertFileUri(companyDetail.getLogoUrl());
            companyInfoDAO.save(companyInfoDataObject);
        }

        return did;
    }


    public LoginResponse login(LoginRequest loginRequest) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        AccountInfoDataObject accountInfo = accountDAO.getOne(Wrappers.<AccountInfoDataObject>query().eq("username", username), false);
        if (accountInfo == null){
            throw new DataBrainException(ErrorEnums.InvalidCredential);
        }
        String pwdHash = AccountUtils.getPwdHash(cryptoSuite, password, accountInfo.getSalt());
        if (!Objects.equals(pwdHash, accountInfo.getPwdhash())) {
            throw new DataBrainException(ErrorEnums.InvalidCredential);

        }
        String token = tokenHandler.generateToken(accountInfo.getPkId());
        LoginResponse result = new LoginResponse();
        result.setToken(token);
        result.setDid(accountInfo.getDid());
        return result;
    }

//    public HotCompaniesResponse listHotOrgs(int topN) {
//        return new HotCompaniesResponse(orgDAO.listHotOrgs(topN));
//    }
//
//    public PageQueryCompanyResponse listOrgsByPage(PageQueryCompanyRequest request) {
//        Paging paging = new Paging(request.getPageNo(), request.getPageSize());
//        PagedResult<IdName> pagingResult = orgDAO.listOrgsByPage(paging);
//
//        return new PageQueryCompanyResponse(pagingResult);
//    }
//
//    public String getPrivateKey(String did) {
//        AccountDO accountDO =  accountDAO.getAccountByDid(did);
//        if (accountDO == null){
//            throw new DataBrainException(ErrorEnums.AccountNotExists);
//        }
//        return accountDO.getPrivateKey();
//    }
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
//
//    public QueryAccountByIdResponse getAccountDetail(String did){
//        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
//        AccountDO accountDO = accountDAO.getAccountByDid(did);
//        if (accountDO == null){
//            throw new DataBrainException(ErrorEnums.AccountNotExists);
//        }
//        Object detail = null;
//        if (accountDO.getAccountType() == AccountType.Personal){
//            detail = this.getNormalUserDetail(did);
//        }
//        else if(accountDO.getAccountType() == AccountType.Enterprise){
//            detail = this.getOrgDetail(did);
//        }
//
//        QueryAccountByIdResponse ret = new QueryAccountByIdResponse();
//        ret.setDid(did);
//        ret.setAddress(cryptoSuite.loadKeyPair(accountDO.getPrivateKey()).getAddress());
//        ret.setType(accountDO.getAccountType().name());
//        ret.setReviewStatus(accountDO.getReviewStatus().name());
//        ret.setDetail(detail);
//
//        return ret;
//    }
//
//    public CompanyDetail getOrgDetail(String did){
//        OrgInfoDataObject orgInfoDataObject = orgDAO.getOne(
//                Wrappers.<OrgInfoDataObject>query().eq("org_id",did));
//        CompanyDetail orgUserDetail = new CompanyDetail();
//        BeanUtils.copyProperties(orgInfoDataObject,orgUserDetail);
//        return  orgUserDetail;
//    }
//
//    public PersonalDetail getNormalUserDetail(String did){
//        UserInfoDataObject userDetailDataObject = userInfoDAO.getOne(
//                Wrappers.<UserInfoDataObject>query().eq("user_id",did));
//        PersonalDetail userDetail = new PersonalDetail();
//        BeanUtils.copyProperties(userDetailDataObject, userDetailDataObject);
//        return userDetail;
//    }
}
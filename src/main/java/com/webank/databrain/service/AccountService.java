package com.webank.databrain.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.webank.databrain.blockchain.AccountModule;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.db.dao.IAccountDbService;
import com.webank.databrain.db.dao.IOrgInfoDbService;
import com.webank.databrain.db.dao.IUserInfoDbService;
import com.webank.databrain.db.entity.OrgInfoDataObject;
import com.webank.databrain.db.entity.UserInfoDataObject;
import com.webank.databrain.enums.AccountType;
import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.enums.ReviewStatus;
import com.webank.databrain.error.DataBrainException;
import com.webank.databrain.handler.key.ThreadLocalKeyPairHandler;
import com.webank.databrain.handler.token.ITokenHandler;
import com.webank.databrain.model.domain.account.AccountDO;
import com.webank.databrain.model.dto.account.NormalUserDetail;
import com.webank.databrain.model.dto.account.OrgUserDetail;
import com.webank.databrain.model.dto.common.IdName;
import com.webank.databrain.model.dto.common.Paging;
import com.webank.databrain.model.request.account.LoginRequest;
import com.webank.databrain.model.request.account.PageQueryCompanyRequest;
import com.webank.databrain.model.request.account.RegisterRequest;
import com.webank.databrain.model.response.account.HotCompaniesResponse;
import com.webank.databrain.model.response.account.LoginResponse;
import com.webank.databrain.model.response.account.PageQueryCompanyResponse;
import com.webank.databrain.model.response.account.QueryAccountByIdResponse;
import com.webank.databrain.model.response.common.PagedResult;
import com.webank.databrain.utils.AccountUtils;
import com.webank.databrain.utils.BlockchainUtils;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.codec.decode.TransactionDecoderInterface;
import org.fisco.bcos.sdk.v3.transaction.tools.JsonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDateTime;
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
    private IAccountDbService accountDAO;

    @Autowired
    private IUserInfoDbService userInfoDAO;

    @Autowired
    private IOrgInfoDbService orgDAO;


    @Autowired
    private ITokenHandler tokenHandler;

    @Autowired
    private TransactionDecoderInterface txDecoder;

    @Transactional
    public String registerAccount(RegisterRequest request) throws Exception{
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
        accountDAO.insert(username, accountType, did, privateKey, salt, pwdHash);
        if (accountType == AccountType.NormalUser.ordinal()){
            NormalUserDetail normalUser = JsonUtils.fromJson(request.getDetailJson(), NormalUserDetail.class);
            userInfoDAO.insert(did, normalUser);
        } else if(accountType == AccountType.Enterprise.ordinal()){
            OrgUserDetail orgUserDetail = JsonUtils.fromJson(request.getDetailJson(), OrgUserDetail.class);
            orgDAO.insert(did, orgUserDetail);
        }

        return did;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        AccountDO accountDO = accountDAO.getAccountByName(username);
        if (accountDO == null){
            throw new DataBrainException(ErrorEnums.InvalidCredential);
        }
        String pwdHash = AccountUtils.getPwdHash(cryptoSuite, password, accountDO.getSalt());
        if (!Objects.equals(pwdHash, accountDO.getPwdhash())) {
            throw new DataBrainException(ErrorEnums.InvalidCredential);

        }
        LoginResponse result = new LoginResponse();
        result.setToken(tokenHandler.generateToken(accountDO.getDid()));
        result.setDid(accountDO.getDid());
        return result;
    }

    public HotCompaniesResponse listHotOrgs(int topN) {
        return new HotCompaniesResponse(orgDAO.listHotOrgs(topN));
    }

    public PageQueryCompanyResponse listOrgsByPage(PageQueryCompanyRequest request) {
        Paging paging = new Paging(request.getPageNo(), request.getPageSize());
        PagedResult<IdName> pagingResult = orgDAO.listOrgsByPage(paging);

        return new PageQueryCompanyResponse(pagingResult);
    }

    public String getPrivateKey(String did) {
        AccountDO accountDO =  accountDAO.getAccountByDid(did);
        if (accountDO == null){
            throw new DataBrainException(ErrorEnums.AccountNotExists);
        }
        return accountDO.getPrivateKey();
    }


    public void auditAccount(String username, boolean agree) throws Exception{
        //获取did
        AccountDO accountDO = accountDAO.getAccountByName(username);
        if (accountDO == null) {
            throw new DataBrainException(ErrorEnums.AccountNotExists);
        }
        byte[] didBytes = AccountUtils.decode(accountDO.getDid());
        //链上审批
        CryptoKeyPair witnessKeyPair = this.witnessKeyPair;
        AccountModule accountModule = AccountModule.load(sysConfig.getContractConfig().getAccountContract(), client, witnessKeyPair);
        TransactionReceipt txReceipt = accountModule.approve(didBytes, agree);
        BlockchainUtils.ensureTransactionSuccess(txReceipt, txDecoder);
        //修改数据库状态
        accountDAO.updateReviewStatus(accountDO.getDid(), agree?ReviewStatus.Approved:ReviewStatus.Denied, LocalDateTime.now());
    }



    public QueryAccountByIdResponse getAccountDetail(String did){
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        AccountDO accountDO = accountDAO.getAccountByDid(did);
        if (accountDO == null){
            throw new DataBrainException(ErrorEnums.AccountNotExists);
        }
        Object detail = null;
        if (accountDO.getAccountType() == AccountType.NormalUser){
            detail = this.getNormalUserDetail(did);
        }
        else if(accountDO.getAccountType() == AccountType.Enterprise){
            detail = this.getOrgDetail(did);
        }

        QueryAccountByIdResponse ret = new QueryAccountByIdResponse();
        ret.setDid(did);
        ret.setAddress(cryptoSuite.loadKeyPair(accountDO.getPrivateKey()).getAddress());
        ret.setType(accountDO.getAccountType().name());
        ret.setReviewStatus(accountDO.getReviewStatus().name());
        ret.setDetail(detail);

        return ret;
    }

    public OrgUserDetail getOrgDetail(String did){
        OrgInfoDataObject orgInfoDataObject = orgDAO.getOne(
                Wrappers.<OrgInfoDataObject>query().eq("org_id",did));
        OrgUserDetail orgUserDetail = new OrgUserDetail();
        BeanUtils.copyProperties(orgInfoDataObject,orgUserDetail);
        return  orgUserDetail;
    }

    public NormalUserDetail getNormalUserDetail(String did){
        UserInfoDataObject userDetailDataObject = userInfoDAO.getOne(
                Wrappers.<UserInfoDataObject>query().eq("user_id",did));
        NormalUserDetail userDetail = new NormalUserDetail();
        BeanUtils.copyProperties(userDetailDataObject, userDetailDataObject);
        return userDetail;
    }
}

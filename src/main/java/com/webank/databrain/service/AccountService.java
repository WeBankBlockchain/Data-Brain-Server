package com.webank.databrain.service;

import com.webank.databrain.blockchain.AccountModule;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.db.dao.IAccountDbService;
import com.webank.databrain.db.dao.IOrgInfoDbService;
import com.webank.databrain.db.dao.IUserInfoDbService;
import com.webank.databrain.enums.AccountType;
import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.enums.ReviewStatus;
import com.webank.databrain.error.DataBrainException;
import com.webank.databrain.handler.token.ITokenHandler;
import com.webank.databrain.model.account.*;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.utils.AccountUtils;
import com.webank.databrain.utils.BlockchainUtils;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.tools.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class AccountService {

    @Autowired
    private Client client;

    @Autowired
    private CryptoSuite cryptoSuite;

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

    public String registerAccount(RegisterRequestVO request) throws Exception{
        //Generation private key
        CryptoKeyPair keyPair = cryptoSuite.generateRandomKeyPair();
        //Save to blockchain
        AccountModule accountContract = AccountModule.load(
                sysConfig.getContracts().getAccountContract(),
                client,
                keyPair);
        TransactionReceipt txReceipt = accountContract.register(BigInteger.valueOf(request.getAccountType().ordinal()), new byte[32]);
        byte[] didBytes = accountContract.getRegisterOutput(txReceipt).getValue1();
        BlockchainUtils.ensureTransactionSuccess(txReceipt);
        //Save to database
        String username = request.getUsername();
        int userType = request.getAccountType().ordinal();
        String did = AccountUtils.encode(didBytes);
        String privateKey = keyPair.getHexPrivateKey();
        String salt = sysConfig.getSalt();
        String pwdHash = cryptoSuite.hash(username + salt);
        accountDAO.insert(username, userType, did, privateKey, salt, pwdHash);
        if (userType == AccountType.NormalUser.ordinal()){
            NormalUserDetail normalUser = JsonUtils.fromJson(request.getDetailJson(), NormalUserDetail.class);
            userInfoDAO.insert(did, normalUser);
        } else if(userType == AccountType.Enterprise.ordinal()){
            OrgUserDetail orgUserDetail = JsonUtils.fromJson(request.getDetailJson(), OrgUserDetail.class);
            orgDAO.insert(did, orgUserDetail);
        }

        return did;
    }

    public LoginResult login(String username, String password) {
        AccountDO accountDO = accountDAO.getAccountByName(username);
        if (accountDO == null){
            throw new DataBrainException(ErrorEnums.InvalidCredential);
        }
        String pwdhash = cryptoSuite.hash(password+accountDO.getSalt());
        if (!Objects.equals(pwdhash, accountDO.getPwdhash())) {
            throw new DataBrainException(ErrorEnums.InvalidCredential);

        }
        LoginResult result = new LoginResult();
        result.setToken(tokenHandler.generateToken());
        result.setDid(accountDO.getDid());
        return result;
    }

    public List<IdName> listHotOrgs(int topN) {
        return orgDAO.listHotOrgs(topN);
    }

    public PagingResult<OrgSummary> listOrgsByPage(Paging paging) {
        return orgDAO.listOrgsByPage(paging);
    }

    public String getPrivateKey(String did) {
        AccountDO accountDO =  accountDAO.getAccountByDid(did);
        if (accountDO == null){
            throw new DataBrainException(ErrorEnums.DidNotExists);
        }
        return accountDO.getPrivateKey();
    }


    public void auditAccount(String username, boolean agree) throws Exception{
        //获取did
        AccountDO accountDO = accountDAO.getAccountByName(username);
        if (accountDO == null) {
            throw new DataBrainException(ErrorEnums.UsernameNotExists);
        }
        byte[] didBytes = AccountUtils.decode(accountDO.getDid());
        //链上审批
        CryptoKeyPair witnessKeyPair = cryptoSuite.getCryptoKeyPair();
        AccountModule accountModule = AccountModule.load(sysConfig.getContracts().getAccountContract(), client, witnessKeyPair);
        TransactionReceipt txReceipt = accountModule.approve(didBytes, agree);
        BlockchainUtils.ensureTransactionSuccess(txReceipt);
        //修改数据库状态
        accountDAO.updateReviewStatus(accountDO.getDid(), agree?ReviewStatus.Approved:ReviewStatus.Denied, LocalDateTime.now());
    }
}

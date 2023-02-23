package com.webank.databrain.service;

import com.webank.databrain.blockchain.AccountModule;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.db.dao.AccountDAO;
import com.webank.databrain.db.mapper.AccountMapper;
import com.webank.databrain.enums.AccountStatus;
import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.error.DataBrainException;
import com.webank.databrain.handler.TokenHandler;
import com.webank.databrain.model.account.AccountDO;
import com.webank.databrain.model.account.AccountID;
import com.webank.databrain.model.account.AccountSummary;
import com.webank.databrain.model.account.RegisterRequestVO;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.utils.AccountUtils;
import com.webank.databrain.utils.BlockchainUtils;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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
    private AccountDAO accountDAO;

    @Autowired
    private TokenHandler tokenHandler;

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
        return did;

    }

    public String login(String username, String password) {
        AccountDO accountDO = accountDAO.getAccountByName(username);
        if (accountDO == null){
            throw new DataBrainException(ErrorEnums.InvalidCredential);
        }
        String pwdhash = cryptoSuite.hash(password+accountDO.getSalt());
        if (!Objects.equals(pwdhash, accountDO.getPwdhash())) {
            throw new DataBrainException(ErrorEnums.InvalidCredential);

        }
        return tokenHandler.generateToken();
    }

    public List<IdName> listHotEnterprises(int topN) {
        return null;
    }

    public List<PagingResult<AccountSummary>> listEnterprises(Paging paging) {
        return null;
    }

    public String getPrivateKey(String username, String password) {
        return null;
    }


    public void auditAccount(String username, boolean agree, String reason) {

    }
}

package com.webank.databrain.service;

import com.webank.databrain.blockchain.AccountModule;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.db.mapper.AccountMapper;
import com.webank.databrain.enums.AccountStatus;
import com.webank.databrain.model.account.AccountID;
import com.webank.databrain.model.account.AccountSummary;
import com.webank.databrain.model.account.RegisterRequestVO;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.utils.BlockchainUtils;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private Client client;

    @Autowired
    private CryptoSuite cryptoSuite;

    @Autowired
    private SysConfig sysConfig;

    @Autowired
    private AccountMapper mapper;

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
        String did = txReceipt.getOutput();
        String privateKey = keyPair.getHexPrivateKey();
        String salt = sysConfig.getSalt();
        String pwdHash = cryptoSuite.hash(username + salt);
        int reviewState = AccountStatus.UnRegistered.ordinal();


        return did;

    }

    public String login(String username, String password) {
        return null;
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

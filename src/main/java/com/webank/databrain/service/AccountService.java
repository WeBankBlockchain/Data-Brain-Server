package com.webank.databrain.service;

import com.webank.databrain.blockchain.AccountModule;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.model.account.AccountID;
import com.webank.databrain.model.account.AccountSummary;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private Client client;

    @Autowired
    private CryptoSuite cryptoSuite;

    @Autowired
    private SysConfig sysConfig;

    public AccountID registerAccount(String username, String password, ) {
        //Generation private key
        CryptoKeyPair keyPair = cryptoSuite.generateRandomKeyPair();
        //Save to blockchain
        AccountModule accountContract = AccountModule.load(
                sysConfig.getContracts().getAccountContract(),
                client,
                keyPair);
//        accountContract.register(
        //Save
        return null;
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

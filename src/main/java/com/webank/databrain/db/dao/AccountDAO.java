package com.webank.databrain.db.dao;

import com.webank.databrain.db.entity.AccountDataObject;
import com.webank.databrain.db.mapper.AccountMapper;
import com.webank.databrain.model.account.AccountDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AccountDAO {

    @Autowired
    private AccountMapper mapper;

    @PostConstruct
    public void init(){
        mapper.createTable();
    }

    public void insert(String username, int accountType, String did, String privateKey, String salt, String pwdHash) {
        AccountDataObject dataObject = new AccountDataObject();
        dataObject.setUsername(username);
        dataObject.setAccountType(accountType);
        dataObject.setDid(did);
        dataObject.setPrivateKey(privateKey);
        dataObject.setSalt(salt);
        dataObject.setPwdhash(pwdHash);

        this.mapper.insert(dataObject);
    }

    public AccountDO getAccountByName(String username) {
        return null;
    }
}

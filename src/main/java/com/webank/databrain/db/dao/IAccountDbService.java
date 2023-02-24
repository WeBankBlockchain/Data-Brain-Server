package com.webank.databrain.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.db.entity.AccountDataObject;
import com.webank.databrain.model.account.AccountDO;

public interface IAccountDbService extends IService<AccountDataObject> {
    public void insert(String username, int accountType, String did, String privateKey, String salt, String pwdHash) ;

    public AccountDO getAccountByName(String username) ;

    public AccountDO getAccountByDid(String did) ;
}

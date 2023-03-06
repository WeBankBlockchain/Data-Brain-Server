package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.IAccountDbService;
import com.webank.databrain.db.entity.AccountDataObject;
import com.webank.databrain.db.mapper.AccountMapper;
import com.webank.databrain.enums.AccountType;
import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.enums.ReviewStatus;
import com.webank.databrain.error.DataBrainException;
import com.webank.databrain.model.domain.account.AccountDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountDataObject> implements IAccountDbService {


    @PostConstruct
    public void init(){
        baseMapper.createTable();
    }

    public void insert(String username, int accountType, String did, String privateKey, String salt, String pwdHash) {
        AccountDataObject dataObject = new AccountDataObject();
        dataObject.setUsername(username);
        dataObject.setAccountType(accountType);
        dataObject.setDid(did);
        dataObject.setPrivateKey(privateKey);
        dataObject.setSalt(salt);
        dataObject.setPwdhash(pwdHash);

        this.baseMapper.insert(dataObject);
    }

    public AccountDO getAccountByName(String username) {
        AccountDataObject accountDataObject =  baseMapper.selectByName(username);
        if(accountDataObject == null){
            throw new DataBrainException(ErrorEnums.AccountNotExists);
        }
        AccountDO ret = new AccountDO();
        BeanUtils.copyProperties(accountDataObject, ret);
        return ret;
    }

    public AccountDO getAccountByDid(String did) {
        AccountDataObject accountDataObject =  baseMapper.selectByDid(did);
        AccountDO ret = new AccountDO();
        BeanUtils.copyProperties(accountDataObject, ret);
        ret.setAccountType(AccountType.values()[accountDataObject.getAccountType()]);
        ret.setReviewStatus(ReviewStatus.values()[accountDataObject.getReviewState()]);
        return ret;
    }

    @Override
    public void updateReviewStatus(String did, ReviewStatus status, LocalDateTime reviewTime) {
        baseMapper.updateReviewStatus(did, status.ordinal(), reviewTime);
    }


}

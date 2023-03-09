package com.webank.databrain.service.impl;

import com.webank.databrain.dao.db.entity.AccountEntity;
import com.webank.databrain.dao.db.mapper.AccountMapper;
import com.webank.databrain.enums.CodeEnum;
import com.webank.databrain.handler.key.ThreadLocalKeyPairHandler;
import com.webank.databrain.handler.token.ITokenHandler;
import com.webank.databrain.service.AccountService1;
import com.webank.databrain.utils.AccountUtils;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.LoginRequest;
import com.webank.databrain.vo.request.RegisterRequest;
import com.webank.databrain.vo.response.LoginResData;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class AccountServiceImpl implements AccountService1 {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private ThreadLocalKeyPairHandler keyPairHandler;
    @Autowired
    private ITokenHandler tokenHandler;

    @Override
    public CommonResponse registerAccount(RegisterRequest req) {
        return null;
    }

    @Override
    public CommonResponse login(LoginRequest req) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        AccountEntity accountEntity = accountMapper.getAccountByUserName(req.getUserName());
        if (null == accountEntity){
            return CommonResponse.error(CodeEnum.USER_NOT_EXISTS);
        }
        String pwdHash = AccountUtils.getPwdHash(cryptoSuite, req.getPassword(), accountEntity.getSalt());
        if (!pwdHash.equals(accountEntity.getPwdHash())) {
            return CommonResponse.error(CodeEnum.PWD_NOT_RIGHT);
        }
        String token = tokenHandler.generateToken(accountEntity.getPkId());
        LoginResData data = new LoginResData();
        data.setToken(token);
        return CommonResponse.success(data);
    }
}

package com.webank.databrain.db.dao.impl;

import com.webank.databrain.config.SysConfig;
import com.webank.databrain.db.dao.ITokenDAO;
import com.webank.databrain.db.mapper.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class TokenDAOImpl implements ITokenDAO {

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private SysConfig sysConfig;

    @PostConstruct
    public void init(){
        tokenMapper.createTable();
    }


    @Override
    public void upsert(String token, String did) {
        LocalDateTime dateTime = LocalDateTime.now().plusMinutes(sysConfig.getLoginConfig().getTokenExpireMinutes());
        tokenMapper.upsert(token, did, dateTime);
    }

}

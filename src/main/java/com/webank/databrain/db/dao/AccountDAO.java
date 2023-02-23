package com.webank.databrain.db.dao;

import com.webank.databrain.db.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AccountDAO {

    @Autowired
    private AccountMapper mapper;

    @PostConstruct
    public void init(){

    }

}

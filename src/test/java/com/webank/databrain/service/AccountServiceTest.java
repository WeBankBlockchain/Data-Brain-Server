package com.webank.databrain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webank.databrain.ServerApplicationTests;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AccountServiceTest extends ServerApplicationTests {

    @Autowired
    private AccountService1 accountService1;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testLogin() throws JsonProcessingException {
        LoginRequest request = new LoginRequest();
        request.setUserName("hahaha");
        request.setPassword("123456");
        CommonResponse response = accountService1.login(request);
//        log.info("user info: {}", objectMapper.writeValueAsString(response.getData()));
        System.out.println(response.getData());
    }
}

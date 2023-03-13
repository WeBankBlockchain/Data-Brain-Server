package com.webank.databrain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webank.databrain.ServerApplicationTests;
import com.webank.databrain.dao.db.entity.PersonInfoEntity;
import com.webank.databrain.enums.AccountType;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.account.*;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.transaction.tools.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AccountServiceTest extends ServerApplicationTests {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testPersonalUserRegister() throws Exception{
        String username = "personalUser0004";
        String password = "12345678";
        RegisterRequest request = new RegisterRequest();
        request.setUserName(username);
        request.setPassword(password);
        request.setAccountType(AccountType.Personal);
        PersonInfoEntity personInfoEntity = new PersonInfoEntity();
        personInfoEntity.setPersonName("haha");
        personInfoEntity.setPersonContact("1231131313");
        personInfoEntity.setPersonEmail("email");
        personInfoEntity.setPersonCertType("type");
        personInfoEntity.setPersonCertNo("11111");
        request.setDetailJson(objectMapper.writeValueAsString(personInfoEntity));
        CommonResponse response = accountService.registerAccount(request);
        log.info("response: {}", objectMapper.writeValueAsString(response));
    }

    @Test
    public void testEnterpriseUserRegister() throws Exception{
        String username = "companyUser00001";
        String password = "12345678";
        RegisterRequest request = new RegisterRequest();
        request.setUserName(username);
        request.setPassword(password);
        request.setAccountType(AccountType.Company);
        CompanyDetailRequest companyDetail = new CompanyDetailRequest();
        companyDetail.setCompanyName("阿里");
        companyDetail.setCompanyDesc("某公司");
        companyDetail.setCertNo("123456");
        request.setDetailJson(JsonUtils.toJson(companyDetail));
        CommonResponse response = accountService.registerAccount(request);
        log.info("response: {}", objectMapper.writeValueAsString(response));
    }

    @Test
    public void testLogin() throws Exception{
        String username = "personalUser0002";
        String password = "12345678";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName(username);
        loginRequest.setPassword(password);
        CommonResponse response = accountService.login(loginRequest);
        log.info("response: {}", objectMapper.writeValueAsString(response));
    }


    @Test
    public void testAudit() throws Exception{
//        String username = "personalUser0016";
//        String password = "12345678";
//        RegisterRequest request = new RegisterRequest();
//        request.setUserName(username);
//        request.setPassword(password);
//        request.setAccountType(AccountType.Personal);
//        PersonalDetailRequest orgUserDetail = new PersonalDetailRequest();
//        orgUserDetail.setName("李四");
//        request.setDetailJson(JsonUtils.toJson(orgUserDetail));
//        CommonResponse<RegisterResponse> result = accountService.registerAccount(request);
//        String did = result.getData().getDid();
//
//        ApproveAccountRequest approveAccountRequest = new ApproveAccountRequest();
//        approveAccountRequest.setApproved(true);
//        approveAccountRequest.setDid(did);
//
//        accountService.approveAccount(approveAccountRequest);

    }
}

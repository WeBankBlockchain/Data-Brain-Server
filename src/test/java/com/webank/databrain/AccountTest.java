package com.webank.databrain;

import com.webank.databrain.enums.AccountType;
import com.webank.databrain.model.request.account.LoginRequest;
import com.webank.databrain.model.request.account.RegisterRequest;
import com.webank.databrain.model.dto.account.CompanyDetailInput;
import com.webank.databrain.model.response.account.LoginResponse;
import com.webank.databrain.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.transaction.tools.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AccountTest extends ServerApplicationTests{
    @Autowired
    private AccountService accountService;
    @Test
    public void testPersonalUserRegister() throws Exception{
        String username = "fiscobcos";
        String password = "12345678";
        RegisterRequest request = new RegisterRequest();
        request.setUsername(username);
        request.setPassword(password);
        request.setAccountType(AccountType.Personal);
        CompanyDetailInput orgUserDetail = new CompanyDetailInput();
        orgUserDetail.setCompanyName("facebook");
        request.setDetailJson(JsonUtils.toJson(orgUserDetail));
        accountService.registerAccount(request);
    }

    @Test
    public void testEnterpriseUserRegister() throws Exception{
        String username = "fiscobcos2";
        String password = "12345678";
        RegisterRequest request = new RegisterRequest();
        request.setUsername(username);
        request.setPassword(password);
        request.setAccountType(AccountType.Company);
        CompanyDetailInput companyDetail = new CompanyDetailInput();
        companyDetail.setCompanyName("facebook");
        companyDetail.setCompanyDesc("社交公司");
        request.setDetailJson(JsonUtils.toJson(companyDetail));
        accountService.registerAccount(request);
    }

    @Test
    public void testLogin() throws Exception{
        String username = "fiscobcos2";
        String password = "12345678";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        LoginResponse loginResult = accountService.login(loginRequest);
        System.out.println(loginResult);

        loginResult = accountService.login(loginRequest);
        System.out.println(loginResult);

    }
}

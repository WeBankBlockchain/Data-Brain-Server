package com.webank.databrain;

import com.webank.databrain.enums.AccountType;
import com.webank.databrain.model.account.*;
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
    public void testEnterpriseRegister() throws Exception{
        String username = "chuyuzhi";
        String password = "12345678";
        RegisterRequestVO request = new RegisterRequestVO();
        request.setUsername(username);
        request.setPassword(password);
        request.setAccountType(AccountType.NormalUser);
        OrgUserDetail orgUserDetail = new OrgUserDetail();
        orgUserDetail.setOrgName("facebook");
        request.setDetailJson(JsonUtils.toJson(orgUserDetail));
        accountService.registerAccount(request);
    }

    @Test
    public void testLogin() throws Exception{
        String username = "chuyuzhi";
        String password = "12345678";

        LoginRequestVO loginRequest = new LoginRequestVO();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        LoginResult loginResult = accountService.login(loginRequest);
        System.out.println(loginResult);
    }
}

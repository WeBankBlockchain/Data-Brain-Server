package com.webank.databrain;

import com.webank.databrain.enums.AccountType;
import com.webank.databrain.model.request.account.LoginRequest;
import com.webank.databrain.model.request.account.RegisterRequest;
import com.webank.databrain.model.response.account.LoginResponse;
import com.webank.databrain.model.vo.account.OrgUserDetail;
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
        RegisterRequest request = new RegisterRequest();
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

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        LoginResponse loginResult = accountService.login(loginRequest);
        System.out.println(loginResult);
    }
}

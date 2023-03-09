package com.webank.databrain;

import com.webank.databrain.enums.AccountType;
import com.webank.databrain.model.req.account.*;
import com.webank.databrain.model.resp.account.*;
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
        String username = "personalUser0002";
        String password = "12345678";
        RegisterRequest request = new RegisterRequest();
        request.setUsername(username);
        request.setPassword(password);
        request.setAccountType(AccountType.Personal);
        CompanyDetailInput orgUserDetail = new CompanyDetailInput();
        orgUserDetail.setCompanyName("facebook");
        request.setDetailJson(JsonUtils.toJson(orgUserDetail));
        Object result = accountService.registerAccount(request);
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    public void testEnterpriseUserRegister() throws Exception{
        String username = "companyUser00001";
        String password = "12345678";
        RegisterRequest request = new RegisterRequest();
        request.setUsername(username);
        request.setPassword(password);
        request.setAccountType(AccountType.Company);
        CompanyDetailInput companyDetail = new CompanyDetailInput();
        companyDetail.setCompanyName("阿里");
        companyDetail.setCompanyDesc("某公司");
        companyDetail.setCertNo("123456");
        request.setDetailJson(JsonUtils.toJson(companyDetail));
        Object result = accountService.registerAccount(request);
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    public void testRegistration() throws Exception{
        String requestStr = "{\"accountType\":\"0\",\"username\":\"1\",\"password\":\"1\",\"detailJson\":\"{\\\"name\\\":\\\"1\\\",\\\"contact\\\":\\\"1\\\",\\\"email\\\":\\\"a@1.com\\\",\\\"certType\\\":\\\"nationalID\\\",\\\"certNum\\\":\\\"1\\\"}\"}";
        RegisterRequest request = JsonUtils.fromJson(requestStr, RegisterRequest.class);


        Object result = accountService.registerAccount(request);
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    public void testLogin() throws Exception{
        String username = "companyUser00001";
        String password = "12345678";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        LoginResponse loginResult = accountService.login(loginRequest);
        System.out.println(loginResult);

        loginResult = accountService.login(loginRequest);
        System.out.println(loginResult);

    }

    @Test
    public void testHotCompanies() throws Exception{
        HotCompaniesResponse response = accountService.listHotCompanies(5);
        System.out.println(JsonUtils.toJson(response));
    }

    @Test
    public void testListCompanyByPage() throws Exception{
        PageQueryCompanyRequest request = new PageQueryCompanyRequest();
        request.setPageNo(1);
        request.setPageSize(2);
        PageQueryCompanyResponse response = accountService.listCompanyByPage(request);
        System.out.println(JsonUtils.toJson(response));

    }

    @Test
    public void testQueryByUsername() throws Exception{
        QueryByUsernameRequest request = new QueryByUsernameRequest();
        request.setUsername("personalUser0002");

        QueryPersonByUsernameResponse personResponse = accountService.getPersonByUsername(request.getUsername());
        System.out.println(JsonUtils.toJson(personResponse));

        request.setUsername("companyUser00001");
        QueryCompanyByUsernameResponse companyResponse = accountService.getCompanyByUsername(request.getUsername());
        System.out.println(JsonUtils.toJson(companyResponse));

    }
}

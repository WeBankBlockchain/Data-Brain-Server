package com.webank.databrain.controller;

import com.webank.databrain.model.request.account.*;
import com.webank.databrain.model.response.account.*;
import com.webank.databrain.model.response.common.CommonResponse;
import com.webank.databrain.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "注册")
    @PostMapping("register")
    public CommonResponse<RegisterResponse> register(@RequestBody RegisterRequest request) throws Exception {
        String did = accountService.registerAccount(request);

        return CommonResponse.success(new RegisterResponse(did));
    }

    @ApiOperation(value = "登陆")
    @PostMapping("login")
    public CommonResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse result = accountService.login(request);
        return CommonResponse.success(result);
    }

//    @ApiOperation(value = "热门公司")
//    @PostMapping("getHotCompanies")
//    public CommonResponse<HotCompaniesResponse> getHotCompanies(@RequestBody HotCompaniesRequest request) {
//        HotCompaniesResponse hotCompaniesResponse = accountService.listHotOrgs(request.getTopN());
//        return CommonResponse.success(hotCompaniesResponse);
//    }
////
//    @ApiOperation(value = "公司列表")
//    @PostMapping("/pageQueryCompany")
//    public CommonResponse<PageQueryCompanyResponse> pageQueryCompany(@RequestBody PageQueryCompanyRequest request) {
//        PageQueryCompanyResponse response = accountService.listOrgsByPage(request);
//        return CommonResponse.success(response);
//    }
////
//    @ApiOperation(value = "账户详情")
//    @PostMapping("queryAccountById")
//    public CommonResponse<QueryAccountByIdResponse> queryAccountById(@RequestBody QueryAccountByIdRequest request) {
//        QueryAccountByIdResponse detail = accountService.getAccountDetail(request.getDid());
//        return CommonResponse.success(detail);
//    }

}

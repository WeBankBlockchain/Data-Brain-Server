package com.webank.databrain.controller;

import com.webank.databrain.model.req.account.HotCompaniesRequest;
import com.webank.databrain.model.req.account.PageQueryCompanyRequest;
import com.webank.databrain.model.req.account.QueryByUsernameRequest;
import com.webank.databrain.model.req.account.RegisterRequest;
import com.webank.databrain.model.resp.CommonResponse;
import com.webank.databrain.model.resp.account.*;
import com.webank.databrain.service.AccountService;
import com.webank.databrain.service.AccountService1;
import com.webank.databrain.vo.request.LoginRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

//@RestController
@RequestMapping("/api/account")
@Slf4j
public class AccountController1 {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountService1 accountService1;

    @ApiOperation(value = "注册")
    @PostMapping("register")
    public CommonResponse<RegisterResponse> register(@RequestBody RegisterRequest request) throws Exception {
        return CommonResponse.success(accountService.registerAccount(request));
    }

    @ApiOperation(value = "登陆")
    @PostMapping("login")
    public com.webank.databrain.vo.common.CommonResponse login(@RequestBody @Valid LoginRequest request) {
        return accountService1.login(request);
    }

    @ApiOperation(value = "热门公司")
    @PostMapping("getHotCompanies")
    public CommonResponse<HotCompaniesResponse> getHotCompanies(@RequestBody HotCompaniesRequest request) {
        HotCompaniesResponse hotCompaniesResponse = accountService.listHotCompanies(request.getTopN());
        return CommonResponse.success(hotCompaniesResponse);
    }
//
    @ApiOperation(value = "公司列表")
    @PostMapping("/pageQueryCompany")
    public CommonResponse<PageQueryCompanyResponse> pageQueryCompany(@RequestBody PageQueryCompanyRequest request) {
        PageQueryCompanyResponse response = accountService.listCompanyByPage(request);
        return CommonResponse.success(response);
    }
//
    @ApiOperation(value = "查询个人用户详情")
    @PostMapping("queryPersonByUsername")
    public CommonResponse<QueryPersonByUsernameResponse> queryPersonByUsername(@RequestBody QueryByUsernameRequest request) {
        QueryPersonByUsernameResponse detail = accountService.getPersonByUsername(request.getUsername());
        return CommonResponse.success(detail);
    }

    @ApiOperation(value = "查询机构用户详情")
    @PostMapping("queryCompanyByUsername")
    public CommonResponse<QueryCompanyByUsernameResponse> queryCompanyByUsername(@RequestBody QueryByUsernameRequest request) {
        QueryCompanyByUsernameResponse detail = accountService.getCompanyByUsername(request.getUsername());
        return CommonResponse.success(detail);
    }
}

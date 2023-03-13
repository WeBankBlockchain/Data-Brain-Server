package com.webank.databrain.controller;

import com.webank.databrain.service.AccountService;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.account.*;
import com.webank.databrain.vo.response.account.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;


    @ApiOperation(value = "注册")
    @PostMapping("register")
    public CommonResponse<RegisterResponse> register(@RequestBody RegisterRequest request) throws Exception {
        return accountService.registerAccount(request);
    }

    @ApiOperation(value = "登陆")
    @PostMapping("login")
    public CommonResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return accountService.login(request);
    }

    @ApiOperation(value = "热门公司")
    @PostMapping("getHotCompanies")
    public CommonResponse<HotCompaniesResponse> getHotCompanies(@RequestBody HotCompaniesRequest request) {
        return accountService.listHotCompanies(request.getTopN());
    }
//
    @ApiOperation(value = "公司列表")
    @PostMapping("/pageQueryCompany")
    public CommonResponse<PageQueryCompanyResponse> pageQueryCompany(@RequestBody PageQueryCompanyRequest request) {
        return accountService.listCompanyByPage(request);
    }
//
    @ApiOperation(value = "查询个人用户详情")
    @PostMapping("queryPersonByUsername")
    public CommonResponse<QueryPersonByUsernameResponse> queryPersonByUsername(@RequestBody QueryByUsernameRequest request) {
        return accountService.getPersonByUsername(request.getUsername());
    }

    @ApiOperation(value = "查询机构用户详情")
    @PostMapping("queryCompanyByUsername")
    public CommonResponse<QueryCompanyByUsernameResponse> queryCompanyByUsername(@RequestBody QueryByUsernameRequest request) {
        return accountService.getCompanyByUsername(request.getUsername());
    }

    @ApiOperation(value = "根据条件搜索机构")
    @PostMapping("searchCompany")
    public CommonResponse<SearchCompanyResponse> searchCompanies(@RequestBody SearchCompanyRequest request) {
        return accountService.searchCompanies(request);
    }

    @ApiOperation(value = "根据条件搜索个人用户")
    @PostMapping("searchPerson")
    public CommonResponse<SearchPersonResponse> searchPersons(@RequestBody SearchPersonRequest request) {
        return accountService.searchPersons(request);
    }

    @ApiOperation(value = "审批用户")
    @PostMapping("approveAccount")
    public CommonResponse<Void> approveAccount(@RequestBody ApproveAccountRequest request) throws Exception{
        accountService.approveAccount(request);
        return CommonResponse.success(null);
    }
}

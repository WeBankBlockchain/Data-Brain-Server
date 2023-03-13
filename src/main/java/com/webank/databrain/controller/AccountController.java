package com.webank.databrain.controller;

import com.webank.databrain.service.AccountService;
import com.webank.databrain.service.CompanyService;
import com.webank.databrain.service.PersonService;
import com.webank.databrain.vo.common.CommonPageQueryRequest;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.common.HotDataRequest;
import com.webank.databrain.vo.request.account.*;
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

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PersonService personService;

    @ApiOperation(value = "注册")
    @PostMapping("register")
    public CommonResponse register(@RequestBody @Valid RegisterRequest request) throws Exception {
        return accountService.registerAccount(request);
    }

    @ApiOperation(value = "登陆")
    @PostMapping("login")
    public CommonResponse login(@RequestBody @Valid LoginRequest request) {
        return accountService.login(request);
    }

    @ApiOperation(value = "审批用户")
    @PostMapping("approveAccount")
    public CommonResponse approveAccount(@RequestBody @Valid ApproveAccountRequest request) throws Exception{
        return accountService.approveAccount(request);
    }

    @ApiOperation(value = "热门公司")
    @PostMapping("getHotCompanies")
    public CommonResponse getHotCompanies(@RequestBody @Valid HotDataRequest request) {
        return companyService.listHotCompanies(request);
    }

    @ApiOperation(value = "公司列表")
    @PostMapping("/pageQueryCompany")
    public CommonResponse pageQueryCompany(@RequestBody @Valid CommonPageQueryRequest request) {
        return companyService.listCompanyByPage(request);
    }

    @ApiOperation(value = "查询个人用户详情")
    @PostMapping("queryPersonByUsername")
    public CommonResponse queryPersonByUsername(@RequestBody @Valid QueryByUsernameRequest request) {
        return personService.getPersonByUsername(request);
    }

    @ApiOperation(value = "查询机构用户详情")
    @PostMapping("queryCompanyByUsername")
    public CommonResponse queryCompanyByUsername(@RequestBody @Valid QueryByUsernameRequest request) {
        return companyService.getCompanyByUsername(request);
    }

    @ApiOperation(value = "根据条件搜索机构")
    @PostMapping("searchCompany")
    public CommonResponse searchCompanies(@RequestBody @Valid SearchAccountRequest request) {
        return companyService.searchCompanies(request);
    }

    @ApiOperation(value = "根据条件搜索个人用户")
    @PostMapping("searchPerson")
    public CommonResponse searchPersons(@RequestBody @Valid SearchAccountRequest request) {
        return personService.searchPersons(request);
    }
}

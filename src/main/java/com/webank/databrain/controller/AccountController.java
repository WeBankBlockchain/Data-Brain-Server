package com.webank.databrain.controller;

import com.webank.databrain.model.account.*;
import com.webank.databrain.model.common.CommonResponse;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "注册")
    @PostMapping("register")
    public CommonResponse register(@RequestBody RegisterRequestVO request) throws Exception {
        String did = accountService.registerAccount(request);
        return CommonResponse.createSuccessResult(did);
    }

    @ApiOperation(value = "登陆")
    @PostMapping("login")
    public CommonResponse login(@RequestBody LoginRequestVO request) {
        LoginResult result = accountService.login(request);
        return CommonResponse.createSuccessResult(result);
    }

    @ApiOperation(value = "热门公司")
    @PostMapping("getHotCompanies")
    public CommonResponse getHotCompanies(@RequestBody HotOrgsRequestVO request) {
        List<IdName> idNames = accountService.listHotOrgs(request.getTopN());
        return CommonResponse.createSuccessResult(idNames);
    }
//
    @ApiOperation(value = "公司列表")
    @PostMapping("/pageQueryCompany")
    public CommonResponse pageQueryCompany(@RequestBody ListOrgsByPageRequestVO request) {
        PagingResult<OrgSummary> orgs = accountService.listOrgsByPage(request);
        return CommonResponse.createSuccessResult(orgs);
    }
//
    @ApiOperation(value = "账户详情")
    @PostMapping("queryAccountById")
    public CommonResponse queryAccountById(@RequestBody GetOrgDetailRequestVO request) {
        AccountDetailResponse detail = accountService.getAccountDetail(request.getDid());
        return CommonResponse.createSuccessResult(detail);
    }

}

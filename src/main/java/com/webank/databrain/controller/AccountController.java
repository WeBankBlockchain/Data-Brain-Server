package com.webank.databrain.controller;

import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.error.DataBrainException;
import com.webank.databrain.model.account.*;
import com.webank.databrain.model.common.CommonResponse;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.product.ProductDetail;
import com.webank.databrain.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;
    @PostMapping("register")
    public CommonResponse register(@RequestBody RegisterRequestVO request) throws Exception {
        String did = accountService.registerAccount(request);
        return CommonResponse.createSuccessResult(did);
    }

    @PostMapping("login")
    public CommonResponse login(@RequestBody LoginRequestVO request) {
        LoginResult result = accountService.login(request);
        return CommonResponse.createSuccessResult(result);
    }

//    public CommonResponse listHotOrgs(int topN) {
//        return orgDAO.listHotOrgs(topN);
//    }
//
//    public CommonResponse listOrgsByPage(Paging paging) {
//        return orgDAO.listOrgsByPage(paging);
//    }
//
//    public CommonResponse getPrivateKey(String did) {
//        AccountDO accountDO =  accountDAO.getAccountByDid(did);
//        if (accountDO == null){
//            throw new DataBrainException(ErrorEnums.DidNotExists);
//        }
//        return accountDO.getPrivateKey();
//    }



}

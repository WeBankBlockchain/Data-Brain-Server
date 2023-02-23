package com.webank.databrain.controller;

import com.webank.databrain.model.account.AccountID;
import com.webank.databrain.model.account.RegisterRequestVO;
import com.webank.databrain.model.common.CommonResponse;
import com.webank.databrain.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService service;
    @PostMapping("register")
    public CommonResponse register(@RequestBody RegisterRequestVO request) {
        //arg validation
        //
        AccountID accountId = service.registerAccount(request.getUsername(), request.getPassword());
        return null;
    }

}

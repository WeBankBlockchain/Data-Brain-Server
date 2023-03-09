package com.webank.databrain.service;

import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.LoginRequest;
import com.webank.databrain.vo.request.RegisterRequest;

public interface AccountService1 {

    CommonResponse registerAccount(RegisterRequest req);
    CommonResponse login(LoginRequest req);
}

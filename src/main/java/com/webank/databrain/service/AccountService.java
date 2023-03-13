package com.webank.databrain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.account.*;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionException;

public interface AccountService {

    CommonResponse registerAccount(RegisterRequest request) throws TransactionException, JsonProcessingException;
    CommonResponse login(LoginRequest request);
    CommonResponse approveAccount(ApproveAccountRequest request) throws TransactionException;
}

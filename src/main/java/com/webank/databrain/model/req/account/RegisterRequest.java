package com.webank.databrain.model.req.account;

import com.webank.databrain.enums.AccountType;
import lombok.Data;

@Data
public class RegisterRequest {

    private String username;

    private String password;

    private AccountType accountType;

    private String detailJson;
}

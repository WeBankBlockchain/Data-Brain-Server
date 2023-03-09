package com.webank.databrain.model.input.account;

import com.webank.databrain.enums.AccountType;
import lombok.Data;

@Data
public class RegisterRequest {

    private String username;

    private String password;

    private AccountType accountType;

    private String detailJson;
}

package com.webank.databrain.model.account;

import com.webank.databrain.enums.AccountType;
import lombok.Data;

@Data
public class RegisterRequestVO {

    private String username;

    private String password;

    private AccountType accountType;

    private String detailJson;
}

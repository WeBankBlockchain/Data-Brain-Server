package com.webank.databrain.model.account;

import lombok.Data;

@Data
public class LoginResult {

    private String token;

    private String did;
}

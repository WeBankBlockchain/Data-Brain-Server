package com.webank.databrain.model.response.account;

import lombok.Data;

@Data
public class LoginResponse {
    private String did;

    private String token;
}

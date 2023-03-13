package com.webank.databrain.vo.response.account;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private int accountType;
    private String did;
}

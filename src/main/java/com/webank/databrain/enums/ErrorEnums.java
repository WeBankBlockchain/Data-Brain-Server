package com.webank.databrain.enums;

import lombok.Getter;

@Getter
public enum ErrorEnums {
    InvalidCredential("00000001", "Invalid username or password"),
    UsernameNotExists("00000002", "Username not exists"),
    DidNotExists("00000003", "Did not exists");
    private String code;
    private String message;

    ErrorEnums(String code, String message){
        this.code = code;
        this.message = message;
    }

}

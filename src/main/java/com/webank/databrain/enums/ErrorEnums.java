package com.webank.databrain.enums;

import lombok.Getter;

@Getter
public enum ErrorEnums {
    InvalidCredential("00000001", "Invalid username or password");

    private String code;
    private String message;

    ErrorEnums(String code, String message){
        this.code = code;
        this.message = message;
    }

}

package com.webank.databrain.enums;

import lombok.Getter;

@Getter
public enum ErrorEnums {
    Success("0", "Success"),
    InvalidCredential("10000001", "用户名或密码不正确"),
    AccountNotExists("10000002", "Did不存在"),
    ProductNotExists("10000003", "产品不存在"),

    FileNotExists("10000005", "文件不存在"),
    UnknownError("99999999", "");

    private String code;
    private String message;

    ErrorEnums(String code, String message){
        this.code = code;
        this.message = message;
    }

}

package com.webank.databrain.model.account;

import cn.hutool.core.codec.Base64;

public class AccountID {
    private byte[] content;


    @Override
    public String toString(){
        return Base64.encode(this.content);
    }

}

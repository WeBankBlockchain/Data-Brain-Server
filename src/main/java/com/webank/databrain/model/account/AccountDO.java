package com.webank.databrain.model.account;

import lombok.Data;

@Data
public class AccountDO {
    private String username;

    private String salt;

    private String pwdhash;
}

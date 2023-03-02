package com.webank.databrain.model.account;

import com.webank.databrain.enums.AccountType;
import com.webank.databrain.enums.ReviewStatus;
import lombok.Data;

@Data
public class AccountDO {
    private String did;

    private String username;

    private AccountType accountType;

    private ReviewStatus reviewStatus;

    private String salt;

    private String pwdhash;

    private String privateKey;
}

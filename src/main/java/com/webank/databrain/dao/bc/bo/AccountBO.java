package com.webank.databrain.dao.bc.bo;

import com.webank.databrain.enums.AccountType;
import com.webank.databrain.enums.ReviewStatus;
import lombok.Data;

@Data
public class AccountBO {
    private String did;
    private String userName;
    private AccountType accountType;
    private ReviewStatus reviewStatus;
    private String salt;
    private String pwdHash;
    private String privateKey;
}

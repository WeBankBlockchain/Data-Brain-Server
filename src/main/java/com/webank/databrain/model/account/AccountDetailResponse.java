package com.webank.databrain.model.account;

import lombok.Data;

@Data
public class AccountDetailResponse {

    private String did;

    private String address;

    private String reviewStatus;

    private String type;

    private Object detail;
}

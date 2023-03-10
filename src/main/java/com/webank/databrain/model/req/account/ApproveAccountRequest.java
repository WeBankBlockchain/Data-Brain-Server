package com.webank.databrain.model.req.account;

import lombok.Data;

@Data
public class ApproveAccountRequest {

    private String did;

    private boolean approved;

}


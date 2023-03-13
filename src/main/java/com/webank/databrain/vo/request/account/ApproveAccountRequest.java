package com.webank.databrain.vo.request.account;

import lombok.Data;

@Data
public class ApproveAccountRequest {

    private String did;

    private boolean approved;

}


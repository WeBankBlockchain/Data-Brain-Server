package com.webank.databrain.model.resp.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryAccountByIdResponse {
    private String did;

    private String address;

    private String reviewStatus;

    private String type;

    private Object detail;
}

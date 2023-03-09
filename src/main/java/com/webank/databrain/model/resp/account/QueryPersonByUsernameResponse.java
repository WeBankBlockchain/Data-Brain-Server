package com.webank.databrain.model.resp.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryPersonByUsernameResponse {

    private String did;

    private String keyAddress;

    private int status;

    private String personName;

    private String personContact;

    private String personEmail;

    private String personCertType;

    private String personCertNo;

    private long createTime;
}

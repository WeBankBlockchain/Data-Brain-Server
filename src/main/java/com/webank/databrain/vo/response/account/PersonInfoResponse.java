package com.webank.databrain.vo.response.account;

import lombok.Data;

@Data
public class PersonInfoResponse {
    private String userName;

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

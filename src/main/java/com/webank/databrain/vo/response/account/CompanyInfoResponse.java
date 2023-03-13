package com.webank.databrain.vo.response.account;

import lombok.Data;

@Data
public class CompanyInfoResponse {
    private String did;

    private String keyAddress;

    private int status;

    private String companyName;

    private String companyDesc;

    private String companyCertType;

    private String companyCertFileUri;

    private String companyContact;

    private long createTime;
}

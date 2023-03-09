package com.webank.databrain.model.resp.account;

import lombok.Data;

@Data
public class QueryCompanyByUsernameResponse {

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

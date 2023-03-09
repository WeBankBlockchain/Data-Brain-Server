package com.webank.databrain.model.po;

import lombok.Data;

@Data
public class CompanyJoinAccountPO {

    private String did;

    private long accountId;

    private String companyName;

    private String companyDesc;

    private String companyCertFileUri;

    private String companyContact;

    private int companyCertType;

}

package com.webank.databrain.db.entity;

import lombok.Data;

@Data
public class CompanyJoinAccountDataObject {

    private String did;

    private long accountId;

    private String companyName;

    private String companyDesc;

    private String companyCertFileUri;

    private String companyContact;

    private int companyCertType;

}

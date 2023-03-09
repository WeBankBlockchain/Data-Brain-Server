package com.webank.databrain.model.bo;

import lombok.Data;

import java.util.Date;

@Data
public class CompanyInfoBO {
    private String did;

    private String privateKey;

    private int status;

    private String companyName;

    private String companyDesc;

    private String companyCertType;

    private String companyCertFileUri;

    private String companyContact;

    private Date createTime;
}

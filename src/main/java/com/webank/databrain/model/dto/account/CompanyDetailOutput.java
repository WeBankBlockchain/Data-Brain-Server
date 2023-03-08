package com.webank.databrain.model.dto.account;

import lombok.Data;

@Data
public class CompanyDetailOutput {
    private String did;

    private String companyName;

    private String companyDesc;

    private String logoUrl;

    private String contact;

    private int certType;
}

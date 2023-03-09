package com.webank.databrain.model.input.account;

import lombok.Data;

@Data
public class CompanyDetailInput {

    private String companyName;

    private String companyDesc;

    private String logoUrl;

    private String contact;

    private int certType;

}

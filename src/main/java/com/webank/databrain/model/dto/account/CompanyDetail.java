package com.webank.databrain.model.dto.account;

import lombok.Data;

@Data
public class CompanyDetail {

    private String companyName;

    private String companyDesc;

    private String logoUrl;

    private String contact;

    private int certType;

    private String certContent;

}

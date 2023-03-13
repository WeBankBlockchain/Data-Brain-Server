package com.webank.databrain.vo.request.account;

import lombok.Data;

@Data
public class CompanyDetailRequest {

    private String companyName;

    private String companyDesc;

    private String certFileUrl;

    private String contact;

    private String certType;

    private String certNo;

}

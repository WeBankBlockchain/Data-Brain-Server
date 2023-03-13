package com.webank.databrain.dao.db.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class CompanyInfoEntity implements Serializable {

    private Long pkId;
    private Long accountId;
    private String companyName;
    private String companyDesc;
    private String companyCertType;
    private String companyCertNo;
    private String companyCertFileUri;
    private String companyContact;
    private Date createTime;
    private Date updateTime;


}

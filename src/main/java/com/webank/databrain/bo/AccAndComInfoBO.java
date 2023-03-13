package com.webank.databrain.bo;

import lombok.Data;
import java.util.Date;

@Data
public class AccAndComInfoBO {
    private String userName;
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
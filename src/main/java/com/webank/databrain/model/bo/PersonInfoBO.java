package com.webank.databrain.model.bo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PersonInfoBO {

    private String did;

    private String privateKey;

    private int status;

    private String personName;

    private String personContact;

    private String personEmail;

    private String personCertType;

    private String personCertNo;

    private Date createTime;

}
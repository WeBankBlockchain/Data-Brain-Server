package com.webank.databrain.dao.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class PersonInfoEntity implements Serializable {
    private Long pkId;
    private Long accountId;  // account pkId
    private String personName;
    private String personContact;
    private String personEmail;
    private String personCertType;
    private String personCertNo;
    private Timestamp createTime;
    private Timestamp updateTime;
}

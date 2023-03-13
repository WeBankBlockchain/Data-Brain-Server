package com.webank.databrain.dao.db.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class AccountInfoEntity implements Serializable {
    private Long pkId;
    private String userName;
    private String did;
    private Integer accountType;
    private String privateKey;
    private String salt;
    private String pwdHash;
    private Integer status;
    private Date reviewTime;
    private Date createTime;
    private Date updateTime;
}

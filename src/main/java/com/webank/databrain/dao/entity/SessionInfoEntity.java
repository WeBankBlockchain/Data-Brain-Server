package com.webank.databrain.dao.entity;


import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class SessionInfoEntity implements Serializable {

    private Long pkId;

    /**
     * token
     */
    private String token;

    /**
     * 账户外键ID
     */
    private Long accountId;

    /**
     * 过期时间
     */
    private Timestamp expiredAt;
    private Timestamp createTime;
    private Timestamp updateTime;
}

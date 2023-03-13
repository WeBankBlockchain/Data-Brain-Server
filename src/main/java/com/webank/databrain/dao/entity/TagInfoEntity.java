package com.webank.databrain.dao.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class TagInfoEntity implements Serializable {

    private Long pkId;

    /**
     * 标签名
     */
    private String tagName;
    private Timestamp createTime;
    private Timestamp updateTime;
}

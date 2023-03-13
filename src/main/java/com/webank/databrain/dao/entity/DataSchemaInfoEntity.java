package com.webank.databrain.dao.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class DataSchemaInfoEntity implements Serializable {

    private Long pkId;

    /**
     * 数据目录链上id
     */
    private String dataSchemaGid;

    /**
     * 数据目录名称
     */
    private String dataSchemaName;

    /**
     * 提供方外键id
     */
    private Long providerId;

    /**
     * 产品外键ID
     */
    private Long productId;

    private Integer version;

    /**
     * 是否可见
     */
    private Integer visible;

    /**
     * 描述
     */
    private String dataSchemaDesc;

    /**
     * 用途
     */
    private String dataSchemaUsage;

    /**
     * 价格
     */
    private Integer price;

    private Timestamp createTime;

    private Timestamp updateTime;

}

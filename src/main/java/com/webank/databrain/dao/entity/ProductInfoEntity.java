package com.webank.databrain.dao.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Accessors(chain = true)
public class ProductInfoEntity implements Serializable {

    private Long pkId;

    /**
     * 产品链上id
     */
    private String productGid;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 提供方外键ID
     */
    private Long providerId;

    /**
     * 产品详情
     */
    private String productDesc;

    /**
     * 审核状态
     */
    private Integer status;

    /**
     * 审核时间
     */
    private Timestamp reviewTime;

    private Timestamp createTime;

    private Timestamp updateTime;


}

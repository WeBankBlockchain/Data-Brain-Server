package com.webank.databrain.bo;

import lombok.Data;

import java.util.Date;

@Data
public class DataSchemaDetailBO {
    private Long schemaId;

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

    private String providerGId;


    private String providerName;


    /**
     * 产品外键ID
     */
    private Long productId;


    private String productGId;

    private String productName;


    /**
     * 版本号
     */
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

    private Date createTime;
}
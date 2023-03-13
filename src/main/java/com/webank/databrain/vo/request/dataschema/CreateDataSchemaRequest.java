package com.webank.databrain.vo.request.dataschema;

import lombok.Data;

import java.util.Date;

@Data
public class CreateDataSchemaRequest {


    private String did;

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


    private Long tagId;

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

    private Integer dataFormat;

    /**
     * 类型，HTTP-0，HTTPS-1，SFTP-2...
     */
    private Integer dataProtocol;

    /**
     * 内容格式
     */
    private String contentSchema;

    /**
     * 数据的查询条件定义
     */
    private String accessCondition;

    /**
     * 数据访问连接
     */
    private String uri;

    /**
     * 生效时间
     */
    private Date effectTime;

    /**
     * 失效时间
     */
    private Date expireTime;
}

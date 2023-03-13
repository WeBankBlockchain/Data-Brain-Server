package com.webank.databrain.dao.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class DataSchemaAccessInfoEntity implements Serializable {

    private Long pkId;

    /**
     * 数据目录外键id
     */
    private Long dataSchemaId;

    /**
     * 类型，json-0，xml-1，doc-2，pic-3...
     */
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

    private Date createTime;

    private Date updateTime;


}

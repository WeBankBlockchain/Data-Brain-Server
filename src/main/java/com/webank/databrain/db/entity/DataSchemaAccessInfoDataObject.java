package com.webank.databrain.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_data_schema_access_info")
public class DataSchemaAccessInfoDataObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    /**
     * 数据目录外键id
     */
    @TableField("data_schema_id")
    private Long dataSchemaId;

    /**
     * 类型，json-0，xml-1，doc-2，pic-3...
     */
    @TableField("data_format")
    private Integer dataFormat;

    /**
     * 类型，HTTP-0，HTTPS-1，SFTP-2...
     */
    @TableField("data_protocol")
    private Integer dataProtocol;

    /**
     * 内容格式
     */
    @TableField("content_schema")
    private String contentSchema;

    /**
     * 数据的查询条件定义
     */
    @TableField("access_condition")
    private String accessCondition;

    /**
     * 数据访问连接
     */
    @TableField("uri")
    private String uri;

    /**
     * 生效时间
     */
    @TableField("effect_time")
    private Date effectTime;

    /**
     * 失效时间
     */
    @TableField("expire_time")
    private Date expireTime;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}

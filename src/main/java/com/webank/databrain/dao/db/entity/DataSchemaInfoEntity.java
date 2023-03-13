package com.webank.databrain.dao.db.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@TableName("t_data_schema_info")
public class DataSchemaInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    /**
     * 数据目录链上id
     */
    @TableField("data_schema_gid")
    private String dataSchemaGid;

    /**
     * 数据目录名称
     */
    @TableField("data_schema_name")
    private String dataSchemaName;

    /**
     * 提供方外键id
     */
    @TableField("provider_id")
    private Long providerId;

    /**
     * 产品外键ID
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 版本号
     */
    @TableField("version")
    @Version
    private Integer version;

    /**
     * 是否可见
     */
    @TableField("visible")
    private Integer visible;

    /**
     * 描述
     */
    @TableField("data_schema_desc")
    private String dataSchemaDesc;

    /**
     * 用途
     */
    @TableField("data_schema_usage")
    private String dataSchemaUsage;

    /**
     * 价格
     */
    @TableField("price")
    private Integer price;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}

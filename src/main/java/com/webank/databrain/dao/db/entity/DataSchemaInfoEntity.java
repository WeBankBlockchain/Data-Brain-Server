package com.webank.databrain.dao.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
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

    /**
     * 版本号
     */
    @Version
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

    private Date updateTime;


}

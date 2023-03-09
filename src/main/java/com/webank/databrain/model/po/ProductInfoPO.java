package com.webank.databrain.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_product_info")
public class ProductInfoPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    /**
     * 产品链上id
     */
    @TableField("product_gid")
    private String productGid;

    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 提供方外键ID
     */
    @TableField("provider_id")
    private Long providerId;

    /**
     * 产品详情
     */
    @TableField("product_desc")
    private String productDesc;

    /**
     * 审核状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 审核时间
     */
    @TableField("review_time")
    private Date reviewTime;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}

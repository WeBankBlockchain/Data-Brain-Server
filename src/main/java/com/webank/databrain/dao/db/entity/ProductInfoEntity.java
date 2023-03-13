package com.webank.databrain.dao.db.entity;

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
public class ProductInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private Date reviewTime;

    private Date createTime;

    private Date updateTime;


}

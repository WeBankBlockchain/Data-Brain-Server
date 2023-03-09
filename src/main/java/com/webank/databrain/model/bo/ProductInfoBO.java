package com.webank.databrain.model.bo;

import lombok.Data;

import java.util.Date;

@Data
public class ProductInfoBO {


    private Long productId;

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

    private String did;

    private String companyName;
}

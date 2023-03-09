package com.webank.databrain.model.resp.product;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDetail {

    private Long productId;

    private String productGid;

    /**
     * 产品名称
     */
    private String productName;


    private String providerId;

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

    private String companyName;
}

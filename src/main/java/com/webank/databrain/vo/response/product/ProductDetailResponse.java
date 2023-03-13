package com.webank.databrain.vo.response.product;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDetailResponse {

    private Long productId;

    private String productGid;

    /**
     * 产品名称
     */
    private String productName;


    private Long providerId;


    private String providerGid;


    private String providerName;


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

}

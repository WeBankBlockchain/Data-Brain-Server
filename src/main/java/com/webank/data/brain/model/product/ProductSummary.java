package com.webank.data.brain.model.product;

import com.webank.data.brain.model.common.IdName;

import java.util.Date;

public class ProductSummary extends IdName {
    //The enterprise name the product belongs to, not the corresponding username.
    private String enterpriseName;

    private Date createdAt;
}

package com.webank.databrain.model.product;

import com.webank.databrain.model.common.IdName;

import java.util.Date;

public class ProductSummary extends IdName {
    //The enterprise name the product belongs to, not the corresponding username.
    private String enterpriseName;

    private Date createdAt;
}

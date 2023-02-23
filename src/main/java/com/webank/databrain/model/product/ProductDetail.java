package com.webank.databrain.model.product;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDetail {

    private String productId;
    private String productName;
    private String providerId;
    private String information;
    private Integer reviewState;
    private LocalDateTime reviewTime;
    private LocalDateTime createTime;


}

package com.webank.databrain.vo.response.product;

import lombok.Data;

@Data
public class ProductIdAndNameResponse {

    public String id;

    public String name;

    private Long productId;
}

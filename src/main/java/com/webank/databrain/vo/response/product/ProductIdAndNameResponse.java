package com.webank.databrain.vo.response.product;

import lombok.Data;

@Data
public class ProductIdAndNameResponse {

    public String productGid;

    public String productName;

    private Long productId;
}

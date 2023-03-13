package com.webank.databrain.vo.request.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {


    private Long productId;
    private String productGId;

    private String productName;

    private String information;
}

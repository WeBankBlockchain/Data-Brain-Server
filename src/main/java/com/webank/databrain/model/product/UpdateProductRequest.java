package com.webank.databrain.model.product;

import com.webank.databrain.model.common.CommonRequest;
import lombok.Data;

@Data
public class UpdateProductRequest extends CommonRequest {

    private String productId;

    private String productName;

    private String information;

}

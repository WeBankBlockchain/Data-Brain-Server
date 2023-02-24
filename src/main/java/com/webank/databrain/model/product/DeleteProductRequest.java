package com.webank.databrain.model.product;

import com.webank.databrain.model.common.CommonRequest;
import lombok.Data;

@Data
public class DeleteProductRequest extends CommonRequest {
    private String productId;
}

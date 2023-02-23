package com.webank.databrain.model.product;

import com.webank.databrain.model.common.CommonRequest;
import lombok.Data;

@Data
public class CreateProductRequest extends CommonRequest {

    private String productName;

    private String information;

}

package com.webank.databrain.model.product;

import com.webank.databrain.model.common.CommonRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ApproveProductRequest extends CommonRequest {
    private String productId;

    private boolean agree;

}

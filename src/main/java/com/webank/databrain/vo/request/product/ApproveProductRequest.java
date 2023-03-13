package com.webank.databrain.vo.request.product;

import com.webank.databrain.vo.common.CommonRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApproveProductRequest extends CommonRequest {

    private String productGId;

    private String did;

    private Long productId;

    private boolean agree;

}

package com.webank.databrain.vo.request.product;

import com.webank.databrain.vo.common.CommonPageQueryRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageQueryProductRequest extends CommonPageQueryRequest {

    private String productId;

    private int topN;
}

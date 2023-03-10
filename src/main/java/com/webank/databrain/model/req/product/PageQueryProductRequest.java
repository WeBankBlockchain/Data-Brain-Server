package com.webank.databrain.model.req.product;

import com.webank.databrain.model.req.PagedRequest;
import com.webank.databrain.vo.common.CommonPageQueryRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageQueryProductRequest extends CommonPageQueryRequest {

    private String productId;

    private int topN;
}

package com.webank.databrain.model.req.product;

import com.webank.databrain.model.req.PagedRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageQueryProductRequest extends PagedRequest {

    private String productId;

    private int topN;
}

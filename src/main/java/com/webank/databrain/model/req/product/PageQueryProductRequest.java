package com.webank.databrain.model.req.product;

import com.webank.databrain.model.req.PagedRequest;
import lombok.Data;

@Data
public class PageQueryProductRequest extends PagedRequest {

    private String productId;

    private int topN;
}

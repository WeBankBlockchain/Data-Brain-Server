package com.webank.databrain.model.request.product;

import com.webank.databrain.model.request.common.PagedRequest;
import lombok.Data;

@Data
public class PageQueryProductRequest extends PagedRequest {

    private String productId;

    private int topN;
}

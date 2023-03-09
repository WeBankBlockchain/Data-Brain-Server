package com.webank.databrain.model.input.product;

import com.webank.databrain.model.input.PagedRequest;
import lombok.Data;

@Data
public class PageQueryProductRequest extends PagedRequest {

    private String productId;

    private int topN;
}

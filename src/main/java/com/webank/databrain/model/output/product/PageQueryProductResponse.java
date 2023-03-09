package com.webank.databrain.model.output.product;

import com.webank.databrain.model.dto.product.ProductDetail;
import com.webank.databrain.model.output.BasePageQueryResult;
import com.webank.databrain.model.output.PagedResult;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PageQueryProductResponse extends BasePageQueryResult<ProductDetail> {

    public PageQueryProductResponse(PagedResult<ProductDetail> pagedResult) {
        super(pagedResult);
    }
}

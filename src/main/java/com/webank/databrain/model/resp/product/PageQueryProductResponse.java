package com.webank.databrain.model.resp.product;

import com.webank.databrain.model.resp.BasePageQueryResult;
import com.webank.databrain.model.resp.PagedResult;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PageQueryProductResponse extends BasePageQueryResult<ProductDetail> {

    public PageQueryProductResponse(PagedResult<ProductDetail> pagedResult) {
        super(pagedResult);
    }
}

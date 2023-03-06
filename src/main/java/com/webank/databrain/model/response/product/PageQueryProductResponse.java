package com.webank.databrain.model.response.product;

import com.webank.databrain.model.response.common.BasePageQueryResult;
import com.webank.databrain.model.response.common.PagedResult;
import com.webank.databrain.model.dto.product.ProductDetail;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PageQueryProductResponse extends BasePageQueryResult<ProductDetail> {

    public PageQueryProductResponse(PagedResult<ProductDetail> pagedResult) {
        super(pagedResult);
    }
}

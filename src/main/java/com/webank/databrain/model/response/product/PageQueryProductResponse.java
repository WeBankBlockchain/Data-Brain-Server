package com.webank.databrain.model.response.product;

import com.webank.databrain.model.response.common.PagedResult;
import com.webank.databrain.model.vo.product.ProductDetail;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PageQueryProductResponse extends PagedResult<ProductDetail> {

    public PageQueryProductResponse(List<ProductDetail> productDetails, long current, long size, long total, long pages) {
        super(productDetails, current, size, total, pages);
    }
}

package com.webank.databrain.vo.response.product;

import com.webank.databrain.model.resp.BaseHotResponse;
import com.webank.databrain.vo.response.account.CompanyIdAndNameResponse;

import java.util.List;

public class HotProductResponse extends BaseHotResponse<ProductIdAndNameResponse> {

    public HotProductResponse(List<ProductIdAndNameResponse> idNames) {
        super(idNames);
    }
}

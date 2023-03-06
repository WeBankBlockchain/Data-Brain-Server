package com.webank.databrain.model.response.product;

import com.webank.databrain.model.vo.common.IdName;
import com.webank.databrain.model.response.common.BaseHotResponse;

import java.util.Collection;

public class HotProductsResponse extends BaseHotResponse {

    public HotProductsResponse(Collection<IdName> idNames) {
        super(idNames);
    }
}

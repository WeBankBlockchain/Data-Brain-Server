package com.webank.databrain.model.response.product;

import com.webank.databrain.model.dto.common.IdName;
import com.webank.databrain.model.response.common.BaseHotResponse;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class HotProductsResponse extends BaseHotResponse<IdName> {

    public HotProductsResponse(List<IdName> idNames) {
        super(idNames);
    }
}

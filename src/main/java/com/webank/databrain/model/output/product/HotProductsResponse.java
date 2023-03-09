package com.webank.databrain.model.output.product;

import com.webank.databrain.model.dto.common.IdName;
import com.webank.databrain.model.output.BaseHotResponse;
import lombok.Data;

import java.util.List;

@Data
public class HotProductsResponse extends BaseHotResponse<IdName> {

    public HotProductsResponse(List<IdName> idNames) {
        super(idNames);
    }
}

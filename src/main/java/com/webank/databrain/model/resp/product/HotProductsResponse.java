package com.webank.databrain.model.resp.product;

import com.webank.databrain.model.resp.IdName;
import com.webank.databrain.model.resp.BaseHotResponse;
import lombok.Data;

import java.util.List;

@Data
public class HotProductsResponse extends BaseHotResponse<IdName> {

    public HotProductsResponse(List<IdName> idNames) {
        super(idNames);
    }
}

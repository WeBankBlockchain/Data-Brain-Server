package com.webank.databrain.model.resp.product;

import com.webank.databrain.model.resp.IdName;
import com.webank.databrain.model.resp.BaseHotResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class HotProductsResponse extends BaseHotResponse<IdName> {

    public HotProductsResponse(List<IdName> idNames) {
        super(idNames);
    }
}

package com.webank.databrain.model.resp.account;

import com.webank.databrain.model.resp.IdName;
import com.webank.databrain.model.resp.BaseHotResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class HotCompaniesResponse extends BaseHotResponse<IdNameWithType> {

    public HotCompaniesResponse(List<IdNameWithType> idNames) {
        super(idNames);
    }
}

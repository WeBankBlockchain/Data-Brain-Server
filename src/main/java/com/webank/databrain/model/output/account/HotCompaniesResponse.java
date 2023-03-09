package com.webank.databrain.model.output.account;

import com.webank.databrain.model.dto.common.IdName;
import com.webank.databrain.model.output.BaseHotResponse;
import lombok.Data;

import java.util.List;

@Data
public class HotCompaniesResponse extends BaseHotResponse<IdName> {

    public HotCompaniesResponse(List<IdName> idNames) {
        super(idNames);
    }
}

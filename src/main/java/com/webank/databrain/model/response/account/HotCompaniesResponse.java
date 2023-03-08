package com.webank.databrain.model.response.account;

import com.webank.databrain.model.dto.common.IdName;
import com.webank.databrain.model.response.common.BaseHotResponse;
import lombok.Data;

import java.util.List;

@Data
public class HotCompaniesResponse extends BaseHotResponse<IdName> {

    public HotCompaniesResponse(List<IdName> idNames) {
        super(idNames);
    }
}

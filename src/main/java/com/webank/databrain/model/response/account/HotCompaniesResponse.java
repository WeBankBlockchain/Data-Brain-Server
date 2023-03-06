package com.webank.databrain.model.response.account;

import com.webank.databrain.model.vo.common.IdName;
import com.webank.databrain.model.response.common.BaseHotResponse;

import java.util.Collection;

public class HotCompaniesResponse extends BaseHotResponse {

    public HotCompaniesResponse(Collection<IdName> idNames) {
        super(idNames);
    }
}

package com.webank.databrain.vo.response.account;

import com.webank.databrain.model.resp.BaseHotResponse;
import com.webank.databrain.model.resp.IdName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class HotCompaniesResponse extends BaseHotResponse<IdName> {

    public HotCompaniesResponse(List<IdName> idNames) {
        super(idNames);
    }
}

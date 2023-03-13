package com.webank.databrain.vo.response.account;

import com.webank.databrain.vo.common.BaseHotResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class HotCompaniesResponse extends BaseHotResponse<CompanyIdAndNameResponse> {

    public HotCompaniesResponse(List<CompanyIdAndNameResponse> idNames) {
        super(idNames);
    }
}

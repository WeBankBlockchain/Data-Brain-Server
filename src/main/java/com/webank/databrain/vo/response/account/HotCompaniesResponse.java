package com.webank.databrain.vo.response.account;

import com.webank.databrain.model.resp.BaseHotResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class HotCompaniesResponse extends BaseHotResponse<CompanyInfoResponse> {

    public HotCompaniesResponse(List<CompanyInfoResponse> idNames) {
        super(idNames);
    }
}

package com.webank.databrain.model.resp.account;

import com.webank.databrain.model.resp.PagedResult;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchCompanyResponse {

    private PagedResult<CompanyInfoVO> result;


}

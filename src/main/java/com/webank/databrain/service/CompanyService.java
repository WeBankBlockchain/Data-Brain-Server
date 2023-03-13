package com.webank.databrain.service;

import com.webank.databrain.vo.common.CommonPageQueryRequest;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.common.HotDataRequest;
import com.webank.databrain.vo.request.account.QueryByUsernameRequest;
import com.webank.databrain.vo.request.account.SearchAccountRequest;

public interface CompanyService {
    CommonResponse listHotCompanies(HotDataRequest request);
    CommonResponse listCompanyByPage(CommonPageQueryRequest request);
    CommonResponse getCompanyByUsername(QueryByUsernameRequest request);
    CommonResponse searchCompanies(SearchAccountRequest request);
}

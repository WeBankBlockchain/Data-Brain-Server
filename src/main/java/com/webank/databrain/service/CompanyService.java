package com.webank.databrain.service;

import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.account.PageQueryCompanyRequest;
import com.webank.databrain.vo.request.account.SearchCompanyRequest;

public interface CompanyService {
    CommonResponse listHotCompanies(int topCount);
    CommonResponse listCompanyByPage(PageQueryCompanyRequest request);
    CommonResponse getCompanyByUsername(String username);
    CommonResponse searchCompanies(SearchCompanyRequest request);
}

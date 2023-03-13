package com.webank.databrain.service;

import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.account.QueryByUsernameRequest;
import com.webank.databrain.vo.request.account.SearchAccountRequest;

public interface PersonService {
    CommonResponse getPersonByUsername(QueryByUsernameRequest request);
    CommonResponse searchPersons(SearchAccountRequest request);
}

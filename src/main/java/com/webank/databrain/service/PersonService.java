package com.webank.databrain.service;

import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.account.SearchPersonRequest;

public interface PersonService {
    CommonResponse getPersonByUsername(String userName);
    CommonResponse searchPersons(SearchPersonRequest request);
}

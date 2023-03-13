package com.webank.databrain.service.impl;


import com.webank.databrain.bo.AccAndPersonInfoBO;
import com.webank.databrain.dao.mapper.PersonInfoMapper;
import com.webank.databrain.service.PersonService;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.common.PageListData;
import com.webank.databrain.vo.request.account.QueryByUsernameRequest;
import com.webank.databrain.vo.request.account.SearchAccountRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonInfoMapper personInfoMapper;

    @Override
    public CommonResponse getPersonByUsername(QueryByUsernameRequest request) {
        AccAndPersonInfoBO accAndPersonInfoBO = personInfoMapper.queryPersonByUsername(request.getUserName());
        return CommonResponse.success(accAndPersonInfoBO);
    }

    @Override
    public CommonResponse searchPersons(SearchAccountRequest request) {
        int totalCount = personInfoMapper.totalCountWithStatus(request.getAccountStatus());

        int pageCount = (int) Math.ceil(1.0 * totalCount / request.getPageSize());

        PageListData pageListData = new PageListData<>();
        pageListData.setPageCount(pageCount);
        pageListData.setTotalCount(totalCount);

        int offset = (request.getPageNo() - 1) * request.getPageSize();

        List<AccAndPersonInfoBO> boList = personInfoMapper.listPersonWithStatus(request.getAccountStatus(), offset, request.getPageSize());
        pageListData.setItemList(boList);
        return CommonResponse.success(pageListData);
    }
}

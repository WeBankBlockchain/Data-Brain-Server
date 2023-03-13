package com.webank.databrain.service.impl;


import com.webank.databrain.bo.AccAndComInfoBO;
import com.webank.databrain.dao.entity.CompanyInfoEntity;
import com.webank.databrain.dao.mapper.CompanyInfoMapper;
import com.webank.databrain.service.CompanyService;
import com.webank.databrain.vo.common.CommonPageQueryRequest;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.common.PageListData;
import com.webank.databrain.vo.common.HotDataRequest;
import com.webank.databrain.vo.request.account.QueryByUsernameRequest;
import com.webank.databrain.vo.request.account.SearchAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Override
    public CommonResponse listHotCompanies(HotDataRequest request) {
        List<CompanyInfoEntity> companyInfoEntityList = companyInfoMapper.listHotCompanies(request.getTopCount());
        return CommonResponse.success(companyInfoEntityList);
    }

    @Override
    public CommonResponse listCompanyByPage(CommonPageQueryRequest request) {

        int totalCount = companyInfoMapper.totalCount();
        int pageCount = (int) Math.ceil(1.0 * totalCount / request.getPageSize());

        PageListData pageListData = new PageListData<>();
        pageListData.setPageCount(pageCount);
        pageListData.setTotalCount(totalCount);

        int offset = (request.getPageNo() - 1) * request.getPageSize();

        List<AccAndComInfoBO> companyInfoEntityList = companyInfoMapper.listCompanies(offset, request.getPageSize());
        pageListData.setItemList(companyInfoEntityList);
        return CommonResponse.success(pageListData);
    }


    @Override
    public CommonResponse getCompanyByUsername(QueryByUsernameRequest request) {
        AccAndComInfoBO accAndComInfoBO = companyInfoMapper.queryCompanyByUsername(request.getUserName());
        return CommonResponse.success(accAndComInfoBO);
    }

    @Override
    public CommonResponse searchCompanies(SearchAccountRequest request) {

        int totalCount = companyInfoMapper.totalCountWithStatus(request.getAccountStatus());
        int pageCount = (int) Math.ceil(1.0 * totalCount / request.getPageSize());

        PageListData pageListData = new PageListData<>();
        pageListData.setPageCount(pageCount);
        pageListData.setTotalCount(totalCount);

        int offset = (request.getPageNo() - 1) * request.getPageSize();

        List<AccAndComInfoBO> boList = companyInfoMapper.listCompanyWithStatus(request.getAccountStatus(), offset, request.getPageSize());
        pageListData.setItemList(boList);

        return CommonResponse.success(pageListData);
    }
}

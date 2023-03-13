package com.webank.databrain.service.impl;


import com.webank.databrain.dao.db.mapper.CompanyInfoMapper;
import com.webank.databrain.enums.AccountStatus;
import com.webank.databrain.enums.CodeEnum;
import com.webank.databrain.handler.key.ThreadLocalKeyPairHandler;
import com.webank.databrain.model.bo.CompanyInfoBO;
import com.webank.databrain.service.CompanyService;
import com.webank.databrain.utils.AccountUtils;
import com.webank.databrain.utils.PagingUtils;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.account.PageQueryCompanyRequest;
import com.webank.databrain.vo.request.account.SearchCompanyRequest;
import com.webank.databrain.vo.response.account.*;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private ThreadLocalKeyPairHandler keyPairHandler;
    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Override
    public CommonResponse listHotCompanies(int topCount) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        List<CompanyInfoBO> companyInfoDataObjects = companyInfoMapper.listHotCompanies(topCount);
        List<CompanyInfoResponse> items = companyInfoDataObjects.stream().map(b-> AccountUtils.companyBOToVO(cryptoSuite, b)).collect(Collectors.toList());
        HotCompaniesResponse response = new HotCompaniesResponse(items);
        return CommonResponse.success(response);
    }

    @Override
    public CommonResponse listCompanyByPage(PageQueryCompanyRequest request) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        int itemsCount = companyInfoMapper.totalCount();
        List<CompanyInfoBO> companyInfoDataObjects = companyInfoMapper.listCompanies(request.getPageNo(), request.getPageSize());
        List<CompanyInfoResponse> items = companyInfoDataObjects.stream().map(b->AccountUtils.companyBOToVO(cryptoSuite, b)).collect(Collectors.toList());

        PageQueryCompanyResponse response = new PageQueryCompanyResponse();
        response.setTotalCount(itemsCount);
        response.setItemList(items);
        response.setPageCount(PagingUtils.toPageCount(itemsCount, request.getPageSize()));

        return CommonResponse.success(response);
    }

    @Override
    public CommonResponse getCompanyByUsername(String userName) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();

        CompanyInfoBO data = companyInfoMapper.queryCompanyByUsername(userName);
        if (data == null){
            return CommonResponse.error(CodeEnum.USER_NOT_EXISTS);
        }

        CompanyInfoResponse voItem = AccountUtils.companyBOToVO(cryptoSuite, data);
        QueryCompanyByUsernameResponse ret = new QueryCompanyByUsernameResponse();
        ret.setItem(voItem);
        return CommonResponse.success(ret);
    }

    @Override
    public CommonResponse searchCompanies(SearchCompanyRequest request) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        String statusStr = request.getCondition().getAccountStatus();
        AccountStatus status = AccountStatus.valueOf(statusStr);
        int totalCount = companyInfoMapper.totalCountWithStatus(status.ordinal());
        List<CompanyInfoBO> boList = companyInfoMapper.listCompanyWithStatus(status.ordinal(), request.getPageNo(), request.getPageSize());
        List<CompanyInfoResponse> voItems = boList.stream().map(b -> AccountUtils.companyBOToVO(cryptoSuite, b)).collect(Collectors.toList());
        SearchCompanyResponse searchCompanyResponse = new SearchCompanyResponse();
        searchCompanyResponse.setItemList(voItems);
        searchCompanyResponse.setTotalCount(totalCount);
        searchCompanyResponse.setPageCount(PagingUtils.toPageCount(totalCount, request.getPageSize()));
        return CommonResponse.success(searchCompanyResponse);
    }
}

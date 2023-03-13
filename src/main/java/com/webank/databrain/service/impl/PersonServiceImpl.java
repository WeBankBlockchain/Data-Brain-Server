package com.webank.databrain.service.impl;


import com.webank.databrain.dao.db.mapper.PersonInfoMapper;
import com.webank.databrain.enums.AccountStatus;
import com.webank.databrain.enums.CodeEnum;
import com.webank.databrain.handler.key.ThreadLocalKeyPairHandler;
import com.webank.databrain.dao.bc.bo.PersonInfoBO;
import com.webank.databrain.service.PersonService;
import com.webank.databrain.utils.AccountUtils;
import com.webank.databrain.utils.PagingUtils;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.account.SearchPersonRequest;
import com.webank.databrain.vo.response.account.PersonInfoResponse;
import com.webank.databrain.vo.response.account.QueryPersonByUsernameResponse;
import com.webank.databrain.vo.response.account.SearchPersonResponse;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonInfoMapper personInfoMapper;

    @Autowired
    private ThreadLocalKeyPairHandler keyPairHandler;
    @Override
    public CommonResponse getPersonByUsername(String userName) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();

        PersonInfoBO data = personInfoMapper.queryPersonByUsername(userName);
        if (data == null){
            return CommonResponse.error(CodeEnum.USER_NOT_EXISTS);
        }

        PersonInfoResponse voItem = AccountUtils.personBOToVO(cryptoSuite, data);
        QueryPersonByUsernameResponse ret = new QueryPersonByUsernameResponse();
        ret.setItem(voItem);

        return CommonResponse.success(ret);
    }

    @Override
    public CommonResponse searchPersons(SearchPersonRequest request) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        String statusStr = request.getCondition().getAccountStatus();
        AccountStatus status = AccountStatus.valueOf(statusStr);
        int totalCount = personInfoMapper.totalCountWithStatus(status.ordinal());
        int pageCount = PagingUtils.toPageCount(totalCount, request.getPageSize());
        List<PersonInfoBO> boList = personInfoMapper.listPersonWithStatus(status.ordinal(), request.getPageNo(), request.getPageSize());
        List<PersonInfoResponse> voItems = boList.stream().map(b -> AccountUtils.personBOToVO(cryptoSuite, b)).collect(Collectors.toList());
        SearchPersonResponse response = new SearchPersonResponse();
        response.setItemList(voItems);
        response.setPageCount(pageCount);
        response.setTotalCount(totalCount);

        return CommonResponse.success(response);
    }
}

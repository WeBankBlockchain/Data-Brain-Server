package com.webank.databrain.service;

import com.webank.databrain.model.account.AccountID;
import com.webank.databrain.model.account.AccountSummary;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;

import java.util.List;

public class AccountService {

    public AccountID registerAccount(String username, String password) {
        //1. Generation private key
        //2
    }

    public String login(String username, String password) {
        return null;
    }

    public List<IdName> listHotEnterprises(int topN) {
        return null;
    }

    public List<PagingResult<AccountSummary>> listEnterprises(Paging paging) {
        return null;
    }

    public String getPrivateKey(String username, String password) {
        return null;
    }


    public void auditAccount(String username, boolean agree, String reason) {

    }
}

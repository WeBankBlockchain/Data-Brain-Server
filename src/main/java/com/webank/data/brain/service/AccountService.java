package com.webank.data.brain.service;

import com.webank.data.brain.model.account.AccountID;
import com.webank.data.brain.model.account.AccountSummary;
import com.webank.data.brain.model.common.IdName;
import com.webank.data.brain.model.common.Paging;
import com.webank.data.brain.model.common.PagingResult;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.List;

public class AccountService {

    public AccountID registerAccount(String username, String password) {
        return null;
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

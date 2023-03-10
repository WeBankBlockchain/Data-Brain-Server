package com.webank.databrain.model.req.account;

import com.webank.databrain.model.req.PagedRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchCompanyRequest extends PagedRequest {


    private SearchCondition condition;

    @Data
    public static class SearchCondition{
        private String accountStatus;
    }
}

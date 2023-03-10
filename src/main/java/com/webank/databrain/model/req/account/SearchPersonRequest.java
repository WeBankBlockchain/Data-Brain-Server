package com.webank.databrain.model.req.account;

import com.webank.databrain.model.req.PagedRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchPersonRequest extends PagedRequest {

    private SearchCondition condition;

    @Data
    @AllArgsConstructor
    public static class SearchCondition{
        private String accountStatus;
    }
}

package com.webank.databrain.vo.request.account;

import com.webank.databrain.model.req.PagedRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SearchPersonRequest extends PagedRequest {

    private SearchCondition condition;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchCondition{
        private String accountStatus;
    }
}

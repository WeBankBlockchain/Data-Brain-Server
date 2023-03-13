package com.webank.databrain.vo.request.account;

import com.webank.databrain.vo.common.CommonPageQueryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SearchCompanyRequest extends CommonPageQueryRequest {


    private SearchCondition condition;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchCondition{
        private String accountStatus;
    }
}

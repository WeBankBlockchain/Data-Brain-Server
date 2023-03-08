package com.webank.databrain.model.response.account;

import com.webank.databrain.model.dto.account.CompanyDetailOutput;
import com.webank.databrain.model.dto.common.IdName;
import com.webank.databrain.model.response.common.BasePageQueryResult;
import com.webank.databrain.model.response.common.PagedResult;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageQueryCompanyResponse extends BasePageQueryResult<IdName> {

    public PageQueryCompanyResponse(PagedResult<IdName> pagedResult) {
        super(pagedResult);
    }


}

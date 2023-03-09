package com.webank.databrain.model.output.account;

import com.webank.databrain.model.output.IdName;
import com.webank.databrain.model.output.BasePageQueryResult;
import com.webank.databrain.model.output.PagedResult;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageQueryCompanyResponse extends BasePageQueryResult<IdName> {

    public PageQueryCompanyResponse(PagedResult<IdName> pagedResult) {
        super(pagedResult);
    }


}

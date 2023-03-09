package com.webank.databrain.model.resp.account;

import com.webank.databrain.model.resp.IdName;
import com.webank.databrain.model.resp.BasePageQueryResult;
import com.webank.databrain.model.resp.PagedResult;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageQueryCompanyResponse extends BasePageQueryResult<IdName> {

    public PageQueryCompanyResponse(PagedResult<IdName> pagedResult) {
        super(pagedResult);
    }


}

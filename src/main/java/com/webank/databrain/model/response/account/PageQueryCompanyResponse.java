package com.webank.databrain.model.response.account;

import com.webank.databrain.model.vo.common.IdName;
import com.webank.databrain.model.response.common.PagedResult;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageQueryCompanyResponse{

    private PagedResult<IdName> result;
}

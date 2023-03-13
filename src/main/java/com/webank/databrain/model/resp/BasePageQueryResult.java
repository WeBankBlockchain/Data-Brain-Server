package com.webank.databrain.model.resp;

import com.webank.databrain.vo.common.PageListData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePageQueryResult<T> {

    private PagedResult<T> result;

}

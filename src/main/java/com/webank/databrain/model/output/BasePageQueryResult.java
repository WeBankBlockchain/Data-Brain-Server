package com.webank.databrain.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePageQueryResult<T> {

    private PagedResult<T> result;

}

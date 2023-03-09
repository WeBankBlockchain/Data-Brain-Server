package com.webank.databrain.vo.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommonPageQueryRequest extends CommonRequest{
    private int pageNo = 1;
    private int pageSize = 10;
}

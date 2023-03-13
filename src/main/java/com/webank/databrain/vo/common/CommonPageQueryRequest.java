package com.webank.databrain.vo.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommonPageQueryRequest extends CommonRequest{
    @Min(value = 1, message = "页码必须大于0")
    private int pageNo = 1;
    @Min(value = 1, message = "每页条数必须大于0")
    private int pageSize = 10;
}

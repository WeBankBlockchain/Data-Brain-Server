package com.webank.databrain.model.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PagedRequest extends BaseRequest {

    //First Page is 1
    private int pageNo;

    private int pageSize;

}

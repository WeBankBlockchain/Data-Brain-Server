package com.webank.databrain.model.req;

import lombok.Data;

@Data
public class PagedRequest extends BaseRequest {

    //First Page is 1
    private int pageNo;

    private int pageSize;

}

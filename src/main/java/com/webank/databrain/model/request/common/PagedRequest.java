package com.webank.databrain.model.request.common;

import lombok.Data;

@Data
public class PagedRequest extends CommonRequest{

    //First Page is 1
    private int pageNo;

    private int pageSize;

}

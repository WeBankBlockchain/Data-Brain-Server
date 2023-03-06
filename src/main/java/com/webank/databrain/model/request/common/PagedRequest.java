package com.webank.databrain.model.request.common;

import lombok.Data;

@Data
public class PagedRequest extends CommonRequest{


    private int pageNo;

    private int pageSize;

}

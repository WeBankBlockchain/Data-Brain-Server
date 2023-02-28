package com.webank.databrain.model.product;

import lombok.Data;

@Data
public class QueryProductRequest {

    private int pageNo = 1;

    private int pageSize = 10;

}

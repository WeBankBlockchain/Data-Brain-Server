package com.webank.databrain.model.dataschema;

import lombok.Data;

@Data
public class QuerySchemaRequest {

    private int pageNo = 1;

    private int pageSize = 10;

    private String productId;

    private String providerId;

    private long tagId = 0;

    private String keyWord;

}

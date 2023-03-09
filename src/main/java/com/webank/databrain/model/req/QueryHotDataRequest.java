package com.webank.databrain.model.req;


import lombok.Data;

@Data
public class QueryHotDataRequest extends BaseRequest {

    private int topN;

}

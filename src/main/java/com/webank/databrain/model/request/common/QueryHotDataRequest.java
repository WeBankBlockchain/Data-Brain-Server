package com.webank.databrain.model.request.common;


import lombok.Data;

@Data
public class QueryHotDataRequest extends CommonRequest {

    private int topN;

}

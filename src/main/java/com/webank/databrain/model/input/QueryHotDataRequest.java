package com.webank.databrain.model.input;


import com.webank.databrain.model.input.BaseRequest;
import lombok.Data;

@Data
public class QueryHotDataRequest extends BaseRequest {

    private int topN;

}

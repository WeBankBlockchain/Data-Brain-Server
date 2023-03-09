package com.webank.databrain.model.req;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryHotDataRequest extends BaseRequest {

    private int topN;

}

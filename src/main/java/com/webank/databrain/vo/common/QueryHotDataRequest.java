package com.webank.databrain.vo.common;


import com.webank.databrain.model.req.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryHotDataRequest extends BaseRequest {

    private int topN;

}

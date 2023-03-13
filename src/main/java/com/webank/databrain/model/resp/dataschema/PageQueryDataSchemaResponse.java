package com.webank.databrain.model.resp.dataschema;

import com.webank.databrain.model.resp.BasePageQueryResult;
import com.webank.databrain.model.resp.PagedResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PageQueryDataSchemaResponse extends BasePageQueryResult<DataSchemaDetailResponse> {


    public PageQueryDataSchemaResponse(PagedResult<DataSchemaDetailResponse> result){
        super(result);
    }

}

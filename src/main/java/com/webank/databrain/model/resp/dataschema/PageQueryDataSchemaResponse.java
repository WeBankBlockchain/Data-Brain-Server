package com.webank.databrain.model.resp.dataschema;

import com.webank.databrain.model.resp.BasePageQueryResult;
import com.webank.databrain.model.resp.PagedResult;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageQueryDataSchemaResponse extends BasePageQueryResult<DataSchemaDetail> {


    public PageQueryDataSchemaResponse(PagedResult<DataSchemaDetail> result){
        super(result);
    }

}

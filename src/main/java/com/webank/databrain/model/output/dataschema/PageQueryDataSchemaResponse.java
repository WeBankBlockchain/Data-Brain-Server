package com.webank.databrain.model.output.dataschema;

import com.webank.databrain.model.dto.dataschema.DataSchemaDetail;
import com.webank.databrain.model.output.BasePageQueryResult;
import com.webank.databrain.model.output.PagedResult;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageQueryDataSchemaResponse extends BasePageQueryResult<DataSchemaDetail> {


    public PageQueryDataSchemaResponse(PagedResult<DataSchemaDetail> result){
        super(result);
    }

}

package com.webank.databrain.model.response.dataschema;

import com.webank.databrain.model.dto.dataschema.DataSchemaDetail;
import com.webank.databrain.model.response.common.BasePageQueryResult;
import com.webank.databrain.model.response.common.PagedResult;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageQueryDataSchemaResponse extends BasePageQueryResult<DataSchemaDetail> {


    public PageQueryDataSchemaResponse(PagedResult<DataSchemaDetail> result){
        super(result);
    }

}

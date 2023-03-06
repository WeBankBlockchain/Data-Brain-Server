package com.webank.databrain.model.response.dataschema;

import com.webank.databrain.model.vo.dataschema.DataSchemaDetail;
import com.webank.databrain.model.response.common.PagedResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageQueryDataSchemaResponse{

    private PagedResult<DataSchemaDetail> result;
}

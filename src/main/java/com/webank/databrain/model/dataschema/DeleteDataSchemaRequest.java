package com.webank.databrain.model.dataschema;

import com.webank.databrain.model.common.CommonRequest;
import lombok.Data;

@Data
public class DeleteDataSchemaRequest extends CommonRequest {
    private String schemaId;

}

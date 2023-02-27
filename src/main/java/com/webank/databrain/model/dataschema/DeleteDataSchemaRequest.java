package com.webank.databrain.model.dataschema;

import com.webank.databrain.model.common.CommonRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeleteDataSchemaRequest extends CommonRequest {
    private String schemaId;

}

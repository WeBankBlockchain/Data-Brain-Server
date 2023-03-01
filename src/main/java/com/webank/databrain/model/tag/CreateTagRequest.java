package com.webank.databrain.model.tag;

import com.webank.databrain.model.common.CommonRequest;
import lombok.Data;

@Data
public class CreateTagRequest extends CommonRequest {

    private String tag;

    private String schemaId;

}

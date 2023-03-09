package com.webank.databrain.model.input.dataschema;

import com.webank.databrain.model.input.PagedRequest;
import lombok.Data;

@Data
public class PageQueryDataSchemaRequest extends PagedRequest {

    private String productId;

    private String providerId;

    private String tag;

    private String keyWord;

    private String schemaId;

}

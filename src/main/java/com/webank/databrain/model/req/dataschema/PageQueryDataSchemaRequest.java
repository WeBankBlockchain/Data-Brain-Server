package com.webank.databrain.model.req.dataschema;

import com.webank.databrain.model.req.PagedRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageQueryDataSchemaRequest extends PagedRequest {

    private String productId;

    private String providerId;

    private String tag;

    private String keyWord;

    private String schemaId;

}

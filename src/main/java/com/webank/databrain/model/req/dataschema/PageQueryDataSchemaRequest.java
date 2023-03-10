package com.webank.databrain.model.req.dataschema;

import com.webank.databrain.model.req.PagedRequest;
import com.webank.databrain.vo.common.CommonPageQueryRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageQueryDataSchemaRequest extends CommonPageQueryRequest {

    private Long productId;

    private Long providerId;

    private Long tagId;

    private String keyWord;

    private Long schemaId;

}

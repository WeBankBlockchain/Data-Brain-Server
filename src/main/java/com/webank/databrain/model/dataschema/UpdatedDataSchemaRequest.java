package com.webank.databrain.model.dataschema;

import com.webank.databrain.model.common.CommonRequest;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdatedDataSchemaRequest extends CommonRequest {

    private String schemaId;

    private Long providerId;

    private Long tagId;

    private Long productId;

    private Integer version;

    private Integer visible;

    private String description;

    private String usage;

    private Integer price;

    private Integer type;

    private Integer protocol;

    private String schema;

    private String condition;

    private String uri;

    private LocalDateTime effectTime;

    private LocalDateTime expireTime;
}

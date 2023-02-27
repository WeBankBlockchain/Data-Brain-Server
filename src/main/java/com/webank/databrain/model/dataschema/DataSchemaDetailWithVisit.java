package com.webank.databrain.model.dataschema;

import com.webank.databrain.model.common.IdName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DataSchemaDetailWithVisit extends IdName {

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

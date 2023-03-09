package com.webank.databrain.model.dto.dataschema;

import com.webank.databrain.model.output.IdName;

import java.time.LocalDateTime;

public class DataSchemaDetailWithVisit extends IdName {
    private String schemaId;

    private String schemaName;


    private String providerId;

    private String productName;

    private String providerName;

    private String tag;

    private String productId;

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

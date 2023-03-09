package com.webank.databrain.model.input.dataschema;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateDataSchemaRequest {

    private String schemaId;

    private String providerId;

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

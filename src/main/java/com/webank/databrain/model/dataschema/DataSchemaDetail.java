package com.webank.databrain.model.dataschema;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DataSchemaDetail {

    private Long schemaId;

    private Long providerId;

    private Long productId;

    private Integer version;

    private Integer visible;

    private String description;

    private String usage;

    private Integer price;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}

package com.webank.databrain.model.dataschema;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DataSchemaDetail {

    private String schemaId;

    private String providerId;

    private String productId;

    private String productName;

    private String providerName;

    private String tag;

    private Integer version;

    private Integer visible;

    private String description;

    private String usage;

    private Integer price;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}

package com.webank.databrain.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "schema_info")
public class DataSchemaDataObject implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    @ApiModelProperty(value = "目录id")
    private String schemaId;

    @ApiModelProperty(value = "提供方id")
    private String providerId;


    private Long tagId;


    @ApiModelProperty(value = "产品id")
    private String productId;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "是否可见")
    private Integer visible;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "用途")
    private String usage;

    @ApiModelProperty(value = "价格")
    private Integer price;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}

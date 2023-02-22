package com.webank.data.brain.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value="Schema对象", description="")
public class Schema implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    @ApiModelProperty(value = "目录id")
    private Long schemaId;

    @ApiModelProperty(value = "提供方id")
    private Long providerId;

    @ApiModelProperty(value = "产品id")
    private Long productId;

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

    @ApiModelProperty(value = "访问详情id")
    private Long visitId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}

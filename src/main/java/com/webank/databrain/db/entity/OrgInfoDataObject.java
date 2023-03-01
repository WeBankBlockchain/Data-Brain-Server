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
@ApiModel(value="OrgInfo对象", description="")
@TableName(value = "org_info")
public class OrgInfoDataObject implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    @ApiModelProperty(value = "DID")
    private String orgId;

    private String orgName;


    @ApiModelProperty(value = "证件类型")
    private Integer certType;

    @ApiModelProperty(value = "证件内容")
    private String certContent;

    @ApiModelProperty(value = "联系方式")
    private String contact;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}

package com.webank.databrain.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="Account对象", description="")
public class Account implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    @ApiModelProperty(value = "账号")
    @TableField("accountNumber")
    private String accountNumber;

    @ApiModelProperty(value = "用户类型")
    private Integer type;

    @ApiModelProperty(value = "DID")
    private String did;

    private String salt;

    private String pwdhash;

    @ApiModelProperty(value = "审核状态")
    private Integer reviewState;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime reviewTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}

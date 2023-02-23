package com.webank.databrain.db.entity;

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
@ApiModel(value="VisitInfo对象", description="")
public class VisitInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    @ApiModelProperty(value = "类型，json-0，xml-1，doc-2，pic-3...")
    private Integer type;

    @ApiModelProperty(value = "类型，HTTP-0，HTTPS-1，SFTP-2...")
    private Integer protocol;

    @ApiModelProperty(value = "数据schema,格式为Json")
    private String schema;

    @ApiModelProperty(value = "数据的查询条件定义")
    private String condition;

    @ApiModelProperty(value = "数据访问连接")
    private String uri;

    @ApiModelProperty(value = "生效时间")
    private LocalDateTime effectTime;

    @ApiModelProperty(value = "失效时间")
    private LocalDateTime expireTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}

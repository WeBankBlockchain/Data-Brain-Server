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

import javax.persistence.Id;

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
public class AccountDataObject implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
//    @Id()
    private Long pkId;

    private String username;

    private Integer accountType;

    private String did;

    private String privateKey;

    private String salt;

    private String pwdhash;

    private Integer reviewState;

    private LocalDateTime reviewTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}

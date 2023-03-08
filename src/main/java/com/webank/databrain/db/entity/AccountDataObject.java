package com.webank.databrain.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
@Data
@TableName(value = "t_account_info")
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

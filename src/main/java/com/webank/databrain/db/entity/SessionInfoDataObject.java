package com.webank.databrain.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_session_info")
public class SessionInfoDataObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    /**
     * token
     */
    @TableField("token")
    private String token;

    /**
     * 账户外键ID
     */
    @TableField("account_id")
    private Long accountId;

    /**
     * 过期时间
     */
    @TableField("expiredAt")
    private Date expiredAt;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}

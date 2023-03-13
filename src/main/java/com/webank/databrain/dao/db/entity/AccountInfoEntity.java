package com.webank.databrain.dao.db.entity;

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
@TableName("t_account_info")
public class AccountInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    /**
     * 账号
     */
    @TableField("user_name")
    private String username;

    /**
     * DID
     */
    @TableField("did")
    private String did;

    /**
     * 用户类型
     */
    @TableField("account_type")
    private Integer accountType;

    /**
     * 私钥
     */
    @TableField("private_key")
    private String privateKey;

    @TableField("salt")
    private String salt;

    @TableField("pwd_hash")
    private String pwdHash;

    /**
     * 账户状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 审核时间
     */
    @TableField("review_time")
    private Date reviewTime;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}

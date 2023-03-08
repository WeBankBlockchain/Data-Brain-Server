package com.webank.databrain.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@TableName("t_person_info")
public class PersonInfoDataObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    /**
     * 账户外键ID
     */
    @TableField("person_id")
    private Long personId;

    /**
     * 姓名
     */
    @TableField("person_name")
    private String personName;

    /**
     * 个人联系方式
     */
    @TableField("person_contact")
    private String personContact;

    /**
     * 个人邮箱
     */
    @TableField("person_email")
    private String personEmail;

    /**
     * 个人证件类型
     */
    @TableField("person_cert_type")
    private Integer personCertType;

    /**
     * 个人证件号
     */
    @TableField("person_cert_no")
    private String personCertNo;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}

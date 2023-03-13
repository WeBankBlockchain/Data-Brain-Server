package com.webank.databrain.dao.db.entity;

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
@TableName("t_person_info")
public class PersonInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long pkId;

    /**
     * 账户外键ID
     */
    private Long accountId;

    /**
     * 姓名
     */
    private String personName = "";

    /**
     * 个人联系方式
     */
    private String personContact = "";

    /**
     * 个人邮箱
     */
    private String personEmail = "";

    /**
     * 个人证件类型
     */
    private String personCertType = "";

    /**
     * 个人证件号
     */
    private String personCertNo = "";

    private Date createTime;

    private Date updateTime;


}

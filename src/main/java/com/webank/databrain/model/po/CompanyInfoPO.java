package com.webank.databrain.model.po;

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
@TableName("t_company_info")
public class CompanyInfoPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    /**
     * 账户外键ID
     */
    @TableField("account_id")
    private Long accountId;

    /**
     * 公司名称
     */
    @TableField("company_name")
    private String companyName;

    /**
     * 公司详情
     */
    @TableField("company_desc")
    private String companyDesc;

    /**
     * 法人证件类型
     */
    @TableField("company_cert_type")
    private String companyCertType;

    /**
     * 法人证件号码
     */
    @TableField("company_cert_no")
    private String companyCertNo;

    /**
     * 公司证件图片链接
     */
    @TableField("company_cert_file_uri")
    private String companyCertFileUri;

    /**
     * 公司联系方式
     */
    @TableField("company_contact")
    private String companyContact;


    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}

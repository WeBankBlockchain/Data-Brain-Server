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
@TableName("t_company_info")
public class CompanyInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long pkId;

    /**
     * 账户外键ID
     */
    private Long accountId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司详情
     */
    private String companyDesc;

    /**
     * 法人证件类型
     */
    private String companyCertType;

    /**
     * 法人证件号码
     */
    private String companyCertNo;

    /**
     * 公司证件图片链接
     */
    private String companyCertFileUri;

    /**
     * 公司联系方式
     */
    private String companyContact;


    private Date createTime;

    private Date updateTime;


}

package com.webank.databrain.dao.mapper;

import com.webank.databrain.bo.AccAndComInfoBO;
import com.webank.databrain.dao.entity.CompanyInfoEntity;
import org.apache.ibatis.annotations.*;
import java.util.List;


@Mapper
public interface CompanyInfoMapper {

    @Select("SELECT * FROM t_company_info ORDER BY create_time DESC LIMIT 1, #{topCount}")
    List<CompanyInfoEntity> listHotCompanies(int topCount);

    @Select("SELECT a.*, c.* FROM t_company_info c JOIN t_account_info a ORDER BY c.create_time DESC LIMIT #{offset}, #{pageSize}")
    @ResultType(AccAndComInfoBO.class)
    List<AccAndComInfoBO> listCompanies(int offset, int pageSize);

    @Select("SELECT * FROM t_company_info c INNER JOIN t_account_info a ON c.account_id = a.pk_id WHERE a.user_name=#{userName}")
    @ResultType(AccAndComInfoBO.class)
    AccAndComInfoBO queryCompanyByUsername(String userName);

    @Select("SELECT a.*, c.* FROM t_company_info c INNER  JOIN t_account_info a ON c.account_id = a.pk_id WHERE a.status = #{status} ORDER BY c.pk_id DESC LIMIT #{start}, #{pageSize}")
    @ResultType(AccAndComInfoBO.class)
    List<AccAndComInfoBO> listCompanyWithStatus(@Param("status") int status, @Param("start") long start, @Param("pageSize")int pageSize);

    @Insert("INSERT INTO t_company_info (account_id, company_name, company_desc, company_cert_type, company_cert_no, company_cert_file_uri, company_contact) VALUES(#{accountId},#{companyName},#{companyDesc},#{companyCertType},#{companyCertNo},#{companyCertFileUri},#{companyContact})")
    void insertCompany(CompanyInfoEntity companyInfoEntity);

    @Select("SELECT COUNT(1) FROM t_company_info")
    int totalCount();

    @Select("SELECT COUNT(1) FROM t_company_info c INNER JOIN t_account_info a  ON c.account_id = a.pk_id WHERE a.status=#{status}")
    int totalCountWithStatus(int status);
}

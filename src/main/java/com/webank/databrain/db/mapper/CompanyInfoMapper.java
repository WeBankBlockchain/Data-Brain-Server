package com.webank.databrain.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import com.webank.databrain.model.bo.CompanyInfoBO;
import com.webank.databrain.model.bo.PersonInfoBO;
import com.webank.databrain.model.po.CompanyInfoPO;
import com.webank.databrain.model.resp.IdName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface CompanyInfoMapper extends BaseMapper<CompanyInfoPO> {

    @Select("SELECT a.did as id, c.company_name as name, a.account_type FROM t_company_info c INNER JOIN t_account_info a ON c.account_id = a.pk_id ")
    @ResultType(CompanyInfoBO.class)
    List<CompanyInfoBO> listHotCompanies(int topN);

    @Select("SELECT a.did as id, c.company_name as name, a.account_type FROM t_company_info c INNER JOIN t_account_info a ON c.account_id = a.pk_id ORDER BY c.pk_id DESC LIMIT #{start}, #{pageSize}")
    @ResultType(CompanyInfoBO.class)
    List<CompanyInfoBO> listCompanies(@Param("start") long start, @Param("pageSize")int pageSize);

    @Select("SELECT a.*, c.* FROM t_company_info c INNER  JOIN t_account_info a ON c.account_id = a.pk_id WHERE a.user_name=#{username}")
    @ResultType(CompanyInfoBO.class)
    CompanyInfoBO queryCompanyByUsername(@Param("username") String username);


    @Select("SELECT a.*, c.* FROM t_company_info c INNER  JOIN t_account_info a ON c.account_id = a.pk_id WHERE a.status = #{status} ORDER BY c.pk_id DESC LIMIT #{start}, #{pageSize}")
    @ResultType(CompanyInfoBO.class)
    List<CompanyInfoBO> listCompanyWithStatus(@Param("status") int status, @Param("start") long start, @Param("pageSize")int pageSize);

}

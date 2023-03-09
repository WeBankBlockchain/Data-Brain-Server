package com.webank.databrain.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webank.databrain.model.po.AccountInfoPO;
import com.webank.databrain.model.po.CompanyJoinAccountPO;
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
public interface AccountInfoMapper extends BaseMapper<AccountInfoPO> {


    @Select("SELECT a.did, c.*  FROM t_company_info c INNER JOIN t_account_info a ON c.account_id = a.pk_id ORDER BY a.pk_id DESC LIMIT ${start}, ${pageSize}")
    @ResultType(CompanyJoinAccountPO.class)
    List<CompanyJoinAccountPO> listCompany(long start, int pageSize);
    @Select("SELECT a.did, c.*  FROM t_company_info c INNER JOIN t_account_info a ON c.account_id = a.pk_id ORDER BY a.pk_id DESC LIMIT 0, ${topN}")
    @ResultType(CompanyJoinAccountPO.class)
    List<CompanyJoinAccountPO> listHotCompany(int topN);
}

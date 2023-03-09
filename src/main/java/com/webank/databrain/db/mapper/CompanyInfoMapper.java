package com.webank.databrain.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import com.webank.databrain.model.po.CompanyInfoPO;
import com.webank.databrain.model.resp.IdName;
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

    @Select("SELECT a.did as id, c.company_name as name FROM t_company_info c INNER JOIN t_account_info a ON c.account_id = a.pk_id ")
    @ResultType(IdName.class)
    List<IdName> listHotCompanies(int topN);
}

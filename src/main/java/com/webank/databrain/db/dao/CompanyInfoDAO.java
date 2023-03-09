package com.webank.databrain.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.model.bo.CompanyInfoBO;
import com.webank.databrain.model.bo.PersonInfoBO;
import com.webank.databrain.model.resp.IdName;
import com.webank.databrain.model.po.CompanyInfoPO;
import com.webank.databrain.model.resp.account.IdNameWithType;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface CompanyInfoDAO extends IService<CompanyInfoPO> {
    List<IdNameWithType> listHotCompany(int topN);
    List<IdNameWithType> listCompany(int pageNo, int pageSize);

    CompanyInfoBO queryCompanyByUsername(String username);
}

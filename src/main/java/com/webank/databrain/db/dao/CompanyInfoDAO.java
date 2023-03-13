package com.webank.databrain.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.model.bo.CompanyInfoBO;
import com.webank.databrain.dao.db.entity.CompanyInfoEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface CompanyInfoDAO {
    List<CompanyInfoBO> listHotCompany(int topN);
    List<CompanyInfoBO> listCompany(int pageNo, int pageSize);

    CompanyInfoBO queryCompanyByUsername(String username);

    List<CompanyInfoBO> listCompanyWithStatus(int status, int pageNo, int pageSize);

    void saveItem(CompanyInfoEntity companyInfoEntity);

    int totalCount();

    int totalCountWithStatus(int status);

}

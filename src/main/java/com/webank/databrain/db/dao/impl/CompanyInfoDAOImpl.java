package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.CompanyInfoDAO;
import com.webank.databrain.model.bo.CompanyInfoBO;
import com.webank.databrain.dao.db.entity.CompanyInfoEntity;
import com.webank.databrain.dao.db.mapper.CompanyInfoMapper;
import com.webank.databrain.utils.PagingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
@Service
public class CompanyInfoDAOImpl implements CompanyInfoDAO {

    @Autowired
    private CompanyInfoMapper mapper;
    @Override
    public List<CompanyInfoBO> listHotCompany(int topN) {
        List<CompanyInfoBO> ret =  mapper.listHotCompanies(topN);
        return ret;
    }

    @Override
    public List<CompanyInfoBO> listCompany(int pageNo, int pageSize) {
        long start = PagingUtils.getStartOffset(pageNo, pageSize);
        return mapper.listCompanies(start, pageSize);
    }

    @Override
    public CompanyInfoBO queryCompanyByUsername(String username) {
        return mapper.queryCompanyByUsername(username);
    }

    @Override
    public List<CompanyInfoBO> listCompanyWithStatus(int status, int pageNo, int pageSize) {
        long start = PagingUtils.getStartOffset(pageNo, pageSize);
        return mapper.listCompanyWithStatus(status, start, pageSize);
    }

    @Override
    public void saveItem(CompanyInfoEntity companyInfoEntity) {
        mapper.insertItem(companyInfoEntity);
    }

    @Override
    public int totalCount() {
        return mapper.totalCount();
    }

    @Override
    public int totalCountWithStatus(int status) {
        return mapper.totalCountWithStatus(status);
    }
}

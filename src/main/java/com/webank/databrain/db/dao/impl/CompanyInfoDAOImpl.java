package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.CompanyInfoDAO;
import com.webank.databrain.model.bo.CompanyInfoBO;
import com.webank.databrain.model.po.CompanyInfoPO;
import com.webank.databrain.db.mapper.CompanyInfoMapper;
import com.webank.databrain.utils.PagingUtils;
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
public class CompanyInfoDAOImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfoPO> implements CompanyInfoDAO {

    @Override
    public List<CompanyInfoBO> listHotCompany(int topN) {
        return baseMapper.listHotCompanies(topN);
    }

    @Override
    public List<CompanyInfoBO> listCompany(int pageNo, int pageSize) {
        long start = PagingUtils.getStartOffset(pageNo, pageSize);
        return baseMapper.listCompanies(start, pageSize);
    }

    @Override
    public CompanyInfoBO queryCompanyByUsername(String username) {
        return baseMapper.queryCompanyByUsername(username);
    }

    @Override
    public List<CompanyInfoBO> listCompanyWithStatus(int status, int pageNo, int pageSize) {
        long start = PagingUtils.getStartOffset(pageNo, pageSize);
        return baseMapper.listCompanyWithStatus(status, start, pageSize);
    }
}

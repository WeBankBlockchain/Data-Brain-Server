package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.AccountInfoDAO;
import com.webank.databrain.db.entity.AccountInfoDataObject;
import com.webank.databrain.db.entity.CompanyJoinAccountDataObject;
import com.webank.databrain.db.mapper.AccountInfoMapper;
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
public class AccountInfoDAOImpl extends ServiceImpl<AccountInfoMapper, AccountInfoDataObject> implements AccountInfoDAO {

    @Override
    public List<CompanyJoinAccountDataObject> listCompany(int pageNo, int pageSize) {
        long start = PagingUtils.getStartOffset(pageNo, pageSize);
        return baseMapper.listCompany(start, pageSize);
    }

    @Override
    public List<CompanyJoinAccountDataObject> listHotCompany(int topN) {
        return baseMapper.listHotCompany(topN);
    }
}

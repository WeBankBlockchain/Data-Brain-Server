package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.AccountInfoDAO;
import com.webank.databrain.model.po.AccountInfoPO;
import com.webank.databrain.model.po.CompanyJoinAccountPO;
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
public class AccountInfoDAOImpl extends ServiceImpl<AccountInfoMapper, AccountInfoPO> implements AccountInfoDAO {

    @Override
    public List<CompanyJoinAccountPO> listCompany(int pageNo, int pageSize) {
        long start = PagingUtils.getStartOffset(pageNo, pageSize);
        return baseMapper.listCompany(start, pageSize);
    }

    @Override
    public List<CompanyJoinAccountPO> listHotCompany(int topN) {
        return baseMapper.listHotCompany(topN);
    }
}

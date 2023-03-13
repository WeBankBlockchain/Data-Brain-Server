package com.webank.databrain.db.dao.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.webank.databrain.db.dao.AccountInfoDAO;
import com.webank.databrain.enums.AccountStatus;
import com.webank.databrain.dao.db.entity.AccountInfoEntity;
import com.webank.databrain.dao.db.mapper.AccountInfoMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
@Service
public class AccountInfoDAOImpl extends MPJBaseServiceImpl<AccountInfoMapper, AccountInfoEntity> implements AccountInfoDAO {

    @Override
    public void updateAccountStatus(String did, AccountStatus status) {
        baseMapper.updateStatus(did, status.ordinal());
    }

    @Override
    public void saveItem(AccountInfoEntity accountInfoDo) {
        baseMapper.insertItem(accountInfoDo);
    }
}

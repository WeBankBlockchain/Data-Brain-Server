package com.webank.databrain.db.dao.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.webank.databrain.db.dao.AccountInfoDAO;
import com.webank.databrain.enums.AccountStatus;
import com.webank.databrain.dao.db.entity.AccountInfoEntity;
import com.webank.databrain.dao.db.mapper.AccountInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AccountInfoDAOImpl implements AccountInfoDAO {
    @Autowired
    private AccountInfoMapper mapper;
    @Override
    public void updateAccountStatus(String did, AccountStatus status) {
        mapper.updateStatus(did, status.ordinal());
    }

    @Override
    public void saveItem(AccountInfoEntity accountInfoDo) {
        mapper.insertItem(accountInfoDo);
    }

    @Override
    public AccountInfoEntity selectByDid(String did) {
        return mapper.selectByDid(did);
    }

    @Override
    public AccountInfoEntity selectByUserName(String username) {
        return mapper.selectByUserName(username);
    }
}

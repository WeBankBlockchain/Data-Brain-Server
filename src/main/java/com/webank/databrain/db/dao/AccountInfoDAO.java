package com.webank.databrain.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.enums.AccountStatus;
import com.webank.databrain.dao.db.entity.AccountInfoEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface AccountInfoDAO extends IService<AccountInfoEntity> {

    void updateAccountStatus(String did, AccountStatus status);

    void saveItem(AccountInfoEntity accountInfoDo);
}

package com.webank.databrain.db.dao.impl;

import com.webank.databrain.db.entity.Account;
import com.webank.databrain.db.mapper.AccountMapper;
import com.webank.databrain.db.dao.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}

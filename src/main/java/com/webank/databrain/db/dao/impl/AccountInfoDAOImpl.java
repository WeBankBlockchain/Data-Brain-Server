package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.AccountInfoDAO;
import com.webank.databrain.db.entity.AccountInfoDataObject;
import com.webank.databrain.db.mapper.AccountInfoMapper;
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
public class AccountInfoDAOImpl extends ServiceImpl<AccountInfoMapper, AccountInfoDataObject> implements AccountInfoDAO {

}

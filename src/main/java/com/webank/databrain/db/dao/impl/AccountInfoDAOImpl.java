package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.webank.databrain.db.dao.AccountInfoDAO;
import com.webank.databrain.model.output.IdName;
import com.webank.databrain.model.po.AccountInfoPO;
import com.webank.databrain.db.mapper.AccountInfoMapper;
import com.webank.databrain.model.po.CompanyInfoPO;
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
public class AccountInfoDAOImpl extends MPJBaseServiceImpl<AccountInfoMapper, AccountInfoPO> implements AccountInfoDAO {




}

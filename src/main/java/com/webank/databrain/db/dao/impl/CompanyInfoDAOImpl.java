package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.webank.databrain.db.dao.CompanyInfoDAO;
import com.webank.databrain.model.resp.IdName;
import com.webank.databrain.model.po.AccountInfoPO;
import com.webank.databrain.model.po.CompanyInfoPO;
import com.webank.databrain.db.mapper.CompanyInfoMapper;
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
    public List<IdName> listHotCompany(int topN) {
        MPJLambdaWrapper<CompanyInfoPO> wrapper = new MPJLambdaWrapper<CompanyInfoPO>()
                .selectAs(AccountInfoPO::getDid, IdName::getId)
                .selectAs(CompanyInfoPO::getCompanyName, IdName::getName)
                .innerJoin(AccountInfoPO.class, AccountInfoPO::getPkId, CompanyInfoPO::getAccountId)
                .orderByDesc(CompanyInfoPO::getPkId);

        Page<IdName> result = baseMapper.selectJoinPage(
                new Page<IdName>(1, topN),
                IdName.class,
                wrapper);

        return result.getRecords();
    }

    @Override
    public List<IdName> listCompany(int pageNo, int pageSize) {
        return null;
    }
}

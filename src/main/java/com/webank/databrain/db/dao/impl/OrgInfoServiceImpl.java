package com.webank.databrain.db.dao.impl;

import com.webank.databrain.db.entity.OrgInfoDataObject;
import com.webank.databrain.db.mapper.OrgInfoMapper;
import com.webank.databrain.db.dao.IOrgInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.model.account.OrgSummary;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
@Service
public class OrgInfoServiceImpl extends ServiceImpl<OrgInfoMapper, OrgInfoDataObject> implements IOrgInfoService {


    public List<IdName> selectHotEnterprises(int topN) {
        return baseMapper.selectHotEnterprises(topN);
    }

    public List<PagingResult<OrgSummary>> listEnterprises(Paging paging) {
        int total = baseMapper.count();
        return null;
    }
}

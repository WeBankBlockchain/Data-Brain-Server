package com.webank.databrain.db.dao.impl;

import com.webank.databrain.db.entity.OrgInfoDataObject;
import com.webank.databrain.db.mapper.OrgInfoMapper;
import com.webank.databrain.db.dao.IOrgInfoDbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.model.account.OrgSummary;
import com.webank.databrain.model.account.OrgUserDetail;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
@Service
public class OrgInfoServiceImpl extends ServiceImpl<OrgInfoMapper, OrgInfoDataObject> implements IOrgInfoDbService {

    @PostConstruct
    public void init(){
        baseMapper.createTable();
    }

    public List<IdName> listHotOrgs(int topN) {
        return baseMapper.listHotOrgs(topN);
    }

    public PagingResult<OrgSummary> listOrgsByPage(Paging paging) {
        int total = baseMapper.count();

        int startOffset = (paging.getPageNo() - 1) * paging.getPageSize();
        int limitSize = paging.getPageSize();
        List<OrgInfoDataObject> orgs = baseMapper.listOrgsByPage(startOffset, limitSize);

        PagingResult<OrgSummary> ret = new PagingResult<>();

        ret.setPage(paging.getPageNo());
        ret.setPageSize(limitSize);
        ret.setTotalItems(total);
        ret.setTotalPages((total + limitSize - 1) / limitSize);//上取整
        ret.setData(orgs.stream().map(o->{
            OrgSummary summary = new OrgSummary();
            BeanUtils.copyProperties(o, summary);
            return summary;
        }).collect(Collectors.toList()));
        return ret;
    }

    public void insert(String did, OrgUserDetail orgUserDetail) {
        OrgInfoDataObject dbo = new OrgInfoDataObject();
        BeanUtils.copyProperties(orgUserDetail, dbo);
        dbo.setOrgId(did);

        baseMapper.insert(dbo);
    }
}

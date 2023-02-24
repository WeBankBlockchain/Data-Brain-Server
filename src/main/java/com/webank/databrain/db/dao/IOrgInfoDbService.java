package com.webank.databrain.db.dao;

import com.webank.databrain.db.entity.OrgInfoDataObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.model.account.OrgSummary;
import com.webank.databrain.model.account.OrgUserDetail;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
public interface IOrgInfoDbService extends IService<OrgInfoDataObject> {

    public List<IdName> selectHotOrgs(int topN);

    public PagingResult<OrgSummary> listOrgs(Paging paging) ;

    public void insert(String did, OrgUserDetail orgUserDetail) ;
}

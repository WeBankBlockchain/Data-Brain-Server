package com.webank.databrain.db.dao;

import com.webank.databrain.db.entity.OrgInfoDataObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.model.dto.account.OrgUserDetail;
import com.webank.databrain.model.dto.common.IdName;
import com.webank.databrain.model.dto.common.Paging;
import com.webank.databrain.model.response.common.PagedResult;

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

    public List<IdName> listHotOrgs(int topN);

    public PagedResult<IdName> listOrgsByPage(Paging paging) ;

    public void insert(String did, OrgUserDetail orgUserDetail) ;
}

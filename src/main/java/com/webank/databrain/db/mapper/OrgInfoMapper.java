package com.webank.databrain.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webank.databrain.db.entity.OrgInfoDataObject;
import com.webank.databrain.model.dto.common.IdName;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
public interface OrgInfoMapper extends BaseMapper<OrgInfoDataObject> {

    void createTable();

    List<IdName> listHotOrgs(int topN);

    int count();

    List<OrgInfoDataObject> listOrgsByPage(int start, int size);
}

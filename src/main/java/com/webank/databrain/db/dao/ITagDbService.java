package com.webank.databrain.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.db.entity.TagDataObject;
import com.webank.databrain.model.dto.common.IdName;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
public interface ITagDbService extends IService<TagDataObject> {

    List<IdName> listHotTags(int topN);

//    PagingResult<TagSummary> listTagsByPage(Paging paging);
}

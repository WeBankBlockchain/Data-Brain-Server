package com.webank.databrain.db.dao;

import com.webank.databrain.db.entity.TagDataObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.tag.TagSummary;

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

    PagingResult<TagSummary> listTagsByPage(Paging paging);
}

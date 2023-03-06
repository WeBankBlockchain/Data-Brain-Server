package com.webank.databrain.db.mapper;

import com.webank.databrain.db.entity.TagDataObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webank.databrain.model.vo.common.IdName;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
public interface TagMapper extends BaseMapper<TagDataObject> {

    void createTable();

    List<IdName> listHotTags(int topN);

    List<IdName> listTagsByPage(int start, int size);


    int count();
}

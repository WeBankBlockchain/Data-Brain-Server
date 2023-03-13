package com.webank.databrain.dao.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.dao.db.entity.TagInfoEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface TagInfoDAO{

    void saveItem(TagInfoEntity tagPO);

    List<TagInfoEntity> queryHotTags(int topN);
}

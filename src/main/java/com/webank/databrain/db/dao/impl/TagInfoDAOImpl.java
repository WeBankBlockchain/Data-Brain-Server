package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.TagInfoDAO;
import com.webank.databrain.dao.db.entity.TagInfoEntity;
import com.webank.databrain.dao.db.mapper.TagInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TagInfoDAOImpl implements TagInfoDAO {

    @Autowired
    private TagInfoMapper mapper;

    @Override
    public void saveItem(TagInfoEntity tagPO) {
        mapper.insertItem(tagPO);
    }

    @Override
    public List<TagInfoEntity> queryHotTags(int topN) {
        return mapper.queryHotTags(topN);
    }
}

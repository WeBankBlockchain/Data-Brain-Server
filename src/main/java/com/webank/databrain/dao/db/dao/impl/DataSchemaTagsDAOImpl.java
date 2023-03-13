package com.webank.databrain.dao.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.dao.db.dao.DataSchemaTagsDAO;
import com.webank.databrain.dao.db.entity.DataSchemaTagsEntity;
import com.webank.databrain.dao.db.mapper.DataSchemaTagsMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
@Service
public class DataSchemaTagsDAOImpl extends ServiceImpl<DataSchemaTagsMapper, DataSchemaTagsEntity> implements DataSchemaTagsDAO {

    @Override
    public void saveDataSchemaTag(DataSchemaTagsEntity dataSchemaTagsEntity) {
        baseMapper.insertDataSchemaTag(dataSchemaTagsEntity);
    }
}

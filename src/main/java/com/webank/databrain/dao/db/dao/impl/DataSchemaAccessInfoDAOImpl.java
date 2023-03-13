package com.webank.databrain.dao.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.dao.db.dao.DataSchemaAccessInfoDAO;
import com.webank.databrain.dao.db.entity.DataSchemaAccessInfoEntity;
import com.webank.databrain.dao.db.mapper.DataSchemaAccessInfoMapper;
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
public class DataSchemaAccessInfoDAOImpl extends ServiceImpl<DataSchemaAccessInfoMapper, DataSchemaAccessInfoEntity> implements DataSchemaAccessInfoDAO {

    @Override
    public void saveDataSchemaAccessInfo(DataSchemaAccessInfoEntity dataSchemaAccessInfoEntity) {
        baseMapper.insertDataSchemaAccessInfo(dataSchemaAccessInfoEntity);
    }
}

package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.dao.db.entity.DataSchemaInfoEntity;
import com.webank.databrain.db.dao.DataSchemaInfoDAO;
import com.webank.databrain.dao.db.mapper.DataSchemaInfoMapper;
import com.webank.databrain.model.bo.DataSchemaInfoBO;
import com.webank.databrain.utils.PagingUtils;
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
public class DataSchemaInfoDAOImpl extends ServiceImpl<DataSchemaInfoMapper, DataSchemaInfoEntity> implements DataSchemaInfoDAO {

    @Override
    public List<DataSchemaInfoBO> pageQuerySchema(int pageNo, int pageSize, Long productId, Long providerId, Long tagId, String keyWord) {
        long start = PagingUtils.getStartOffset(pageNo, pageSize);
        return baseMapper.pageQuerySchema(start,pageSize, productId,  providerId, tagId,  keyWord);
    }

    @Override
    public DataSchemaInfoEntity getDataSchemaInfoByGId(String schemaId) {
        return baseMapper.getSchemaByGId(schemaId);
    }

    @Override
    public void saveDataSchemaInfo(DataSchemaInfoEntity dataSchemaInfoEntity) {
        baseMapper.insertDataSchemaInfo(dataSchemaInfoEntity);
    }

    @Override
    public void updateDataSchemaInfo(DataSchemaInfoEntity dataSchemaInfoEntity) {
        baseMapper.updateDataSchemaInfo(dataSchemaInfoEntity);
    }
}

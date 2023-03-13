package com.webank.databrain.dao.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.dao.db.entity.DataSchemaInfoEntity;
import com.webank.databrain.dao.db.mapper.DataSchemaInfoMapper;
import com.webank.databrain.dao.db.dao.DataSchemaInfoDAO;
import com.webank.databrain.vo.response.dataschema.DataSchemaWithAccessResponse;
import com.webank.databrain.utils.PagingUtils;
import com.webank.databrain.vo.response.dataschema.DataSchemaInfoResponse;
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
    public List<DataSchemaInfoResponse> pageQuerySchema(int pageNo, int pageSize, Long productId, Long providerId, Long tagId, String keyWord) {
        long start = PagingUtils.getStartOffset(pageNo, pageSize);
        return baseMapper.pageQuerySchema(start,pageSize, productId,  providerId, tagId,  keyWord);
    }

    @Override
    public DataSchemaInfoEntity getDataSchemaInfoByGId(String schemaId) {
        return baseMapper.getSchemaByGId(schemaId);
    }

    public DataSchemaWithAccessResponse getSchemaWithAccessByGid(String schemaId){
        return baseMapper.getSchemaWithAccessByGid(schemaId);
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

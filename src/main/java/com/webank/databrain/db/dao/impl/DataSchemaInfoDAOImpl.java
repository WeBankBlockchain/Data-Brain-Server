package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.DataSchemaInfoDAO;
import com.webank.databrain.db.mapper.DataSchemaInfoMapper;
import com.webank.databrain.model.bo.DataSchemaInfoBO;
import com.webank.databrain.model.po.DataSchemaInfoPO;
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
public class DataSchemaInfoDAOImpl extends ServiceImpl<DataSchemaInfoMapper, DataSchemaInfoPO> implements DataSchemaInfoDAO {

    @Override
    public List<DataSchemaInfoBO> pageQueryProduct(int pageNo, int pageSize, Long productId, Long providerId, Long tagId, String keyWord) {
        long start = PagingUtils.getStartOffset(pageNo, pageSize);
        return baseMapper.pageQuerySchema(start,pageSize, productId,  providerId, tagId,  keyWord);
    }

    @Override
    public DataSchemaInfoPO getDataSchemaInfoByGId(String schemaId) {
        return baseMapper.getSchemaByGId(schemaId);
    }

    @Override
    public void saveDataSchemaInfo(DataSchemaInfoPO dataSchemaInfoPO) {
        baseMapper.insertDataSchemaInfo(dataSchemaInfoPO);
    }

    @Override
    public void updateDataSchemaInfo(DataSchemaInfoPO dataSchemaInfoPO) {
        baseMapper.updateDataSchemaInfo(dataSchemaInfoPO);
    }
}

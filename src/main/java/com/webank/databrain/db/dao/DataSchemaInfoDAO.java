package com.webank.databrain.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.dao.db.entity.DataSchemaInfoEntity;
import com.webank.databrain.vo.response.dataschema.DataSchemaInfoBO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface DataSchemaInfoDAO extends IService<DataSchemaInfoEntity> {

    List<DataSchemaInfoBO> pageQuerySchema(int start, int pageSize, Long productId, Long providerId, Long tagId, String keyWord);

    DataSchemaInfoEntity getDataSchemaInfoByGId(String schemaId);

    void saveDataSchemaInfo(DataSchemaInfoEntity dataSchemaInfoEntity);

    void updateDataSchemaInfo(DataSchemaInfoEntity dataSchemaInfoEntity);


}

package com.webank.databrain.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.model.bo.DataSchemaInfoBO;
import com.webank.databrain.model.bo.ProductInfoBO;
import com.webank.databrain.model.po.DataSchemaInfoPO;
import com.webank.databrain.model.po.ProductInfoPO;
import com.webank.databrain.model.resp.IdName;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface DataSchemaInfoDAO extends IService<DataSchemaInfoPO> {

    List<DataSchemaInfoBO> pageQueryProduct(int start, int pageSize, Long productId, Long providerId, Long tagId, String keyWord);

    DataSchemaInfoPO getDataSchemaInfoByGId(String schemaId);

    void saveDataSchemaInfo(DataSchemaInfoPO productInfoPO);

    void updateDataSchemaInfo(DataSchemaInfoPO productInfoPO);


}

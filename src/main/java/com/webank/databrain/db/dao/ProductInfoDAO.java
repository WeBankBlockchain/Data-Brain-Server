package com.webank.databrain.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.dao.db.entity.ProductInfoEntity;
import com.webank.databrain.model.bo.ProductIdAndName;
import com.webank.databrain.model.bo.ProductInfoBO;
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
public interface ProductInfoDAO extends IService<ProductInfoEntity> {

    List<ProductInfoBO> pageQueryProduct(int start, int pageSize);

    List<IdName> getHotProduct(int topN);

    ProductInfoBO getProductByGId(String productId);

    List<ProductIdAndName> getProductNameByIds(List<Long> ids);

    void saveProductInfo(ProductInfoEntity productInfoEntity);

    void updateProductInfo(ProductInfoEntity productInfoEntity);
}

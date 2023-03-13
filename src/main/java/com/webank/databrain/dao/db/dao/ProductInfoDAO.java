package com.webank.databrain.dao.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.dao.db.entity.ProductInfoEntity;
import com.webank.databrain.model.resp.IdName;
import com.webank.databrain.vo.response.product.ProductIdAndNameResponse;
import com.webank.databrain.vo.response.product.ProductInfoResponse;

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

    List<ProductInfoResponse> pageQueryProduct(int start, int pageSize);

    List<ProductIdAndNameResponse> getHotProduct(int topN);

    ProductInfoResponse getProductByGId(String productId);

    List<ProductIdAndNameResponse> getProductNameByIds(List<Long> ids);

    void saveProductInfo(ProductInfoEntity productInfoEntity);

    void updateProductInfo(ProductInfoEntity productInfoEntity);

    void updateProductInfoState(ProductInfoEntity productInfoEntity);

}

package com.webank.databrain.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.model.bo.ProductInfoBO;
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
public interface ProductInfoDAO extends IService<ProductInfoPO> {

    List<ProductInfoBO> pageQueryProduct(int start, int pageSize);

    List<IdName> getHotProduct(int topN);

    ProductInfoBO getProductByGId(String productId);


}

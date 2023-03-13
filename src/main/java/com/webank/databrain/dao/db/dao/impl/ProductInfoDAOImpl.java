package com.webank.databrain.dao.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.dao.db.entity.ProductInfoEntity;
import com.webank.databrain.dao.db.mapper.ProductInfoMapper;
import com.webank.databrain.dao.db.dao.ProductInfoDAO;
import com.webank.databrain.model.resp.IdName;
import com.webank.databrain.utils.PagingUtils;
import com.webank.databrain.vo.response.product.ProductIdAndNameResponse;
import com.webank.databrain.vo.response.product.ProductInfoResponse;
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
public class ProductInfoDAOImpl extends ServiceImpl<ProductInfoMapper, ProductInfoEntity> implements ProductInfoDAO {

    @Override
    public List<ProductInfoResponse> pageQueryProduct(int pageNo, int pageSize) {
        long start = PagingUtils.getStartOffset(pageNo, pageSize);
        return baseMapper.pageQueryProduct(start,pageSize);
    }

    @Override
    public List<IdName> getHotProduct(int topN) {
        return baseMapper.getHotProduct(topN);
    }

    @Override
    public ProductInfoResponse getProductByGId(String productId) {
        return baseMapper.getProductByGId(productId);
    }

    @Override
    public List<ProductIdAndNameResponse> getProductNameByIds(List<Long> ids) {
        return baseMapper.getProductNameByIds(ids);
    }

    public void saveProductInfo(ProductInfoEntity productInfoEntity){
        baseMapper.insertProductInfoPO(productInfoEntity);
    }

    public void updateProductInfo(ProductInfoEntity productInfoEntity){
        baseMapper.updateProductInfo(productInfoEntity);
    }

    @Override
    public void updateProductInfoState(ProductInfoEntity productInfoEntity) {
        baseMapper.updateProductInfoState(productInfoEntity);
    }
}

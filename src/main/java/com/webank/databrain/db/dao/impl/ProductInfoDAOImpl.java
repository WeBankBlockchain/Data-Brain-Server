package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.ProductInfoDAO;
import com.webank.databrain.model.bo.ProductInfoBO;
import com.webank.databrain.model.po.ProductInfoPO;
import com.webank.databrain.db.mapper.ProductInfoMapper;
import com.webank.databrain.model.resp.IdName;
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
public class ProductInfoDAOImpl extends ServiceImpl<ProductInfoMapper, ProductInfoPO> implements ProductInfoDAO {

    @Override
    public List<ProductInfoBO> pageQueryProduct(int pageNo, int pageSize) {
        long start = PagingUtils.getStartOffset(pageNo, pageSize);
        return baseMapper.pageQueryProduct(start,pageSize);
    }

    @Override
    public List<IdName> getHotProduct(int topN) {
        return baseMapper.getHotProduct(topN);
    }

    @Override
    public ProductInfoBO getProductByGId(String productId) {
        return baseMapper.getProductByGId(productId);
    }
}

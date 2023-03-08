package com.webank.databrain.db.dao.impl;

import com.webank.databrain.db.entity.ProductInfoDataObject;
import com.webank.databrain.db.mapper.ProductInfoMapper;
import com.webank.databrain.db.dao.ProductInfoDAO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
@Service
public class ProductInfoDAOImpl extends ServiceImpl<ProductInfoMapper, ProductInfoDataObject> implements ProductInfoDAO {

}

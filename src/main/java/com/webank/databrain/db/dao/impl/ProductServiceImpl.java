package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.IProductService;
import com.webank.databrain.db.entity.ProductDataObject;
import com.webank.databrain.db.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductDataObject> implements IProductService {

}

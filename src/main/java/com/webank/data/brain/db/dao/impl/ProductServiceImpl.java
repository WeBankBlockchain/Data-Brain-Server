package com.webank.data.brain.db.dao.impl;

import com.webank.data.brain.db.entity.Product;
import com.webank.data.brain.db.mapper.ProductMapper;
import com.webank.data.brain.db.dao.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}

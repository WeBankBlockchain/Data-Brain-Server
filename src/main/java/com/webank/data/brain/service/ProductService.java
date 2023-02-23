package com.webank.data.brain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webank.data.brain.db.dao.IProductService;
import com.webank.data.brain.db.entity.Product;
import com.webank.data.brain.model.common.IdName;
import com.webank.data.brain.model.common.Paging;
import com.webank.data.brain.model.common.PagingResult;
import com.webank.data.brain.model.product.NewProduct;
import com.webank.data.brain.model.product.ProductDetail;
import com.webank.data.brain.model.product.ProductSummary;
import com.webank.data.brain.model.product.UpdatedProduct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private IProductService productService;

    public List<IdName> getHotProducts(int topN) {
        return null;
    }

    public PagingResult<ProductDetail> listProducts(Paging paging) {
        IPage<Product> result = productService.page(new Page<>(paging.getPageNo(), paging.getPageSize()));
        List<Product> productList = result.getRecords();
        List<ProductDetail> productDetails = new ArrayList<>();

        productList.forEach(product -> {
            ProductDetail productDetail = new ProductDetail();
            BeanUtils.copyProperties(product,productDetail);
            productDetails.add(productDetail);
        });
        return new PagingResult<>(
                productDetails,
                result.getCurrent(),
                result.getSize(),
                result.getTotal(),
                result.getPages());
    }



    public List<ProductDetail> getProductDetail(String productId) {
        return null;
    }

    public String createProduct(NewProduct product, byte[] signature) {
        return null;
    }

    public void updateProduct(UpdatedProduct product, byte[] signature) {

    }

    public void deleteProduct(String productId, byte[] signature) {

    }


    public void auditProduct(String productId, boolean agree, byte[] signature, String reason) {

    }
}

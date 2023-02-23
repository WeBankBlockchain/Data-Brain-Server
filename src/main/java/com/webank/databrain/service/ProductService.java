package com.webank.databrain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webank.databrain.db.dao.IProductService;
import com.webank.databrain.db.entity.Product;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.product.NewProduct;
import com.webank.databrain.model.product.ProductDetail;
import com.webank.databrain.model.product.ProductIdName;
import com.webank.databrain.model.product.UpdatedProduct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private IProductService productService;

    public List<ProductIdName> getHotProducts(int topN) {
        List<Product> productList = productService.query().select("").orderByDesc("pk_id").last("limit " + topN ).list();
        List<ProductIdName> idNames = new ArrayList<>();
        productList.forEach(product -> {
            ProductIdName productIdName = new ProductIdName();
            BeanUtils.copyProperties(product,productIdName);
            idNames.add(productIdName);
        });
        return idNames;
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

    public ProductDetail getProductDetail(long productId) {
        Product product = productService.getById(productId);
        ProductDetail productDetail = new ProductDetail();
        BeanUtils.copyProperties(product,productDetail);
        return productDetail;
    }

    public String createProduct(NewProduct product, byte[] signature) {
        return null;
    }

//    public void updateProduct(UpdatedProduct product, byte[] signature) {
//
//    }
//
//    public void deleteProduct(String productId, byte[] signature) {
//
//    }
//
//
//    public void auditProduct(String productId, boolean agree, byte[] signature, String reason) {
//
//    }
}

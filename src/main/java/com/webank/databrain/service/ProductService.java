package com.webank.databrain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webank.databrain.db.dao.IProductService;
import com.webank.databrain.db.entity.ProductDataObject;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.product.NewProduct;
import com.webank.databrain.model.product.ProductDetail;
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

    public List<IdName> getHotProducts(int topN) {
        return null;
    }

    public PagingResult<ProductDetail> listProducts(Paging paging) {
        IPage<ProductDataObject> result = productService.page(new Page<>(paging.getPageNo(), paging.getPageSize()));
        List<ProductDataObject> productList = result.getRecords();
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

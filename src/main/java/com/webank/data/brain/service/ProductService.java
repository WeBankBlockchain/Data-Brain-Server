package com.webank.data.brain.service;

import com.webank.data.brain.model.account.AccountSummary;
import com.webank.data.brain.model.common.IdName;
import com.webank.data.brain.model.common.Paging;
import com.webank.data.brain.model.common.PagingResult;
import com.webank.data.brain.model.product.*;

import java.util.List;

public class ProductService {

    public List<IdName> getHotProducts(int topN) {
        return null;
    }

    public List<PagingResult<ProductSummary>> listProducts(Paging paging) {
        return null;
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

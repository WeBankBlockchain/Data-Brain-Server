package com.webank.databrain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webank.databrain.blockchain.ProductModule;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.db.dao.IProductService;
import com.webank.databrain.db.entity.ProductDataObject;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.product.CreateProductRequest;
import com.webank.databrain.model.product.ProductDetail;
import com.webank.databrain.model.product.ProductIdName;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private Client client;

    @Autowired
    private CryptoSuite cryptoSuite;

    @Autowired
    private SysConfig sysConfig;

    @Autowired
    private IProductService productService;

    public List<ProductIdName> getHotProducts(int topN) {
        List<ProductDataObject> productList = productService
                .query()
                .select("productId","productName")
                .orderByDesc("pk_id")
                .last("limit " + topN )
                .list();
        List<ProductIdName> idNames = new ArrayList<>();
        productList.forEach(product -> {
            ProductIdName productIdName = new ProductIdName();
            BeanUtils.copyProperties(product,productIdName);
            idNames.add(productIdName);
        });
        return idNames;
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

    public ProductDetail getProductDetail(String productId) {
        ProductDataObject product = productService.getOne(Wrappers.<ProductDataObject>query().eq("productId",productId));
        ProductDetail productDetail = new ProductDetail();
        BeanUtils.copyProperties(product,productDetail);
        return productDetail;
    }

    public String createProduct(CreateProductRequest productRequest, byte[] signature) {
        CryptoKeyPair keyPair = cryptoSuite.generateRandomKeyPair();
        ProductModule productModule = ProductModule.load(
                sysConfig.getContracts().getAccountContract(),
                client,
                keyPair);

        TransactionReceipt receipt = productModule.createaProduct(cryptoSuite.hash((
                productRequest.getProductName() + productRequest.getInformation())
                .getBytes(StandardCharsets.UTF_8)));
        String productId = StringUtils.fromByteArray(productModule.getCreateaProductOutput(receipt).getValue1());
        ProductDataObject product = new ProductDataObject();
        product.setProductId(productId);
        product.setProductName(productRequest.getProductName());
        product.setInformation(productRequest.getInformation());
        product.setCreateTime(LocalDateTime.now());
        productService.save(product);
        return productId;
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

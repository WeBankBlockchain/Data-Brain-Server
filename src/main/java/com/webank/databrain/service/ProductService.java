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
import com.webank.databrain.model.product.*;
import com.webank.databrain.utils.BlockchainUtils;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionException;
import org.fisco.bcos.sdk.v3.utils.ByteUtils;
import org.fisco.bcos.sdk.v3.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public String createProduct(CreateProductRequest productRequest, byte[] signature) throws TransactionException {
        CryptoKeyPair keyPair = cryptoSuite.generateRandomKeyPair();
        ProductModule productModule = ProductModule.load(
                sysConfig.getContracts().getAccountContract(),
                client,
                keyPair);

        TransactionReceipt receipt = productModule.createaProduct(cryptoSuite.hash((
                productRequest.getProductName() + productRequest.getInformation())
                .getBytes(StandardCharsets.UTF_8)));
        BlockchainUtils.ensureTransactionSuccess(receipt);

        String productId = StringUtils.fromByteArray(productModule.getCreateaProductOutput(receipt).getValue1());
        ProductDataObject product = new ProductDataObject();
        product.setProductId(productId);
        product.setProductName(productRequest.getProductName());
        product.setInformation(productRequest.getInformation());
        product.setCreateTime(LocalDateTime.now());
        productService.save(product);
        return productId;
    }

    public void updateProduct(UpdateProductRequest productRequest, byte[] signature) throws TransactionException {
        CryptoKeyPair keyPair = cryptoSuite.generateRandomKeyPair();
        ProductModule productModule = ProductModule.load(
                sysConfig.getContracts().getAccountContract(),
                client,
                keyPair);

        TransactionReceipt receipt = productModule.modifyProduct(
                ByteUtils.hexStringToBytes(productRequest.getProductId()),
                cryptoSuite.hash((
                productRequest.getProductName() + productRequest.getInformation())
                .getBytes(StandardCharsets.UTF_8)));
        BlockchainUtils.ensureTransactionSuccess(receipt);

        ProductDataObject product = new ProductDataObject();
        product.setProductId(productRequest.getProductId());
        product.setProductName(productRequest.getProductName());
        product.setInformation(productRequest.getInformation());
        product.setUpdateTime(LocalDateTime.now());
        productService.saveOrUpdate(product);
    }

    public void deleteProduct(DeleteProductRequest productRequest) throws TransactionException {
        CryptoKeyPair keyPair = cryptoSuite.generateRandomKeyPair();
        ProductModule productModule = ProductModule.load(
                sysConfig.getContracts().getAccountContract(),
                client,
                keyPair);
        TransactionReceipt receipt = productModule.deleteProduct(
                ByteUtils.hexStringToBytes(productRequest.getProductId())
               );
        BlockchainUtils.ensureTransactionSuccess(receipt);
    }

    public void approveProduct(ApproveProductRequest productRequest) throws TransactionException {
        CryptoKeyPair keyPair = cryptoSuite.generateRandomKeyPair();
        ProductModule productModule = ProductModule.load(
                sysConfig.getContracts().getAccountContract(),
                client,
                keyPair);
        TransactionReceipt receipt = productModule.approveProduct(
                ByteUtils.hexStringToBytes(productRequest.getProductId()), productRequest.isAgree()
        );
        BlockchainUtils.ensureTransactionSuccess(receipt);
    }
}

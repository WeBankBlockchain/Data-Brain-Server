package com.webank.databrain.service;

import cn.hutool.core.codec.Base64;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.dao.bc.contract.ProductModule;
import com.webank.databrain.dao.db.entity.AccountInfoEntity;
import com.webank.databrain.dao.db.entity.ProductInfoEntity;
import com.webank.databrain.db.dao.AccountInfoDAO;
import com.webank.databrain.db.dao.ProductInfoDAO;
import com.webank.databrain.enums.CodeEnum;
import com.webank.databrain.handler.key.ThreadLocalKeyPairHandler;
import com.webank.databrain.model.bo.ProductInfoBO;
import com.webank.databrain.vo.request.product.CreateProductRequest;
import com.webank.databrain.vo.request.product.UpdateProductRequest;
import com.webank.databrain.model.resp.IdName;
import com.webank.databrain.model.resp.PagedResult;
import com.webank.databrain.model.resp.Paging;
import com.webank.databrain.model.resp.product.ProductDetail;
import com.webank.databrain.utils.BlockchainUtils;
import com.webank.databrain.utils.SessionUtils;
import com.webank.databrain.vo.common.CommonResponse;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.codec.decode.TransactionDecoderInterface;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionException;
import org.fisco.bcos.sdk.v3.utils.ByteUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private Client client;

    @Autowired
    private ThreadLocalKeyPairHandler keyPairHandler;

    @Autowired
    private CryptoKeyPair witnessKeyPair;
    @Autowired
    private SysConfig sysConfig;

    @Autowired
    private TransactionDecoderInterface txDecoder;

    @Autowired
    private AccountInfoDAO accountInfoDAO;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductInfoDAO productInfoDAO;

    public CommonResponse getHotProducts(int topN) {
        List<IdName> idNames = productInfoDAO.getHotProduct(topN);
        return CommonResponse.success(idNames);
    }

    public CommonResponse pageQueryProducts(Paging paging) {
        List<ProductInfoBO> productInfoPOList = productInfoDAO.pageQueryProduct(paging.getPageNo(),paging.getPageSize());
        List<ProductDetail> productDetails = new ArrayList<>();

        productInfoPOList.forEach(product -> {
            ProductDetail productDetail = new ProductDetail();
            BeanUtils.copyProperties(product, productDetail);
            productDetail.setProviderId(product.getDid());
            productDetails.add(productDetail);
        });
        return CommonResponse.success(new PagedResult<>(productDetails,
                paging.getPageNo(),
                paging.getPageSize())
        );
    }

    public CommonResponse getProductDetail(String productId) {
        ProductInfoBO product = productInfoDAO.getProductByGId(productId);
        ProductDetail productDetail = new ProductDetail();
        BeanUtils.copyProperties(product,productDetail);
        productDetail.setProviderId(product.getDid());
        return CommonResponse.success(productDetail);
    }

    public CommonResponse createProduct(String did, CreateProductRequest productRequest) throws TransactionException {

        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        AccountInfoEntity entity = accountInfoDAO.getOne(Wrappers.<AccountInfoEntity>query().eq("did", did));
        if (entity == null){
            return CommonResponse.error(CodeEnum.USER_NOT_EXISTS);
        }
        String privateKey = entity.getPrivateKey();
        CryptoKeyPair keyPair = cryptoSuite.loadKeyPair(privateKey);
        ProductModule productModule = ProductModule.load(
                sysConfig.getContractConfig().getProductContract(),
                client,
                keyPair);

        TransactionReceipt receipt = productModule.createProduct(cryptoSuite.hash((
                productRequest.getProductName() + productRequest.getProductDesc())
                .getBytes(StandardCharsets.UTF_8)));
        BlockchainUtils.ensureTransactionSuccess(receipt, txDecoder);

        String productId = Base64.encode(productModule.getCreateProductOutput(receipt).getValue1());
        ProductInfoEntity product = new ProductInfoEntity();
        product.setProductGid(productId);
        product.setProductName(productRequest.getProductName());
        product.setProductDesc(productRequest.getProductDesc());
        product.setCreateTime(new Date());
        productInfoDAO.saveProductInfo(product);
        return CommonResponse.success(productId);
    }

    public CommonResponse updateProduct(UpdateProductRequest productRequest) throws TransactionException {
        String did = SessionUtils.currentAccountDid();
        AccountInfoEntity entity = accountInfoDAO.getOne(Wrappers.<AccountInfoEntity>query().eq("did", did));
        if (entity == null){
            return CommonResponse.error(CodeEnum.USER_NOT_EXISTS);
        }
        String privateKey = entity.getPrivateKey();
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        CryptoKeyPair keyPair = cryptoSuite.loadKeyPair(privateKey);
        ProductModule productModule = ProductModule.load(
                sysConfig.getContractConfig().getAccountContract(),
                client,
                keyPair);

        TransactionReceipt receipt = productModule.modifyProduct(
                ByteUtils.hexStringToBytes(productRequest.getProductGId()),
                cryptoSuite.hash((
                productRequest.getProductName() + productRequest.getInformation())
                .getBytes(StandardCharsets.UTF_8)));
        BlockchainUtils.ensureTransactionSuccess(receipt, txDecoder);

        ProductInfoEntity product = new ProductInfoEntity();
        product.setPkId(productRequest.getProductId());
        product.setProductName(productRequest.getProductName());
        product.setProductDesc(productRequest.getInformation());
        product.setUpdateTime(new Date());
        productInfoDAO.updateProductInfo(product);

        return CommonResponse.success(productRequest.getProductGId());
    }


//    public CommonResponse approveProduct(ApproveProductRequest productRequest) throws TransactionException {
//        String privateKey = accountService.getPrivateKey(productRequest.getDid());
//        CryptoKeyPair keyPair = cryptoSuite.loadKeyPair(privateKey);
//        ProductModule productModule = ProductModule.load(
//                sysConfig.getContractConfig().getAccountContract(),
//                client,
//                keyPair);
//        TransactionReceipt receipt = productModule.approveProduct(
//                ByteUtils.hexStringToBytes(productRequest.getProductId()), productRequest.isAgree()
//        );
//        BlockchainUtils.ensureTransactionSuccess(receipt, txDecoder);
//
//        ProductDataObject product = productService.getOne(Wrappers.<ProductDataObject>query().
//                eq("productId",productRequest.getProductId()));
//        product.setReviewState(ReviewStatus.Approved.ordinal());
//        product.setReviewTime(LocalDateTime.now());
//        productService.saveOrUpdate(product);
//    }
}

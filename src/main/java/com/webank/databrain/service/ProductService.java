package com.webank.databrain.service;

//import com.webank.databrain.db.dao.IProductService;
//import com.webank.databrain.db.entity.ProductDataObject;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

//    @Autowired
//    private Client client;
//
//    @Autowired
//    private CryptoSuite cryptoSuite;
//
//    @Autowired
//    private SysConfig sysConfig;
//
//    @Autowired
//    private AccountService accountService;
//
//    @Autowired
//    private IProductService productService;
//
//    @Autowired
//    private TransactionDecoderInterface txDecoder;
//
//    public HotProductsResponse getHotProducts(int topN) {
//        List<ProductDataObject> productList = productService
//                .query()
//                .select("product_id","product_name")
//                .orderByDesc("pk_id")
//                .last("limit " + topN )
//                .list();
//        List<IdName> idNames = new ArrayList<>();
//        productList.forEach(product -> {
//            IdName item = new IdName();
//            item.setId(product.getProductId());
//            item.setName(product.getProductName());
//            idNames.add(item);
//        });
//        HotProductsResponse response = new HotProductsResponse(idNames);
//        return response;
//    }
//
//    public PageQueryProductResponse pageQueryProducts(Paging paging) {
//        IPage<ProductDataObject> result = productService.page(new Page<>(paging.getPageNo(), paging.getPageSize()));
//        List<ProductDataObject> productList = result.getRecords();
//        List<ProductDetail> productDetails = new ArrayList<>();
//
//        productList.forEach(product -> {
//            ProductDetail productDetail = new ProductDetail();
//            BeanUtils.copyProperties(product, productDetail);
//            productDetails.add(productDetail);
//        });
//        return new PageQueryProductResponse(new PagedResult<>(productDetails,
//                result.getCurrent(),
//                result.getSize(),
//                result.getTotal(),
//                result.getPages())
//        );
//    }
//
//    public ProductDetail getProductDetail(String productId) {
//        ProductDataObject product = productService.getOne(Wrappers.<ProductDataObject>query().eq("product_id",productId));
//        ProductDetail productDetail = new ProductDetail();
//        BeanUtils.copyProperties(product,productDetail);
//        return productDetail;
//    }
//
//    public CreateProductResponse createProduct(String did, CreateProductRequest productRequest) throws TransactionException {
//        CompanyDetail orgUserDetail = accountService.getOrgDetail(did);
//        if(orgUserDetail == null){
//            throw new DataBrainException(ErrorEnums.AccountNotExists);
//        }
//        String privateKey = accountService.getPrivateKey(did);
//        CryptoKeyPair keyPair = cryptoSuite.loadKeyPair(privateKey);
//        ProductModule productModule = ProductModule.load(
//                sysConfig.getContractConfig().getProductContract(),
//                client,
//                keyPair);
//
//        TransactionReceipt receipt = productModule.createProduct(cryptoSuite.hash((
//                productRequest.getProductName() + productRequest.getInformation())
//                .getBytes(StandardCharsets.UTF_8)));
//        BlockchainUtils.ensureTransactionSuccess(receipt, txDecoder);
//
//        String productId = Base64.encode(productModule.getCreateProductOutput(receipt).getValue1());
//        ProductDataObject product = new ProductDataObject();
//        product.setProductId(productId);
//        product.setProviderId(did);
//        product.setProviderName(orgUserDetail.getCompanyName());
//        product.setProductName(productRequest.getProductName());
//        product.setInformation(productRequest.getInformation());
//        product.setCreateTime(LocalDateTime.now());
//        productService.save(product);
//        return new CreateProductResponse(productId);
//    }
//
//    public UpdateProductResponse updateProduct(UpdateProductRequest productRequest) throws TransactionException {
//        String did = SessionUtils.currentAccountDid();
//        String privateKey = accountService.getPrivateKey(did);
//        CryptoKeyPair keyPair = cryptoSuite.loadKeyPair(privateKey);
//        ProductModule productModule = ProductModule.load(
//                sysConfig.getContractConfig().getAccountContract(),
//                client,
//                keyPair);
//
//        TransactionReceipt receipt = productModule.modifyProduct(
//                ByteUtils.hexStringToBytes(productRequest.getProductId()),
//                cryptoSuite.hash((
//                productRequest.getProductName() + productRequest.getInformation())
//                .getBytes(StandardCharsets.UTF_8)));
//        BlockchainUtils.ensureTransactionSuccess(receipt, txDecoder);
//
//        ProductDataObject product = new ProductDataObject();
//        product.setProductId(productRequest.getProductId());
//        product.setProductName(productRequest.getProductName());
//        product.setInformation(productRequest.getInformation());
//        product.setUpdateTime(LocalDateTime.now());
//        productService.saveOrUpdate(product);
//
//        return new UpdateProductResponse(productRequest.getProductId());
//    }

//    public void deleteProduct(DeleteProductRequest productRequest) throws TransactionException {
//        String privateKey = accountService.getPrivateKey(productRequest.getDid());
//        CryptoKeyPair keyPair = cryptoSuite.loadKeyPair(privateKey);
//        ProductModule productModule = ProductModule.load(
//                sysConfig.getContractConfig().getAccountContract(),
//                client,
//                keyPair);
//        TransactionReceipt receipt = productModule.deleteProduct(
//                ByteUtils.hexStringToBytes(productRequest.getProductId())
//               );
//        BlockchainUtils.ensureTransactionSuccess(receipt, txDecoder);
//    }
//
//    public void approveProduct(ApproveProductRequest productRequest) throws TransactionException {
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

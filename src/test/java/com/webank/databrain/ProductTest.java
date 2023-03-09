package com.webank.databrain;

import com.webank.databrain.service.AccountService;
import com.webank.databrain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductTest extends  ServerApplicationTests{
    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;


//    @Test
//    void productTest() throws Exception {
//        RegisterRequest request = new RegisterRequest();
//        request.setAccountType(AccountType.Enterprise);
//        request.setPassword("123456");
//        request.setUsername("ggggggg2");
//        request.setDetailJson("{\"name\" : \"aauser\"}");
//        String userId = accountService.registerAccount(request);
//
//        accountService.auditAccount("ggggggg2",true);
//
//        CreateProductRequest createProductRequest = new CreateProductRequest();
//        createProductRequest.setProductName("华为数据目录");
//        createProductRequest.setInformation("详细信息.....");
//        CreateProductResponse response = productService.createProduct("AAGUiTL0lMDoQ+/lX0gfiHZ0R4FDI9PJ5HH38cRZQLw=", createProductRequest);
//        System.out.println("response = " + response);
//    }
//
//    @Test
//    void pageQueryTest() throws Exception {
//        PagedResult<ProductDetail> result =  productService.pageQueryProducts(new Paging(1,10));
//        System.out.println(JSONUtil.toJsonStr(result));
//
//        List<IdName> productIdNames = productService.getHotProducts(10);
//        System.out.println(JSONUtil.toJsonStr(productIdNames));
//    }
//
//    @Test
//    void updateProduct() throws Exception {
//        RegisterRequest request = new RegisterRequest();
//        request.setAccountType(AccountType.Enterprise);
//        request.setPassword("123456");
//        request.setUsername("ggggggg2");
//        request.setDetailJson("{\"name\" : \"aauser\"}");
//        String userId = accountService.registerAccount(request);
//
//        accountService.auditAccount("ggggggg2",true);
//
//        CreateProductRequest createProductRequest = new CreateProductRequest();
//        createProductRequest.setProductName("华为数据目录");
//        createProductRequest.setInformation("详细信息.....");
//        CreateProductResponse createProductResponse = productService.createProduct("AAGUiTL0lMDoQ+/lX0gfiHZ0R4FDI9PJ5HH38cRZQLw=", createProductRequest);
//
//
//        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
//        updateProductRequest.setProductId(createProductResponse.getProductId());
//        updateProductRequest.setProductName("华为数据目录1");
//        updateProductRequest.setInformation("详细信息");
//        productService.updateProduct(updateProductRequest);
//    }
}

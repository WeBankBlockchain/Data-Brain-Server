package com.webank.databrain;

import cn.hutool.json.JSONUtil;
import com.webank.databrain.enums.AccountType;
import com.webank.databrain.model.account.RegisterRequestVO;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.product.CreateProductRequest;
import com.webank.databrain.model.product.ProductDetail;
import com.webank.databrain.model.product.ProductIdName;
import com.webank.databrain.model.product.UpdateProductRequest;
import com.webank.databrain.service.AccountService;
import com.webank.databrain.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductTest extends  ServerApplicationTests{
    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;


    @Test
    void productTest() throws Exception {
        RegisterRequestVO request = new RegisterRequestVO();
        request.setAccountType(AccountType.Enterprise);
        request.setPassword("123456");
        request.setUsername("ggggggg2");
        request.setDetailJson("{\"name\" : \"aauser\"}");
        String userId = accountService.registerAccount(request);

        accountService.auditAccount("ggggggg2",true);

        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName("华为数据目录");
        createProductRequest.setInformation("详细信息.....");
        createProductRequest.setDid("AAGUiTL0lMDoQ+/lX0gfiHZ0R4FDI9PJ5HH38cRZQLw=");
        String did = productService.createProduct(createProductRequest);
        System.out.println("did = " + did);
    }

    @Test
    void pageQueryTest() throws Exception {
        PagingResult<ProductDetail> result =  productService.pageQueryProducts(new Paging(1,10));
        System.out.println(JSONUtil.toJsonStr(result));

        List<ProductIdName> productIdNames = productService.getHotProducts(10);
        System.out.println(JSONUtil.toJsonStr(productIdNames));
    }

    @Test
    void updateProduct() throws Exception {
        RegisterRequestVO request = new RegisterRequestVO();
        request.setAccountType(AccountType.Enterprise);
        request.setPassword("123456");
        request.setUsername("ggggggg2");
        request.setDetailJson("{\"name\" : \"aauser\"}");
        String userId = accountService.registerAccount(request);

        accountService.auditAccount("ggggggg2",true);

        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName("华为数据目录");
        createProductRequest.setInformation("详细信息.....");
        createProductRequest.setDid("AAGUiTL0lMDoQ+/lX0gfiHZ0R4FDI9PJ5HH38cRZQLw=");
        String did = productService.createProduct(createProductRequest);
        System.out.println("did = " + did);

        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setProductId(did);
        updateProductRequest.setProductName("华为数据目录1");
        updateProductRequest.setInformation("详细信息");
        productService.updateProduct(updateProductRequest);
    }
}

package com.webank.databrain.service;

import cn.hutool.json.JSONUtil;
import com.webank.databrain.ServerApplicationTests;
import com.webank.databrain.vo.request.product.CreateProductRequest;
import com.webank.databrain.model.resp.Paging;
import com.webank.databrain.vo.common.CommonResponse;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceTest  extends ServerApplicationTests {

    @Autowired
    private ProductService productService;


    @Test
    void productQueryTest() throws Exception {
        CommonResponse result =  productService.pageQueryProducts(new Paging(1,1));
        System.out.println(JSONUtil.toJsonStr(result));
    }


    @Test
    void productHotTest() throws Exception {
        CommonResponse result =  productService.getHotProducts(10);
        System.out.println(JSONUtil.toJsonStr(result));
    }

    @Test
    void createProductTest() throws TransactionException {
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName("华为数据目录");
        createProductRequest.setInformation("详细信息.....");
        CommonResponse response = productService.createProduct("AAGUiTL0lMDoQ+/lX0gfiHZ0R4FDI9PJ5HH38cRZQLw=", createProductRequest);
        System.out.println("response = " + response);
    }
}

package com.webank.databrain.service;

import cn.hutool.json.JSONUtil;
import com.webank.databrain.ServerApplicationTests;
import com.webank.databrain.model.resp.Paging;
import com.webank.databrain.vo.common.CommonResponse;
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
}

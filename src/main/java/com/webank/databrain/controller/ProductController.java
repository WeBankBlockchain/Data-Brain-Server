package com.webank.databrain.controller;

import com.webank.databrain.model.common.CommonResponse;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.product.ProductDetail;
import com.webank.databrain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/pageQueryProduct")
    public CommonResponse<PagingResult<ProductDetail>> pageQueryProduct(@RequestParam(name = "pageNo") int pageNo,
                                                                        @RequestParam(name = "pageSize") int pageSize
                                                   ){
        log.info("pageQueryProduct pageNo = {}, pageSize = {}",pageNo,pageSize);
        PagingResult<ProductDetail> result;
        try {
            result = productService.listProducts(new Paging(pageNo,pageSize));
        } catch (Exception e) {
            log.error("pageQueryProduct failed ", e);
            return CommonResponse.createFailedResult(500,"pageQueryProduct failed");
        }
        return CommonResponse.createSuccessResult(result);
    }


}

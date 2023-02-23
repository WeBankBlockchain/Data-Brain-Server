package com.webank.databrain.controller;

import com.webank.databrain.model.common.CommonResponse;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.product.ProductDetail;
import com.webank.databrain.model.product.ProductIdName;
import com.webank.databrain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping(value = "/queryProductById")
    public CommonResponse<ProductDetail> queryProductById(@RequestParam(name = "productId") String productId
    ){
        log.info("pageQueryProduct productId = {}",productId);
        ProductDetail result;
        try {
            result = productService.getProductDetail(productId);
        } catch (Exception e) {
            log.error("pageQueryProduct failed ", e);
            return CommonResponse.createFailedResult(500,"pageQueryProduct failed");
        }
        return CommonResponse.createSuccessResult(result);
    }

    @RequestMapping(value = "/getHotProducts")
    public CommonResponse<List<ProductIdName>> getHotProducts(@RequestParam(name = "topN") int topN
    ){
        log.info("getHotProducts topN = {}",topN);
        List<ProductIdName> result;
        try {
            result = productService.getHotProducts(topN);
        } catch (Exception e) {
            log.error("pageQueryProduct failed ", e);
            return CommonResponse.createFailedResult(500,"pageQueryProduct failed");
        }
        return CommonResponse.createSuccessResult(result);
    }


}

package com.webank.databrain.controller;

import com.webank.databrain.model.common.CommonResponse;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.product.CreateProductRequest;
import com.webank.databrain.model.product.ProductDetail;
import com.webank.databrain.model.product.ProductIdName;
import com.webank.databrain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/pageQueryProduct")
    public CommonResponse<PagingResult<ProductDetail>> pageQueryProduct(@RequestParam(name = "pageNo") int pageNo,
                                                                        @RequestParam(name = "pageSize") int pageSize
                                                   ){
        log.info("pageQueryProduct pageNo = {}, pageSize = {}",pageNo,pageSize);
        PagingResult<ProductDetail> result = productService.pageQueryProducts(new Paging(pageNo,pageSize));
        return CommonResponse.createSuccessResult(result);
    }

    @RequestMapping(value = "/queryProductById")
    public CommonResponse<ProductDetail> queryProductById(@RequestParam(name = "productId") String productId
    ) {
        log.info("pageQueryProduct productId = {}", productId);
        ProductDetail result =productService.getProductDetail(productId);
        return CommonResponse.createSuccessResult(result);
    }

    @RequestMapping(value = "/getHotProducts")
    public CommonResponse<List<ProductIdName>> getHotProducts(@RequestParam(name = "topN") int topN
    ){
        log.info("getHotProducts topN = {}",topN);
        List<ProductIdName> result = productService.getHotProducts(topN);
        return CommonResponse.createSuccessResult(result);
    }


    @RequestMapping(value = "/createProduct")
    public CommonResponse<String> createProduct(@RequestBody CreateProductRequest createProductRequest) throws Exception{
        log.info("createProduct did = {}",createProductRequest.getDid());
        String productId = productService.createProduct(createProductRequest);
        return CommonResponse.createSuccessResult(productId);
    }


}

package com.webank.databrain.controller;

import com.webank.databrain.service.ProductService;
import com.webank.databrain.vo.common.CommonPageQueryRequest;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.common.HotDataRequest;
import com.webank.databrain.vo.request.product.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/pageQueryProduct")
    public CommonResponse pageQueryProduct(@RequestBody @Valid CommonPageQueryRequest request){
        log.info("pageQueryProduct pageNo = {}, pageSize = {}",request.getPageNo(),request.getPageSize());
        return productService.pageQueryProducts(request);
    }
    @PostMapping(value = "/queryProductById")
    public CommonResponse queryProductById(@RequestBody QueryProductByIdRequest queryProductRequest) {
        log.info("pageQueryProduct productId = {}", queryProductRequest.getProductGid());
        return productService.getProductDetail(queryProductRequest.getProductGid());
    }
    @PostMapping(value = "/getHotProducts")
    public CommonResponse getHotProducts(@RequestBody HotDataRequest request){
        return productService.getHotProducts(request);
    }
    @PostMapping(value = "/createProduct")
    public CommonResponse createProduct(@RequestBody CreateProductRequest createProductRequest) throws Exception{
        return productService.createProduct(createProductRequest);
    }

    @PostMapping(value = "/updateProduct")
    public CommonResponse updateProduct(@RequestBody UpdateProductRequest updateProductRequest) throws Exception{
        return productService.updateProduct(updateProductRequest);
    }


}

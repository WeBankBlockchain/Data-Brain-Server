package com.webank.databrain.controller;

import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.model.resp.Paging;
import com.webank.databrain.service.ProductService;
import com.webank.databrain.utils.SessionUtils;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.product.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/pageQueryProduct")
    public CommonResponse pageQueryProduct(
            @RequestBody PageQueryProductRequest queryProductRequest
    ){
        log.info("pageQueryProduct pageNo = {}, pageSize = {}",queryProductRequest.getPageNo(),queryProductRequest.getPageSize());
        if(queryProductRequest.getPageNo() <= 0 || queryProductRequest.getPageSize() <= 0){
            return CommonResponse.error(ErrorEnums.UnknownError.getCode(), "pageNo or pageSize error");
        }
        return productService.pageQueryProducts(new Paging(
                queryProductRequest.getPageNo()
                ,queryProductRequest.getPageSize()));
    }
    @PostMapping(value = "/queryProductById")
    public CommonResponse queryProductById(@RequestBody QueryProductByIdRequest queryProductRequest
    ) {
        log.info("pageQueryProduct productId = {}", queryProductRequest.getProductId());
        return productService.getProductDetail(queryProductRequest.getProductId());
    }
    @PostMapping(value = "/getHotProducts")
    public CommonResponse getHotProducts(@RequestBody HotProductsRequest hotProductRequest
    ){
        log.info("getHotProducts topN = {}", hotProductRequest.getTopN());
        return productService.getHotProducts(hotProductRequest.getTopN());
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

package com.webank.databrain.controller;

import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.model.common.CommonResponse;
import com.webank.databrain.model.common.HotQueryRequest;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.product.CreateProductRequest;
import com.webank.databrain.model.product.ProductDetail;
import com.webank.databrain.model.product.ProductIdName;
import com.webank.databrain.model.product.QueryProductRequest;
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

    @PostMapping(value = "/pageQueryProduct")
    public CommonResponse<PagingResult<ProductDetail>> pageQueryProduct(
            @RequestBody QueryProductRequest queryProductRequest
    ){
        log.info("pageQueryProduct pageNo = {}, pageSize = {}",queryProductRequest.getPageNo(),queryProductRequest.getPageSize());
        if(queryProductRequest.getPageNo() <= 0 || queryProductRequest.getPageSize() <= 0){
            return CommonResponse.createFailedResult(ErrorEnums.UnknownError.getCode(), "pageNo or pageSize error");
        }
        PagingResult<ProductDetail> result = productService.pageQueryProducts(new Paging(
                queryProductRequest.getPageNo()
                ,queryProductRequest.getPageSize()));
        return CommonResponse.createSuccessResult(result);
    }

    @PostMapping(value = "/queryProductById")
    public CommonResponse<ProductDetail> queryProductById(@RequestBody QueryProductRequest queryProductRequest
    ) {
        log.info("pageQueryProduct productId = {}", queryProductRequest.getProductId());
        ProductDetail result =productService.getProductDetail(queryProductRequest.getProductId());
        return CommonResponse.createSuccessResult(result);
    }

    @PostMapping(value = "/getHotProducts")
    public CommonResponse<List<ProductIdName>> getHotProducts(@RequestBody HotQueryRequest hotProductRequest
    ){
        log.info("getHotProducts topN = {}", hotProductRequest.getTopN());
        List<ProductIdName> result = productService.getHotProducts(hotProductRequest.getTopN());
        return CommonResponse.createSuccessResult(result);
    }


    @PostMapping(value = "/createProduct")
    public CommonResponse<String> createProduct(@RequestBody CreateProductRequest createProductRequest) throws Exception{
        log.info("createProduct did = {}",createProductRequest.getDid());
        String productId = productService.createProduct(createProductRequest);
        return CommonResponse.createSuccessResult(productId);
    }


}

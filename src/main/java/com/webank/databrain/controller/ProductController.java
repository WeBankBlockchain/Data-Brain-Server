package com.webank.databrain.controller;

import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.model.response.common.CommonResponse;
import com.webank.databrain.model.vo.common.Paging;
import com.webank.databrain.model.request.product.*;
import com.webank.databrain.model.response.product.CreateProductResponse;
import com.webank.databrain.model.response.product.HotProductsResponse;
import com.webank.databrain.model.response.product.PageQueryProductResponse;
import com.webank.databrain.model.response.product.UpdateProductResponse;
import com.webank.databrain.model.vo.product.ProductDetail;
import com.webank.databrain.service.ProductService;
import com.webank.databrain.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/pageQueryProduct")
    public CommonResponse<PageQueryProductResponse> pageQueryProduct(
            @RequestBody PageQueryProductRequest queryProductRequest
    ){
        log.info("pageQueryProduct pageNo = {}, pageSize = {}",queryProductRequest.getPageNo(),queryProductRequest.getPageSize());
        if(queryProductRequest.getPageNo() <= 0 || queryProductRequest.getPageSize() <= 0){
            return CommonResponse.createFailedResult(ErrorEnums.UnknownError.getCode(), "pageNo or pageSize error");
        }
        PageQueryProductResponse result = productService.pageQueryProducts(new Paging(
                queryProductRequest.getPageNo()
                ,queryProductRequest.getPageSize()));
        return CommonResponse.createSuccessResult(result);
    }

    @PostMapping(value = "/queryProductById")
    public CommonResponse<ProductDetail> queryProductById(@RequestBody QueryProductByIdRequest queryProductRequest
    ) {
        log.info("pageQueryProduct productId = {}", queryProductRequest.getProductId());
        ProductDetail result =productService.getProductDetail(queryProductRequest.getProductId());
        return CommonResponse.createSuccessResult(result);
    }

    @PostMapping(value = "/getHotProducts")
    public CommonResponse<HotProductsResponse> getHotProducts(@RequestBody HotProductsRequest hotProductRequest
    ){
        log.info("getHotProducts topN = {}", hotProductRequest.getTopN());
        HotProductsResponse result = productService.getHotProducts(hotProductRequest.getTopN());

        HotProductsResponse response = new HotProductsResponse(result);
        return CommonResponse.createSuccessResult(result);
    }


    @PostMapping(value = "/createProduct")
    public CommonResponse<CreateProductResponse> createProduct(@RequestBody CreateProductRequest createProductRequest) throws Exception{
        String did = SessionUtils.currentAccountDid();
        CreateProductResponse response = productService.createProduct(did, createProductRequest);
        return CommonResponse.createSuccessResult(response);
    }

    @PostMapping(value = "/updateProduct")
    public CommonResponse<UpdateProductResponse> updateProduct(@RequestBody UpdateProductRequest updateProductRequest) throws Exception{
        UpdateProductResponse response = productService.updateProduct(updateProductRequest);
        return CommonResponse.createSuccessResult(response);
    }


}

package com.webank.databrain.controller;

import com.webank.databrain.model.common.CommonResponse;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.dataschema.CreateDataSchemaRequest;
import com.webank.databrain.model.dataschema.DataSchemaDetail;
import com.webank.databrain.model.dataschema.DataSchemaDetailWithVisit;
import com.webank.databrain.service.DataSchemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/schema")
public class DataSchemaController {


    @Autowired
    private DataSchemaService schemaService;


    @PostMapping(value = "/pageQuerySchema")
    public CommonResponse<PagingResult<DataSchemaDetail>> pageQuerySchema(
            @RequestParam(name = "pageNo") int pageNo,
            @RequestParam(name = "pageSize") int pageSize
    ){
        log.info("pageQuerySchema pageNo = {}, pageSize = {}",pageNo,pageSize);
        PagingResult<DataSchemaDetail> result = schemaService.pageQuerySchema(new Paging(pageNo,pageSize));;
        return CommonResponse.createSuccessResult(result);
    }

    @PostMapping(value = "/pageQuerySchemaBySearch")
    public CommonResponse<PagingResult<DataSchemaDetail>> pageQuerySchemaBySearch(
            @RequestParam(name = "pageNo") int pageNo,
            @RequestParam(name = "pageSize") int pageSize,
            @RequestParam(name = "keyWord") String keyWord
    ){
        log.info("pageQuerySchemaBySearch pageNo = {}, pageSize = {}",pageNo,pageSize);
        PagingResult<DataSchemaDetail> result = schemaService.pageQuerySchemaBySearch(keyWord,new Paging(pageNo,pageSize));;
        return CommonResponse.createSuccessResult(result);
    }

    @PostMapping(value = "/pageQuerySchemaByProductId")
    public CommonResponse<PagingResult<DataSchemaDetail>> pageQuerySchemaByProductId(
            @RequestParam(name = "pageNo") int pageNo,
            @RequestParam(name = "pageSize") int pageSize,
            @RequestParam(name = "productId") String productId
    ){
        log.info("pageQuerySchemaByProductId pageNo = {}, pageSize = {}",pageNo,pageSize);
        PagingResult<DataSchemaDetail> result = schemaService.pageQuerySchemaByProductId(productId,new Paging(pageNo,pageSize));;
        return CommonResponse.createSuccessResult(result);
    }

    @PostMapping(value = "/pageQuerySchemasByProvider")
    public CommonResponse<PagingResult<DataSchemaDetail>> pageQuerySchemasByProvider(
            @RequestParam(name = "pageNo") int pageNo,
            @RequestParam(name = "pageSize") int pageSize,
            @RequestParam(name = "providerId") String providerId
    ){
        log.info("pageQuerySchemaByProvider providerId = {}",providerId);
        PagingResult<DataSchemaDetail> result  = schemaService.pageQuerySchemaByProvider(providerId,new Paging(pageNo,pageSize));;
        return CommonResponse.createSuccessResult(result);
    }

    @PostMapping(value = "/pageQuerySchemaByTag")
    public CommonResponse<PagingResult<DataSchemaDetail>> pageQuerySchemaByTag(
            @RequestParam(name = "pageNo") int pageNo,
            @RequestParam(name = "pageSize") int pageSize,
            @RequestParam(name = "tagId") long tagId
    ){
        log.info("pageQuerySchemaByProvider tagId = {}",tagId);
        PagingResult<DataSchemaDetail> result = schemaService.pageQuerySchemaByTag(tagId,new Paging(pageNo,pageSize));;
        return CommonResponse.createSuccessResult(result);
    }



    @PostMapping(value = "/createSchema")
    public CommonResponse<String> createSchema(@RequestBody CreateDataSchemaRequest createDataSchemaRequest) throws Exception{
        log.info("createSchema did = {}",createDataSchemaRequest.getDid());
        String productId = schemaService.createDataSchema(createDataSchemaRequest);;
        return CommonResponse.createSuccessResult(productId);
    }

    @PostMapping(value = "/querySchemaById")
    public CommonResponse<DataSchemaDetailWithVisit> querySchemaById(@RequestParam(name = "schemaId") String schemaId
    ){
        log.info("querySchemaById schemaId = {}",schemaId);
        DataSchemaDetailWithVisit result = schemaService.getDataSchemaById(schemaId);;
        return CommonResponse.createSuccessResult(result);
    }

}

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("schema")
public class DataSchemaController {


    @Autowired
    private DataSchemaService schemaService;


    @RequestMapping(value = "/pageQuerySchema")
    public CommonResponse<PagingResult<DataSchemaDetail>> pageQuerySchema(
            @RequestParam(name = "pageNo") int pageNo,
            @RequestParam(name = "pageSize") int pageSize
    ){
        log.info("pageQuerySchema pageNo = {}, pageSize = {}",pageNo,pageSize);
        PagingResult<DataSchemaDetail> result;
        try {
            result = schemaService.pageQuerySchema(new Paging(pageNo,pageSize));
        } catch (Exception e) {
            log.error("pageQuerySchema failed ", e);
            return CommonResponse.createFailedResult(500,"pageQuerySchema failed");
        }
        return CommonResponse.createSuccessResult(result);
    }

    @RequestMapping(value = "/pageQuerySchemaBySearch")
    public CommonResponse<PagingResult<DataSchemaDetail>> pageQuerySchemaBySearch(
            @RequestParam(name = "pageNo") int pageNo,
            @RequestParam(name = "pageSize") int pageSize,
            @RequestParam(name = "keyWord") String keyWord
    ){
        log.info("pageQuerySchemaBySearch pageNo = {}, pageSize = {}",pageNo,pageSize);
        PagingResult<DataSchemaDetail> result;
        try {
            result = schemaService.pageQuerySchemaBySearch(keyWord,new Paging(pageNo,pageSize));
        } catch (Exception e) {
            log.error("pageQuerySchemaBySearch failed ", e);
            return CommonResponse.createFailedResult(500,"pageQuerySchemaBySearch failed");
        }
        return CommonResponse.createSuccessResult(result);
    }

    @RequestMapping(value = "/pageQuerySchemaByProductId")
    public CommonResponse<PagingResult<DataSchemaDetail>> pageQuerySchemaByProductId(
            @RequestParam(name = "pageNo") int pageNo,
            @RequestParam(name = "pageSize") int pageSize,
            @RequestParam(name = "productId") String productId
    ){
        log.info("pageQuerySchemaByProductId pageNo = {}, pageSize = {}",pageNo,pageSize);
        PagingResult<DataSchemaDetail> result;
        try {
            result = schemaService.pageQuerySchemaByProductId(productId,new Paging(pageNo,pageSize));
        } catch (Exception e) {
            log.error("pageQuerySchemaByProductId failed ", e);
            return CommonResponse.createFailedResult(500,"pageQuerySchemaByProductId failed");
        }
        return CommonResponse.createSuccessResult(result);
    }

    @RequestMapping(value = "/pageQuerySchemaByPrId")
    public CommonResponse<PagingResult<DataSchemaDetail>> pageQuerySchemasByProvider(
            @RequestParam(name = "pageNo") int pageNo,
            @RequestParam(name = "pageSize") int pageSize,
            @RequestParam(name = "providerId") String providerId
    ){
        log.info("pageQuerySchemaByProvider providerId = {}",providerId);
        PagingResult<DataSchemaDetail> result;
        try {
            result = schemaService.pageQuerySchemaByProvider(providerId,new Paging(pageNo,pageSize));
        } catch (Exception e) {
            log.error("pageQuerySchemaByProvider failed ", e);
            return CommonResponse.createFailedResult(500,"pageQuerySchemaByProvider failed");
        }
        return CommonResponse.createSuccessResult(result);
    }

    @RequestMapping(value = "/pageQuerySchemaByTag")
    public CommonResponse<PagingResult<DataSchemaDetail>> pageQuerySchemaByTag(
            @RequestParam(name = "pageNo") int pageNo,
            @RequestParam(name = "pageSize") int pageSize,
            @RequestParam(name = "tagId") long tagId
    ){
        log.info("pageQuerySchemaByProvider tagId = {}",tagId);
        PagingResult<DataSchemaDetail> result;
        try {
            result = schemaService.pageQuerySchemaByTag(tagId,new Paging(pageNo,pageSize));
        } catch (Exception e) {
            log.error("pageQuerySchemaByProvider failed ", e);
            return CommonResponse.createFailedResult(500,"pageQuerySchemaByProvider failed");
        }
        return CommonResponse.createSuccessResult(result);
    }



    @RequestMapping(value = "/createSchema")
    public CommonResponse<String> createSchema(@RequestBody CreateDataSchemaRequest createDataSchemaRequest){
        log.info("createSchema did = {}",createDataSchemaRequest.getDid());
        String productId;
        try {
            productId = schemaService.createDataSchema(createDataSchemaRequest);
        } catch (Exception e) {
            log.error("createSchema failed ", e);
            return CommonResponse.createFailedResult(500,"createSchema failed");
        }
        return CommonResponse.createSuccessResult(productId);
    }

    @RequestMapping(value = "/querySchemaById")
    public CommonResponse<DataSchemaDetailWithVisit> querySchemaById(@RequestParam(name = "schemaId") String schemaId
    ){
        log.info("querySchemaById schemaId = {}",schemaId);
        DataSchemaDetailWithVisit result;
        try {
            result = schemaService.getDataSchemaById(schemaId);
        } catch (Exception e) {
            log.error("queryProductById failed ", e);
            return CommonResponse.createFailedResult(500,"querySchemaById failed");
        }
        return CommonResponse.createSuccessResult(result);
    }

}

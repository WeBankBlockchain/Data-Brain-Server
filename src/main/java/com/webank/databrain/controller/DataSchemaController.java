package com.webank.databrain.controller;

import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.model.common.CommonResponse;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.dataschema.*;
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
            @RequestBody QuerySchemaRequest querySchemaRequest
    ){
        log.info("pageQuerySchema pageNo = {}, pageSize = {}",
                querySchemaRequest.getPageNo(),
                querySchemaRequest.getPageSize());
        if(querySchemaRequest.getPageNo() <= 0 || querySchemaRequest.getPageSize() <= 0){
            return CommonResponse.createFailedResult(ErrorEnums.UnknownError.getCode(), "pageNo or pageSize error");
        }
        PagingResult<DataSchemaDetail> result = schemaService.pageQuerySchema(new Paging(
                querySchemaRequest.getPageNo(),
                querySchemaRequest.getPageSize()),
                querySchemaRequest.getProductId(),
                querySchemaRequest.getProviderId(),
                querySchemaRequest.getTag(),
                querySchemaRequest.getKeyWord()
        );
        return CommonResponse.createSuccessResult(result);
    }


    @PostMapping(value = "/createSchema")
    public CommonResponse<String> createSchema(@RequestBody CreateDataSchemaRequest createDataSchemaRequest) throws Exception{
        log.info("createSchema did = {}",createDataSchemaRequest.getDid());
        String productId = schemaService.createDataSchema(createDataSchemaRequest);;
        return CommonResponse.createSuccessResult(productId);
    }

    @PostMapping(value = "/updateSchema")
    public CommonResponse<String> updateSchema(@RequestBody UpdatedDataSchemaRequest updatedDataSchemaRequest) throws Exception{
        log.info("createSchema did = {}",updatedDataSchemaRequest.getDid());
        String schemaId = schemaService.updateDataSchema(updatedDataSchemaRequest);;
        return CommonResponse.createSuccessResult(schemaId);
    }

    @PostMapping(value = "/querySchemaById")
    public CommonResponse<DataSchemaDetailWithVisit> querySchemaById( @RequestBody QuerySchemaRequest querySchemaRequest
    ){
        log.info("querySchemaById schemaId = {}",querySchemaRequest.getSchemaId());
        DataSchemaDetailWithVisit result = schemaService.getDataSchemaById(querySchemaRequest.getSchemaId());
        return CommonResponse.createSuccessResult(result);
    }

}

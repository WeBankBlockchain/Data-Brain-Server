package com.webank.databrain.controller;

import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.service.DataSchemaService;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.common.Paging;
import com.webank.databrain.vo.request.dataschema.CreateDataSchemaRequest;
import com.webank.databrain.vo.request.dataschema.PageQueryDataSchemaRequest;
import com.webank.databrain.vo.request.dataschema.QuerySchemaByIdRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/schema")
public class DataSchemaController {


    @Autowired
    private DataSchemaService schemaService;


    @PostMapping(value = "/pageQuerySchema")
    public CommonResponse pageQuerySchema(
            @RequestBody PageQueryDataSchemaRequest querySchemaRequest
    ){
        log.info("pageQuerySchema pageNo = {}, pageSize = {}",
                querySchemaRequest.getPageNo(),
                querySchemaRequest.getPageSize());
        if(querySchemaRequest.getPageNo() <= 0 || querySchemaRequest.getPageSize() <= 0){
            return CommonResponse.error(ErrorEnums.UnknownError.getCode(), "pageNo or pageSize error");
        }
        return schemaService.pageQuerySchema(new Paging(
                querySchemaRequest.getPageNo(),
                querySchemaRequest.getPageSize()),
                querySchemaRequest.getProductId(),
                querySchemaRequest.getProviderId(),
                querySchemaRequest.getTagId(),
                querySchemaRequest.getKeyWord()
        );
    }
//
//
    @PostMapping(value = "/createSchema")
    public CommonResponse createSchema(@RequestBody CreateDataSchemaRequest createDataSchemaRequest) throws Exception{
        return schemaService.createDataSchema(createDataSchemaRequest);
    }
//
//    @PostMapping(value = "/updateSchema")
//    public CommonResponse<UpdateDataSchemaResponse> updateSchema(@RequestBody UpdateDataSchemaRequest updateDataSchemaRequest) throws Exception{
//        String did = SessionUtils.currentAccountDid();
//        UpdateDataSchemaResponse response = schemaService.updateDataSchema(did, updateDataSchemaRequest);;
//        return CommonResponse.success(response);
//    }
//
    @PostMapping(value = "/querySchemaById")
    public CommonResponse querySchemaById(@RequestBody QuerySchemaByIdRequest request
    ){
        log.info("querySchemaById schemaId = {}",request.getSchemaGid());
        return schemaService.getDataSchemaByGid(request.getSchemaGid());
    }

}

package com.webank.databrain.controller;

import com.webank.databrain.service.DataSchemaService;
import com.webank.databrain.vo.common.CommonResponse;
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
    public CommonResponse pageQuerySchema(@RequestBody PageQueryDataSchemaRequest querySchemaRequest){

        return schemaService.pageQuerySchema(querySchemaRequest);
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

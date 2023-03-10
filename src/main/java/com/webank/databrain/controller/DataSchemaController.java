package com.webank.databrain.controller;

import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.model.req.dataschema.PageQueryDataSchemaRequest;
import com.webank.databrain.model.resp.Paging;
import com.webank.databrain.service.DataSchemaService;
import com.webank.databrain.vo.common.CommonResponse;
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
        CommonResponse response = schemaService.pageQuerySchema(new Paging(
                querySchemaRequest.getPageNo(),
                querySchemaRequest.getPageSize()),
                querySchemaRequest.getProductId(),
                querySchemaRequest.getProviderId(),
                querySchemaRequest.getTagId(),
                querySchemaRequest.getKeyWord()
        );
        return CommonResponse.success(response);
    }
//
//
//    @PostMapping(value = "/createSchema")
//    public CommonResponse<CreateDataSchemaResponse> createSchema(@RequestBody CreateDataSchemaRequest createDataSchemaRequest) throws Exception{
//        CreateDataSchemaResponse createDataSchemaResponse = schemaService.createDataSchema(createDataSchemaRequest);;
//        return CommonResponse.success(createDataSchemaResponse);
//    }
//
//    @PostMapping(value = "/updateSchema")
//    public CommonResponse<UpdateDataSchemaResponse> updateSchema(@RequestBody UpdateDataSchemaRequest updateDataSchemaRequest) throws Exception{
//        String did = SessionUtils.currentAccountDid();
//        UpdateDataSchemaResponse response = schemaService.updateDataSchema(did, updateDataSchemaRequest);;
//        return CommonResponse.success(response);
//    }
//
//    @PostMapping(value = "/querySchemaById")
//    public CommonResponse<QueryDataSchemaByIdResponse> querySchemaById(@RequestBody PageQueryDataSchemaRequest request
//    ){
//        log.info("querySchemaById schemaId = {}",request.getSchemaId());
//        QueryDataSchemaByIdResponse result = schemaService.getDataSchemaById(request.getSchemaId());
//        return CommonResponse.success(result);
//    }

}

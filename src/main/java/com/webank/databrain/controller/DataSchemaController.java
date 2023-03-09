package com.webank.databrain.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/schema")
public class DataSchemaController {

//
//    @Autowired
//    private DataSchemaService schemaService;
//
//
//    @PostMapping(value = "/pageQuerySchema")
//    public CommonResponse<PageQueryDataSchemaResponse> pageQuerySchema(
//            @RequestBody PageQueryDataSchemaRequest querySchemaRequest
//    ){
//        log.info("pageQuerySchema pageNo = {}, pageSize = {}",
//                querySchemaRequest.getPageNo(),
//                querySchemaRequest.getPageSize());
//        if(querySchemaRequest.getPageNo() <= 0 || querySchemaRequest.getPageSize() <= 0){
//            return CommonResponse.fail(ErrorEnums.UnknownError.getCode(), "pageNo or pageSize error");
//        }
//        PageQueryDataSchemaResponse response = schemaService.pageQuerySchema(new Paging(
//                querySchemaRequest.getPageNo(),
//                querySchemaRequest.getPageSize()),
//                querySchemaRequest.getProductId(),
//                querySchemaRequest.getProviderId(),
//                querySchemaRequest.getTag(),
//                querySchemaRequest.getKeyWord()
//        );
//        return CommonResponse.success(response);
//    }
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

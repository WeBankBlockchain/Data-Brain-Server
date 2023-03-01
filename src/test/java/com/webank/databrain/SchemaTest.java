package com.webank.databrain;

import cn.hutool.json.JSONUtil;
import com.webank.databrain.enums.AccountType;
import com.webank.databrain.model.account.RegisterRequestVO;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.dataschema.CreateDataSchemaRequest;
import com.webank.databrain.model.dataschema.DataSchemaDetail;
import com.webank.databrain.model.dataschema.DataSchemaDetailWithVisit;
import com.webank.databrain.service.AccountService;
import com.webank.databrain.service.DataSchemaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class SchemaTest extends ServerApplicationTests{


    @Autowired
    private AccountService accountService;

    @Autowired
    private DataSchemaService schemaService;


    @Test
    void schemaQueryTest() throws Exception {
        PagingResult<DataSchemaDetail> result =  schemaService.pageQuerySchema(new Paging(1,1), null,null,0,null);
        System.out.println(JSONUtil.toJsonStr(result));

        DataSchemaDetailWithVisit visit = schemaService.getDataSchemaById("AAE5B5xx/WADdsUVkcPZlkdSCQJzdLfKf0u1jMMiZxM=");
        System.out.println(JSONUtil.toJsonStr(visit));
    }

    @Test
    void schemaCreateTest() throws Exception {
        CreateDataSchemaRequest schemaRequest  = new CreateDataSchemaRequest();
        schemaRequest.setSchema("{\"test\":\"String\"....}");
        schemaRequest.setSchemaName("测试目录");
        schemaRequest.setCondition("查询条件");
        schemaRequest.setPrice(123);
        schemaRequest.setEffectTime(LocalDateTime.MAX);
        schemaRequest.setExpireTime(LocalDateTime.now());
        schemaRequest.setDescription("描述");
        schemaRequest.setTagName("科技");

        schemaService.createDataSchema()
    }



}

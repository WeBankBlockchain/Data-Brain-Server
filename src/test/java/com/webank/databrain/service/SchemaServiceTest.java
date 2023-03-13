package com.webank.databrain.service;

import cn.hutool.json.JSONUtil;
import com.webank.databrain.ServerApplicationTests;
import com.webank.databrain.model.resp.Paging;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.dataschema.CreateDataSchemaRequest;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class SchemaServiceTest  extends ServerApplicationTests {

    @Autowired
    private DataSchemaService schemaService;


    @Test
    void schemaQueryTest() throws Exception {
        CommonResponse result =  schemaService.pageQuerySchema(new Paging(1,1), 0L,0L,0L,"");
        System.out.println(JSONUtil.toJsonStr(result));
    }

    @Test
    void createSchemaTest() throws Exception {
        CreateDataSchemaRequest schemaRequest = new CreateDataSchemaRequest();
        schemaRequest.setContentSchema("{\"test24\":\"String\"....}");
        schemaRequest.setDataSchemaName("华为数据");
        schemaRequest.setAccessCondition("查询条件");
        schemaRequest.setPrice(123);
        schemaRequest.setEffectTime(new Date());
        schemaRequest.setExpireTime(new Date());
        schemaRequest.setDataSchemaDesc("描述");
        schemaRequest.setTagId(1L);
        schemaRequest.setProductId(1L);
        schemaRequest.setProductGId("AAAQGayMdnmwj5IbY/O5ZaN/wdCoB8BcEbeT2CwCpHwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAg==");
        schemaRequest.setProviderId(15L);
        schemaRequest.setProviderGId("AAAQGayMdnmwj5IbY/O5ZaN/wdCoB8BcEbeT2CwCpHw=");
        schemaRequest.setVisible(1);
        schemaRequest.setUri("127.0.0.1");
        CommonResponse result =  schemaService.createDataSchema(schemaRequest);
        System.out.println(JSONUtil.toJsonStr(result));
    }



}

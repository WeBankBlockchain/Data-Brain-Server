package com.webank.databrain.service;

import cn.hutool.json.JSONUtil;
import com.webank.databrain.ServerApplicationTests;
import com.webank.databrain.model.resp.Paging;
import com.webank.databrain.vo.common.CommonResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
public class SchemaServiceTest  extends ServerApplicationTests {

    @Autowired
    private DataSchemaService schemaService;


    @Test
    void schemaQueryTest() throws Exception {
        CommonResponse result =  schemaService.pageQuerySchema(new Paging(1,1), 0L,0L,0L,"数据");
        System.out.println(JSONUtil.toJsonStr(result));

    }
}

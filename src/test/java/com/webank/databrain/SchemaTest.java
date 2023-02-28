package com.webank.databrain;

import cn.hutool.json.JSONUtil;
import com.webank.databrain.enums.AccountType;
import com.webank.databrain.model.account.RegisterRequestVO;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.dataschema.DataSchemaDetail;
import com.webank.databrain.model.dataschema.DataSchemaDetailWithVisit;
import com.webank.databrain.service.AccountService;
import com.webank.databrain.service.DataSchemaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SchemaTest extends ServerApplicationTests{


    @Autowired
    private AccountService accountService;

    @Autowired
    private DataSchemaService schemaService;


    @Test
    void schemaTest() throws Exception {
        PagingResult<DataSchemaDetail> result =  schemaService.pageQuerySchema(new Paging(1,1), null,null,0,null);
        System.out.println(JSONUtil.toJsonStr(result));

        DataSchemaDetailWithVisit visit = schemaService.getDataSchemaById("AAE5B5xx/WADdsUVkcPZlkdSCQJzdLfKf0u1jMMiZxM=");
        System.out.println(JSONUtil.toJsonStr(visit));
    }

}

package com.webank.databrain;

import com.webank.databrain.enums.AccountType;
import com.webank.databrain.model.account.RegisterRequestVO;
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
        RegisterRequestVO request = new RegisterRequestVO();
        request.setAccountType(AccountType.Enterprise);
        request.setPassword("123456");
        request.setUsername("test1");
        request.setDetailJson("{\"name\" : \"user\"}");
        String userId = accountService.registerAccount(request);

        accountService.auditAccount("test1",true);


    }

}

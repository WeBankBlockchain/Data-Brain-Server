package com.webank.databrain.service;

import cn.hutool.json.JSONUtil;
import com.webank.databrain.ServerApplicationTests;
import com.webank.databrain.enums.AccountType;
import com.webank.databrain.model.resp.Paging;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.account.ApproveAccountRequest;
import com.webank.databrain.vo.request.account.CompanyDetailRequest;
import com.webank.databrain.vo.request.account.RegisterRequest;
import com.webank.databrain.vo.request.dataschema.CreateDataSchemaRequest;
import com.webank.databrain.vo.request.product.ApproveProductRequest;
import com.webank.databrain.vo.request.product.CreateProductRequest;
import com.webank.databrain.vo.response.account.RegisterResponse;
import org.checkerframework.checker.units.qual.C;
import org.fisco.bcos.sdk.v3.transaction.tools.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class SchemaServiceTest  extends ServerApplicationTests {

    @Autowired
    private DataSchemaService schemaService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;


    @Test
    void schemaQueryTest() throws Exception {
        CommonResponse result =  schemaService.pageQuerySchema(new Paging(1,1), 0L,0L,0L,"");
        System.out.println(JSONUtil.toJsonStr(result));
    }

    @Test
    void createSchemaTest() throws Exception {
        String username = "companyUser00004";
        String password = "12345678";
        RegisterRequest request = new RegisterRequest();
        request.setUserName(username);
        request.setPassword(password);
        request.setAccountType(AccountType.Company);
        CompanyDetailRequest companyDetail = new CompanyDetailRequest();
        companyDetail.setCompanyName("头条3");
        companyDetail.setCompanyDesc("某公司1");
        companyDetail.setCertNo("123456");
        request.setDetailJson(JsonUtils.toJson(companyDetail));
        CommonResponse<RegisterResponse> result = accountService.registerAccount(request);
        System.out.println(JsonUtils.toJson(result));
        String did = result.getData().getDid();

        ApproveAccountRequest approveAccountRequest = new ApproveAccountRequest();
        approveAccountRequest.setApproved(true);
        approveAccountRequest.setDid(did);
        accountService.approveAccount(approveAccountRequest);

        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName("华为P6000");
        createProductRequest.setProductDesc("华为P6000手机..");
        createProductRequest.setDid(did);
        CommonResponse response = productService.createProduct(createProductRequest);
        System.out.println("response = " + response);

        String productGid = (String) response.getData();
        ApproveProductRequest approveProductRequest = new ApproveProductRequest();
        approveProductRequest.setProductGId(productGid);
        approveProductRequest.setAgree(true);
        approveProductRequest.setDid(did);
        response = productService.approveProduct(approveProductRequest);
        System.out.println("response = " + response);


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
        schemaRequest.setProductGId(productGid);
        schemaRequest.setProviderId(15L);
        schemaRequest.setProviderGId(did);
        schemaRequest.setVisible(1);
        schemaRequest.setUri("127.0.0.1");
        result =  schemaService.createDataSchema(schemaRequest);
        System.out.println(JSONUtil.toJsonStr(result));
    }



}

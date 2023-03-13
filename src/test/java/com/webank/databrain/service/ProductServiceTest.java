package com.webank.databrain.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.webank.databrain.ServerApplicationTests;
import com.webank.databrain.enums.AccountType;
import com.webank.databrain.vo.request.account.ApproveAccountRequest;
import com.webank.databrain.vo.request.account.CompanyDetailRequest;
import com.webank.databrain.vo.request.account.PersonalDetailRequest;
import com.webank.databrain.vo.request.account.RegisterRequest;
import com.webank.databrain.vo.request.product.ApproveProductRequest;
import com.webank.databrain.vo.request.product.CreateProductRequest;
import com.webank.databrain.model.resp.Paging;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.response.account.RegisterResponse;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionException;
import org.fisco.bcos.sdk.v3.transaction.tools.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceTest  extends ServerApplicationTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;


    @Test
    void productQueryTest() throws Exception {
        CommonResponse result =  productService.pageQueryProducts(new Paging(1,10));
        System.out.println(JSONUtil.toJsonStr(result));
    }


    @Test
    void productHotTest() throws Exception {
        CommonResponse result =  productService.getHotProducts(10);
        System.out.println(JSONUtil.toJsonStr(result));
    }

    @Test
    void createProductTest() throws Exception {
        String username = "companyUser"+ RandomUtil.randomNumbers(5);
        String password = "12345678";
        RegisterRequest request = new RegisterRequest();
        request.setUserName(username);
        request.setPassword(password);
        request.setAccountType(AccountType.Company);
        CompanyDetailRequest companyDetail = new CompanyDetailRequest();
        companyDetail.setCompanyName("腾讯" + RandomUtil.randomNumbers(5));
        companyDetail.setCompanyDesc("某公司2");
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
        createProductRequest.setProductDesc("华为P600手机..." + RandomUtil.randomNumbers(5));
        createProductRequest.setDid(did);
        CommonResponse response = productService.createProduct(createProductRequest);
        System.out.println("response = " + response);

        ApproveProductRequest approveProductRequest = new ApproveProductRequest();
        approveProductRequest.setProductGId((String) response.getData());
        approveProductRequest.setAgree(true);
        approveProductRequest.setDid(did);
        response = productService.approveProduct(approveProductRequest);
        System.out.println("response = " + response);
    }


    @Test
    void getProductDetailTest() throws TransactionException {
        CommonResponse response = productService.getProductDetail("AAAQGayMdnmwj5IbY/O5ZaN/wdCoB8BcEbeT2CwCpHwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAg==");
        System.out.println("response = " + response);
    }


}

package com.webank.databrain.service;

import com.webank.databrain.ServerApplicationTests;
import com.webank.databrain.model.req.tags.CreateTagRequest;
import com.webank.databrain.model.resp.tags.HotTagsResponse;
import org.fisco.bcos.sdk.v3.transaction.tools.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TagServiceTest extends ServerApplicationTests {

    @Autowired
    private TagService service;


    @Test
    public void testInsertTags() throws Exception{

        Object response = service.createTag(new CreateTagRequest("公积金"));
        System.out.println(JsonUtils.toJson(response));
        response = service.createTag(new CreateTagRequest("社保"));
        System.out.println(JsonUtils.toJson(response));
        response = service.createTag(new CreateTagRequest("理财"));
        System.out.println(JsonUtils.toJson(response));
        response = service.createTag(new CreateTagRequest("汽车消费"));
        System.out.println(JsonUtils.toJson(response));
        response = service.createTag(new CreateTagRequest("企业贷款"));
        System.out.println(JsonUtils.toJson(response));
        response = service.createTag(new CreateTagRequest("个人运动"));
        System.out.println(JsonUtils.toJson(response));
        response = service.createTag(new CreateTagRequest("税务"));
        System.out.println(JsonUtils.toJson(response));
        response = service.createTag(new CreateTagRequest("交通"));
        System.out.println(JsonUtils.toJson(response));
        response = service.createTag(new CreateTagRequest("教育"));
        System.out.println(JsonUtils.toJson(response));
        response = service.createTag(new CreateTagRequest("房产交易"));
        System.out.println(JsonUtils.toJson(response));
        response = service.createTag(new CreateTagRequest("民政数据"));
        System.out.println(JsonUtils.toJson(response));
        response = service.createTag(new CreateTagRequest("消费贷款"));
        System.out.println(JsonUtils.toJson(response));
    }

    @Test
    public void testListHotTags() throws Exception{
        Object response = service.listHotTags(2);
        System.out.println(JsonUtils.toJson(response));
    }

}

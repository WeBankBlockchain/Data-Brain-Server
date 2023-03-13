package com.webank.databrain.controller;

import com.webank.databrain.vo.request.tags.HotTagsRequest;
import com.webank.databrain.model.resp.CommonResponse;
import com.webank.databrain.vo.response.tags.HotTagsResponse;
import com.webank.databrain.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("getHotTags")
    public CommonResponse<HotTagsResponse> getHotTags(@RequestBody HotTagsRequest request) {
        return tagService.listHotTags(request.getTopN());
    }
}

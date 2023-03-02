package com.webank.databrain.controller;

import com.webank.databrain.model.common.CommonResponse;
import com.webank.databrain.model.common.HotQueryRequest;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("getHotTags")
    public CommonResponse getHotTags(@RequestBody HotQueryRequest request) {
        List<IdName> hotTags = tagService.listHotTags(request.getTopN());
        return CommonResponse.createSuccessResult(hotTags);
    }
}

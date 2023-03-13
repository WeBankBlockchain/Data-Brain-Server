package com.webank.databrain.controller;

import com.webank.databrain.service.TagService;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.common.HotDataRequest;
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
    public CommonResponse getHotTags(@RequestBody HotDataRequest request) {
        return tagService.listHotTags(request);
    }
}

package com.webank.databrain.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/tag")
public class TagController {

//    @Autowired
//    private TagService tagService;
//
//    @PostMapping("getHotTags")
//    public CommonResponse<HotTagsResponse> getHotTags(@RequestBody HotTagsRequest request) {
//        HotTagsResponse hotTags = tagService.listHotTags(request.getTopN());
//        return CommonResponse.success(hotTags);
//    }
}

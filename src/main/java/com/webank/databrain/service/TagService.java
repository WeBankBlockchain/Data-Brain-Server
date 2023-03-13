package com.webank.databrain.service;


import com.webank.databrain.dao.db.entity.TagInfoEntity;
import com.webank.databrain.dao.db.mapper.TagInfoMapper;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.tags.CreateTagRequest;
import com.webank.databrain.vo.response.tag.TagIdAndNameResponse;
import com.webank.databrain.vo.response.tags.CreateTagResponse;
import com.webank.databrain.vo.response.tags.HotTagsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagInfoMapper tagInfoMapper;

    public CommonResponse<HotTagsResponse> listHotTags(int topN) {

        List<TagInfoEntity> tags = tagInfoMapper.queryHotTags(topN);

        List<TagIdAndNameResponse> idNames = tags.stream().map(t->{
            TagIdAndNameResponse idName = new TagIdAndNameResponse();
            idName.setTagId(String.valueOf(t.getPkId()));
            idName.setTagName(t.getTagName());
            return idName;
        }).collect(Collectors.toList());
        return CommonResponse.success(new HotTagsResponse(idNames));
    }

    public CommonResponse<CreateTagResponse> createTag(CreateTagRequest createTagRequest){
        TagInfoEntity tagPO = new TagInfoEntity();
        tagPO.setTagName(createTagRequest.getTagName());
        tagInfoMapper.insertItem(tagPO);

        return CommonResponse.success(new CreateTagResponse(tagPO.getPkId().longValue()));
    }

}

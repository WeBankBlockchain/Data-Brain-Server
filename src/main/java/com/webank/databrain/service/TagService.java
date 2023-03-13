package com.webank.databrain.service;


import com.webank.databrain.dao.entity.TagInfoEntity;
import com.webank.databrain.dao.mapper.TagInfoMapper;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.common.HotDataRequest;
import com.webank.databrain.vo.request.tags.CreateTagRequest;
import com.webank.databrain.vo.response.tags.CreateTagResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagInfoMapper tagInfoMapper;

    public CommonResponse listHotTags(HotDataRequest request) {

        List<TagInfoEntity> tags = tagInfoMapper.queryHotTags(request.getTopCount());
        return CommonResponse.success(tags);
    }

    public CommonResponse<CreateTagResponse> createTag(CreateTagRequest createTagRequest){
        TagInfoEntity tagPO = new TagInfoEntity();
        tagPO.setTagName(createTagRequest.getTagName());
        tagInfoMapper.insertItem(tagPO);

        return CommonResponse.success(new CreateTagResponse(tagPO.getPkId().longValue()));
    }

}

package com.webank.databrain.service;

//import com.webank.databrain.db.dao.ITagDbService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webank.databrain.db.dao.TagInfoDAO;
import com.webank.databrain.dao.db.entity.TagInfoEntity;
import com.webank.databrain.model.req.tags.CreateTagRequest;
import com.webank.databrain.model.resp.CommonResponse;
import com.webank.databrain.model.resp.IdName;
import com.webank.databrain.model.resp.tags.CreateTagResponse;
import com.webank.databrain.model.resp.tags.HotTagsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagInfoDAO tagInfoDAO;

    public CommonResponse<HotTagsResponse> listHotTags(int topN) {

        List<TagInfoEntity> tags = tagInfoDAO.queryHotTags(topN);

        List<IdName> idNames = tags.stream().map(t->{
            IdName idName = new IdName();
            idName.setId(String.valueOf(t.getPkId()));
            idName.setName(t.getTagName());
            return idName;
        }).collect(Collectors.toList());
        return CommonResponse.success(new HotTagsResponse(idNames));
    }

    public CommonResponse<CreateTagResponse> createTag(CreateTagRequest createTagRequest){
        TagInfoEntity tagPO = new TagInfoEntity();
        tagPO.setTagName(createTagRequest.getTagName());
        tagInfoDAO.saveItem(tagPO);

        return CommonResponse.success(new CreateTagResponse(tagPO.getPkId().longValue()));
    }

}

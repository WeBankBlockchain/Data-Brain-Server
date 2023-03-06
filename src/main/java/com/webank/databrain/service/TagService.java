package com.webank.databrain.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.webank.databrain.db.dao.ITagDbService;
import com.webank.databrain.db.entity.TagDataObject;
import com.webank.databrain.model.vo.common.IdName;
import com.webank.databrain.model.vo.common.Paging;
import com.webank.databrain.model.tag.CreateTagRequest;
import com.webank.databrain.model.tag.TagDetail;
import com.webank.databrain.model.tag.TagSummary;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private ITagDbService tagDbService;

    public List<IdName> listHotTags(int topN) {
        return tagDbService.listHotTags(topN);
    }

    public PagingResult<TagSummary> listAllTags(Paging paging) {
        return tagDbService.listTagsByPage(paging);
    }

    public TagDetail getTagByName(String tag){
        TagDataObject tagDataObject = tagDbService.getOne(Wrappers.<TagDataObject>query().eq("tag",tag));
        if(tagDataObject == null){
            return null;
        }
        TagDetail tagDetail = new TagDetail();
        BeanUtils.copyProperties(tagDataObject,tagDetail);
        tagDetail.setTagId(tagDataObject.getPkId());
        return tagDetail;
    }

    public void createTag(CreateTagRequest createTagRequest){
        TagDataObject tagDataObject = new TagDataObject();
        tagDataObject.setCreateTime(LocalDateTime.now());
        tagDataObject.setUpdateTime(LocalDateTime.now());
        tagDataObject.setTag(createTagRequest.getTag());
        tagDataObject.setSchemaIdList(createTagRequest.getSchemaId());
        tagDbService.save(tagDataObject);
    }

    public void updateTag(TagDetail tagDetail) {
        TagDataObject tagDataObject = new TagDataObject();
        BeanUtils.copyProperties(tagDetail,tagDataObject);
        tagDataObject.setUpdateTime(LocalDateTime.now());
        tagDbService.saveOrUpdate(tagDataObject);
    }
}

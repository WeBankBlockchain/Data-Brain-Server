package com.webank.databrain.service;

//import com.webank.databrain.db.dao.ITagDbService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webank.databrain.db.dao.TagInfoDAO;
import com.webank.databrain.dao.db.entity.TagInfoEntity;
import com.webank.databrain.model.req.tags.CreateTagRequest;
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

    public HotTagsResponse listHotTags(int topN) {
        IPage<TagInfoEntity> paged = tagInfoDAO.page(new Page(1, topN), Wrappers.<TagInfoEntity>query().select("pk_id ", "tag_name"));
        List<TagInfoEntity> items =  paged.getRecords();

        List<IdName> idNames = items.stream().map(t->{
            IdName idName = new IdName();
            idName.setId(String.valueOf(t.getPkId()));
            idName.setName(t.getTagName());
            return idName;
        }).collect(Collectors.toList());
        return new HotTagsResponse(idNames);
    }

//    public PagingResult<TagSummary> listAllTags(Paging paging) {
//        return tagDbService.listTagsByPage(paging);
//    }
//
//    public TagDetail getTagByName(String tag){
//        TagDataObject tagDataObject = tagDbService.getOne(Wrappers.<TagDataObject>query().eq("tag",tag));
//        if(tagDataObject == null){
//            return null;
//        }
//        TagDetail tagDetail = new TagDetail();
//        BeanUtils.copyProperties(tagDataObject,tagDetail);
//        tagDetail.setTagId(tagDataObject.getPkId());
//        return tagDetail;
//    }
//
    public CreateTagResponse createTag(CreateTagRequest createTagRequest){
        TagInfoEntity tagPO = new TagInfoEntity();
        tagPO.setTagName(createTagRequest.getTagName());
        tagInfoDAO.save(tagPO);

        return new CreateTagResponse(tagPO.getPkId().longValue());
    }
//
//    public void updateTag(TagDetail tagDetail) {
//        TagDataObject tagDataObject = new TagDataObject();
//        BeanUtils.copyProperties(tagDetail,tagDataObject);
//        tagDataObject.setUpdateTime(LocalDateTime.now());
//        tagDbService.saveOrUpdate(tagDataObject);
//    }
}

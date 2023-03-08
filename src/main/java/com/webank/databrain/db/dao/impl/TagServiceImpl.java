package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.ITagDbService;
import com.webank.databrain.db.entity.TagDataObject;
import com.webank.databrain.db.mapper.TagMapper;
import com.webank.databrain.model.dto.common.IdName;
import com.webank.databrain.model.dto.common.Paging;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, TagDataObject> implements ITagDbService {
    @PostConstruct
    public void init(){
        baseMapper.createTable();
    }


    @Override
    public List<IdName> listHotTags(int topN) {
        return baseMapper.listHotTags(topN);
    }

//    @Override
//    public PagingResult<TagSummary> listTagsByPage(Paging paging) {
//        PagingResult<TagSummary> result = new PagingResult();
//        int totalCount = baseMapper.count();
//
//        int pageNo = paging.getPageNo();
//        int pageSize = paging.getPageSize();
//        int startOffset = (pageNo - 1) * pageSize;
//        List<IdName> idNames = baseMapper.listTagsByPage(startOffset, pageSize);
//
//        int totalPages = (totalCount + pageSize - 1) / pageSize;
//
//        result.setTotalPages(totalPages);
//        result.setTotalItems(totalCount);
//        result.setPage(pageNo);
//        result.setPageSize(pageSize);
//        result.setItems(idNames.stream().map(idName -> {
//            TagSummary t = new TagSummary();
//            BeanUtils.copyProperties(idName, t);
//            return t;
//        }).collect(Collectors.toList()));
//
//        return result;
//    }
}

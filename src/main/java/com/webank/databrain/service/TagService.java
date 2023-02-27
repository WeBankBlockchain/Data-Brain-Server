package com.webank.databrain.service;

import com.webank.databrain.db.dao.ITagDbService;
import com.webank.databrain.model.common.IdName;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.tag.TagSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

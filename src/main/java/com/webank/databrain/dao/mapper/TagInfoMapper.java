package com.webank.databrain.dao.mapper;

import com.webank.databrain.dao.entity.TagInfoEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagInfoMapper {

    @Insert("INSERT INTO t_tag_info (tag_name) VALUES(#{tagName})")
    @Options(useGeneratedKeys = true, keyProperty = "pkId")
    void insertItem(TagInfoEntity tagPO);

    @Select("SELECT * FROM t_tag_info ORDER BY pk_id DESC LIMIT #{topN}")
    @ResultType(TagInfoEntity.class)
    List<TagInfoEntity> queryHotTags(int topN);
}

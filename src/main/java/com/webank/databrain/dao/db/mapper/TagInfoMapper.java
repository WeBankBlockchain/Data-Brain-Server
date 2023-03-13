package com.webank.databrain.dao.db.mapper;

import com.webank.databrain.dao.db.entity.TagInfoEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface TagInfoMapper {

    @Insert("INSERT INTO t_tag_info (tag_name) VALUES(#{tagName})")
    @Options(useGeneratedKeys = true, keyProperty = "pkId")
    void insertItem(TagInfoEntity tagPO);

    @Select("SELECT * FROM t_tag_info ORDER BY pk_id DESC LIMIT #{topN}")
    @ResultType(TagInfoEntity.class)
    List<TagInfoEntity> queryHotTags(int topN);
}

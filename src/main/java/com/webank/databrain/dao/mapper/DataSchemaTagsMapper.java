package com.webank.databrain.dao.mapper;

import com.webank.databrain.dao.entity.DataSchemaTagsEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface DataSchemaTagsMapper {

    @Insert("INSERT INTO t_data_schema_tags(" +
            "data_schema_id," +
            "tag_id" +
            ") " +
            "VALUES(" +
            "#{dataSchemaId}, " +
            "#{tagId}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "pkId", keyColumn = "pk_id")
    void insertDataSchemaTag(DataSchemaTagsEntity dataSchemaTagsEntity);

}

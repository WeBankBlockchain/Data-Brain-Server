package com.webank.databrain.dao.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webank.databrain.dao.db.entity.DataSchemaTagsEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface DataSchemaTagsMapper extends BaseMapper<DataSchemaTagsEntity> {

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

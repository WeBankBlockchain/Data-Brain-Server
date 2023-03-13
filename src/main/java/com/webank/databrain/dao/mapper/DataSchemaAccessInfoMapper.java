package com.webank.databrain.dao.mapper;

import com.webank.databrain.dao.entity.DataSchemaAccessInfoEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface DataSchemaAccessInfoMapper {

    @Insert("INSERT INTO t_data_schema_access_info(" +
            "data_schema_id," +
            "data_format," +
            "data_protocol, " +
            "content_schema," +
            "access_condition," +
            "uri," +
            "effect_time," +
            "expire_time," +
            "create_time" +
            ") " +
            "VALUES(" +
            "#{dataSchemaId}, " +
            "#{dataFormat}, " +
            "#{dataProtocol}," +
            "#{contentSchema}," +
            "#{accessCondition}," +
            "#{uri}," +
            "#{effectTime}," +
            "#{expireTime}," +
            "#{createTime}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "pkId", keyColumn = "pk_id")
    void insertDataSchemaAccessInfo(DataSchemaAccessInfoEntity dataSchemaAccessInfoEntity);

}

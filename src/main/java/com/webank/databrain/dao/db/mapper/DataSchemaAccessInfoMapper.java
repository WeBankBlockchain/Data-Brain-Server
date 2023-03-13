package com.webank.databrain.dao.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webank.databrain.dao.db.entity.DataSchemaAccessInfoEntity;
import com.webank.databrain.dao.db.entity.DataSchemaInfoEntity;
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
public interface DataSchemaAccessInfoMapper extends BaseMapper<DataSchemaAccessInfoEntity> {

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

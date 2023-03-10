package com.webank.databrain.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webank.databrain.model.bo.DataSchemaInfoBO;
import com.webank.databrain.model.bo.ProductInfoBO;
import com.webank.databrain.model.po.DataSchemaInfoPO;
import com.webank.databrain.model.po.ProductInfoPO;
import com.webank.databrain.model.resp.IdName;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface DataSchemaInfoMapper extends BaseMapper<DataSchemaInfoPO> {


    @Select("SELECT " +
            "a.data_schema_gid," +
            "a.data_schema_name," +
            "a.provider_id, " +
            "a.product_id," +
            "a.version," +
            "a.visible," +
            "a.data_schema_desc," +
            "a.data_schema_usage," +
            "a.price," +
            "a.create_time," +
            "b.tag_id," +
            "c.tag_name," +
            "d.product_name," +
            "d.product_gid as productGId," +
            "e.company_name as providerName," +
            "f.did as providerGId" +
            " from t_data_schema_info a " +
            "left join t_data_schema_tags b on a.pk_id = b.data_schema_id " +
            "left join t_tag_info c on b.tag_id = c.pk_id" +
            "left join t_product_info d on a.product_id = d.pk_id" +
            "left join t_company_info e on a.provider_id = e.pk_id" +
            "left join t_account_info f on e.account_id = f.pk_id" +
            "where 1 =1  " +
            "<if test='productId != null'> AND a.product_id = #{productId} </if>" +
            "<if test='providerId != null'> AND a.provider_id = #{providerId} </if>" +
            "<if test='tagId != null'> AND b.tag_id = #{tagId} </if>" +
            "<if test='keyWord != null'> AND a.data_schema_name like concat('%', #{keyWord}, '%') " +
            "or a.data_schema_desc like concat('%', #{keyWord}, '%') </if>" +
            "ORDER BY a.create_time DESC LIMIT #{start}, #{pageSize} " )
    @ResultType(DataSchemaInfoBO.class)
    List<DataSchemaInfoBO> pageQuerySchema(@Param("start") long start,
                                           @Param("pageSize")int pageSize,
                                           @Param("productId") Long productId,
                                           @Param("providerId") Long providerId,
                                           @Param("tagId") Long tagId,
                                           @Param("keyWord") String keyWord);

    @Select("select * from t_data_schema_info where data_schema_gid = #{schemaId}")
    @ResultType(DataSchemaInfoPO.class)
    DataSchemaInfoPO getSchemaByGId(String schemaId);


    @Insert("INSERT INTO t_data_schema_info(" +
            "data_schema_gid," +
            "data_schema_name," +
            "provider_id, " +
            "product_id," +
            "version," +
            "visible," +
            "data_schema_desc," +
            "data_schema_usage," +
            "price," +
            "create_time" +
            ") " +
            "VALUES(" +
            "#{dataSchemaGid}, " +
            "#{dataSchemaName}, " +
            "#{providerId}," +
            "#{productId}," +
            "#{version}," +
            "#{visible}," +
            "#{dataSchemaDesc}," +
            "#{dataSchemaUsage}," +
            "#{price}," +
            "#{updateTime}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "pkId", keyColumn = "pk_id")
    void insertDataSchemaInfo(DataSchemaInfoPO dataSchemaInfoPO);


    @Update("UPDATE t_data_schema_info SET " +
            "data_schema_name=#{dataSchemaName}, " +
            "visible=#{visible}, " +
            "data_schema_desc=#{dataSchemaDesc}, " +
            "update_time=#{updateTime} " +
            "WHERE pk_id=#{pkId}")
    void updateDataSchemaInfo(DataSchemaInfoPO dataSchemaInfoPO);

}

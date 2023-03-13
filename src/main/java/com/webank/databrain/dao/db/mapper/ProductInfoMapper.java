package com.webank.databrain.dao.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webank.databrain.dao.db.entity.ProductInfoEntity;
import com.webank.databrain.vo.response.product.ProductIdAndName;
import com.webank.databrain.vo.response.product.ProductInfoBO;
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
public interface ProductInfoMapper extends BaseMapper<ProductInfoEntity> {

    @Select("SELECT a.pk_id as productId,a.product_gid, a.product_name,a.product_desc,a.status,a.review_time,a.create_time,b.did,c.company_name" +
            " FROM t_product_info a JOIN t_account_info b ON a.provider_id = b.pk_id" +
            " JOIN t_company_info c ON a.provider_id = c.account_id" +
            " ORDER BY a.create_time DESC LIMIT #{start}, #{pageSize}")
    @ResultType(ProductInfoBO.class)
    List<ProductInfoBO> pageQueryProduct(@Param("start") long start, @Param("pageSize")int pageSize);

    @Select("SELECT b.did as id, c.company_name as name FROM t_product_info a " +
            "JOIN t_account_info b ON a.provider_id = b.pk_id " +
            "JOIN t_company_info c ON a.provider_id = c.account_id " +
            "ORDER BY a.create_time DESC LIMIT 1, #{topN}")
    @ResultType(IdName.class)
    List<IdName> getHotProduct(@Param("topN") int topN);

    @Select("SELECT a.pk_id as productId, a.product_gid, a.product_name,a.product_desc,a.status,a.review_time,a.create_time,b.did,c.company_name" +
            " FROM t_product_info a JOIN t_account_info b ON a.provider_id = b.pk_id" +
            " JOIN t_company_info c ON a.provider_id = c.account_id" +
            " where a.product_gid = #{productId}")
    @ResultType(ProductInfoBO.class)
    ProductInfoBO getProductByGId(@Param("productId") String productId);


    @Select("SELECT pk_id as productId, product_gid as id , product_name as name FROM t_product_info where pk_id IN (#{ids}) ")
    @ResultType(ProductIdAndName.class)
    List<ProductIdAndName> getProductNameByIds(@Param("ids") List<Long> ids);


    @Insert("INSERT INTO t_product_info(" +
            "product_gid," +
            "product_name," +
            "provider_id, " +
            "product_desc," +
            "status," +
            "review_time," +
            "create_time" +
            ") " +
            "VALUES(" +
            "#{productGid}, " +
            "#{productName}, " +
            "#{providerId}," +
            "#{productDesc}," +
            "#{status}," +
            "#{reviewTime}," +
            "#{createTime}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "pkId", keyColumn = "pk_id")
    void insertProductInfoPO(ProductInfoEntity productInfoEntity);


    @Update("UPDATE t_product_info SET " +
            "product_name=#{productName}, " +
            "review_time=#{reviewTime}, " +
            "status=#{status}, " +
            "update_time=#{updateTime} " +
            "WHERE pk_id=#{pkId}")
    void updateProductInfo(ProductInfoEntity productInfoEntity);

}

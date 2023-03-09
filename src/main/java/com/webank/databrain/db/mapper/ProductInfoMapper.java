package com.webank.databrain.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webank.databrain.model.bo.ProductInfoBO;
import com.webank.databrain.model.po.ProductInfoPO;
import com.webank.databrain.model.resp.IdName;
import org.apache.ibatis.annotations.Param;
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
public interface ProductInfoMapper extends BaseMapper<ProductInfoPO> {

    @Select("SELECT a.pk_id as productId,a.product_gid, a.product_name,a.product_desc,a.status,a.review_time,a.create_time,b.did,c.company_name" +
            " FROM t_product_info a JOIN t_account_info b ON a.provider_id = b.pk_id" +
            " JOIN t_company_info c ON a.provider_id = c.account_id" +
            " ORDER BY a.create_time DESC LIMIT #{start}, #{pageSize}")
    @ResultType(ProductInfoBO.class)
    List<ProductInfoBO> pageQueryProduct(@Param("start") long start, @Param("pageSize")int pageSize);

    @Select("SELECT b.did as id, c.company_name as name FROM t_product_info a " +
            "JOIN t_account_info b ON a.provider_id = b.pk_id " +
            "JOIN t_company_info c ON ON a.provider_id = c.account_id " +
            "ORDER BY a.create_time DESC LIMIT 1, #{topN}")
    @ResultType(IdName.class)
    List<IdName> getHotProduct(int topN);

    @Select("SELECT a.pk_id as productId, a.product_gid, a.product_name,a.product_desc,a.status,a.review_time,a.create_time,b.did,c.company_name" +
            " FROM t_product_info a JOIN t_account_info b ON a.provider_id = b.pk_id" +
            " JOIN t_company_info c ON a.provider_id = c.account_id" +
            " where a.product_gid = #{productId}")
    @ResultType(ProductInfoBO.class)
    ProductInfoBO getProductByGId(String productId);

}

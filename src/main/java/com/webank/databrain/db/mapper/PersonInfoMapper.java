package com.webank.databrain.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webank.databrain.model.bo.PersonInfoBO;
import com.webank.databrain.model.po.PersonInfoPO;
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
public interface PersonInfoMapper extends BaseMapper<PersonInfoPO> {

    @Select("SELECT a.*, p.* FROM t_person_info p INNER  JOIN t_account_info a ON p.account_id = a.pk_id WHERE a.user_name=#{username}")
    @ResultType(PersonInfoBO.class)
    PersonInfoBO queryPersonByUsername(@Param("username") String username);

    @Select("SELECT a.*, p.* FROM t_person_info p INNER  JOIN t_account_info a ON p.account_id = a.pk_id WHERE a.status=#{status} ORDER BY p.pk_id DESC LIMIT #{start}, #{limit}")
    @ResultType(PersonInfoBO.class)
    List<PersonInfoBO> listPersonWithStatus(int status, long start, int limit);

}

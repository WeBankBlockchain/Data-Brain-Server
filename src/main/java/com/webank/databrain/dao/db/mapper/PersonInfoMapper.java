package com.webank.databrain.dao.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webank.databrain.model.bo.PersonInfoBO;
import com.webank.databrain.dao.db.entity.PersonInfoEntity;
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
public interface PersonInfoMapper extends BaseMapper<PersonInfoEntity> {

    @Select("SELECT a.*, p.* FROM t_person_info p INNER  JOIN t_account_info a ON p.account_id = a.pk_id WHERE a.user_name=#{username}")
    @ResultType(PersonInfoBO.class)
    PersonInfoBO queryPersonByUsername(@Param("username") String username);

    @Select("SELECT a.*, p.* FROM t_person_info p INNER  JOIN t_account_info a ON p.account_id = a.pk_id WHERE a.status=#{status} ORDER BY p.pk_id DESC LIMIT #{start}, #{limit}")
    @ResultType(PersonInfoBO.class)
    List<PersonInfoBO> listPersonWithStatus(int status, long start, int limit);

    @Insert("INSERT INTO t_person_info (account_id, person_name, person_contact, person_email, person_cert_type, person_cert_no) VALUES(#{accountId},#{personName},#{personContact},#{personEmail},#{personCertType},#{personCertNo})")
    @Options(useGeneratedKeys=true, keyProperty="pkId")
    void insertItem(PersonInfoEntity personInfoPo);

    @Select("SELECT COUNT(1) FROM t_person_info p INNER  JOIN t_account_info a ON p.account_id = a.pk_id WHERE a.status=#{status}")
    int totalCountWithStatus(int status);
}

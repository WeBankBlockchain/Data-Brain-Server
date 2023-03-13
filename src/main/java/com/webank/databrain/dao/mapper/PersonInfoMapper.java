package com.webank.databrain.dao.mapper;

import com.webank.databrain.bo.AccAndPersonInfoBO;
import com.webank.databrain.dao.entity.PersonInfoEntity;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface PersonInfoMapper {

    @Select("SELECT a.*, p.* FROM t_person_info p INNER  JOIN t_account_info a ON p.account_id = a.pk_id WHERE a.user_name=#{username}")
    @ResultType(AccAndPersonInfoBO.class)
    AccAndPersonInfoBO queryPersonByUsername(@Param("username") String username);

    @Select("SELECT a.*, p.* FROM t_person_info p INNER  JOIN t_account_info a ON p.account_id = a.pk_id WHERE a.status=#{status} ORDER BY p.pk_id DESC LIMIT #{start}, #{limit}")
    @ResultType(AccAndPersonInfoBO.class)
    List<AccAndPersonInfoBO> listPersonWithStatus(int status, long start, int limit);

    @Insert("INSERT INTO t_person_info (account_id, person_name, person_contact, person_email, person_cert_type, person_cert_no) VALUES(#{accountId},#{personName},#{personContact},#{personEmail},#{personCertType},#{personCertNo})")
    void insertPerson(PersonInfoEntity entity);

    @Select("SELECT COUNT(1) FROM t_person_info p INNER  JOIN t_account_info a ON p.account_id = a.pk_id WHERE a.status=#{status}")
    int totalCountWithStatus(int status);
}

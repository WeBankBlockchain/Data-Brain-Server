package com.webank.databrain.dao.db.mapper;

import com.webank.databrain.dao.db.entity.AccountInfoEntity;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface AccountInfoMapper {

    @Update("UPDATE t_account_info SET status=#{status} WHERE did=#{did}")
    void updateStatus(String did, int status);

    @Insert("INSERT INTO t_account_info (user_name, did, account_type, private_key, salt, pwd_hash, status) values(#{userName}, #{did}, #{accountType}, #{privateKey}, #{salt}, #{pwdHash}, #{status})")
    @Options(useGeneratedKeys=true, keyProperty="pkId", keyColumn="pk_id")
    int insertAccount(AccountInfoEntity entity);

    @Select("SELECT * FROM t_account_info WHERE did=#{did}")
    AccountInfoEntity selectByDid(String did);

    @Select("SELECT * FROM t_account_info WHERE user_name=#{userName}")
    @ResultType(AccountInfoEntity.class)
    AccountInfoEntity selectByUserName(String userName);
}

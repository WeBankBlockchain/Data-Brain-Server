package com.webank.databrain.dao.db.mapper;

import com.webank.databrain.dao.db.entity.AccountEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {

    @Insert("INSERT INTO t_account_info(user_name, did, account_type, private_key, salt, pwd_hash, status) " +
            "VALUES(#{userName}, #{did}, #{accountType}, #{privateKey}, #{salt}, #{pwdHash}, #{status})")
    void insertAccount(AccountEntity userInfo);

    @Select("SELECT * FROM t_account_info WHERE user_name = #{userName}")
    AccountEntity getAccountByUserName(String userName);
}

package com.webank.databrain.dao.mapper;

import com.webank.databrain.dao.entity.SessionInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SessionInfoMapper {

    @Update("INSERT INTO t_session_info(`token`,`account_id`, `expired_at`) VALUES(#{token},#{accountId}, #{expiredAt})" +
            "ON DUPLICATE KEY UPDATE token=#{token}, account_id=#{accountId}, expired_at=#{expiredAt}")
    void replace(SessionInfoEntity dataObject);
}

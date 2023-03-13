package com.webank.databrain.dao.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webank.databrain.model.po.SessionInfoPO;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
//MyBatisPlus的saveOrUpdate极为难用，所以自己写一个
public interface SessionInfoMapper extends BaseMapper<SessionInfoPO> {

//    @Update("INSERT INTO t_session_info(`token`,`account_id`, `expired_at`) VALUES(#{token},#{accountId}, #{expiredAt})" +
//            "ON DUPPLICATE KEY UPDATE SET token=#{token}, account_id=#{accountId}, expired_at=#{expiredAt}")
//    void replace(@Param("token") String token, @Param("accountId") long accountId, @Param("expiredAt")LocalDateTime expiredAt);

    @Update("INSERT INTO t_session_info(`token`,`account_id`, `expired_at`) VALUES(#{token},#{accountId}, #{expiredAt})" +
            "ON DUPLICATE KEY UPDATE token=#{token}, account_id=#{accountId}, expired_at=#{expiredAt}")
    void replace(SessionInfoPO dataObject);
}

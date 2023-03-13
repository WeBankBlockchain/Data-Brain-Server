package com.webank.databrain.dao.db.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.webank.databrain.dao.db.entity.AccountInfoEntity;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface AccountInfoMapper extends MPJBaseMapper<AccountInfoEntity> {


    @Update("UPDATE t_account_info SET status=#{status} WHERE did=#{did}")
    void updateStatus(String did, int status);
}

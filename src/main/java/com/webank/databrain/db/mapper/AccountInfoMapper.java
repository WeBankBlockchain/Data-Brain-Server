package com.webank.databrain.db.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.webank.databrain.model.po.AccountInfoPO;
import com.webank.databrain.model.resp.IdName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface AccountInfoMapper extends MPJBaseMapper<AccountInfoPO> {


    @Update("UPDATE t_account_info SET status=#{status} WHERE did=#{did}")
    void updateStatus(String did, int status);
}

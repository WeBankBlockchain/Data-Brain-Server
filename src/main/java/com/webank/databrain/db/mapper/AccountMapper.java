package com.webank.databrain.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webank.databrain.db.entity.AccountDataObject;

import java.time.LocalDateTime;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
public interface AccountMapper extends BaseMapper<AccountDataObject> {

    void createTable();

    AccountDataObject selectByName(String username);

    AccountDataObject selectByDid(String did);


    void updateReviewStatus(String did, int status, LocalDateTime reviewTime);

}

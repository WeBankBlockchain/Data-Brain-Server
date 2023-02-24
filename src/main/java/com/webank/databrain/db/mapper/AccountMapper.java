package com.webank.databrain.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webank.databrain.db.entity.AccountDataObject;
import com.webank.databrain.model.common.IdName;

import java.util.List;

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




}

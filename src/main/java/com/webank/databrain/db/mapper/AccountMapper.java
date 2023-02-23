package com.webank.databrain.db.mapper;

import com.webank.databrain.db.entity.AccountDataObject;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
public interface AccountMapper {

    void createTable();
//
//    @Insert("INSERT INTO ")
    void insert(AccountDataObject accountDataObject);
}

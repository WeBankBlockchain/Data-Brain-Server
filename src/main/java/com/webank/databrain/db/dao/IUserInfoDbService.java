package com.webank.databrain.db.dao;

import com.webank.databrain.db.entity.UserInfoDataObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.model.dto.account.NormalUserDetail;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
public interface IUserInfoDbService extends IService<UserInfoDataObject> {
    public void insert(String did, NormalUserDetail normalUser) ;
}

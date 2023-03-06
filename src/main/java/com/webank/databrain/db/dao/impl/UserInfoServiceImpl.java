package com.webank.databrain.db.dao.impl;

import com.webank.databrain.db.entity.UserInfoDataObject;
import com.webank.databrain.db.mapper.UserInfoMapper;
import com.webank.databrain.db.dao.IUserInfoDbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.model.vo.account.NormalUserDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lt
 * @since 2023-02-22
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoDataObject> implements IUserInfoDbService {

    public void insert(String did, NormalUserDetail normalUser) {
        UserInfoDataObject dbo = new UserInfoDataObject();
        BeanUtils.copyProperties(normalUser,  dbo);
        dbo.setUserId(did);

        baseMapper.insert(dbo);
    }
}

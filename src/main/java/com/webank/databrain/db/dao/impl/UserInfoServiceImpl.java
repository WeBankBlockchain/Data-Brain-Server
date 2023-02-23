package com.webank.databrain.db.dao.impl;

import com.webank.databrain.db.entity.UserInfo;
import com.webank.databrain.db.mapper.UserInfoMapper;
import com.webank.databrain.db.dao.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}

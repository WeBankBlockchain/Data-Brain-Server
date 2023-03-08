package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.SessionInfoDAO;
import com.webank.databrain.db.entity.SessionInfoDataObject;
import com.webank.databrain.db.mapper.SessionInfoMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
@Service
public class SessionInfoDAOImpl extends ServiceImpl<SessionInfoMapper, SessionInfoDataObject> implements SessionInfoDAO {

}

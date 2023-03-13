package com.webank.databrain.dao.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.dao.db.dao.SessionInfoDAO;
import com.webank.databrain.model.po.SessionInfoPO;
import com.webank.databrain.dao.db.mapper.SessionInfoMapper;
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
public class SessionInfoDAOImpl extends ServiceImpl<SessionInfoMapper, SessionInfoPO> implements SessionInfoDAO {

    @Override
    public void replace(SessionInfoPO dataObject) {
        baseMapper.replace(dataObject);
    }
}

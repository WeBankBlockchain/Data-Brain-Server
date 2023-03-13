package com.webank.databrain.dao.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.model.po.SessionInfoPO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface SessionInfoDAO extends IService<SessionInfoPO> {
    void replace(SessionInfoPO dataObject);
}

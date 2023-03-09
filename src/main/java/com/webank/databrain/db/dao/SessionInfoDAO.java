package com.webank.databrain.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.model.po.SessionInfoDataObject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface SessionInfoDAO extends IService<SessionInfoDataObject> {
    void replace(SessionInfoDataObject dataObject);
}

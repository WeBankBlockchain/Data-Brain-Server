package com.webank.databrain.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.model.bo.PersonInfoBO;
import com.webank.databrain.dao.db.entity.PersonInfoEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface PersonInfoDAO extends IService<PersonInfoEntity> {

    PersonInfoBO queryPersonByUsername(String username);

    List<PersonInfoBO> listPersonWithStatus(int ordinal, int pageNo, int pageSize);
}

package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.PersonInfoDAO;
import com.webank.databrain.model.bo.PersonInfoBO;
import com.webank.databrain.model.po.PersonInfoPO;
import com.webank.databrain.db.mapper.PersonInfoMapper;
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
public class PersonInfoDAOImpl extends ServiceImpl<PersonInfoMapper, PersonInfoPO> implements PersonInfoDAO {

    @Override
    public PersonInfoBO queryPersonByUsername(String username) {
        return baseMapper.queryPersonByUsername(username);
    }
}

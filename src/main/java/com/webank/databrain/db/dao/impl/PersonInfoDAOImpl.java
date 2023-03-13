package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.PersonInfoDAO;
import com.webank.databrain.model.bo.PersonInfoBO;
import com.webank.databrain.dao.db.entity.PersonInfoEntity;
import com.webank.databrain.dao.db.mapper.PersonInfoMapper;
import com.webank.databrain.utils.PagingUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
@Service
public class PersonInfoDAOImpl extends ServiceImpl<PersonInfoMapper, PersonInfoEntity> implements PersonInfoDAO {

    @Override
    public PersonInfoBO queryPersonByUsername(String username) {
        return baseMapper.queryPersonByUsername(username);
    }


    @Override
    public List<PersonInfoBO> listPersonWithStatus(int accountStatus, int pageNo, int pageSize) {
        long start = PagingUtils.getStartOffset(pageNo, pageSize);
        return baseMapper.listPersonWithStatus(accountStatus, start, pageSize);
    }
}

package com.webank.databrain.db.dao.impl;

import com.webank.databrain.db.entity.PersonInfoDataObject;
import com.webank.databrain.db.mapper.PersonInfoMapper;
import com.webank.databrain.db.dao.PersonInfoDAO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class PersonInfoDAOImpl extends ServiceImpl<PersonInfoMapper, PersonInfoDataObject> implements PersonInfoDAO {

}

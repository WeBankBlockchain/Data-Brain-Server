package com.webank.databrain.db.dao.impl;

import com.webank.databrain.db.entity.OrgInfoDataObject;
import com.webank.databrain.db.mapper.OrgInfoMapper;
import com.webank.databrain.db.dao.IOrgInfoService;
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
public class OrgInfoServiceImpl extends ServiceImpl<OrgInfoMapper, OrgInfoDataObject> implements IOrgInfoService {

}

package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.CompanyInfoDAO;
import com.webank.databrain.db.entity.CompanyInfoDataObject;
import com.webank.databrain.db.mapper.CompanyInfoMapper;
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
public class CompanyInfoDAOImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfoDataObject> implements CompanyInfoDAO {

}

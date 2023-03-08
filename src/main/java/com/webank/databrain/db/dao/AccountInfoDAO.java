package com.webank.databrain.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webank.databrain.db.entity.AccountInfoDataObject;
import com.webank.databrain.db.entity.CompanyJoinAccountDataObject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
public interface AccountInfoDAO extends IService<AccountInfoDataObject> {

    List<CompanyJoinAccountDataObject> listCompany(int pageNo, int pageSize);
}

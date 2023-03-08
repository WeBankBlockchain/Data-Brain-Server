package com.webank.databrain.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webank.databrain.db.dao.ISchemaService;
import com.webank.databrain.db.entity.DataSchemaDataObject;
import com.webank.databrain.db.mapper.SchemaMapper;
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
public class SchemaServiceImpl extends ServiceImpl<SchemaMapper, DataSchemaDataObject> implements ISchemaService {

}

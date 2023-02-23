package com.webank.databrain.db.dao.impl;

import com.webank.databrain.db.entity.Tag;
import com.webank.databrain.db.mapper.TagMapper;
import com.webank.databrain.db.dao.ITagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}

package com.webank.data.brain.db.dao.impl;

import com.webank.data.brain.db.entity.Tag;
import com.webank.data.brain.db.mapper.TagMapper;
import com.webank.data.brain.db.dao.ITagService;
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

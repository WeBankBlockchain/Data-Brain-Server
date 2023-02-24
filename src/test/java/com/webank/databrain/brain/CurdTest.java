package com.webank.databrain.brain;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webank.databrain.db.entity.UserInfoDataObject;
import com.webank.databrain.db.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class CurdTest extends ServerApplicationTests{

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    void pageTest() {
        IPage<Map<String, Object>> page = userInfoMapper.selectMapsPage(new Page<>(1, 5), Wrappers.<UserInfoDataObject>query().orderByAsc("user_id"));
        assertThat(page).isNotNull();
//        assertThat(page.getRecords()).isNotEmpty();
//        assertThat(page.getRecords().get(0)).isNotEmpty();
        System.out.println(page.getRecords().get(0));
    }
}

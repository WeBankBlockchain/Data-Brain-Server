package com.webank.data.brain.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.webank.data.brain.db.mapper")
public class MybatisPlusConfig {

}

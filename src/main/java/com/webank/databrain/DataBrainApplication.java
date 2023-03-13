package com.webank.databrain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableTransactionManagement
@MapperScan(value = "com.webank.databrain.dao.mapper")
public class DataBrainApplication {
	public static void main(String[] args) {
		SpringApplication.run(DataBrainApplication.class, args);
	}

}

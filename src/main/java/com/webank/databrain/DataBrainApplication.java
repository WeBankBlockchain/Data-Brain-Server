package com.webank.databrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {MPJSqlInjector.class})
@SpringBootApplication
public class DataBrainApplication {


	public static void main(String[] args) {
		SpringApplication.run(DataBrainApplication.class, args);
	}

}

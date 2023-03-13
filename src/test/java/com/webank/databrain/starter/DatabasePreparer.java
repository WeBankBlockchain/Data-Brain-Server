package com.webank.databrain.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatabasePreparer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DataSource dataSource;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Hi");
    }
}

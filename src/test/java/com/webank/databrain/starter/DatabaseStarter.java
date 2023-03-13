package com.webank.databrain.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class DatabaseStarter implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DataSource dataSource;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try{
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream in = classLoader.getResourceAsStream("scripts/db-script.sql");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder createTableStatement = new StringBuilder();
            String line = br.readLine();

            while (line != null){
                createTableStatement.append(line);
                line = br.readLine();
            }

            System.out.println(createTableStatement);
            Statement statement = dataSource.getConnection().createStatement();
            statement.execute(createTableStatement.toString());
        }catch (Exception ex){
            ex.printStackTrace();
            System.exit(-1);
        }


        System.out.println("Hi");
    }
}

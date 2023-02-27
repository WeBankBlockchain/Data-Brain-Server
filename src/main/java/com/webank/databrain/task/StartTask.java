package com.webank.databrain.task;

import com.webank.databrain.config.SysConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StartTask implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SysConfig sysConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("config: {}", sysConfig);
    }
}

package com.weix.api.core.config;
/*
import com.weix.api.core.CacheDemo;*/

import com.weix.api.core.CacheDemo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

public class CustomizedApplicationEventListener implements ApplicationListener{

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
       /*
        ApplicationEnvironmentPreparedEvent (org.springframework.boot.context.event)
        ApplicationPreparedEvent (org.springframework.boot.context.event)
        ApplicationReadyEvent (org.springframework.boot.context.event)
        ApplicationFailedEvent (org.springframework.boot.context.event)
        ApplicationStartingEvent (org.springframework.boot.context.event)
        ApplicationStartedEvent (org.springframework.boot.context.event)
         */
        System.out.println("+++++++++++++++++++++++++++"+event.getClass()+"+++++++++++++++");

        //准备环境变量 ApplicationEnvironmentPreparedEvent

        //准备配置信息 ApplicationPreparedEvent

        //启动完成 ApplicationReadyEvent

        if(event instanceof ApplicationReadyEvent){
            ApplicationReadyEvent readyEvent = (ApplicationReadyEvent) event;
            ConfigurableApplicationContext context = readyEvent.getApplicationContext();
            CacheDemo cacheDemo = context.getBean(CacheDemo.class);
            System.out.println(cacheDemo);
        }
    }
}

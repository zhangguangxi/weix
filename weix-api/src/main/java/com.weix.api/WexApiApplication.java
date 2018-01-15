package com.weix.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
        (
        scanBasePackages = {
                "com.weix"
        }
)
@EnableScheduling
public class WexApiApplication extends WebMvcConfigurerAdapter{
    public static void main(String[] args) {
        SpringApplication.run(WexApiApplication.class,args);
    }
}

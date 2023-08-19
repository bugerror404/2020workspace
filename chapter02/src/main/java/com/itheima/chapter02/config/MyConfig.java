package com.itheima.chapter02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //配置类
public class MyConfig {
    @Bean
    public MyService myService(){
        return new MyService();
    }
}

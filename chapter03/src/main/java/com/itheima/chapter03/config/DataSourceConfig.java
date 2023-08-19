package com.itheima.chapter03.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration //注解
public class DataSourceConfig {
    @Bean //作为组件加入到spring框架中
    @ConfigurationProperties(prefix = "spring.datasource") //以xxx为开头的属性值注入到第三方数据源参数的注入
    public DataSource getDruid(){
        return new DruidDataSource();
    }
}

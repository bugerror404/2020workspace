package com.itheima.chapter03.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration  //配置类
public class RedisConfig {
    @Bean  //实例化
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> template=new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);//设置连接工厂
        //使用JSON格式序列化对象，对缓存数据的key和value进行转换
        Jackson2JsonRedisSerializer jacksonSerial=new Jackson2JsonRedisSerializer(Object.class);
        //解决查询缓存转换异常
        ObjectMapper om=new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSerial.setObjectMapper(om);
        template.setDefaultSerializer(jacksonSerial);
        return template;

    }

}

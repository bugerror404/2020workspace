package com.itheima;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller +@ResponseBody 注解的一个组合 返回josn字符串
public class HelloController {
    @GetMapping("/hello")  //url地址
    public String hello(){
        return "hello spring boot";
    }
}

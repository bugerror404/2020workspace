package com.itheima;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) //测试运行器，加载Spring Boot测试注解
@SpringBootTest //标记单元测试类
public class ManualChapter01ApplicationTests {
    @Autowired //注入
    private HelloController helloController;
    @Test
    public void helloControllerTest(){
        String hello = helloController.hello();//alt+回车快捷键
        System.out.println(hello);


    }
}

   //psvm   sout

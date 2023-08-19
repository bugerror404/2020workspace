package com.itheima.chapter02;

import com.itheima.chapter02.domain.Person;
import com.itheima.chapter02.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Chapter02ApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private Person person;
    @Autowired
    private Student student;
    @Test
    void contextLoads() {
        System.out.println(person);
    }
    @Test
   void studentTest(){
       System.out.println(student);
   }
    @Test
    public void iocTest(){
        System.out.println(applicationContext.containsBean("myService"));
    }
}

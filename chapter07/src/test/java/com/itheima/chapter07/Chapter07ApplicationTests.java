package com.itheima.chapter07;

import com.itheima.chapter07.entity.Customer;
import com.itheima.chapter07.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Chapter07ApplicationTests {
    @Autowired
    private CustomerMapper customerMapper;
    @Test
    void contextLoads() {
    }
    @Test
    void findById(){
        Customer customer = customerMapper.selectByPrimaryKey(1);
        System.out.println(customer);

    }

}

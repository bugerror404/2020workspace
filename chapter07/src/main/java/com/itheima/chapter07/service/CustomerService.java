package com.itheima.chapter07.service;

import com.itheima.chapter07.entity.Authority;
import com.itheima.chapter07.entity.Customer;
import com.itheima.chapter07.mapper.AuthorityMapper;
import com.itheima.chapter07.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private AuthorityMapper authorityMapper;

    //根据用户名username查询用户信息
    public Customer getCustomer(String username){
        Customer customer=null;
        Object o = redisTemplate.opsForValue().get("customer_" + username);
        if(o!=null){
            customer=(Customer)o;
        }else{
           customer = customerMapper.selectByUsername(username);
           if(customer!=null){
               redisTemplate.opsForValue().set("customer_"+username,customer);
           }
        }
        return customer;
    }
    //根据用户名username查询用户权限
    public List<Authority> getCustomerAuthority(String username){
        List<Authority> authorities=null;
        Object o = redisTemplate.opsForValue().get("authorities_" + username);
        if (o!=null){
            authorities=(List<Authority>)o;
        }else{
            authorities = authorityMapper.findAByUsername(username);
            if (authorities.size()>0){
                redisTemplate.opsForValue().set("authorities_"+username,authorities);
            }
        }
        return authorities;
    }
}

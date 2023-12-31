package com.itheima.chapter07.service;


import com.itheima.chapter07.entity.Authority;
import com.itheima.chapter07.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private CustomerService customerService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //1.通过业务方法获取用户及权限信息
        Customer customer = customerService.getCustomer(s);
        List<Authority> authorities = customerService.getCustomerAuthority(s);
        //2.对用户权限进行封装
        List<SimpleGrantedAuthority> list = authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
        //3.返回封装的UserDetails用户详情类
        if(customer!=null){
            UserDetails userDetails = new User(customer.getUsername(), customer.getPassword(), list);
            return userDetails;
        }else {
            //如果查询的用户不存在，必须抛出异常
            throw new UsernameNotFoundException("当前用户不存在");
        }
    }
}

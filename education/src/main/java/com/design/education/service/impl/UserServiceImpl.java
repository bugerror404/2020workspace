package com.design.education.service.impl;

import com.design.education.entity.User;
import com.design.education.entity.UserAuthority;
import com.design.education.mapper.AuthorityMapper;
import com.design.education.mapper.UserAuthorityMapper;
import com.design.education.mapper.UserMapper;
import com.design.education.service.IUserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAuthorityMapper userAuthorityMapper;
    @Override
    public void RegisterUser(User user) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreated(new Date());
        user.setValid(true);
        userMapper.insert(user);
        User user1=userMapper.selectByName(user.getUsername());
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUserId(user1.getId());
        userAuthority.setAuthorityId(2);
        userAuthorityMapper.insert(userAuthority);
    }
//    @Override
//    public User selectById(Integer id){
//        User user=userMapper.selectByPrimaryKey(id);
//        return user;
//    }
}

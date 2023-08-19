package com.design.education.service;

import com.design.education.entity.User;

public interface IUserService {
    //用户注册
    public void RegisterUser(User user);
    //根据id查找用户信息
    //public User selectById(Integer id);
}

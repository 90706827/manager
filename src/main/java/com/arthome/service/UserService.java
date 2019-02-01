package com.arthome.service;

import com.arthome.entity.User;
import com.arthome.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName UserService
 * Description 用户
 * Author Mr.Jangni
 * Date 2018/12/23 14:52
 * Version 1.0
 **/
@Component
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }
    public User selectUserByPhoneNo(String phoneNo) {
        return userMapper.selectUserByPhoneNo(phoneNo);
    }
    public User selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

}
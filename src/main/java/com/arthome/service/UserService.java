package com.arthome.service;

import com.arthome.entity.User;
import com.arthome.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    @Caching(
            cacheable = {
                    @Cacheable(value = "user", key = "#userName"),
                    @Cacheable(value = "user", key = "#phoneNo"),
                    @Cacheable(value = "user", key = "#email")
            }
    )
    public User selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }
    @Caching(
            cacheable = {
                    @Cacheable(value = "user", key = "#userName"),
                    @Cacheable(value = "user", key = "#phoneNo"),
                    @Cacheable(value = "user", key = "#email")
            }
    )
    public User selectUserByPhoneNo(String phoneNo) {
        return userMapper.selectUserByPhoneNo(phoneNo);
    }

    @Caching(
            cacheable = {
                    @Cacheable(value = "user", key = "#userName"),
                    @Cacheable(value = "user", key = "#phoneNo"),
                    @Cacheable(value = "user", key = "#email")
            }
    )
    public User selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

}
package com.arthome.mapper;

import com.arthome.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(String uId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户名获取用户
     * @param userName 用户名称
     * @return 用户
     */
    User selectUserByUserName(String userName);

    /**
     * Author Mr.Jangni
     * Description 根据手机号获取用户
     * Date 2019/2/1 22:26
     * Param [phoneNo]
     * Return com.arthome.entity.User
     **/
    User selectUserByPhoneNo(String phoneNo);
    /**
     * Author Mr.Jangni
     * Description 根据邮箱获取用户
     * Date 2019/2/1 23:28
     * Param [email]
     * Return com.arthome.entity.User 
     **/
    User selectUserByEmail(String email);
}
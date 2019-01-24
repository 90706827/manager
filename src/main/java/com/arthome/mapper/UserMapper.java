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
}
package com.arthome.mapper;

import com.arthome.entity.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(String uId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(String uId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}
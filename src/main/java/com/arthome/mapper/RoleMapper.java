package com.arthome.mapper;

import com.arthome.entity.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectByUserName(String userName);

    int updateByPrimaryKey(Role record);

    int updateByPrimaryKeySelective(Role record);
}
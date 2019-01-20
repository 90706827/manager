package com.arthome.mapper;

import com.arthome.entity.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(String uId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String uId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    /**
     * 根据用户查询角色
     * @param userName 用户名称
     * @return 用户拥有的角色
     */
    List<Role> selectRoleByUserName(String userName);
}
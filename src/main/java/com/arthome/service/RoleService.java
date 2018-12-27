package com.arthome.service;

import com.arthome.entity.Power;
import com.arthome.entity.Role;
import com.arthome.mapper.PowerMapper;
import com.arthome.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName RoleService
 * Description 用户角色权限认证
 * Author Mr.Jangni
 * Date 2018/12/24 19:53
 * Version 1.0
 **/
@Component
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PowerMapper powerMapper;

    public List<Role> selectUserByUserName(String userName) {
        return roleMapper.selectByUserName(userName);
    }

    public List<Power> selectByRoleId(Integer roleId){
        return powerMapper.selectByRoleId(roleId);
    }
}
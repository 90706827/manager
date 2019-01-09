package com.arthome.mapper;

import com.arthome.entity.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectByUserName(String userName);

    int updateByPrimaryKey(Role record);

    int updateByPrimaryKeySelective(Role record);
}
package com.arthome.mapper;

import com.arthome.entity.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}
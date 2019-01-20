package com.arthome.mapper;

import com.arthome.entity.RolePower;

public interface RolePowerMapper {
    int deleteByPrimaryKey(String uId);

    int insert(RolePower record);

    int insertSelective(RolePower record);

    RolePower selectByPrimaryKey(String uId);

    int updateByPrimaryKeySelective(RolePower record);

    int updateByPrimaryKey(RolePower record);
}
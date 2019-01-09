package com.arthome.mapper;

import com.arthome.entity.RolePower;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePowerMapper {
    int insert(RolePower record);

    int insertSelective(RolePower record);
}
package com.arthome.mapper;

import com.arthome.entity.Power;

import java.util.List;

public interface PowerMapper {
    int deleteByPrimaryKey(String uId);

    int insert(Power record);

    int insertSelective(Power record);

    Power selectByPrimaryKey(String uId);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKey(Power record);

    /**
     * 根据角色id获取权限
     * @param uId 角色id
     * @return 角色所属权限
     */
    List<Power> selectPowerByRoleId(String uId);
}
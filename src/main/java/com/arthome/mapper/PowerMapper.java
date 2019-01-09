package com.arthome.mapper;

import com.arthome.entity.Power;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Power record);

    int insertSelective(Power record);

    Power selectByPrimaryKey(Integer id);

    List<Power> selectByRoleId(Integer roleId);

    int updateByPrimaryKey(Power record);

    int updateByPrimaryKeySelective(Power record);
}
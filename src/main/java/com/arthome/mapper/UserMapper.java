package com.arthome.mapper;

import com.arthome.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUserName(String userName);

    int updateByPrimaryKey(User record);

    int updateByPrimaryKeySelective(User record);
}
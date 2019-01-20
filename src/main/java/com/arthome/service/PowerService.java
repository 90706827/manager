package com.arthome.service;

import com.arthome.entity.Power;
import com.arthome.mapper.PowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName PowerService
 * Description 权限
 * Author  Mr.Jangni
 * Date 2019/1/19 19:22
 * Version 1.0
 **/
@Component
public class PowerService {

    @Autowired
    private PowerMapper powerMapper;

    public List<Power> selectPowerByRoleId(String uId){
        return powerMapper.selectPowerByRoleId(uId);
    }
}

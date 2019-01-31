package com.arthome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName MenuController
 * Description 菜单
 * Author Mr.Jangni
 * Date 2019/1/28 21:12
 * Version 1.0
 **/
@Controller
@RequestMapping("/menu")
public class MenuController {

    @RequestMapping(value = "/list")
    public String list() {
        return "menu/list";
    }
}
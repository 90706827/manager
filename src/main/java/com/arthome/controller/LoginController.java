package com.arthome.controller;

import com.arthome.config.Logger;
import com.arthome.service.RoleService;
import com.arthome.service.UserService;
import com.arthome.utils.VerifyCodeUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName LoginController
 * Description 登录退出
 * Author Mr.Jangni
 * Date 2018/12/24 22:20
 * Version 1.0
 **/
@Controller
public class LoginController implements Logger {


    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    //注解的使用
    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/create")
    public String create() {
        return "Create success!";
    }

    //错误页面展示
    @RequestMapping(value = "/error", method = RequestMethod.POST)
    public String error() {
        return "error";
    }

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public void getYzm(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            //生成随机字串
            String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
            //存入会话session
            HttpSession session = request.getSession(true);
            logger.info("后端生成验证码:"+verifyCode.toLowerCase());
            session.setAttribute("_code", verifyCode.toLowerCase());
            //生成图片
            int w = 118, h = 38;
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (Exception e) {
            logger.error("获取验证码异常：%s", e.getMessage());
        }
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
    //get
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        logger.info("默认登录页面Get请求");
        return "login";
    }
    //post登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request,@Param("password") String username, @Param("password") String password, @Param("captchaCode")String captchaCode) {

        logger.info("登录页面Post请求"+password + "|" + password+ "|" + captchaCode);
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        logger.info(subject.getSession().getAttribute("errorMsg").toString());
        if(subject.isAuthenticated()){
            logger.info("用户已经登录跳转首页");
            return "index";
        }

        logger.info("用户未登录跳转登录页面");
        return "login";
    }

//    //登出
//    @RequestMapping(value = "/logout")
//    public String logout() {
//        SecurityUtils.getSubject().logout();
//        return "login";
//    }
//    //数据初始化
//    @RequestMapping(value = "/addUser")
//    public String addUser(@RequestBody Map<String,Object> map){
//        User user = loginService.addUser(map);
//        return "addUser is ok! \n" + user;
//    }
//
//    //角色初始化
//    @RequestMapping(value = "/addRole")
//    public String addRole(@RequestBody Map<String,Object> map){
//        Role role = loginService.addRole(map);
//        return "addRole is ok! \n" + role;
//    }

    @RequestMapping(value = "/login/tel")
    public String telCode(HttpServletRequest request) {
        //存入会话session
        HttpSession session = request.getSession(true);
        session.setAttribute("auth_time", 1);
        return "telcode";
    }
}
package com.arthome.shiro;

import com.arthome.config.Logger;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * ClassName CaptchaToken
 * Description 验证码
 * Author Mr.Jangni
 * Date 2019/1/16 22:20
 * Version 1.0
 **/
public class CaptchaToken extends UsernamePasswordToken implements Logger {
    // 序列化ID
    private static final long serialVersionUID = -2804050723838289739L;

    // 验证码
    private String captchaCode;
    //盐
    private String salt;
    //限制状态
    private Integer status;
    //数据库密码
    private String dbPassword;

    public CaptchaToken(String username, String password, String captchaCode, String host) {
        // 父类UsernamePasswordToken的构造函数,后两个参数暂不需要, 不设置 rememberMe-是否记住登录
        super(username, password, false, host);
        this.captchaCode = captchaCode;
    }

    /**
     * 构造函数
     * 用户名和密码是登录必须的,因此构造函数中包含两个字段
     */
    public CaptchaToken(String username, String password, String captchaCode, String salt, Integer status, String dbPassword, boolean rememberMe, String host) {
        // 父类UsernamePasswordToken的构造函数,后两个参数暂不需要, 不设置 rememberMe-是否记住登录
        super(username, password, rememberMe, host);
        logger.info("设置token");
        this.captchaCode = captchaCode;
        this.salt = salt;
        this.status = status;
        this.dbPassword = dbPassword;
    }

    public String getSalt() {
        return salt;
    }

    /**
     * 获取验证码
     */
    String getCaptchaCode() {
        return captchaCode;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
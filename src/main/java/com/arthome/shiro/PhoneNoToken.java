package com.arthome.shiro;

import com.arthome.config.Logger;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * ClassName PhoneNoToken
 * Description 手机号登录Token
 * Author Mr.Jangni
 * Date 2019/2/1 22:20
 * Version 1.0
 **/
public class PhoneNoToken extends UsernamePasswordToken implements Logger {

    // 验证码
    private String captchaCode;

    public PhoneNoToken(String phoneno, String password, String captchaCode, String host) {
        // 父类UsernamePasswordToken的构造函数,后两个参数暂不需要, 不设置 rememberMe-是否记住登录
        super(phoneno, password, false, host);
        this.captchaCode = captchaCode;
    }
    /**
     * 获取验证码
     */
    public String getCaptchaCode() {
        return captchaCode;
    }
}
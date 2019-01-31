package com.arthome.shiro;

import com.arthome.config.Logger;
import com.arthome.entity.User;
import com.arthome.service.UserService;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * ClassName CaptchaFormAuthenticationFilter
 * Description 自定义认证过滤器
 * Author Mr.Jangni
 * Date 2019/1/16 22:23
 * Version 1.0
 **/
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter implements Logger {

    private PassWordService passWordService;
    private UserService userService;

    public CaptchaFormAuthenticationFilter(PassWordService passWordService, UserService userService) {
        this.passWordService = passWordService;
        this.userService = userService;
    }

    /**
     * 构造Token,重写Shiro构造Token的方法,增加验证码
     */
    @Override
    protected AuthenticationToken createToken(String username, String password, ServletRequest request, ServletResponse response) {
        // 获取登录请求中用户输入的验证码
        String captchaCode = request.getParameter("captchaCode");
        User user = userService.selectUserByUserName(username);
        return new CaptchaToken(username, passWordService.encryptPassword(password, user.getPassSalt()), captchaCode, user.getPassSalt(), WebUtils.toHttp(request).getRemoteAddr());
    }

    @Override
    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        super.saveRequestAndRedirectToLogin(request, response);
    }
}
package com.arthome.shiro;

import com.arthome.config.Logger;
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

    /**
     * 构造Token,重写Shiro构造Token的方法,增加验证码
     */
    @Override
    protected AuthenticationToken createToken(String username, String password, ServletRequest request, ServletResponse response) {
        // 获取登录请求中用户输入的验证码
        String captchaCode = request.getParameter("captchaCode");
        if (username.matches("[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}")) {
            return new EmailToken(username, password, captchaCode, WebUtils.toHttp(request).getRemoteAddr());
        } else if (username.matches("1(3|4|5|7|8)\\d{9}")) {
            return new PhoneNoToken(username, password, captchaCode, WebUtils.toHttp(request).getRemoteAddr());
        } else {
            return new UserNameToken(username, password, captchaCode, WebUtils.toHttp(request).getRemoteAddr());
        }
    }

    @Override
    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        super.saveRequestAndRedirectToLogin(request, response);
    }
}
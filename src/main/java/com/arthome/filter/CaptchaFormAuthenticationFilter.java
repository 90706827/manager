package com.arthome.filter;

import com.arthome.config.CaptchaToken;
import com.arthome.config.Logger;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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

        logger.info("验证Token拦截器，获取请求验证码："+captchaCode);
        // 返回带验证码的Token,Token会被传入Realm, 在Realm中可以取得验证码
        return new CaptchaToken(username, password, captchaCode, WebUtils.toHttp(request).getRemoteAddr());
    }
}
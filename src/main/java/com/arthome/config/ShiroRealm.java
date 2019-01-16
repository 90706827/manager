package com.arthome.config;

import com.arthome.entity.Power;
import com.arthome.entity.Role;
import com.arthome.entity.User;
import com.arthome.service.RoleService;
import com.arthome.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * program: java
 * description: 自定义身份和权限认证操作
 * author: Mr.Jangni
 * create: 2018-07-31 23:48
 **/
@Component
public class ShiroRealm extends AuthorizingRealm implements Logger {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param token 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        // 在自定义的认证过滤器中将验证码保存至KaptchaCodeToken中
        // 此处的Token就是认证过滤器中实例化的Token,可以直接强制转换
        CaptchaToken captchaToken = (CaptchaToken) token;

        // 获取用户在登录页面输入的验证码
        String loginCaptcha = captchaToken.getCaptchaCode();

        // 验证码未输入
        if (loginCaptcha == null || "".equals(loginCaptcha)) {
            // 抛出自定义异常(继承AuthenticationException), Shiro会捕获AuthenticationException异常
            // 发现该异常时认为登录失败,执行登录失败逻辑,登录失败页中可以判断如果是CaptchaEmptyException时为验证码为空
            System.out.println("————loginCaptcha 空————");
        }

        // 获取SESSION中的验证码
        // Kaptcha在生成验证码时会将验证码放入SESSION中
        // 默认KEY为KAPTCHA_SESSION_KEY, 可以在Web.xml中配置
        String sessionCaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute("_code");

        // 比较登录输入的验证码和SESSION保存的验证码是否一致
        if (!loginCaptcha.equals(sessionCaptcha)) {
            // 抛出自定义异常(继承AuthenticationException), Shiro会捕获AuthenticationException异常
            // 发现该异常时认为登录失败,执行登录失败逻辑,登录失败页中可以判断如果是CaptchaEmptyException时为验证码错误
            System.out.println("————loginCaptcha 验证码不正确————");
        }
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = token.getPrincipal().toString();
//        User user = userService.getUserByUserName(name);
        User user = new User();
        user.setPassWord("admin");
        user.setUserName("admin");
        if (user == null) {
            //这里返回后会报出对应异常
            throw new AccountException("用户不存在");
        }
        if (!user.getPassWord().equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(user.getUserName(), user.getPassWord().toString(), getName());
    }

    /**
     * 获取授权信息
     * param principalCollection
     * return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名称
        String userName = (String) principalCollection.getPrimaryPrincipal();
        //查询用户
        List<Role> roleList = roleService.selectUserByUserName(userName);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : roleList) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            logger.info("加载角色：{}", role.getRoleName());
            List<Power> powerList = roleService.selectByRoleId(role.getId());
            for (Power power : powerList) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(power.getPowerName());
                logger.info("加载权限：{}", role.getRoleName());
            }
        }
        return simpleAuthorizationInfo;
    }
}

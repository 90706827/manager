package com.arthome.shiro;

import com.arthome.config.Logger;
import com.arthome.entity.Power;
import com.arthome.entity.Role;
import com.arthome.entity.User;
import com.arthome.service.PowerService;
import com.arthome.service.RoleService;
import com.arthome.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
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
public class UserNameRealm extends AuthorizingRealm implements Logger {
    /**
     * 账户禁用
     */
    private static final String USER_STATUS_FORBIDDEN = "0";
    @Autowired
    private PassWordService passWordService;
    @Autowired
    private PowerService powerService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;


    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持 CaptchaToken 类型的Token
        return token instanceof UserNameToken;
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param token 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("————身份认证方法————");

        /**
         * 如果身份验证失败请捕获AuthenticationException或其子类，常见的如：
         * DisabledAccountException（禁用的帐号）
         * LockedAccountException（锁定的帐号）
         * UnknownAccountException（错误的帐号）
         * ExcessiveAttemptsException（登录失败次数过多）
         * IncorrectCredentialsException （错误的凭证）
         * ExpiredCredentialsException（过期的凭证）
         */
        UserNameToken userNameToken = (UserNameToken) token;
        Session session = SecurityUtils.getSubject().getSession();
        String captcha = session.getAttribute("_code").toString();
        User user = userService.selectUserByUserName(userNameToken.getPrincipal().toString());
        if (captcha == null || !captcha.equals(userNameToken.getCaptchaCode())) {
            session.setAttribute("errorMsg", "验证码不正确！");
            throw new AuthenticationException("验证码不正确！");
        }
        String password = passWordService.encryptPassword(String.valueOf(userNameToken.getPassword()), user.getPassSalt());
        if (user == null || !password.equals(user.getPassWord())) {
            session.setAttribute("errorMsg", "用户不存在或密码错误！");
            throw new AuthenticationException("用户不存在，或密码错误！");
        }
        if (USER_STATUS_FORBIDDEN.equals(user.getAllowStatus())) {
            session.setAttribute("errorMsg", "账户被禁用！");
            throw new DisabledAccountException("账户被禁用！");
        }
        return new SimpleAuthenticationInfo(user, userNameToken.getPassword(), getName());
    }

    /**
     * 获取授权信息
     * param principalCollection
     * return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
        // (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principalCollection);
            SecurityUtils.getSubject().logout();
            return null;
        }
        //获取登录用户名称
        User user = (User) principalCollection.getPrimaryPrincipal();
        //查询用户角色
        List<Role> roleList = roleService.selectRoleByUserName(user.getUserName());
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : roleList) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            logger.info("加载用户角色：{}", role.getRoleName());
            List<Power> powerList = powerService.selectPowerByRoleId(role.getuId());
            for (Power power : powerList) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(power.getPowerUrl());
                logger.info("加载权限：{}-{}", power.getPowerName(), power.getPowerUrl());
            }
        }
        return simpleAuthorizationInfo;
    }
}

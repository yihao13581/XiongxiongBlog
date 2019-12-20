package com.junjun.messages.config.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.junjun.messages.beans.UserDO;
import com.junjun.messages.beans.bo.LoginBO;
import com.junjun.messages.service.LoginService;
import com.junjun.messages.utils.consts.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @ClassName UserRealm
 * @Author huanxiong
 * @Description Realm是shiro的认证信息来源。当Subject调用login方法进行认证时，
 * shiro内部会调用Realm获取认证信息（即AuthenticationInfo）。
 * 所以在集成shiro的时候，必须给SecurityManager设置至少一个Realm。
 * 在开发中最常用的就是继承AuthorizingRealm，
 * 重写doGetAuthorizationInfo和doGetAuthenticationInfo。
 * @Date 2019-12-20 15:02
 * Version 1.0
 **/
@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    LoginService loginService;

    @Override
    @SuppressWarnings("unchecked")
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Session session = SecurityUtils.getSubject().getSession();
        // 查询用户的权限
        JSONObject permission = (JSONObject) session.getAttribute(Constants.SESSION_USER_PERMISSION);
        log.info("permission的值为：" + permission);
        log.info("本用于的权限为：" + permission.get("permissionList"));
        // 为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
        return authorizationInfo;
    }

    /**
     * 验证当前登录的Subject
     * LoginController.login()方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        String loginName = (String) authcToken.getPrincipal();
        // 获取用户密码
        String password = new String((char[]) authcToken.getCredentials());
        UserDO userDO = loginService.getUser(loginName, password);
        LoginBO loginBO = new LoginBO();
        BeanUtils.copyProperties(userDO, loginBO);
        if (loginBO == null) {
            // 账号不存在
            throw new UnknownAccountException();
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                loginBO.getUserName(),
                loginBO.getUserPass(),
                //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
                getName()
        );
        // session中不需要保存密码
        loginBO.setUserPass("");
        // 将用户信息放入session中
        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO,
                JSON.toJSONString(loginBO, SerializerFeature.WriteMapNullValue));
        log.info("session中的用户信息为：" + JSON.toJSONString(loginBO, SerializerFeature.WriteMapNullValue));
        return authenticationInfo;
    }
}

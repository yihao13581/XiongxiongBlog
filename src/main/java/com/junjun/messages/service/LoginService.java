package com.junjun.messages.service;

import com.junjun.messages.beans.UserDO;

import java.util.Map;

/**
 * @ClassName LoginService
 * @Author huanxiong
 * @Description 登录的service服务
 * @Date 2019-12-20 15:43
 * Version 1.0
 **/
public interface LoginService {
    /**
     * @return com.junjun.messages.beans.UserDO
     * @MethodName getUser
     * @Description 根据用户名和密码匹配对应的用户
     * @Param [userName, userPass]
     **/
    UserDO getUser(String userName, String userPass);

    /**
     * @return com.junjun.messages.beans.common.OutputObject
     * @MethodName authLogin
     * @Description 登录表单提交处理
     * @Param [userAcct, password]
     **/
    Map<String, String> authLogin(String userAcct, String userPass);

    /**
     * @Description 根据用户id(主键)获取用户信息
     * @Param [id]
     * @return com.junjun.messages.beans.UserDO
     **/
    UserDO getUserById(int id);
}

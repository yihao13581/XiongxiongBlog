package com.junjun.messages.service.impl;

import com.junjun.messages.beans.UserDO;
import com.junjun.messages.mapper.LoginMapper;
import com.junjun.messages.service.LoginService;
import com.junjun.messages.utils.consts.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName LoginServiceImpl
 * @Author huanxiong
 * @Description
 * @Date 2019-12-20 16:25
 * Version 1.0
 **/
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public UserDO getUser(String userName, String userPass) {
        return loginMapper.getUser(userName, userPass);
    }

    @Override
    public String authLogin(String userAcct, String userPass) {

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userAcct, userPass);
        // 返回结果
        String result;
        try {
            // 登录，保存session
            currentUser.login(token);
            log.info("登录成功！");
            result = Constants.REQUEST_SUCCESS;
        } catch (AuthenticationException e) {

           log.info("登录失败！账号或密码错误：" + e);
           result = Constants.REQUEST_FAILED;
        }

        return result;
    }

    @Override
    public String logout() {

        // 返回结果
        String result;

        try {
            Subject currentUser = SecurityUtils.getSubject();
            // 推出登录，清空 session
            currentUser.logout();
            log.info("退出登录成功！");
            result = Constants.REQUEST_SUCCESS;
        } catch (Exception e) {
            result = Constants.REQUEST_FAILED;
            log.error("退出登录失败！");
        }
        return result;
    }
}

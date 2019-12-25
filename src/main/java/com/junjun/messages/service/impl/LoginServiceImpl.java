package com.junjun.messages.service.impl;

import com.junjun.messages.beans.UserDO;
import com.junjun.messages.mapper.LoginMapper;
import com.junjun.messages.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    public Map<String, String> authLogin(String userAcct, String userPass) {
        Map<String, String> map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userAcct, userPass);
        try {
            currentUser.login(token);
            map.put("result", "success");
        } catch (AuthenticationException e) {
            map.put("result", "failed");
        }

        return map;
    }

    @Override
    public UserDO getUserById(int id) {
        return loginMapper.getUserById(id);
    }
}

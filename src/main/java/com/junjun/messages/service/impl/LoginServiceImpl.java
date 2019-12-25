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
        // ���ؽ��
        String result;
        try {
            // ��¼������session
            currentUser.login(token);
            log.info("��¼�ɹ���");
            result = Constants.REQUEST_SUCCESS;
        } catch (AuthenticationException e) {

           log.info("��¼ʧ�ܣ��˺Ż��������" + e);
           result = Constants.REQUEST_FAILED;
        }

        return result;
    }

    @Override
    public String logout() {

        // ���ؽ��
        String result;

        try {
            Subject currentUser = SecurityUtils.getSubject();
            // �Ƴ���¼����� session
            currentUser.logout();
            log.info("�˳���¼�ɹ���");
            result = Constants.REQUEST_SUCCESS;
        } catch (Exception e) {
            result = Constants.REQUEST_FAILED;
            log.error("�˳���¼ʧ�ܣ�");
        }
        return result;
    }
}

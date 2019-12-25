package com.junjun.messages.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.junjun.messages.beans.common.OutputObject;
import com.junjun.messages.service.LoginService;
import com.junjun.messages.utils.consts.Constants;
import com.junjun.messages.utils.enums.ReturnInfoEnums;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName LoginController
 * @Author huanxiong
 * @Description 管理员登录服务
 * @Date 2019-12-20 9:58
 * Version 1.0
 **/
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("admin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * @return com.junjun.messages.beans.common.OutputObject
     * @MethodName login
     * @Description 登录
     * @Param [userAcct, password]
     **/
    @PostMapping("/auth")
    public OutputObject login(@RequestParam String userAcct,
                              @RequestParam String userPass) {

        OutputObject outputObject = new OutputObject();
        log.info("auth入参为：" + "userAcct," + userAcct + ";password," + "**不显示**");

        // 校验必传参数
        if (StringUtils.isBlank(userAcct)) {
            outputObject.setReturnCode(ReturnInfoEnums.E_90003.getCode());
            outputObject.setReturnCode(ReturnInfoEnums.E_90003.getMessage());
            return outputObject;
        }
        if (StringUtils.isBlank(userPass)) {
            outputObject.setReturnCode(ReturnInfoEnums.E_90003.getCode());
            outputObject.setReturnCode(ReturnInfoEnums.E_90003.getMessage());
            return outputObject;
        }

        String result = loginService.authLogin(userAcct, userPass);
        // 组装返回结果
        if (!Constants.REQUEST_SUCCESS.equals(result)) {
            outputObject.setReturnCode(ReturnInfoEnums.PROCESS_FAILED.getCode());
            outputObject.setReturnMessage("用户名/密码错误");
            return outputObject;
        }

        outputObject.setReturnCode(ReturnInfoEnums.PROCESS_SUCCESS.getCode());
        outputObject.setReturnMessage(ReturnInfoEnums.PROCESS_SUCCESS.getMessage());

        log.info("auth出参为：" + JSON.toJSONString(outputObject, SerializerFeature.WriteMapNullValue));
        return outputObject;
    }

    /**
     * @return com.junjun.messages.beans.common.OutputObject
     * @MethodName logout
     * @Description 登出
     * @Param []
     **/
    @PostMapping("logout")
    public OutputObject logout() {

        OutputObject outputObject = new OutputObject();
        String result = loginService.logout();
        if (!Constants.REQUEST_SUCCESS.equals(result)) {
            outputObject.setReturnCode(ReturnInfoEnums.PROCESS_FAILED.getCode());
            outputObject.setReturnMessage("退出登录失败");
            return outputObject;
        }

        outputObject.setReturnCode(ReturnInfoEnums.PROCESS_SUCCESS.getCode());
        outputObject.setReturnMessage(ReturnInfoEnums.PROCESS_SUCCESS.getMessage());

        log.info("auth出参为：" + JSON.toJSONString(outputObject, SerializerFeature.WriteMapNullValue));
        return outputObject;
    }



}

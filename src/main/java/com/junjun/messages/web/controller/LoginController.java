package com.junjun.messages.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.junjun.messages.beans.UserDO;
import com.junjun.messages.beans.common.OutputObject;
import com.junjun.messages.service.LoginService;
import com.junjun.messages.utils.enums.ReturnInfoEnums;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Author huanxiong
 * @Description 管理员登录服务
 * @Date 2019-12-20 9:58
 * Version 1.0
 **/
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

        Map<String, String> result = loginService.authLogin(userAcct, userPass);
        // 组装返回结果

        outputObject.setBean(result);
        outputObject.setReturnCode(ReturnInfoEnums.PROCESS_SUCCESS.getCode());
        outputObject.setReturnMessage(ReturnInfoEnums.PROCESS_SUCCESS.getMessage());

        log.info("auth出参为：" + JSON.toJSONString(outputObject, SerializerFeature.WriteMapNullValue));
        return outputObject;
    }

    @RequestMapping(value="/getUserById",method= RequestMethod.POST)
    public OutputObject getUserById(@RequestParam("id")int id){
        OutputObject outputObject=new OutputObject();
        if(StringUtils.isEmpty(id+"")){
            outputObject.setReturnCode(ReturnInfoEnums.E_50002.getCode());
            outputObject.setReturnCode(ReturnInfoEnums.E_50002.getMessage());
            return outputObject;
        }
        UserDO userDo=loginService.getUserById(id);
        if(userDo==null){
            outputObject.setReturnCode(ReturnInfoEnums.E_50001.getCode());
            outputObject.setReturnCode(ReturnInfoEnums.E_50001.getMessage());
            return outputObject;
        }
        Map<String,String> result=new HashMap<>();
        result.put("id",userDo.getId()+"");
        result.put("userName",userDo.getUserName());
        result.put("userAvatar",userDo.getUserAvatar());
        outputObject.setBean(result);
        outputObject.setReturnCode(ReturnInfoEnums.PROCESS_SUCCESS.getCode());
        outputObject.setReturnMessage(ReturnInfoEnums.PROCESS_SUCCESS.getMessage());
        return  outputObject;
    }


}

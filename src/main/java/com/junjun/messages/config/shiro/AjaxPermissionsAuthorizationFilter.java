package com.junjun.messages.config.shiro;

import com.alibaba.fastjson.JSON;
import com.junjun.messages.beans.common.OutputObject;
import com.junjun.messages.utils.enums.ReturnInfoEnums;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @ClassName AjaxPermissionsAuthorizationFilter
 * @Author huanxiong
 * @Description 对没有登录的请求进行拦截, 统一返回json信息。
 * 覆盖掉Shiro默认的跳转login.jsp的拦截方式
 * @Date 2019-12-20 14:01
 * Version 1.0
 **/
@Slf4j
public class AjaxPermissionsAuthorizationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        OutputObject outputObject = new OutputObject();
        outputObject.setReturnCode(ReturnInfoEnums.E_20011.getCode());
        outputObject.setReturnMessage(ReturnInfoEnums.E_20011.getMessage());

        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(outputObject));
        } catch (Exception e) {
            log.error("统一返回json信息出错：" + e);
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }

    @Bean
    public FilterRegistrationBean registration(AjaxPermissionsAuthorizationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}

package com.junjun.messages.beans.common;
import lombok.Data;

import java.util.List;
import	java.util.Map;

import java.io.Serializable;
import java.util.Set;

/**
 * @ClassName OutputObject
 * @Author huanxiong
 * @Description 统一出参类型
 * @Date 2019-12-19 20:04
 * Version 1.0
 **/
@Data
public class OutputObject implements Serializable {

    private static final long serialVersionUID = 5389118798017179261L;

    private String returnCode;
    private String returnMessage;
    /**
     * 时间戳防止加密内容一致
     **/
    private String timeStamp;
    private Map<String, String> bean;
    private List<Map<String, String>> beans;
    private Map<String, Set<String>> bean2;



    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Map<String, String> getBean() {
        return bean;
    }

    public void setBean(Map<String, String> bean) {
        this.bean = bean;
    }

    public List<Map<String, String>> getBeans() {
        return beans;
    }

    public void setBeans(List<Map<String, String>> beans) {
        this.beans = beans;
    }

    public Map<String, Set<String>> getBean2() {
        return bean2;
    }

    public void setBean2(Map<String, Set<String>> bean2) {
        this.bean2 = bean2;
    }
}

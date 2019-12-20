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

}

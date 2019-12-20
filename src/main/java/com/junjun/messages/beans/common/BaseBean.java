package com.junjun.messages.beans.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BaseBean
 * @Author huanxiong
 * @Description 基础Bean服务
 * @Date 2019-12-19 19:35
 * Version 1.0
 **/
@Data
public class BaseBean implements Serializable {

    private static final long serialVersionUID = -5208238407993890074L;

    /**
     * ID，自动生成
     */
    private Long id;

}

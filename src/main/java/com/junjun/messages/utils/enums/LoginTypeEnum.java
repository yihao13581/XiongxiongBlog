package com.junjun.messages.utils.enums;

/**
 * @ClassName LoginTypeEnum
 * @Author huanxiong
 * @Description 登录类型枚举
 * @Date 2019-12-20 10:51
 * Version 1.0
 **/
public enum LoginTypeEnum {

    /**
     * 密码登录
     */
    NORMAL("Normal"),

    /**
     * 免密码登录
     */
    FREE("Free");

    private String value;

    LoginTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

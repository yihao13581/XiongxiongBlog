package com.junjun.messages.utils.enums;

/**
 * @ClassName ReturnInfoEnums
 * @Author huanxiong
 * @Description 客户端信息枚举
 * @Date 2019-12-20 10:24
 * Version 1.0
 **/
public enum  ReturnInfoEnums {

    // 常用返回状态码
    PROCESS_SUCCESS("0000", "操作成功"),
    PROCESS_FAILED("2999", "操作失败"),
    PROCESS_ERROR("9999", "服务器内部错误"),

    // 错误信息
    E_400("400", "请求处理异常，请稍后再试"),
    E_500("500", "请求方式有误,请检查 GET/POST"),
    E_501("501", "请求路径不存在"),
    E_502("502", "权限不足"),

    E_10008("10008", "角色删除失败,尚有用户属于此角色"),
    E_10009("10009", "账户已存在"),

    E_20011("20011", "登陆已过期,请重新登陆"),

    E_90003("90003", "缺少必填参数"),

    E_50001("5001","用户不存在"),
    E_50002("5002","必填参数为空");
    private String code;
    private String message;

    private ReturnInfoEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }}

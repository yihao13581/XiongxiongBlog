package com.junjun.messages.beans;

import com.junjun.messages.beans.common.BaseBean;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
/**
 * @ClassName OutputObject
 * @Author huanxiong
 * @Description 博主信息
 * @Date 2019-12-19 20:04
 * Version 1.0
 **/
@Data
public class UserDO extends BaseBean {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 昵称
     */
    private String userDisName;

    /**
     * 密码
     */
    private String userPass;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String userEmail;

    /**
     * 头像
     */
    private String userAvatar;

    /**
     * 个人说明
     */
    private String userDesc;

    /**
     * 是否是管理员 1是，0否
     */
    private Integer isAdmin;

    /**
     * 0 正常
     * 1 禁用
     * 2 已删除
     */
    private Integer status = 0;

    /**
     * 最后一次登录时间
     */
    private Date loginLast;

    /**
     * 注册时间
     */
    private Date createTime;

    /**
     * 文章数
     */
    private Integer postCount;

    /**
     * 评论数
     */
    private Integer commentCount;
}

package com.junjun.messages.beans.bo;

import com.junjun.messages.beans.common.BaseBean;
import lombok.Data;

/**
 * @ClassName LoginBO
 * @Author huanxiong
 * @Description 登录业务Bean
 * @Date 2019-12-20 16:37
 * Version 1.0
 **/
@Data
public class LoginBO  extends BaseBean {
    /**
     * 用户名
     */
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
     * 头像
     */
    private String userAvatar;
}

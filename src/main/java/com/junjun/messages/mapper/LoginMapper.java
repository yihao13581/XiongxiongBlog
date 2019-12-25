package com.junjun.messages.mapper;

import com.junjun.messages.beans.UserDO;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName LoginMapper
 * @Author huanxiong
 * @Description 登录相关的服务
 * @Date 2019-12-20 15:56
 * Version 1.0
 **/
public interface LoginMapper {
    UserDO getUser(@Param("userName") String userName, @Param("userPass") String userPass);

    UserDO getUserById(@Param("id") int id);

}

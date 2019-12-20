package com.junjun.messages.beans.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.junjun.messages.utils.consts.CommonConstants;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 删除状态：1删除，0未删除
     */
    @TableField(value = "del_flag")
    @TableLogic
    private Integer delFlag = CommonConstants.STATUS_NORMAL;

    /**
     * 创建人用户名
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;
}

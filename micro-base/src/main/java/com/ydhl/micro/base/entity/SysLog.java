package com.ydhl.micro.base.entity;

import lombok.Data;

import java.util.Date;

/**
 * 表: sys_log
 */
@Data
public class SysLog {
    /**
     * 编号
     *
     * 是否允许NULL值:  不允许
     */
    private Long id;

    /**
     * 平台
     *
     * 是否允许NULL值:  允许
     */
    private String platform;

    /**
     * 用户id
     *
     * 是否允许NULL值:  允许
     */
    private Long userId;

    /**
     * 用户名
     *
     * 是否允许NULL值:  允许
     */
    private String userName;

    /**
     * 模块枚举ModelEnum
     *
     * 是否允许NULL值:  不允许
     */
    private String module;

    /**
     * 操作枚举OperationEnum
     *
     * 是否允许NULL值:  不允许
     */
    private String operation;

    /**
     * 说明
     *
     * 是否允许NULL值:  允许
     */
    private String remark;

    /**
     * 请求方法
     *
     * 是否允许NULL值:  允许
     */
    private String method;

    /**
     * 请求参数
     *
     * 是否允许NULL值:  允许
     */
    private String params;

    /**
     * 响应内容
     *
     * 是否允许NULL值:  允许
     */
    private String result;

    /**
     * 耗时（毫秒）
     *
     * 是否允许NULL值:  允许
     */
    private String totalMillis;

    /**
     * IP地址
     *
     * 是否允许NULL值:  允许
     */
    private String ip;

    /**
     * 用户访问标识
     *
     * 是否允许NULL值:  允许
     */
    private String userAgent;

    /**
     * 创建时间
     *
     * 是否允许NULL值:  允许
     */
    private Date createTime;

    /**
     * 最后修改时间
     *
     * 是否允许NULL值:  允许
     */
    private Date modifyTime;
}

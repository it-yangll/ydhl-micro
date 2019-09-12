package com.ydhl.micro.base.entity;

import lombok.Data;

/**
 * 表: sys_msg_template
 */
@Data
public class SysMsgTemplate {
    /**
     * 消息模板ID
     *
     * 是否允许NULL值:  不允许
     */
    private Long id;

    /**
     * 消息模板代码
     *
     * 是否允许NULL值:  不允许
     */
    private String msgCode;

    /**
     * 消息模板
     *
     * 是否允许NULL值:  不允许
     */
    private String msgTemplate;
}
package com.nado.rlzy.db.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = "message")
public class Message {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 0 平台处理结果通知 1 面试提醒 2 报道提醒 3 返佣提醒 72小时内返佣未充值 4 给付中断
     */
    private Integer type;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 简章id
     */
    @Column(name = "briefchapter_id")
    private Integer briefchapterId;

    /**
     * 求职id
     */
    @Column(name = "sign_up_id")
    private Integer signUpId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 推荐人推荐的那些人的名字
     */
    @Column(name = "sign_up_name")
    private String signUpName;

}
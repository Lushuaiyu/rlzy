package com.nado.rlzy.db.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
@Data
@Table(name = "hr_signup_deliveryrecord")
public class HrSignupDeliveryrecord {
    /**
     * 报名表投递记录表
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 报名表Id
     */
    @Column(name = "signup_id")
    private Integer signupId;

    /**
     * 简章Id
     */
    @Column(name = "brief_chapter_id")
    private Integer briefChapterId;

    /**
     * 求职状态 0已报名 1待面试 2待报到 3待返佣 4不合适 5邀请报名 6已完成 7邀请面试 8 未面试 9 未报到 10已面试 11 已报道
     */
    @Column(name = "job_status")
    private Integer jobStatus;

    /**
     * 未通过原因 0不合适 1未面试 2未报道 3面试未通过 4返佣中断
     */
    @Column(name = "no_pass_reason")
    private Integer noPassReason;

    /**
     * 未报道原因 0 社保原因(无责) 1 其他原因(无责) 2 其他原因(有责)
     */
    @Column(name = "no_report_reason")
    private Integer noReportReason;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 0 未删除 1 已删除
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;


}
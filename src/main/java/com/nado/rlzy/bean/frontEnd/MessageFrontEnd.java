package com.nado.rlzy.bean.frontEnd;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @ClassName 返回给前台的通知
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-09-07 14:58
 * @Version 1.0
 */
@Data
public class MessageFrontEnd {



    /**
     * 返回给前台 0 平台处理结果通知 1 面试提醒 2 报道提醒 3 返佣提醒 72小时内返佣未充值 4 给付中断
     */
    @Transient
    private String type;



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

    /**
     * 内容
     */
    @Column(name = "content")
    private String content;

    @Transient
    private String GroupName;

    @Transient
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime interviewTime;

}

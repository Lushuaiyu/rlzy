package com.nado.rlzy.db.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author lushuaiyu
 */
@Table(name = "feedback")
@Data
public class Feedback {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 遇到的问题
     */
    private String content;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 反馈者姓名
     */
    private String name;

    /**
     * 反馈者手机号
     */
    private String phone;

    /**
     * 是否删除, 默认 0 没删除 1  已删除
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

}
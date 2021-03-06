package com.nado.rlzy.db.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "collect")
public class Collect {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "briefchapter_id")
    private Integer briefchapterId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "create_time")
    private Date createTime;


    @Column(name = "delete_flag")
    private Integer deleteFlag;

    @Column(name = "sign_up_id")
    private Integer signUpId;

    @Column(name = "recruiter_id")
   private Integer recruiterId;



    /**
     * 0 添加搜藏 1 取消搜藏
     */
    @Transient
    private Integer type;
}


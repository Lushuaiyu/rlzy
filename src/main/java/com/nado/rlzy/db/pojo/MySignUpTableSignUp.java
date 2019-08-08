package com.nado.rlzy.db.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = "my_sign_up_table_sign_up")
public class MySignUpTableSignUp {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 我的求职表主键id
     */
    @Column(name = "my_sign_up_table_id")
    private Integer mySignUpTableId;

    /**
     * 报名表主键id
     */
    @Column(name = "sign_up_id")
    private Integer signUpId;

    /**
     * 0 未删除 1 已删除
     */
    @Column(name = "delete_time")
    private Integer deleteTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;
}
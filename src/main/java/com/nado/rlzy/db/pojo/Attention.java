package com.nado.rlzy.db.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
@Data
@Table(name = "attention")
public class Attention {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 关注者id
     */
    @Column(name = "useringId")
    private Integer useringid;

    /**
     * 被关注者id
     */
    @Column(name = "useredId")
    private Integer useredid;

    /**
     * 关注的表id
     */
    @Column(name = "objectId")
    private Integer objectid;

    /**
     * 1为报名表 0为招聘表
     */
    private Integer type;

    /**
     * 关注状态 0已关注 1取消关注
     */
    private Integer status;

    @Column(name = "createTime")
    private LocalDateTime createtime;

    @Column(name = "modifyTime")
    private LocalDateTime modifyTime;


}
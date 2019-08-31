package com.nado.rlzy.db.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "entry_resignation")
public class EntryResignation {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 入职返佣男
     */
    @Column(name = "rebate_male_entry")
    private BigDecimal rebateMaleEntry;

    /**
     * 入职返佣女
     */
    @Column(name = "rebate_female_entry")
    private BigDecimal rebateFemaleEntry;

    /**
     * 返佣时间
     */
    @Column(name = "rebate_time")
    private Date rebateTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否删除 0 未删除 1 已删除
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**
     * 简章id
     */
    @Column(name = "brief_chapter_id")
    private Integer briefChapterId;

    @Column(name = "type")
    private Integer type;


}
package com.nado.rlzy.db.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author lushuaiyu
 */
@Table(name = "hr_dictionary_item")
@Data
public class HrDictionaryItem {
    /**
     * 数据字典项表
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 字典表Id
     */
    @Column(name = "pId")
    private Integer pid;

    /**
     * 显示名
     */
    private String name;

    /**
     * 实际值
     */
    private String value;

    /**
     * 图片
     */
    private String image;

    /**
     * 状态（0禁用、1启用）
     */
    private Integer status;


}
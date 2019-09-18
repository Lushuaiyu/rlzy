package com.nado.rlzy.db.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hr_label")
public class HrLabel {
    /**
     * 企业标签
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 企业Id
     */
    @Column(name = "groupId")
    private Integer groupid;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态（0禁用、1启用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

}
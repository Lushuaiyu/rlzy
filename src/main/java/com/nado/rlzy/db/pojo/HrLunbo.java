package com.nado.rlzy.db.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hr_lunbo")
public class HrLunbo {
    /**
     * 企业轮播图列表
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 企业Id
     */
    @Column(name = "groupId")
    private Integer groupid;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    /**
     * 状态（0禁用、1启用）
     */
    @Column(name = "status")
    private Integer status;
}
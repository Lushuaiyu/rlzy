package com.nado.rlzy.db.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "hr_enterpriseblacklist")
public class HrEnterpriseblacklist {
    /**
     * 企业黑名单表
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 身份证
     */
    @Column(name = "idCard")
    private String idcard;


}
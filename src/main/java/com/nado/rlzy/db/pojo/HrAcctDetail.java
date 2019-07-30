package com.nado.rlzy.db.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HrAcctDetail {
    private Integer id;

    private Integer acctid;

    private BigDecimal amount;

    private BigDecimal beforeamount;

    private BigDecimal afteramount;

    private Date createtime;

    private Integer withdrawway;

    private Integer payway;

    private Integer status;

    private Integer type;

    private String alipaynum;

    private BigDecimal withdrawamount;

    private String accountname;

    private String bankname;

    private String bankcardnum;

    private Integer signupid;


}
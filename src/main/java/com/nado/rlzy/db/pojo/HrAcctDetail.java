package com.nado.rlzy.db.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Table(name = "hr_acct_detail")
public class HrAcctDetail {
    /**
     * 账户明细表
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 账户id
     */
    @Column(name = "acctId")
    private Integer acctid;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 交易前金额
     */
    @Column(name = "beforeAmount")
    private BigDecimal beforeamount;

    /**
     * 交易后金额
     */
    @Column(name = "afterAmount")
    private BigDecimal afteramount;

    /**
     * 提现手续费
     */
    @Column(name = "withdrawFee")
    private BigDecimal withdrawfee;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createTime")
    private LocalDateTime createtime;

    /**
     * 提现方式 0支付宝 1银行卡
     */
    @Column(name = "withdrawWay")
    private Integer withdrawway;

    /**
     * 付款方式 0支付宝 1微信 2线下
     */
    @Column(name = "payWay")
    private Integer payway;

    /**
     * 账户状态 0未付款 1已付款 2审核失败
     */
    private Integer status;

    /**
     * 0 充值（收入） 1发布简章扣款（支出） 2简章返佣获得（收入） 3提现（支出） 4修改简章扣款（支出） 5冻结金额退回（收入）
     */
    private Integer type;

    /**
     * 支付宝账号
     */
    @Column(name = "alipayNum")
    private String alipaynum;

    /**
     * 提现金额
     */
    @Column(name = "withdrawAmount")
    private Long withdrawamount;

    /**
     * 开户人姓名
     */
    @Column(name = "accountName")
    private String accountname;

    /**
     * 开户行
     */
    @Column(name = "bankName")
    private String bankname;

    /**
     * 银行卡号
     */
    @Column(name = "bankcardNum")
    private String bankcardnum;

    /**
     * 返佣的投递表id
     */
    @Column(name = "signUpId")
    private Integer signupid;

    /**
     * 发布或者编辑简章的简章id
     */
    @Column(name = "briefChapterId")
    private Integer briefchapterid;

}
package com.nado.rlzy.db.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lushuaiyu
 */
@Data
@Table(name = "hr_acct")
@ApiModel(description = "账户表")
public class HrAcct {
    @ApiModelProperty(value = "报名表主键id")
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    private Integer id;

    @Column(name = "UserId")
    private Integer userid;

    @Column(name = "AcctBalance")
    private BigDecimal acctbalance;

    @Column(name = "IceBalance")
    private BigDecimal icebalance;

    @Column(name = "DeleteFlag")
    private Byte deleteflag;

    @Column(name = "modifyTime")
    private Date modifytime;

    @Column(name = "CreateTime")
    private Date createtime;

    @Column(name = "sign_up_id")
    private Integer signUpId;

}
package com.nado.rlzy.db.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 返佣表
 *
 * @Author lushuaiyu
 * @Description //TODO
 * @Date 9:23 2019/7/1
 * @Param
 * @return
 **/
@Data
@Table(name = "hr_rebaterecord")
public class HrRebaterecord {
    @Column(name = "Id")
    private Integer id;

    @Column(name = "BriefChapterId")
    private Integer briefchapterId;

    @Column(name = "RebateType")
    private Integer rebateType;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "RebateTime")
    private Date rebateTime;

    @Column(name =  "RebateMale")
    private BigDecimal rebateMale;

    private Integer statusTwo;

    @Column(name = "RebateFemale")
    private BigDecimal rebateFemale;

    @Transient
    private BigDecimal rebateOne;

    /**
     * 具体到某个人 前台显示的返佣金额
     */
    @Transient
    private String rebateMon;

    @Column(name = "status")
    private Integer status;

    /**
     * 返回给前端的返费状态
     */
    @Transient
    private String rebateStatus;

    private String rebateMaleStr;

    private String rebateFemaleStr;

    @Column(name = "DeleteFlag")
    private Byte deleteFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "CreateTime")
    private Date createTime;

    @Column(name = "rebate_variable")
    private BigDecimal rebateVariable;

    @Column(name = "sign_up_id")
    private Integer signUpId;


}
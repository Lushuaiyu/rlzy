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

    private String rebateTimeFrontEnd;

    @Column(name = "RebateMale")
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

    @Column(name = "signup_deliveryrecord_id")
    private Integer signupDeliveryrecordId;

    /**
     * 现在时间和入职返佣时间的差
     */
    @Transient
    private Integer rebateHour;

    /**
     * 用户id
     */
    @Transient
    private Integer userId;

    /**
     * 商户id
     */
    @Transient
    private Integer businessUserId;

    @Transient
    private Integer sex;

    /**
     * 简章表id
     */
    @Transient
    private Integer brId;

    /**
     * 报道时间
     */
    @Transient
    private Date registerTime;

    /**
     * 报名投递表id
     */
    @Transient
    private Integer hsdId;

    @Transient
    private Integer signUpId;


}
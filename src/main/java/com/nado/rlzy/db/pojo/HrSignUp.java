package com.nado.rlzy.db.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "报名表")
@Table(name = "hr_signup")
public class HrSignUp {

    @ApiModelProperty(value = "报名表主键id")
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    private Integer id;



    /**
     * 1 本人 2 推荐人
     */
    @Transient
    private Integer type;


    /**
     * 推荐人数
     */
    @Transient
    private String recommend;

    @ApiModelProperty(value = "用户表Id(推荐人Id)")
    @Column(name = "user_id")
    private Integer userId;

    @ApiModelProperty(value = "组织表Id")
    @Column(name = "group_id")
    private Integer groupId;

    @ApiModelProperty(value = "对应的简章id")
    @Transient
    private Integer briefChapterId;

    @ApiModelProperty(value = "报名者的名字")
    @Column(name = "user_name")
    private String signUpName;

    @Transient
    private Integer mySignUpTableId;

    @Transient
    @ApiModelProperty(value = "登录账号的用户名")
    private String userName;


    @ApiModelProperty(value = "0:女  1:男")
    @Column(name = "sex")
    private Integer sex;

    @Transient
    private String sexString;

    @ApiModelProperty(value = "身份证")
    @Column(name = "id_card")
    private String idCard;

    @ApiModelProperty(value = "学历")
    @Column(name = "education")
    private String education;


    @ApiModelProperty(value = "毕业时间")
    @Column(name = "graduation_time")
    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
    private Date graduationTime;

    @Transient
    private String graduationTime1;

    @ApiModelProperty(value = "专业")
    @Column(name = "profession")
    private String profession;

    @ApiModelProperty(value = "到岗时间")
    @Column(name = "arrival_time")
    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
    private Date arrivalTime;

    @Transient
    private String arrivalTime1;


    @ApiModelProperty(value = "期望薪资下限")
    @Column(name = "expected_salary_upper")
    private BigDecimal expectedSalaryUpper;

    @Column(name = "expected_salary_lower")
    private BigDecimal expectedSalaryLower;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Transient
    private LocalDateTime enterTime;

    @ApiModelProperty(value = "求职状态 0已报名 1待面试 7已面试 2待报到 3待返佣 4不合适 5被邀请 6已完成")
    @Transient
    private Integer jobStatus;

    /**
     * 前端需要的
     */
    private Integer status;


    @ApiModelProperty(value = "身份类型id")
    @Transient
    private Integer typeId;

    @ApiModelProperty(value = "0不合适 1未面试 2未报道 3面试未通过")
    @Transient
    private Integer noPassReason;

    @ApiModelProperty(value = "0:未删除  1:已删除")
    @Column(name = "delete_flag")
    private Byte deleteFlag;

    @Transient
    private String registrationPosition;

    @ApiModelProperty(value = "报名岗位id")
    @Column(name = "registration_position_id")
    private String registrationPositionId;

    @ApiModelProperty(value = "年龄")
    @Column(name = "age")
    private Integer age;

    @ApiModelProperty(value = "与推荐人关系")
    @Column(name = "relation")
    private String relation;

    @ApiModelProperty(value = "是否公开")
    @Column(name = "it_is_public")
    private Integer itIsPublic;
    @Transient
    private String itPublicString;

    @ApiModelProperty(value = "是否获取平台帮助")
    @Column(name = "agree_platform_help")
    private Integer agreePlatformHelp;

    @Transient
    private String agreePlatformHelpString;

    /**
     * 我的报名表属性
     */
    @ApiModelProperty(value = "自定义分组名字")
    @Transient
    private String groupName;

    @ApiModelProperty(value = "职位")
    @Transient
    private String postName;

    @ApiModelProperty(value = "求职表id")
    @Transient
    private Integer signId;

    @ApiModelProperty(value = "头像")
    @Transient
    private String headImage;

    @ApiModelProperty(value = "推荐人数")
    @Transient
    private String recommendNo;

    /**
     * 推荐人名字
     */
    @Transient
    private String recommendName;

    /**
     * 推荐人名字
     */
    private String commendName;

    @ApiModelProperty(value = "推荐说明")
    @Transient
    private String recommendInfo;

    @ApiModelProperty(value = "招聘端 直接录取 返佣非推荐者")
    @Transient
    private BigDecimal value;

    /**
     * 意向岗位
     */
    private String postId;

    private String postIdStr;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "面试时间")
    private Date interviewTime;

    @ApiModelProperty(value = "返佣金额")
    @Transient
    private List<HrRebaterecord> rebat;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "报到时间")
    @Transient
    private Date registerTime;

    @ApiModelProperty(value = "面试时间标识")
    @Transient
    private Integer interviewTimeFlag;

    /**
     * 未报名简章的求职者
     */
    @Transient
    private Integer noSingUp;


    @ApiModelProperty(value = "报到时间标识")
    @Transient
    private Integer reportTimeFlag;

    @ApiModelProperty(value = "未报到原因")
    @Transient
    private Integer noReportReason;

    @ApiModelProperty(value = "报名时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Transient
    private List<HrBriefchapter> chapter;

    @Transient
    private List<HrSignupDeliveryrecord> deliveryrecord;


}
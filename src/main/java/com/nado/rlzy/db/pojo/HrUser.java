package com.nado.rlzy.db.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author lushuaiyu
 */
@Data
@ApiModel(description = "登录用户信息")
@Table(name = "hr_user")
public class HrUser {

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "Id")
    private String id;

    @Column(name = "UserName")
    @ApiModelProperty(value = "用户名")
    private String userName;

    @Transient
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @Transient
    private Integer briefChapterId;

    @Transient
    @ApiModelProperty(value = "身份类型id")
    private Integer typeId;

    @ApiModelProperty(value = "性别")
    @Column(name = "sex")
    private Integer sex;

    @Transient
    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @Column(name = "Password")
    @ApiModelProperty(value = "密码")
    private String password;

    @Column(name = "HeadImage")
    @ApiModelProperty(value = "头像")
    private String headImage;

    @Column(name = "PostIdStr")
    @ApiModelProperty(value = "多个岗位的id")
    private String postIdStr;

    /**
     * 意向岗位
     */
    private String postName;

    @Column(name = "Mobile")
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @Column(name = "IdCard")
    @ApiModelProperty(value = "身份证号码")
    private String idCard;

    @Column(name = "RegisterTime")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "注册时间")
    private LocalDateTime registerTime;

    @Column(name = "RecommendNoUpper")
    @ApiModelProperty(value = "推荐人数上限")
    private Integer recommendNoUpper;

    @Column(name = "RecommendNoLower")
    @ApiModelProperty(value = "推荐人数下限")
    private Integer recommendNoLower;

    /**
     * 推荐人数
     */
    @Transient
    private String recommend;

    /**
     * 已面试
     */
    @Transient
    private String interviewed;

    /**
     * 已报到
     */
    private String arReported;
    /**
     * 未报到
     */
    @Transient
    private String noReported;
    /**
     * 未面试
     */
    @Transient
    private String noInterview;

    /**
     * 每个推荐人下的求职者
     */
    @Transient
    private String jobSeeker;

    /**
     * 违规记录
     */
    @Transient
    private String violationRecord;

    @Column(name = "RecommendInfo")
    @ApiModelProperty(value = "推荐说明")
    private String recommendInfo;

    @Column(name = "RecommenderId")
    @ApiModelProperty(value = "推荐人id")
    private Integer recommenderId;

    @Column(name = "LoginIp")
    @ApiModelProperty(value = "登录ip")
    private String loginIp;

    @Column(name = "LoginTime")
    @ApiModelProperty(value = "登录时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

    @Column(name = "DeleteFlag")
    @ApiModelProperty(value = "是否删除")
    private Integer deleteFlag;

    @Column(name = "type")
    @ApiModelProperty(value = "身份")
    private Integer type;

    @Column(name = "isPublic")
    @ApiModelProperty(value = "是否公开")
    private Integer publicIs;

    @Transient
    @ApiModelProperty(value = "signUp 集合")
    private List<HrSignUp> signUp;

    private List<HrGroup> groups;

    @Column(name = "agreeHelp")
    @ApiModelProperty(value = "是否同意平台帮助")
    private Integer agreeHelp;

    @Column(name = "status")
    @ApiModelProperty(value = "启用 还是禁止")
    private Integer status;

    @ApiModelProperty(value = "学历")
    @Column(name = "education")
    private String education;

    @ApiModelProperty(value = "专业")
    @Column(name = "profession")
    private String profession;

    @ApiModelProperty(value = "毕业时间")
    @Column(name = "graduationTime")
    private Date graduationTime;

    @ApiModelProperty(value = "到岗时间")
    @Column(name = "arrivalTime")
    private String arrivalTime;

    @ApiModelProperty(value = "期望薪资上限")
    @Column(name = "expectedSalaryUpper")
    private BigDecimal expectedSalaryUpper;

    @Transient
    private String groupName;

    @ApiModelProperty(value = "期望薪资下限")
    @Column(name = "expectedSalaryLower")
    private BigDecimal expectedSalaryLower;

    @ApiModelProperty(value = "推荐人数")
    @Transient
    private String recommendedNumber;

    @ApiModelProperty(value = "期望薪资")
    @Transient
    private String expectedSalary;

    /**
     * 违规状态 0 未违规 1 违规 (违规了就停权)
     */
    @Column(name = "violation_flag")
    private Integer violationFlag;

    /**
     * 违规时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "violation_time")
    private LocalDateTime violationTime;

    @Transient
    private List<HrSignupDeliveryrecord> deliveryrecords;

    @Transient
    private String inteview;

    @Transient
    private String report;


}
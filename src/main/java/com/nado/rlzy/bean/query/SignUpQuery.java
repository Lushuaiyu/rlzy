package com.nado.rlzy.bean.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName 新增求职表
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/30 16:22
 * @Version 1.0
 */

@Data
@ApiModel(description = "报名表")
public class SignUpQuery {
    @ApiModelProperty(value = "报名表主键id")
    @Column(name = "id")
    private Integer id;

    @ApiModelProperty(value = "用户表Id(推荐人Id)")
    @Column(name = "user_id")
    private Integer userId;

    @ApiModelProperty(value = "组织表Id")
    @Column(name = "group_id")
    private Integer groupId;

    @ApiModelProperty(value = "对应的简章id")
    @Transient
    private Integer briefChapterId;

    @ApiModelProperty(value = "用户名")
    @Column(name = "user_name")
    private String userName;

    @ApiModelProperty(value = "0:女  1:男")
    @Column(name = "sex")
    private Integer sex;

    @ApiModelProperty(value = "身份证")
    @Column(name = "id_card")
    private String idCard;

    @ApiModelProperty(value = "学历")
    @Column(name = "education")
    private String education;



    @ApiModelProperty(value = "专业")
    @Column(name = "profession")
    private String profession;


    @ApiModelProperty(value = "求职状态 0已报名 1待面试 7已面试 2待报到 3待返佣 4不合适 5被邀请 6已完成")
    @Transient
    private Integer jobStatus;


    @ApiModelProperty(value = "身份类型id")
    @Transient
    private Integer typeId;

    @ApiModelProperty(value = "0不合适 1未面试 2未报道 3面试未通过")
    @Transient
    private Integer noPassReason;

    @ApiModelProperty(value = "报名岗位id")
    @Column(name = "registration_position_id")
    private String registrationPositionId;

    @ApiModelProperty(value = "年龄")
    @Column(name = "age")
    private Integer age;

    @ApiModelProperty(value = "与推荐人关系")
    @Column(name = "relation")
    private Integer relation;

    @ApiModelProperty(value = "是否公开")
    @Column(name = "it_is_public")
    private Integer itIsPublic;

    @ApiModelProperty(value = "是否获取平台帮助")
    @Column(name = "agree_platform_help")
    private Integer agreePlatformHelp;

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

    @ApiModelProperty(value = "推荐说明")
    @Transient
    private Integer recommendInfo;

    @ApiModelProperty(value = "招聘端 直接录取 返佣非推荐者")
    @Transient
    private BigDecimal value;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "面试时间")
    private Date interviewTime;



    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "报到时间")
    @Transient
    private Date registerTime;

    @ApiModelProperty(value = "面试时间标识")
    @Transient
    private Integer interviewTimeFlag;

    @ApiModelProperty(value = "报到时间标识")
    @Transient
    private Integer reportTimeFlag;

    @ApiModelProperty(value = "未报到原因")
    @Transient
    private Integer noReportReason;





}
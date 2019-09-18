package com.nado.rlzy.db.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 招聘简章
 *
 * @Author lushuaiyu
 **/
@Data
@ApiModel(description = "发布简章dto")
public class HrBriefchapter {
    @ApiModelProperty(value = "自增主键id")
    private Integer id;


    @ApiModelProperty(value = "简章id")
    private Integer brId;

    private Integer collectId;

    @ApiModelProperty(value = "被招聘企业ID")
    private Integer recruitedcompanyId;

    @ApiModelProperty(value = "招聘岗位ID")
    private Integer postId;
    private String postIdd;

    @ApiModelProperty(value = "岗位名称")
    private String postName;

    @ApiModelProperty(value = "代招单位下的企业 | 招聘单位")
    private String recruitedCompany;

    @ApiModelProperty(value = "代招单位")
    private String certifier;

    @ApiModelProperty(value = "公司地址")
    private String groupAddress;

    @ApiModelProperty(value = "工作地点")
    private String groupAddress2;

    @ApiModelProperty(value = "是否返佣 0:无返佣 1:有返佣")
    private Integer rebate;
    private String rebateDetail;

    @ApiModelProperty(value = "招聘人数")
    private Integer recruitingNo;
    private String no;

    @ApiModelProperty(value = "综合工资")
    private BigDecimal avgSalary;

    private String avgSalary1;

    @ApiModelProperty(value = "结算工资")
    private BigDecimal detailSalary;

    private String detailSalry1;

    @ApiModelProperty(value = "结算工资方式")
    private String detailSalaryWay;

    private String detailSalaryWayId;

    @ApiModelProperty(value = "学历")
    private String educationId;
    private String education;

    @ApiModelProperty(value = "经验")
    private String experienceId;
    @ApiModelProperty(value = "返回给前台 经验")
    private String experience;
    private String experience1;


    @ApiModelProperty(value = "福利")
    private String welfareId;

    @ApiModelProperty(value = "福利miao")
    private String welfare;

    @ApiModelProperty(value = "职位描述")
    private String postDetail;

    /**
     * 职位描述 图片
     */
    private String descriptionJobPhotoUrl;

    @ApiModelProperty(value = "面试时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime interviewTime;

    @ApiModelProperty(value = "用人单位面试地址")
    private String interviewAddress;

    @ApiModelProperty(value = "非用人单面试地址")
    private String noEmployerAddress;

    @ApiModelProperty(value = "0用人单位 1非用人单位")
    private Integer interviewAddressFlag;

    @ApiModelProperty(value = "代招单位面试地址")
    private String interviewAddress2;

    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    @ApiModelProperty(value = "报道时间")
    private Date registerTime;

    @ApiModelProperty(value = "合同签订方")
    private Integer contractWay;

    /**
     * 合同签订方名字
     */
    private String contractWayDetail;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "合同开始时间")
    private LocalDateTime contractStartTime;

    @ApiModelProperty(value = "合同签订方详情id")
    private Integer contractWayDetailId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "合同到期时间")
    private LocalDateTime contractTime;

    @ApiModelProperty(value = "录取方式")
    private Integer hireWay;
    private String hireWayDetail;

    @ApiModelProperty(value = "返佣")
    private Integer rebateStatus;

    @ApiModelProperty(value = "提交人")
    private Integer userId;

    @ApiModelProperty(value = "审核状态")
    private Integer status;

    @ApiModelProperty(value = "提交认证人id")
    private Integer certifierId;

    @ApiModelProperty(value = "备注 审核不通过时会用到")
    private String remark;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "审核通过时间")
    private LocalDateTime checkedtime;

    @ApiModelProperty(value = "浏览次数")
    private Integer readnum;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createtime;

    @ApiModelProperty(value = "删除标识位")
    private Integer deleteflag;

    @ApiModelProperty(value = "男员工年龄段id")
    private Integer manAgeId;

    @ApiModelProperty(value = "女员工年龄段id")
    private Integer womenAgeId;

    @ApiModelProperty(value = "男员工年龄段")
    private String manAge;
    private String manAge1;


    @ApiModelProperty(value = "女员工年龄段")
    private String womenAge;

    private String womenAge1;

    @ApiModelProperty(value = "工作方式")
    private String workWayId;
    private Integer workWay;

    /**
     * 工作地点
     */
    private String workAddress;

    @ApiModelProperty(value = "工作时间安排")
    private String workTimeArrangeId;
    private Integer workTimeArrange;

    @ApiModelProperty(value = "服装要求")
    private String clothingReguirementId;
    private Integer clothingReguirement;

    @ApiModelProperty(value = "兴趣爱好")
    private String hobbyId;
    private String hobby;

    @ApiModelProperty(value = "求职表id")
    private Integer signUpId;

    private String overtimeTime1;

    @ApiModelProperty(value = "加班时长")
    private Integer overtimeTimeId;
    private String overtimeTimeId2;
    private String overtimeTime;

    @ApiModelProperty(value = "职位描述")
    private String descriptionJob;

    @ApiModelProperty(value = "公司面试地址类型")
    private String interviewAddressType;

    @ApiModelProperty(value = "用工形式")
    private String employmentForms;

    @ApiModelProperty(value = "是否接受平台推荐简历")
    private Integer acceptRecommendedResume;
    private String acceptRecommendedResumeDetail;

    @ApiModelProperty(value = "用人单位证明图片url")
    private String employerCertificatePhotoUrl;

    @ApiModelProperty(value = "男人招聘数量")
    private Integer manNum;
    private String manNum1;

    @ApiModelProperty(value = "女人招聘数量")
    private Integer womenNum;
    private String womenNum1;


    @ApiModelProperty(value = "专业id")
    private String professionId;
    private String profession;

    @ApiModelProperty(value = "返佣金额")
    private List<HrRebaterecord> rebat;

    @ApiModelProperty(value = "访问量")
    private Integer readNum;

    @ApiModelProperty(value = "公司")
    private List<HrGroup> group;

    @ApiModelProperty(value = "公司id")
    private Integer groupId;

    @ApiModelProperty(value = "返佣的钱")
    private String rebateRecord;


    @ApiModelProperty(value = "报名未通过原因")
    private Integer noPassReason;

    /**
     * 求职状态
     */
    private Integer jobStatus;

    @ApiModelProperty(value = "求职表 性别")
    private Integer sex;

    /**
     * 0 已上架 1已下架
     */
    private Integer briefChapterStatus;

    /**
     * 返佣类型
     */
    private Integer rebateType;


    /**
     * 男生面试返佣
     */
    private BigDecimal rebateMaleInterview;
    private String rebateMaleInterview1;

    /**
     * 女生面试返佣
     */
    private BigDecimal rebateFemaleInterview;
    private String rebateFemaleInterview1;

    /**
     * 男生报道返佣
     */
    private BigDecimal rebateMaleReport;
    private String rebateMaleReport1;

    /**
     * 女生报道返佣
     */
    private BigDecimal rebateFemaleReport;
    private String rebateFemaleReport1;

    /**
     * 男生入职返佣
     */
    private BigDecimal rebateMaleEntry;
    private String rebateMaleEntry1;

    /**
     * 女生入职返佣
     */
    private BigDecimal rebateFemaleEntry;
    private String rebateFemaleEntry1;

    /**
     * 返回给前台的入职返佣
     */
    private List<EntryResignation> rebateEntryResignation1;

    /**
     * 入职返佣时间
     */
    private Date rebateTimeEntry;

    /**
     * 入职返佣
     */
    private List<EntryResignation> rebateEntryResignation;

    /**
     * 被推荐人名字
     */
    private String userName;

    /**
     * 报名投递表 id
     */
    private Integer hsdId;




}
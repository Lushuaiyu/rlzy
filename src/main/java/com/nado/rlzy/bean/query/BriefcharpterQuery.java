package com.nado.rlzy.bean.query;

import com.nado.rlzy.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName 招聘简章query
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/6/27 12:32
 * @Version 1.0
 */
@Data
@ApiModel(description = "招聘简章query")
public class BriefcharpterQuery extends BaseQuery {

    @ApiModelProperty(value = "简章id")
    private Integer briefcharpterId;

    @ApiModelProperty(value = "招聘公司下的公司")
    private String recruitedCompany;

    @ApiModelProperty(value = "招聘单位 | 代招单位")
    private String certifier;

    @ApiModelProperty(value = "职位")
    private int[] postName;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "简章状态")
    private Integer status;

    @ApiModelProperty(value = " 专业id")
    private String professionId;

    @ApiModelProperty(value = "职位id")
    private String postId;


    @ApiModelProperty(value = "男生年龄 id")
    private Integer manAgeId;

    @ApiModelProperty(value = "女生年龄 id")
    private Integer womenAgeId;

    @ApiModelProperty(value = "结算工资方式")
    private String detailSalaryWay;

    @ApiModelProperty(value = "性别")
    private Integer sex;


    @ApiModelProperty(value = "学历id 学历可多选")
    private String educationId;


    @ApiModelProperty(value = "招聘企业面试地址 ")
    private String interviewAddress;


    @ApiModelProperty(value = "用工形式 学生专区")
    private Integer [] contractWayDetailId1;

    @ApiModelProperty(value = "代招单位面试地址")
    private String interviewAddress2;

    @ApiModelProperty(value = "工作年限id")
    private Integer experienceId;


    /**
     * 工作地点
     */
    private String workAddress;

    @ApiModelProperty(value = "工作方式id")
    private Integer workWayId;

    @ApiModelProperty(value = "期望工作地点")
    private String expectAddress;

    @ApiModelProperty(value = "工作时间安排id")
    private String workTimeArrangeId;

    @ApiModelProperty(value = "服装要求id")
    private String clothingRequirementId;

    @ApiModelProperty(value = "爱好id")
    private String hobbyId;

    @ApiModelProperty(value = "加班时间ID")
    private Integer overtimeTimeId;
    @ApiModelProperty(value = "福利id")
    private String welfareId;


    @ApiModelProperty(value = "职位描述")
    private String postDetail;

    @ApiModelProperty(value = "面试开始时间")
    private String interviewStartTime;

    @ApiModelProperty(value = "面试结束时间")
    private String interviewEndTime;

    @ApiModelProperty(value = "工作地址")
    private String groupAddress;


    @ApiModelProperty(value = "报道开始时间")
    private String registerStartTime;

    @ApiModelProperty(value = "报道结束时间")
    private String registerEndTime;

    @ApiModelProperty(value = "合同到期时间")
    private String contractTime;

    @ApiModelProperty(value = "录取方式")
    private Integer hireWay;

    @ApiModelProperty(value = "是否接受平台推荐简历")
    private Integer acceptRecommendedResume;

    @ApiModelProperty(value = "招聘单位")
    private String RecruitmentUnit;

    @ApiModelProperty(value = "用工单位")
    private String EmploymentUnit;

    @ApiModelProperty(value = "招聘人数 男")
    private Integer manNum;

    @ApiModelProperty(value = "招聘人数 女")
    private Integer womenNum;

    @ApiModelProperty(value = "合同签订方")
    private Integer contractWay;

    @ApiModelProperty(value = "用工形式id")
    private Integer contractWayDetailId;

    @ApiModelProperty(value = "是否有返佣")
    private String rebateStatus;

    @ApiModelProperty(value = "返佣类型")
    private Integer rebateType;

    @ApiModelProperty(value = "身份类型")
    private Integer type;

    @ApiModelProperty(value = "类型变量")
    private Integer type1;

    @ApiModelProperty(value = "返佣时间")
    private String rebateTime;

    @ApiModelProperty(value = "返佣男")
    private String rebateMale;

    @ApiModelProperty(value = "返佣女")
    private String rebateFemale;

    @ApiModelProperty(value = "被招聘企业id")
    private Integer recruitedCompanyId;

    @ApiModelProperty(value = "提交认证人id")
    private Integer certifierId;

    /**
     * 0 已上架 1已下架
     */
    private Integer briefChapterStatus;

    /**
     * 到岗时间
     */
    private Date arrivalTime;

    /**
     * 毕业时间
     */
    private Date graduationTime;

    /**
     * 期望薪资下限
     */
    private BigDecimal expectedSalaryLower;

    /**
     * 期望薪资上限
     */
    private BigDecimal expectedSalaryUpper;

    /**
     * 学历
     */
    private String education;

    /**
     * 意向岗位
     */
    private String registrationPositionId;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 男生面试返佣
     */
    private BigDecimal rebateMaleInterview;

    /**
     * 女生面试返佣
     */
    private BigDecimal rebateFemaleInterview;

    /**
     * 男生报道返佣
     */
    private BigDecimal rebateMaleReport;

    /**
     * 女生报道返佣
     */
    private BigDecimal rebateFemaleReport;

    /**
     * 男生入职返佣
     */
    private BigDecimal rebateMaleEntry;



    /**
     * 返佣时间入职
     */
    private Date rebateTimeEntry;
}
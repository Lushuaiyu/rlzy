package com.nado.rlzy.bean.query;

import com.nado.rlzy.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName 招聘端 -- 发布简章query
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/6/28 16:41
 * @Version 1.0
 */
@Data
@ApiModel(description = "发布简章入参")
public class ReleaseBriefcharpterQuery extends BaseQuery {
    private Integer id;

    /**
     * 返佣表的简章id
     */
    private Integer briefcharpterId;

    @ApiModelProperty(value = "简章状态")
    private Integer status;

    @ApiModelProperty(value = " 专业id")
    private String professionId;

    private Integer recruitingNo;


    /**
     * 阅读人数
     */
    private Integer readNum;

    /**
     * 综合工资
     */
    private String avgSalary;

    /**
     * 结算工资
     */
    private String detailSalary;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "职位id")
    private Integer postId;

    @ApiModelProperty(value = "职位名称")
    private String postName;

    @ApiModelProperty(value = "man age id")
    private Integer manAgeId;

    /**
     * 0:无返佣 1:有返佣
     */
    private String rebate;

    @ApiModelProperty(value = "性别")
    private Integer sex;


    @ApiModelProperty(value = "women age id")
    private Integer womenAgeId;

    @ApiModelProperty(value = "记xin方式")
    private String detailSalaryWay;

    @ApiModelProperty(value = "学历id")
    private String educationId;

    @ApiModelProperty(value = "用人单位面试地址")
    private String interviewAddress;

    @ApiModelProperty(value = "非用人单面试地址")
    private String noEmployerAddress;

    @ApiModelProperty(value = "用人单位证明图片url")
    private String employerCertificatePhotoUrl;


    @ApiModelProperty(value = "工作年限id")
    private String experienceId;

    @ApiModelProperty(value = "工作方式id")
    private String workWayId;

    @ApiModelProperty(value = "工作时间安排id")
    private String workTimeArrangeId;

    /**
     * 工作地点
     */
    private String workAddress;

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

    @ApiModelProperty(value = "面试时间")
    private String interviewTime;

    @ApiModelProperty(value = "面试地址")
    private String groupAddress;

    @ApiModelProperty(value = "报道时间")
    private String registerTime;

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

    @ApiModelProperty(value = "合同签订方详情id")
    private String contractWayDetailId;

    @ApiModelProperty(value = "是否有返佣")
    private Integer rebateStatus;

    @ApiModelProperty(value = "返佣类型")
    private Integer rebateType;

    @ApiModelProperty(value = "返佣男")
    private String rebateMale;

    /**
     * 职位描述图片url 多张图片 传数组
     */
    private String descriptionJobPhotoUrl;

    @ApiModelProperty(value = "返佣女")
    private String rebateFemale;


    @ApiModelProperty(value = "被招聘企业id")
    private Integer recruitedCompanyId;

    @ApiModelProperty(value = "提交认证人id")
    private Integer certifierId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0 已上架 1已下架
     */
    private Integer briefChapterStatus;

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
     * 男生入职返佣 总计
     */
    private BigDecimal rebateMaleEntry;

    /**
     * 女生入职返佣 总计
     */
    private BigDecimal rebateFemaleEntry;

    /**
     * 入职返佣
     */
    //private JSONObject rebateEntry;
    // private List<EntryResignation> rebateEntry;

    /**
     * 返佣类型 0 面试 报道 入职
     */
    private Integer rebType;






}
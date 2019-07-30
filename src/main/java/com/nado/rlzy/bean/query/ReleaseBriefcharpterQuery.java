package com.nado.rlzy.bean.query;

import com.nado.rlzy.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

    @ApiModelProperty(value = "简章状态")
    private Integer status;

    @ApiModelProperty(value = " 专业id")
    private String professionId;

    @ApiModelProperty(value = "职位id")
    private Integer postId;

    @ApiModelProperty(value = "职位名称")
    private String postName;

    @ApiModelProperty(value = "man age id")
    private Integer manAgeId;

    @ApiModelProperty(value = "性别")
    private Integer sex;


    @ApiModelProperty(value = "women age id")
    private Integer womenAgeId;

    @ApiModelProperty(value = "记xin方式")
    private String detailSalaryWay;

    @ApiModelProperty(value = "学历id")
    private String educationId;

    @ApiModelProperty(value = "面试地址 0 用人单位 1 非用人单位")
    private Integer interviewAddress;

    @ApiModelProperty(value = "工作年限id")
    private String experienceId;

    @ApiModelProperty(value = "工作方式id")
    private String workWayId;

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


}
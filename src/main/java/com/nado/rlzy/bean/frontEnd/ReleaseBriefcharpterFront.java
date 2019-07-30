package com.nado.rlzy.bean.frontEnd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName 发布简章 返回给前台的对象
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/6/28 16:37
 * @Version 1.0
 */
@Data
@ApiModel(description = "发布简章, 返回给前台的对象")
public class ReleaseBriefcharpterFront {

    @ApiModelProperty(value = "招聘公司")
    private String recruitedCompany;

    @ApiModelProperty(value = "上班公司")

    private String certifier;

    @ApiModelProperty(value = "职位")
    private String postName;

    @ApiModelProperty(value = "职位id")
    private Integer postId;

    @ApiModelProperty(value = "专业")
    private String professionName;

    @ApiModelProperty(value = "专业id")
    private String professionId;

    @ApiModelProperty(value = "计薪方式")
    private String detailsalaryWay;

    @ApiModelProperty(value = "男人年龄min")
    private String manAgeMin;

    @ApiModelProperty(value = "男人年龄max")
    private String manAgeMax;

    @ApiModelProperty(value = "女人年龄min")
    private String womenAgeMin;

    @ApiModelProperty(value = "women age id")
    private Integer womenAgeId;

    @ApiModelProperty(value = "女人年龄id")
    private Integer manAgeId;

    @ApiModelProperty(value = "女人年龄max")
    private String womenAgeMax;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "学历id")
    private String educationId;

    @ApiModelProperty(value = "工作经验")
    private String experience;

    @ApiModelProperty(value = "工作经验id")
    private String experienceId;

    @ApiModelProperty(value = "工作方式")
    private String workWay;

    @ApiModelProperty(value = "工作方式id")
    private String workWayId;

    @ApiModelProperty(value = "工作时间安排")
    private String workTimeArrang;

    @ApiModelProperty(value = "工作时间安排")
    private String workTimeArrangeId;

    @ApiModelProperty(value = "服装要求")
    private String clothingRequirement;

    @ApiModelProperty(value = "服装要求id")
    private String clothingRequirementId;

    @ApiModelProperty(value = "工作地点")
    private String workAddress;

    @ApiModelProperty(value = "兴趣爱好")
    private String hobby;

    @ApiModelProperty(value = "兴趣爱好")
    private String hobbyId;

    @ApiModelProperty(value = "加班时长min")
    private String overtimeTimesMin;

    @ApiModelProperty(value = "加班时长max")
    private String overtimeTimesMax;

    @ApiModelProperty(value = "加班时长id")
    private Integer overtimeTimeId;

    @ApiModelProperty(value = "福利")
    private String welfare;

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
    private String hireWay;

    @ApiModelProperty(value = "是否接受平台推荐简历")
    private String acceptRecommendedResume;

    @ApiModelProperty(value = "招聘单位")
    private String RecruitmentUnit;

    @ApiModelProperty(value = "用工单位")
    private String EmploymentUnit;

    @ApiModelProperty(value = "招聘人数 男")
    private String manNum;

    @ApiModelProperty(value = "招聘人数 女")
    private String womenNum;

    @ApiModelProperty(value = "合同签订方")
    private String contractWay;

    @ApiModelProperty(value = "合同签订方详情id")
    private String contractWayDetailId;
}
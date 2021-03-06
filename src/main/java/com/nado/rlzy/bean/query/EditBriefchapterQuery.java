package com.nado.rlzy.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName 招聘端 我的发布 编辑简章
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/24 13:39
 * @Version 1.0
 */
@Data
@ApiModel(description = "编辑简章query")
@AllArgsConstructor
@NoArgsConstructor
public class EditBriefchapterQuery {

    private Integer id;
    /**
     * 代招单位
     */
    private String recruitedCompanyId;

    private Integer postId;

    private Integer recruitingNo;

    private BigDecimal avgSalary;

    private BigDecimal detailSalary;

    private String detailSalaryWay;

    private String educationId;

    private String experienceId;

    private String welfareId;

    private String postDetail;

    private String interviewTime;

    private String interviewAddress;

    private String registerTime;

    private Integer contractWay;

    private String contractTime;

    private Integer hireWay;

   private Integer status;

   private Integer manAgeId;

   private Integer womenAgeId;

   private Integer professionId;

   private Integer workWayId;

   private Integer workTimeArrangeId;

   private Integer clothingRequirementId;

   private String workAddress;

   private Integer hobbyId;

   private Integer overtimeTimeId;



   private String descriptionJobPhotoUrl;



   private String employerCertificatePhotoUrl;

   private String noEmployerAddress;

   private Integer contractWayDetailId;

   private Integer acceptRecommendedResume;



    @ApiModelProperty(value = "编辑简章时 返佣的钱 男")
    private BigDecimal rebateNowMan;

    @ApiModelProperty(value = "编辑简章时 返佣的钱 女")
    private BigDecimal rebateNowWomen;

    @ApiModelProperty(value = "编辑简章时 招聘人数 男")
    private Integer manNumNow;

    @ApiModelProperty(value = "编辑简章时 招聘人数 女")
    private Integer womenNumNow;

    @ApiModelProperty(value = "简章id")
    private Integer briefchapter;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "面试返佣")
    private Integer rebateTypeZero;

    @ApiModelProperty(value = "报道返佣")
    private Integer rebateTypeOne;

    @ApiModelProperty(value = "入职返佣")
    private Integer rebateTypeTwo;

    /**
     * 有无返佣 0 无返佣 1 有返佣
     */
    private Integer rebate;
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
     * 男生入职返佣 临时变量
     */
    private BigDecimal rebateMEntry;

    /**
     * 女生入职返佣
     */
    private BigDecimal rebateFemaleEntry;

    /**
     * 女生入职返佣 临时变量
     */
    private BigDecimal rebateFentry;


    /**
     * 1 代招单位 2 招聘单位
     */
    private Integer typp;


    /**
     * 入职返佣 男 女
     */
    private String resignations;

    private BigDecimal rebateMoney;

    /**
     * 发布简章时的返佣表的id
     */
    private Integer resignationId;


}
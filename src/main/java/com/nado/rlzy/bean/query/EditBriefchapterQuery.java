package com.nado.rlzy.bean.query;

import com.nado.rlzy.db.pojo.EntryResignation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

   /* @ApiModelProperty(value = "面试返佣金额 男")
    private BigDecimal addMoneyMan;
    @ApiModelProperty(value = "面试返佣金额 女")
    private BigDecimal addMoneyWomen;

    @ApiModelProperty(value = "报道返佣男")
    private BigDecimal reportMan;

    @ApiModelProperty(value = "报道返佣 女")
    private BigDecimal reportWomen;

    @ApiModelProperty(value = "入职返佣 男")
    private BigDecimal entryMan;

    @ApiModelProperty(value = "入职返佣 女")
    private BigDecimal entryWomen;*/

    /**
     * 男生面试返佣
     */
    private BigDecimal rebateMaleInterview = BigDecimal.valueOf(0);

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
    private BigDecimal rebateFemaleReport ;

    /**
     * 男生入职返佣
     */
    private BigDecimal rebateMaleEntry ;

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
     * 返佣时间入职
     */
    private Date rebateTimeEntry;

    /**
     *  1 代招单位 2 招聘单位
     */
    private Integer typp;


    /**
     * 入职返佣 男 女
     */
    private List<EntryResignation> resignations;

    private BigDecimal rebateMoney;

    /**
     * 发布简章时的返佣表的id
     */
    private Integer resignationId;



}
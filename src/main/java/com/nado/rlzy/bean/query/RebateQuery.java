package com.nado.rlzy.bean.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName 返佣入参
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/30 17:20
 * @Version 1.0
 */
@Data
public class RebateQuery {

    private Integer id;

    @ApiModelProperty(value = "求职者id")
    private Integer userId;

    @ApiModelProperty(value = "增加的钱")
    private BigDecimal addMoney;
    @ApiModelProperty(value = "减少的钱")
    private BigDecimal subtraction;
    @ApiModelProperty(value = "身份id")
    private Integer typeId;
    @ApiModelProperty(value = "返佣状态")
    private Integer rebateStatus;

    @ApiModelProperty(value = "求职表id")
    private Integer signUpId;

    private Integer signupDeliveryrecordId;

    private Integer rebateId;
    /**
     * 性别
     */
     private Integer sex;

    /**
     * 返佣类型 1 招聘详情 待返佣 查询返佣 2 招聘详情 待返佣 不返佣
     * 3招聘详情 待返佣 返佣过程 需要signUpId 知道是返佣给哪个报名者
     */
    private Integer type;

    /**
     * 商户的userId
     */
    private Integer busInessUserId;

    /**
     * 简章id
     */
    private Integer briefchapterId;

    /**
     * 返佣的金额
     */
    private String rebateMon;

    /**
     * 是否返佣 0 不返佣 1 返佣
     */
    private Integer rebateType;

}
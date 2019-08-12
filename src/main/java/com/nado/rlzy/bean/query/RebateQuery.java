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
    @ApiModelProperty(value = "用户id")
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

    @ApiModelProperty(value = "")
    private Integer rebateId;
    /**
     * 性别
     */
     private Integer sex;

    /**
     * 类型
     */
    private Integer type;

}
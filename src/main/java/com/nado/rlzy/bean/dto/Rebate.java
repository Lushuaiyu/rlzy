package com.nado.rlzy.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName 返佣金额
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/3 15:32
 * @Version 1.0
 */
@Data
@ApiModel(description = "返佣金额")
public class Rebate {
    @ApiModelProperty(value = "返佣金额")
    private String rebate;

    @ApiModelProperty(value = "返佣类型")
    private Integer rebateType;
}
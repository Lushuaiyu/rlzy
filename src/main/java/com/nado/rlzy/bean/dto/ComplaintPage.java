package com.nado.rlzy.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName 投诉页
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/8/4 14:34
 * @Version 1.0
 */
@Data
@ApiModel
public class ComplaintPage {
    @ApiModelProperty(value = "求职者 || 推荐人")
    private String userName;

    @ApiModelProperty(value = "被推荐人")
    private String signName;

    @ApiModelProperty(value = "投诉类型name")
    private String dictionaryName;

    @ApiModelProperty(value = "投诉类型value")
    private String dictionaryValue;

    private String id;
}
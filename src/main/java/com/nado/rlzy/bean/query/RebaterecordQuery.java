package com.nado.rlzy.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName 返佣
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-06-30 09:38
 * @Version 1.0
 */
@Data
@ApiModel(description = "返佣")
public class RebaterecordQuery {

    private String rebateMale;

    private String rebateFemale;

    @ApiModelProperty(value = "返佣类型")
    private Integer rebateType;

    private String rebateTime;
}

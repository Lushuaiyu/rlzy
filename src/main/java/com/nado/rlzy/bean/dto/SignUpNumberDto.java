package com.nado.rlzy.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName 判断报名人数
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/17 11:04
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "报名人数 dto")
public class SignUpNumberDto {
    @ApiModelProperty(value = "报名改简章的女生的数量")
    private Integer girlNum;

    @ApiModelProperty(value = "报名该简章的男生数量")
    private Integer boyNum;

    @ApiModelProperty(value = "该简章的招聘人数 男")
    private Integer manNum;

    @ApiModelProperty(value = "该简章的招聘人数 女")
    private Integer womenNum;

    @ApiModelProperty(value = "是否招聘人数满了")
    private Integer full;
}
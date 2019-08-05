package com.nado.rlzy.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName baseQuery
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/6/27 17:00
 * @Version 1.0
 */
@Data
@ApiModel(value = "分页参数")
public class BaseQuery {
    @ApiModelProperty(value = "当前页")
    private Integer pageNum;
    @ApiModelProperty(value = "每页显示的数量")
    private Integer limit;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "确认密码")
    private String confirmPassword;

    @ApiModelProperty(value = "单位类型 | 身份类型 1 本人 2 推荐人 5招聘单位 6 代招单位")
    private Integer unitType;
    @ApiModelProperty(value = "头像")
    private String imageHead;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "身份证")
    private String idCard;
}
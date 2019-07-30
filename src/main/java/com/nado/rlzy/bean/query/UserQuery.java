package com.nado.rlzy.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName userQuery 登陆注册入参
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/6/29 10:43
 * @Version 1.0
 */
@Data
@ApiModel(description = "登录注册入参")
public class UserQuery {
    @ApiModelProperty(value = "手机号")
    String phone;

    @ApiModelProperty(value = "密码")
    String password;

    @ApiModelProperty(value = "短信验证码")
    String code;
}
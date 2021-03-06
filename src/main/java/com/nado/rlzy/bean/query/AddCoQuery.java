package com.nado.rlzy.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName 添加企业
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/1 15:36
 * @Version 1.0
 */
@Data
@ApiModel(description = "添加企业入参")
public class AddCoQuery {
    @ApiModelProperty(value = "公司名称")
    private String CoName;

    /**
     * 企业id
     */
    private Integer groupId;

    @ApiModelProperty(value = "公司地址")
    private String CoAddress;


    @ApiModelProperty(value = "公司简介")
    private String CompanyProfile;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "公司营业执照")
    private String busLicense;

    /**
     * 代招证明
     */
    private String helpProve;



    /**
     * 1 添加企业 | 2 身份认证失败说明
     */
    public Integer type;
}
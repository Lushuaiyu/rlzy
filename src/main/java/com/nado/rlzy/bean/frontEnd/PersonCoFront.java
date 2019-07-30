package com.nado.rlzy.bean.frontEnd;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName 查询我的企业 返回到前台的对象
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/1 14:55
 * @Version 1.0
 */
@Data
@ApiModel(description = "查询我的企业 返回到前台的对象")
public class PersonCoFront {
    @ApiModelProperty(value = "代招单位")
    private String recruitedCompany;
    @ApiModelProperty(value = "招聘单位")
    private String certifier;

    @ApiModelProperty(value = "身份")
    private String type;

    @ApiModelProperty(value = "代招单位地址")
    private String recruitedCompanyAddress;

    @ApiModelProperty(value = "招聘单位地址")
    private String certifierAddress;

    @ApiModelProperty(value = "代招单位人数")
    private String recPersonNum;

    @ApiModelProperty(value = "招聘单位人数")
    private String certifierNum;

    @ApiModelProperty(value = "代招单位制度")
    private String recCoPolicy;

    @ApiModelProperty(value = "招聘单位制度")
    private String certifierPolicy;

    @ApiModelProperty(value = "代招单位状态")
    private String recStatus;

    @ApiModelProperty(value = "招聘单位状态")
    private String cerStatus;

    @ApiModelProperty(value = "代招单位营业执照")
    private String busLicenseRec;

    @ApiModelProperty(value = "招聘单位营业执照")
    private String busLincenseCer;

}
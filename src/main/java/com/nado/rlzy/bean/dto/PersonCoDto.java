package com.nado.rlzy.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName 我的企业dto
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/1 14:24
 * @Version 1.0
 */
@Data
@ApiModel(description = "我的企业dto" )
public class PersonCoDto {

    @ApiModelProperty(value = "招聘单位")
    private String certifier;



    @ApiModelProperty(value = "身份")
    private Integer type;




    @ApiModelProperty(value = "招聘单位地址")
    private String certifierAddress;

    @ApiModelProperty(value = "招聘单位人数")
    private String certifierNum;

    @ApiModelProperty(value = "招聘单位制度")
    private String certifierPolicy;

    @ApiModelProperty(value = "招聘单位状态")
    private Integer cerStatus;



    @ApiModelProperty(value = "招聘单位营业执照")
    private String busLincenseCer;


}
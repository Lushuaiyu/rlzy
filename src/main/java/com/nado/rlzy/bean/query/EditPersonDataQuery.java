package com.nado.rlzy.bean.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName 编辑个人资料入参
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/9 15:16
 * @Version 1.0
 */
@Data
@ApiModel(description = "编辑个人资料入参")
public class EditPersonDataQuery {

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "身份类型id")
    private Integer typeId;

    @ApiModelProperty(value = "头像")
    private String headImage;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "0:女  1:男")
    private Integer sex;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "推荐人的意向岗位")
    private String postIdStr;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "毕业时间")
    private LocalDateTime graduationTime;

    @ApiModelProperty(value = "专业")
    private String profession;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "到岗时间")
    private LocalDateTime arrivalTime;

    @ApiModelProperty(value = "报名岗位id")
    private String registrationPositionId;

    @ApiModelProperty(value = "期望薪资上限")
    private BigDecimal expectedSalaryUpper;

    @ApiModelProperty(value = "期望薪资下限")
    private BigDecimal expectedSalaryLower;

    @ApiModelProperty(value = "推荐人数量下限")
    private Integer recommendNoLower;

    @ApiModelProperty(value = "推荐人数量上限")
    private Integer recommendNoUpper;

    @ApiModelProperty(value = "是否公开")
    private Integer itIsPublic;

    @ApiModelProperty(value = "是否获取平台帮助" )
    private Integer agreePlatformHelp;

    @ApiModelProperty(value = "推荐说明")
    private String recommendInfo;

    @ApiModelProperty(value = "推荐人id")
    private Integer recommenderId;

    @ApiModelProperty(value = "图片url")
    private String url;
}
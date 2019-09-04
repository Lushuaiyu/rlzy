package com.nado.rlzy.bean.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.nado.rlzy.base.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @ClassName 求职端登录注册入参
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/29 9:34
 * @Version 1.0
 */
@Data
public class RecruitmentSideRegisterHobHuntingQuery extends BaseQuery {

    @ApiModelProperty(value = "性别")
    private Integer sex;
    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "毕业时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date graduationTime;

    @ApiModelProperty(value = "专业")
    private String profession;

    @ApiModelProperty(value = "意向岗位")
    private String registrationPositionId;

    @ApiModelProperty(value = "到岗时间")
    private String arrivalTime;

    @ApiModelProperty(value = "期望工资上限")
    private String expectedSalaryUpper;

    private String expectedSalaryLower;

    @ApiModelProperty(value = "是否公开")
    private Integer itIsPublic;

    @ApiModelProperty(value = "是否获取平台帮助 (0: 获取 1: 不获取)")
    private Integer agreePlatformHelp;

    @ApiModelProperty(value = "推荐人意向岗位")
    private String postIdStr;

    @ApiModelProperty(value = "推荐人数")
    private Integer recommendNo;

    @ApiModelProperty(value = "推荐说明")
    private String recommendInfo;

    @ApiModelProperty(value = "图片上传")
    private MultipartFile file;

    @ApiModelProperty(value = "求职状态")
    private Integer jobStatus;

    @ApiModelProperty(value = "未通过原因")
    private Integer noPassReason;

    @ApiModelProperty(value = "未报到原因")
    private Integer noReportReason;



}
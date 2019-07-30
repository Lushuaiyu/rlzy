package com.nado.rlzy.bean.frontEnd;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName 查询求职列表front
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/2 14:12
 * @Version 1.0
 */
@Data
@ApiModel(description = "查询求职列表front")
public class JobListtFront implements Serializable {
    private static final long serialVersionUID = -4520222154602447886L;

    private String id;

    @ApiModelProperty(value = "求职者姓名")
    private String userName;

    @ApiModelProperty(value = "简章id")
    private String briefChapterId;

    @ApiModelProperty(value = "职位名称")
    private String postName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "身份证号码")
    private String cardId;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "期望工资下限")
    private String expectedSalaryLower;

    @ApiModelProperty(value = "期望工资上限")
    private String expectedSalaryUpper;

    @ApiModelProperty(value = "未报名")
    private String noSignUp;



    @ApiModelProperty(value = "求职者年龄")
    private String age;

    @ApiModelProperty(value = "推荐者姓名")
    private String commendName;

    @ApiModelProperty(value = "求职状态")
    private String jobStatus;

    @ApiModelProperty(value = "推荐人id")
    private Integer userId;


    @ApiModelProperty(value = "报道时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalTime;

    @ApiModelProperty(value = "毕业时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime graduationTime;

    @ApiModelProperty(value = "与推荐者的关系")
    private String relation;


}
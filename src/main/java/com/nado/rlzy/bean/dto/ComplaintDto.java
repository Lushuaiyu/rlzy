package com.nado.rlzy.bean.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.nado.rlzy.bean.model.ComplaintModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName 投诉记录 dto
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/10 10:18
 * @Version 1.0
 */
@Data
@ApiModel(description = "投诉记录 dto")
public class ComplaintDto {

    @ApiModelProperty(value = "企业名称", name = "groupName")
    private String groupName;

    @ApiModelProperty(value = "投诉类型")
    private String complaintType;

    @ApiModelProperty(value = "推荐者姓名")
    private String userName;

    @ApiModelProperty(value = "报名表id")
    private Integer signUpId;

    @ApiModelProperty(value = "被推荐者(求职者)")
    private String signUserName;

    @ApiModelProperty(value = "投诉人")
    private List<ComplaintModel> complaintModels;

    @ApiModelProperty(value = "简章id")
    private Integer briefChapterId;

    @ApiModelProperty(value = "投诉类型id")
    private String complaintTypeId;

    @ApiModelProperty(value = "问题描述")
    private String description;

    @ApiModelProperty(value = "凭证")
    private String evidence;

    @ApiModelProperty(value = "投诉人")
    private String name;

    @ApiModelProperty(value = "投诉人手机号")
    private String phone;

    @ApiModelProperty(value = "投诉状态")
    private String status;

    @ApiModelProperty(value = "是否删除")
    private Integer deleteFlag;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy年MM月dd日")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "投诉id")
    private Integer id;



}
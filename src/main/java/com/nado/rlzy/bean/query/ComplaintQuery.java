package com.nado.rlzy.bean.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * @ClassName 投诉简章入参
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/10 16:39
 * @Version 1.0
 */
@Data
@ApiModel(value = "投诉简章id")
public class ComplaintQuery {


    @ApiModelProperty(value = "投诉id")
    private Integer id;

    @ApiModelProperty(value = "企业名称")
    private String groupName;

    @ApiModelProperty(value = "投诉类型")
    private String complaintType;

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

    /**
     * 联系人
     */
    private String contactPerson;

    @ApiModelProperty(value = "投诉状态")
    private Integer status;

    @ApiModelProperty(value = "是否删除")
    private Integer deleteFlag;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "登录用户身份 (推荐者还是求职者)")
    private Integer type;

    @ApiModelProperty(value = "求职表主键id")
    private Integer signUpId;

    private MultipartFile file;


}
package com.nado.rlzy.db.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "我的求职表")
public class MySignUpTable {
    private Integer id;

    @ApiModelProperty(value = "求职表id")
    private Integer signId;

    @ApiModelProperty(value = "自定义分组名字")
    private String groupName;

    @ApiModelProperty(value = "0 公开 1 不公开 2 平台帮助 3 自定义名称")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @ApiModelProperty(value = "求职者")
    private List<HrSignUp> signUp;


}
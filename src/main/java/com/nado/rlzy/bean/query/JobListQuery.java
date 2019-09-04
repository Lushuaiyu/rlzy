package com.nado.rlzy.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName 查询求职列表query & 新增报名表 query & 我要报名 & 查询推荐人列表
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/2 13:23
 * @Version 1.0
 */
@Data
@ApiModel(description = "查询求职列表 query")
public class JobListQuery {

    @ApiModelProperty(value = "求职表id")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "报名者姓名")
    private String userName;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "专业")
    private String profession;

    @ApiModelProperty(value = "年龄")
    private String age;


    @ApiModelProperty(value = "到岗时间")
    private String arrivalTime;


    @ApiModelProperty(value = "毕业时间")
    private String graduationTime;

    @ApiModelProperty(value = "简章id")
    private Integer briefChapterId;

    @ApiModelProperty(value = "报名岗位id")
    private String registrationPositionId;

    @ApiModelProperty(value = "期望薪资上限")
    private String expectedSalaryUpper;

    @ApiModelProperty(value = "期望薪资下限")
    private String expectedSalaryLower;

    @ApiModelProperty(value = "与被推荐人的关系")
    private Integer relation;

    @ApiModelProperty(value = "是否公开")
    private Integer itIsPublic;

    @ApiModelProperty(value = "是否获取平台帮助")
    private Integer agreePlatformHelp;

    /**
     * 1 求职列表 2 推荐人列表
     */
    private Integer type;




    /**
     * 推荐人id user表主键id
     */
    private String uid;



    /**
     * 推荐人数
     */
    private String recommendNo;

    /**
     * 意向岗位 简章表的postId
     */
    private String postId;







}
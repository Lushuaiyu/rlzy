package com.nado.rlzy.bean.dto;

import com.nado.rlzy.db.pojo.HrPost;
import com.nado.rlzy.db.pojo.HrRebaterecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName 招聘简章dto
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/6/27 11:57
 * @Version 1.0
 */
@Data
@ApiModel(description = "招聘简章dto")
public class HiringPostDto {

    @ApiModelProperty(value = "代招单位")
    private String recruitedCompany;

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "招聘单位")
    private String certifier;

    @ApiModelProperty(value = "职位")
    private String postName;

    @ApiModelProperty(value = "招聘人数")
    private Integer recruitingNo;

    @ApiModelProperty(value = "公司地址")
    private String groupAddress;

    @ApiModelProperty(value = "月综合工资")
    private BigDecimal avgSalary;

    @ApiModelProperty(value = "结算工资")
    private BigDecimal detailSalary;

    @ApiModelProperty(value = "返费")
    private BigDecimal rebateOne;

    @ApiModelProperty(value = "福利")
    private String welfare;

    @ApiModelProperty(value = "工资计算方式")
    private String detailSalaryWay;

    @ApiModelProperty(value = "返佣金额")
    private List<HrRebaterecord> rebate;

    private HrPost post;
}
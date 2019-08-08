package com.nado.rlzy.bean.frontEnd;

import com.nado.rlzy.db.pojo.HrRebaterecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName 返回给前台的对象 简章概览
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/6/27 13:09
 * @Version 1.0
 */
@Data
@ApiModel(description = "返回给前台的对象")
public class BriefcharpterFront {
    private String id;

    @ApiModelProperty(value = "代招单位")
    private String recruitedCompany;


    @ApiModelProperty(value = "招聘单位")
    private String certifier;
    @ApiModelProperty(value = "职位")
    private String postName;
    @ApiModelProperty(value = "招聘人数")
    private String recruitingNo;
    @ApiModelProperty(value = "公司地址")
    private String groupAddress;
    @ApiModelProperty(value = "月综合工资")
    private String avgSalary;
    @ApiModelProperty(value = "结算工资")
    private String detailSalary;
    @ApiModelProperty(value = "返费")
    private String rebateOne;
    @ApiModelProperty(value = "福利")
    private String welfare;

    @ApiModelProperty(value = "返佣的钱")
    private String rebateRecord;

    @ApiModelProperty(value = "返佣金额")
    private List<HrRebaterecord> rebate;
}
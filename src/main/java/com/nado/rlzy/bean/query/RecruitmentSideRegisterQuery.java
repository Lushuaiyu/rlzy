package com.nado.rlzy.bean.query;

import com.nado.rlzy.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName 招聘端注册
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/26 16:39
 * @Version 1.0
 */
@Data
@ApiModel(description = "招聘端注册")
public class RecruitmentSideRegisterQuery extends BaseQuery {



    @ApiModelProperty(value = "公司名称")
    private String groupName;

    @ApiModelProperty(value = "公司地址")
    private String groupAddress;

    @ApiModelProperty(value = "公司简介")
    private String groupInfo;

    @ApiModelProperty(value = "营业执照")
    private String businessLicense;

    @ApiModelProperty(value = "企业许可证")
    private String enterpriseLicense;

    @ApiModelProperty(value = "注册人委托证明")
    private String registrantCertificate;

    @ApiModelProperty(value = "统一社会信用代码")
    private String socialCreditCode;

    @ApiModelProperty(value = "法人")
    private String legalPerson;

    @ApiModelProperty(value = "注册地")
    private String registrationPlace;

    @ApiModelProperty(value = "图片上传")
    private MultipartFile file;

    @ApiModelProperty(value = "提交人身份类型")
    private Integer type;

    @ApiModelProperty(value = "父id")
    private Integer pid;

    @ApiModelProperty(value = "企业状态 禁用还是启用")
    private Integer groupStatus;


}
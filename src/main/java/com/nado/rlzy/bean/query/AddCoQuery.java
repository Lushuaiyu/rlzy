package com.nado.rlzy.bean.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName 添加企业
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/1 15:36
 * @Version 1.0
 */
@Data
@ApiModel(description = "添加企业入参")
public class AddCoQuery {
    @ApiModelProperty(value = "公司名称")
    private String CoName;

    @ApiModelProperty(value = "公司地址")
    private String CoAddress;


    @ApiModelProperty(value = "公司简介")
    private String CompanyProfile;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "公司营业执照")
    private String busLicense;

    @ApiModelProperty(value = "文件上传")
    public MultipartFile file;
}
package com.nado.rlzy.bean.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName 省市区dto
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/6/28 18:24
 * @Version 1.0
 */
@Data
@ApiModel(description = "省市区dto")
public class ProvinceDto {
    private Integer saId;

    private Integer saFather;


    private Integer areaID;

    private Integer syId;

    private String province;

    private String city;

    private Integer syFather;


    private Integer cityId;

    private Integer spId;

    private Integer provinceId;

    private String area;


}
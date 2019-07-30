package com.nado.rlzy.bean.frontEnd;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName 省市区front
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/6/28 18:32
 * @Version 1.0
 */
@Data
@ApiModel(description = "省市区")
public class ProvinceFront {

    private String saId;

    private String saFather;


    private String areaID;

    private String syId;

    private String province;

    private String city;

    private String syFather;


    private String cityId;

    private String spId;

    private String provinceId;

    private String area;
}
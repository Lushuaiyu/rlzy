package com.nado.rlzy.db.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 返佣表
 *
 * @Author lushuaiyu
 * @Description //TODO
 * @Date 9:23 2019/7/1
 * @Param
 * @return
 **/
@Data
public class HrRebaterecord {
    private Integer id;

    private Integer briefchapterId;



    private Integer rebateType;

    private String rebateTime;

    private BigDecimal rebateMale;

    private BigDecimal rebateFemale;

    private BigDecimal rebateOne;

    private Integer status;

    private Byte deleteFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private BigDecimal rebateVariable;


}
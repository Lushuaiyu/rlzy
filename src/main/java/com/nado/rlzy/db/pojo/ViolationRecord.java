package com.nado.rlzy.db.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class ViolationRecord {
    private Integer id;

    private Integer content;

    private Integer signUpId;

    private Date creatTime;

    private Integer deleteFlag;

    private Integer type;
}
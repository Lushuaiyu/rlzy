package com.nado.rlzy.db.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Collect {
    private Integer id;

    private Integer briefchapterId;

    private Integer userId;

    private Date createTime;


    private Integer deleteFlag;

    private Integer signUpId;
}


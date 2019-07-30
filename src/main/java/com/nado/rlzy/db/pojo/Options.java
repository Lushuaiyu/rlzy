package com.nado.rlzy.db.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Options {
    private Integer id;

    private String content;

    private Integer deleteflag;

    private Integer type;

    private Date createtime;

    private Integer ageMin;

    private Integer ageMax;

    private Integer overtimeTimeMin;

    private Integer overtimeTimeMax;
}
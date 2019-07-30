package com.nado.rlzy.db.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class HrPost {
    private Integer id;

    private String name;

    private Byte deleteflag;

    private Date createtime;

    private String postName;


}
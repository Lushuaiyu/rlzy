package com.nado.rlzy.db.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Province {
    private int id;
    private String provinceId="";
    private String provinceName="";
    private List<City> citys;


}
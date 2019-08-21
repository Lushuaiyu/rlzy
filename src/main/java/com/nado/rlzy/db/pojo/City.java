package com.nado.rlzy.db.pojo;

import lombok.Data;

import java.util.List;

@Data
public class City {
    private int id;
    private String cityId="";
    private String cityName="";
    private String father="";
    private List<Area> areas;



}
package com.nado.rlzy.db.pojo;

import lombok.Data;

import java.util.List;

@Data
public class City {
    private int id;
    private String cityID="";
    private String city="";
    private String father="";
    private List<Area> areas;



}
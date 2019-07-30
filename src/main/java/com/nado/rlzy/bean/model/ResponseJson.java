package com.nado.rlzy.bean.model;

import lombok.Data;

import java.util.Map;

@Data
public class ResponseJson<String, T> {

    private int code;
    private String info;
    private Map<String, T> data;


}

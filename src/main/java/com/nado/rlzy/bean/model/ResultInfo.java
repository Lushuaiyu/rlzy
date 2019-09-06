package com.nado.rlzy.bean.model;

import lombok.Data;

/**
 * Created by lsy on 2018/11/9 0009.
 *
 * @author Administrator
 */
@Data
public class ResultInfo {
    private Integer code = 200;
    private String message;
    private Object result;

    public ResultInfo(Integer code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }


    public ResultInfo() {

    }

    public ResultInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultInfo(String message) {
        this.message = message;
    }

    public ResultInfo(Integer code) {
        this.code = code;
    }
}

package com.nado.rlzy.bean.model;

import lombok.Data;

import java.util.List;


/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/5/22 11:49
 * @Version 1.0
 */
@Data
public class Result<T> {
    private String message;
    private Integer code;
    private List<T> data;


    public Result(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public Result(Integer code, String msg, Integer count, List<T> data) {
        this.code = code;
        this.message = msg;
        //this.count = count;
        this.data = data;
    }


    public Result() {
    }

    public Result(Integer code) {
        this.code = code;

    }

    public Result(String msg) {
        this.message = msg;
    }

    public Result(Integer code, List<T> data) {
        this.code = code;
        this.data = data;
    }

    public Result(String msg, List<T> data) {
        this.data = data;
        this.message = msg;
    }

    public Result(List<T> data) {
        this.data = data;
    }





    /* private Integer page;*/



    /*
    private Integer limit;
    */
}
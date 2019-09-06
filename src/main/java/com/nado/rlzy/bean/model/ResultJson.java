package com.nado.rlzy.bean.model;

import lombok.Data;

/**
 * @ClassName 封装返回对象
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/8/3 9:28
 * @Version 1.0
 */
@Data
public class ResultJson {

    private Integer code;
    private String message;
    private Object data;

    public ResultJson(String message, Integer code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ResultJson() {
    }

    public ResultJson(String message) {
        this.message = message;
    }

    public ResultJson(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResultJson(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResultJson(Object data) {
        this.data = data;
    }

    public ResultJson(Integer code) {
        this.code = code;
    }




}
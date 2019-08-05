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

    private String msg;
    private Integer code;
    private Object data;

    public ResultJson(String msg, Integer code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public ResultJson() {
    }

    public ResultJson(String msg) {
        this.msg = msg;
    }

    public ResultJson(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResultJson(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public ResultJson(Object data) {
        this.data = data;
    }

    public ResultJson(Integer code) {
        this.code = code;
    }

}
package com.nado.rlzy.bean.model;

import com.nado.rlzy.platform.emums.UnicomResponseEnums;
import lombok.Data;


/**
 * 统一返回类
 *
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/5/20 18:02
 * @Version 1.0
 */
@Data
public class ResponseBean<T> {
    private boolean success;
    private Integer succCode;
    private String succMsg;
    private T data;
    private String errCode;
    private String errMsg;

    public ResponseBean() {
    }

    public ResponseBean(boolean success, T data) {
        super();
        this.success = success;
        this.data = data;
    }

    public ResponseBean(Integer succCode, String succMsg) {
        super();
        this.succCode = succCode;
        this.succMsg = succMsg;
    }


    @Override
    public String toString() {
        return "ResponseBean{" +
                "success=" + success +
                ", succCode=" + succCode +
                ", succMsg='" + succMsg + '\'' +
                ", data=" + data +
                ", errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }

    public ResponseBean(boolean success, Integer succCode, String succMsg, T data, String errCode, String errMsg) {
        super();
        this.success = success;
        this.succCode = succCode;
        this.succMsg = succMsg;
        this.data = data;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public ResponseBean(boolean success, Integer succCode, T data, String errCode, String errMsg) {
        super();
        this.success = success;
        this.succCode = succCode;
        this.data = data;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }


    public ResponseBean(boolean success, T data, String errCode, String errMsg) {
        super();
        this.success = success;
        this.data = data;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }


    public ResponseBean(boolean success, String errCode, String errMsg) {
        this.success = success;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public ResponseBean(boolean success, UnicomResponseEnums enums) {
        this.success = success;
        this.errCode = enums.getCode();
        this.errMsg = enums.getMsg();
    }


    public ResponseBean(boolean success, T data, UnicomResponseEnums enums) {
        this.success = success;
        this.data = data;
        this.errCode = enums.getCode();
        this.errMsg = enums.getMsg();
    }


}
package com.nado.rlzy.platform.exception;


import com.nado.rlzy.platform.emums.UnicomResponseEnums;

/**
 * 自定义异常 参数异常
 *
 * @ClassName
 * @Description TODO
 * @Author ar
 * @Data 2019/5/20 17:28
 * @Version 1.0
 */
public class ParamsException extends RuntimeException {
    private String code;
    private String msg;

    /**
     * 打印出的日志信息
     *
     * @Author ar
     * @Description
     * @Date 19:00 2019/5/20
     * @Param * @param null
     * @return
     **/
    private String message;

    public ParamsException(UnicomResponseEnums enums, String message) {
        super();
        this.code = enums.getCode();
        this.msg = enums.getMsg();
        this.message = message;
    }

    public ParamsException(UnicomResponseEnums enums) {
        super();
        this.code = enums.getCode();
        this.msg = enums.getMsg();
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ParamsException() {
        super();
    }

    public ParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamsException(String message) {
        super(message);
    }

}
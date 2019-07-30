package com.nado.rlzy.platform.comm.api;

/**
 * 封装api的错误码
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/14 14:41
 * @Version 1.0
 */
public interface IErrorCode {

    long getCode();

    String getMessage();
}
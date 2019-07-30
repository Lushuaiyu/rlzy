package com.nado.rlzy.platform.exception;

import lombok.Data;
import org.hibernate.validator.constraints.SafeHtml;

/**
 * @ClassName图片异常
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/1 16:35
 * @Version 1.0
 */
@Data
public class ImgException extends RuntimeException {

    /**
     * 异常对应的返回码
     */
    private String retCd ;
    /**
     * 异常对应的描述信息
     */
    private String msgDes;

    public ImgException() {
        super();
    }

    public ImgException(String retCd) {
        super();
        this.retCd = retCd;
    }

    public ImgException(String retCd, String msgDes){
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }





}
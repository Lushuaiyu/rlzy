package com.nado.rlzy.platform.exception;


import com.nado.rlzy.platform.constants.RlzyConstant;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class AssertException extends RuntimeException {
    private Integer code = RlzyConstant.OPS_FAILED_CODE;
    private String msg;

    public AssertException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public AssertException(Integer code) {
        this.code = code;
    }

    public AssertException(String msg) {
        super(msg);
        this.msg = msg;
    }


}

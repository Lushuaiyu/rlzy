package com.nado.rlzy.base;

import com.nado.rlzy.bean.model.ResultInfo;


/**
 * 对于controller层的封装
 *
 * @author Administrator
 */
public class BaseController {

    public ResultInfo success(Integer code, String msg, Object result) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }

    public ResultInfo success(String msg, Object result) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }

    public ResultInfo success(Integer code, String msg) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    public ResultInfo success(String msg) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    public ResultInfo success(Integer code) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        return resultInfo;
    }


}

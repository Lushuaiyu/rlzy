package com.nado.rlzy.base;

import com.nado.rlzy.bean.model.ResultInfo;


/**
 * 对于controller层的封装
 *
 * @author Administrator
 */
public class BaseController {

    public ResultInfo success(Integer code, String Message, Object result) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMessage(Message);
        resultInfo.setResult(result);
        return resultInfo;
    }

    public ResultInfo success(String Message, Object result) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMessage(Message);
        resultInfo.setResult(result);
        return resultInfo;
    }

    public ResultInfo success(Integer code, String Message) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMessage(Message);
        return resultInfo;
    }

    public ResultInfo success(String Message) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMessage(Message);
        return resultInfo;
    }

    public ResultInfo success(Integer code) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        return resultInfo;
    }


}

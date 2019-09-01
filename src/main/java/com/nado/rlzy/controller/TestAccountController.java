package com.nado.rlzy.controller;

import cn.hutool.core.lang.Assert;
import com.nado.rlzy.bean.model.ResultJson;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.platform.constants.RlzyConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName 模拟转账controller
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/5 11:09
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "account")
public class TestAccountController {

    @Resource
    private HrSignUpMapper signUpMapper;

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "aa")
    @ResponseBody
    public String aa(){
        String a = "";
        Assert.isFalse(StringUtils.isBlank(a), "asfdafdfdaf");
        return a;

    }

    @RequestMapping(value = "in")
    @ResponseBody
    public ResultJson in(Integer userId){
        List<HrSignUp> hrSignUps = signUpMapper.queryAll(userId);
        ResultJson resultJson = new ResultJson();
        resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        resultJson.setData(hrSignUps);
        return resultJson;
    }
}
package com.nado.rlzy.controller;

import com.nado.rlzy.bean.model.ResultJson;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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
    public String aa(String a) {

        AssertUtil.isTrue(StringUtils.isBlank(a), "asfdafdfdaf");

        return a;

    }

    @PostMapping(value = "aaa")
    @ResponseBody
    public ResultJson in(String str) {
        ResultJson resultJson = new ResultJson();
        if (str != null) {
            resultJson.setCode(RlzyConstant.OPS_FAILED_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
        } else {
            AssertUtil.isTrue(null == str, "字符串不能为空");
        }
        return resultJson;
    }


}
package com.nado.rlzy.controller;

import cn.hutool.core.lang.Assert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
/*    @Autowired
    private TestAccoun
    tService service;

    @RequestMapping("aaa")
    @ResponseBody
    public TestAccount getAccount() {
        //查询账户
        return service.getAccount();
    }

    @RequestMapping("add")
    @ResponseBody
    public Object addMoney() {
        try {
            service.addMoney();
        } catch (Exception e) {
            return "发生异常了：" + service.getAccount();
        }
        return getAccount();
    }*/


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
}
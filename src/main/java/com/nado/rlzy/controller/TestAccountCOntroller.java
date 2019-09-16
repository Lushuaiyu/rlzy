package com.nado.rlzy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nado.rlzy.bean.model.ResultJson;
import com.nado.rlzy.db.pojo.EntryResignation;
import com.nado.rlzy.platform.constants.RlzyConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-09-10 18:34
 * @Version 1.0
 */
@Controller()
@RequestMapping(value = "test")
public class TestAccountCOntroller {
    @ResponseBody
    @RequestMapping(value = "demo", method = RequestMethod.POST)
    public ResultJson testDemo( @RequestBody(required = false) JSONObject obj) {
        String string = obj.toJSONString();
        //解析 json 数据
        JSONObject data = JSON.parseObject(string);
        String dataString = data.getString("rebateEntry");
        System.out.println(dataString);
        List<EntryResignation> list = JSON.parseArray(dataString, EntryResignation.class);
        System.out.println(list);

        ResultJson resultJson = new ResultJson();
        resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
        return resultJson;
    }


}

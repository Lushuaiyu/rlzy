package com.nado.rlzy.controller;

import com.nado.rlzy.bean.model.CommonResult;
import com.nado.rlzy.bean.query.AttentionQuery;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName 聊天controller
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/31 11:26
 * @Version 1.0
 */
@Controller
@RequestMapping("chat")
@Api(description = "聊天")
public class ChatController {

    @Autowired
    private ChatService service;

    @RequestMapping(value = "attention")
    @ResponseBody
    @ApiOperation(value = "求职端 关注|取消关注 简章表", notes = "求职端 关注|取消关注 简章表", httpMethod = "POST")
    @ApiImplicitParam(value = "query", name = "入参", dataType = "AttentionQuery", required = true)
    public CommonResult attention(AttentionQuery query) {
        //已关注
        if (query.getStatus().equals(1)) {
            int attention = service.attention(query);
            return CommonResult.success(attention, RlzyConstant.OPS_SUCCESS_MSG);
        }
        if (query.getStatus().equals(2)) {
            int attention = service.updateAttention(query);
            return CommonResult.success(attention, RlzyConstant.OPS_SUCCESS_MSG);
        }
        return null;
    }
}
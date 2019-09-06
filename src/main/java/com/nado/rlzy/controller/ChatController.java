package com.nado.rlzy.controller;

import com.nado.rlzy.bean.model.ResultJson;
import com.nado.rlzy.bean.query.AttentionQuery;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.platform.exception.AssertException;
import com.nado.rlzy.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName 消息controller
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/31 11:26
 * @Version 1.0
 */
@Controller
@RequestMapping("chat")
@Api(description = "聊天")
public class ChatController {

    @Resource
    private ChatService service;

    @RequestMapping(value = "attention")
    @ResponseBody
    @ApiOperation(value = "求职端 关注|取消关注 简章表", notes = "求职端 关注|取消关注 简章表", httpMethod = "POST")
    @ApiImplicitParam(value = "query", name = "入参", required = true)
    public ResultJson attention(AttentionQuery query) {
            ResultJson resultJson = new ResultJson();
        //已关注
        if (query.getStatus().equals(1)) {
            try {
                int attention = service.attention(query);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(attention);
            } catch (AssertException e) {
                e.printStackTrace();
                resultJson.setMessage(e.getMessage());
                resultJson.setCode(e.getCode());
            } catch (Exception e) {
                e.printStackTrace();
                resultJson.setMessage(RlzyConstant.OPS_FAILED_MSG);
                resultJson.setCode(RlzyConstant.OPS_FAILED_CODE);
            }
        }
        if (query.getStatus().equals(2)) {

            try {
                int attention = service.updateAttention(query);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(attention);
            } catch (AssertException e) {
                e.printStackTrace();
                resultJson.setMessage(e.getMessage());
                resultJson.setCode(e.getCode());
            } catch (Exception e) {
                e.printStackTrace();
                resultJson.setMessage(RlzyConstant.OPS_FAILED_MSG);
                resultJson.setCode(RlzyConstant.OPS_FAILED_CODE);
            }
        }
        return resultJson;
    }
}
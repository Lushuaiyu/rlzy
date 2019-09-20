package com.nado.rlzy.controller;

import com.nado.rlzy.bean.model.ResultJson;
import com.nado.rlzy.bean.query.AttentionQuery;
import com.nado.rlzy.db.mapper.HrUserMapper;
import com.nado.rlzy.db.pojo.HrUser;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @Resource
    private HrUserMapper userMapper;

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

    @RequestMapping(value = "queryMessageMyself")
    @ResponseBody
    @ApiOperation(value = "本人通知", notes = "本人通知", httpMethod = "POST")
    @ApiImplicitParam(value = "userId", name = "用户 id", required = true)
    public ResultJson queryMessageMyself(Integer userId) {
        ResultJson resultJson = new ResultJson();
        Map<String, Object> map1 = new HashMap<>();
        try {
            Integer type = Optional.ofNullable(userMapper.checkUserIdentity(userId)).orElseGet(HrUser::new).getType();

            if (type.equals(1)) {
                //本人
                Map<String, Object> stringObjectMap = service.queryMessageMyself(userId);
                map1.put("queryMessageMyself", stringObjectMap);
            } else {
                //推荐人
                Map<String, Object> map = service.queryMessageReferrer(userId);
                map1.put("queryMessageReferrer", map);
            }
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(map1);
        } catch (AssertException e) {
            e.printStackTrace();
            resultJson.setMessage(e.getMessage());
            resultJson.setCode(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setMessage(RlzyConstant.OPS_FAILED_MSG);
            resultJson.setCode(RlzyConstant.OPS_FAILED_CODE);
        }
        return resultJson;
    }

    @RequestMapping(value = "queryMessageReferrer")
    @ResponseBody
    @ApiOperation(value = "推荐人通知", notes = "推荐人通知", httpMethod = "POST")
    @ApiImplicitParam(value = "userId", name = "用户 id", required = true)
    public ResultJson queryMessageReferrer(Integer userId) {
        ResultJson resultJson = new ResultJson();
        try {
            Map<String, Object> stringObjectMap = service.queryMessageReferrer(userId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(stringObjectMap);
        } catch (AssertException e) {
            e.printStackTrace();
            resultJson.setMessage(e.getMessage());
            resultJson.setCode(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setMessage(RlzyConstant.OPS_FAILED_MSG);
            resultJson.setCode(RlzyConstant.OPS_FAILED_CODE);
        }
        return resultJson;

    }
}
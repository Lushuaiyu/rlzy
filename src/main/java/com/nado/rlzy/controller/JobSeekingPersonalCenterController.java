package com.nado.rlzy.controller;

import com.nado.rlzy.bean.model.Result;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.platform.annotation.UserLoginToken;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.JobSeekingPersonalCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName 求职端 个人中心 Controller
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/22 15:04
 * @Version 1.0
 */
@Controller
@Api(description = "求职端 个人中心 Controller")
@RequestMapping(value = "personalCenter")
public class JobSeekingPersonalCenterController {
    @Autowired
    private JobSeekingPersonalCenterService service;
    /**
     * 简章收藏概览
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:34 2019/7/5
     * @Param []
     **/
    @RequestMapping(value = "recruitmentBrochureCollection")
    @ResponseBody
    @ApiOperation(notes = "简章收藏概览", value = "简章收藏概览", httpMethod = "POST")
    @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true)
    public Result<HrBriefchapter> recruitmentBrochureCollection(Integer userId) {
        List<HrBriefchapter> list = service.recruitmentBrochureCollection(userId);
        Result<HrBriefchapter> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(list);
        return result;

    }

    /**
     * 查查询我的报名表详情
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:32 2019/7/8
     * @Param [signId, userId]
     **/
    @RequestMapping(value = "selectSignUpTable")
    @ResponseBody
    @ApiOperation(notes = "查询我的报名表详情", value = "查询我的报名表详情", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signId", name = "求职id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true)

    })
    public Result<HrSignUp> selectSignUpTable(Integer signId, Integer userId) {
        List<HrSignUp> hrSignUps = service.selectSignUpTable(signId, userId);
        Result<HrSignUp> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(hrSignUps);
        return result;

    }

    /**
     * 查询我的报名表概览
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:49 2019/7/8
     * @Param [userId]
     **/
    @UserLoginToken
    @RequestMapping(value = "selectSignUp")
    @ResponseBody
    @ApiOperation(notes = "查询我的报名表概览", value = "查询我的报名表概览", httpMethod = "POST")
    @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true)
    public Result<HrSignUp> selectSignUp(Integer userId) {
        List<HrSignUp> list = service.selectSignUp(userId);
        Result<HrSignUp> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "searchSignUpUserName")
    @ResponseBody
    @ApiOperation(notes = "查询投诉人 投诉人是报名该简章的求职者", value = "查询投诉人 投诉人是报名该简章的求职者", httpMethod = "POST")
    @ApiImplicitParam(value = "briefChapterId", name = "简章id", dataType = "Integer", required = true)
    public Result<HrSignUp> searchSignUpUserName(Integer briefChapterId) {
        List<HrSignUp> ups = service.searchSignUpUserName(briefChapterId);
        Result<HrSignUp> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(ups);
        return result;

    }
}
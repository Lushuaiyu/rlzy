package com.nado.rlzy.controller;

import com.nado.rlzy.base.BaseController;
import com.nado.rlzy.bean.dto.SignUpNumberDto;
import com.nado.rlzy.bean.model.CommonResult;
import com.nado.rlzy.bean.model.Result;
import com.nado.rlzy.bean.model.ResultInfo;
import com.nado.rlzy.bean.model.ResultJson;
import com.nado.rlzy.bean.query.EditBriefchapterQuery;
import com.nado.rlzy.bean.query.RebateQuery;
import com.nado.rlzy.bean.query.ReleaseBriefcharpterQuery;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.MyReleaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @ClassName 招聘端 我的发布 controller
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/22 13:46
 * @Version 1.0
 */
@Controller
@Api(description = " 招聘端 我的发布 controller")
@RequestMapping(value = "myRelease")
public class MyReleaseController extends BaseController {
    @Autowired
    private MyReleaseService service;


    @RequestMapping(value = "myRelease")
    @ResponseBody
    @ApiOperation(notes = "招聘端 我的发布", value = "招聘端 我的发布", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "typeId", name = "登录身份id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "status", name = "状态", dataType = "Integer", required = true)

    })
    public ResultJson myRelease(Integer userId, Integer typeId, Integer status) {
        Map<String, Object> map = service.myRelease(userId, typeId, status);

        ResultJson result = new ResultJson();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(map);
        return result;
    }

    /**
     * 查询省市区
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:35 2019/6/28
     * @Param [query]
     **/
    @RequestMapping(value = "getPCA")
    @ResponseBody
    @ApiOperation(notes = "求职端 查询省市区", value = "查询省市区", httpMethod = "POST")
    public Result<Province> getPCA() {
        List<Province> pca = service.getPCA();
        Result<Province> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(pca);
        return result;


    }

   /* @RequestMapping(value = "queryReleaseBriefcharpterByparams")
    @ResponseBody
    @ApiOperation(notes = "查询 发布招聘简章的内容", value = "查询 发布招聘简章的内容", httpMethod = "POST")
    @ApiImplicitParam(name = "status", value = "求职者还是推荐者", dataType = "Integer", required = true)
    public Result<ReleaseBriefcharpterFront> queryReleaseBriefcharpterByparams(ReleaseBriefcharpterQuery query) {
        List<ReleaseBriefcharpterFront> vals = service.queryReleaseBriefcharpterByparams(query);
        Result<ReleaseBriefcharpterFront> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(vals);
        return result;


    }*/

    /**
     * 编辑 发布简章 招聘端
     *
     * @return com.nado.rlzy.bean.model.ResultInfo
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:01 2019/7/1
     * @Param [query, status]
     **/
    @RequestMapping(value = "save")
    @ResponseBody
    @ApiOperation(notes = "发布简章", value = "发布简章", httpMethod = "POST")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "query", value = "入参, 详细参数见swagger model部分, 再次入参不一一列举", dataType = "ReleaseBriefcharpterQuery", required = true),
                    @ApiImplicitParam(name = "type", value = "身份, 代招单位还是招聘单位", dataType = "Integer", required = true)
            }
    )
    public ResultInfo save(ReleaseBriefcharpterQuery query, Integer type) {
        service.saveUser(query, type);
        return success(RlzyConstant.OPS_SUCCESS_CODE, RlzyConstant.OPS_SUCCESS_MSG);


    }

    @RequestMapping(value = "recruitmentDetailsOverview")
    @ResponseBody
    @ApiOperation(value = "招聘详情 概览 待返佣部分 0:面试 1:报到 2:入职 0就是第一笔返佣 1是第二笔 2 入职 按照时间从早到晚排", notes = "招聘详情 概览 待返佣部分 0:面试 1:报到 2:入职 0就是第一笔返佣 1是第二笔 2 入职 按照时间从早到晚排", httpMethod = "POST")
    @ApiImplicitParam(value = "jobStatus", name = "报名状态", dataType = "integer []", required = true)
    public Result<HrSignUp> recruitmentDetailsOverview(Integer[] jobStatus) {
        List<HrSignUp> list = service.recruitmentDetailsOverview(jobStatus);
        Result<HrSignUp> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(list);
        return result;

    }

    @ResponseBody
    @RequestMapping(value = "numberOfRecruitsFull")
    @ApiOperation(notes = "判断报名人数是否满了, full 返回0 就是招聘满了", value = "判断报名人数是否满了, full 返回0 就是招聘满了", httpMethod = "POST")
    @ApiImplicitParam(value = "briefchapter", name = "简章id", dataType = "Integer", required = true)
    public Result<SignUpNumberDto> numberOfRecruitsFull(Integer briefchapter) {
        List<SignUpNumberDto> signUpNumberDtos = service.numberOfRecruitsFull(briefchapter);
        Result<SignUpNumberDto> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(signUpNumberDtos);
        return result;
    }

    @RequestMapping(value = "invitationToRegister")
    @ResponseBody
    @ApiOperation(notes = "邀请报名", value = "邀请报名", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integer", required = true)
    public CommonResult invitationToRegister(Integer signUpId) {
        int invitationToRegister = service.invitationToRegister(signUpId);
        return CommonResult.success(invitationToRegister, RlzyConstant.OPS_SUCCESS_MSG);

    }

    @RequestMapping(value = "notSuitable")
    @ResponseBody
    @ApiOperation(notes = "不合适", value = "不合适", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true)
    public CommonResult notSuitable(Integer signUpId) {
        int notSuitable = service.notSuitable(signUpId);
        return CommonResult.success(notSuitable, RlzyConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping(value = "invitationInterview")
    @ResponseBody
    @ApiOperation(notes = "邀请面试", value = "邀请面试", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true)
    public CommonResult invitationInterview(Integer signUpId) {
        int interview = service.invitationInterview(signUpId);
        return CommonResult.success(interview, RlzyConstant.OPS_SUCCESS_MSG);

    }

    @RequestMapping(value = "recruitmentSideDirectAdmission")
    @ResponseBody
    @ApiOperation(notes = "直接录取", value = "直接录取", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "sex", name = "性别", dataType = "Integer", required = true)
    })
    public CommonResult recruitmentSideDirectAdmission(Integer signUpId, Integer userId, Integer sex) {
        int admission = service.directAdmission(signUpId, userId, sex);
        return CommonResult.success(admission, RlzyConstant.OPS_SUCCESS_MSG);

    }


    @RequestMapping(value = "reportNotSuitable")
    @ResponseBody
    @ApiOperation(value = "招聘详情 已报名 不合适", notes = "招聘详情 已报名 不合适", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true)
    public CommonResult reportNotSuitable(Integer signUpId) {
        int count = service.reportNotSuitable(signUpId);
        return CommonResult.success(count, RlzyConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping(value = "recruitmentDetailsInvitationInterview")
    @ResponseBody
    @ApiOperation(value = "招聘详情 已报名 邀请面试", notes = "招聘详情 已报名 邀请面试", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true)
    public CommonResult recruitmentDetailsInvitationInterview(@NotNull(message = "signUpId 不能为空") Integer signUpId) {
        int count = service.recruitmentDetailsInvitationInterview(signUpId);
        return CommonResult.success(count, RlzyConstant.OPS_SUCCESS_MSG);

    }

    @RequestMapping(value = "recruitmentDetailsDirectAdmission")
    @ResponseBody
    @ApiOperation(value = "招聘详情 已报名 直接录取", notes = "招聘详情 已报名 直接录取", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true)
    public CommonResult recruitmentDetailsDirectAdmission(Integer signUpId) {
        int count = service.recruitmentDetailsDirectAdmission(signUpId);
        return CommonResult.success(count, RlzyConstant.OPS_SUCCESS_MSG);
    }


    @RequestMapping(value = "recruitmentInterviewd")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待面试 已面试", notes = "招聘详情 待面试 已面试", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true)
    public CommonResult recruitmentInterviewd(Integer signUpId) {
        int count = service.recruitmentInterviewd(signUpId);
        return CommonResult.success(count, RlzyConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping(value = "recruitmentNoInterviewd")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待面试 未面试", notes = "招聘详情 待面试 未面试", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true)
    public CommonResult recruitmentNoInterviewd(Integer signUpId) {
        int count = service.recruitmentNoInterviewd(signUpId);
        return CommonResult.success(count, RlzyConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping(value = "recruitmentInterviewFailed")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待面试 已面试 面试未通过", notes = "招聘详情 待面试 已面试 面试未通过", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true)
    public CommonResult recruitmentInterviewFailed(Integer signUpId) {
        int count = service.recruitmentInterviewFailed(signUpId);
        return CommonResult.success(count, RlzyConstant.OPS_SUCCESS_MSG);
    }


    @RequestMapping(value = "recruitmentInterviewSuccess")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待面试 已面试 面试已通过", notes = "招聘详情 待面试 已面试 面试已通过", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true)
    public CommonResult recruitmentInterviewSuccess(Integer signUpId) {
        int count = service.recruitmentInterviewSuccess(signUpId);
        return CommonResult.success(count, RlzyConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping(value = "notReported")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待报道 未报到", notes = "招聘详情 待报道 未报到", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true)
    public CommonResult notReported(Integer signUpId) {
        int count = service.notReported(signUpId);
        return CommonResult.success(count, RlzyConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping(value = "reported")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待报道 确认报道", notes = "招聘详情 待报道 确认报道", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true)
    public CommonResult reported(Integer signUpId) {
        int count = service.reported(signUpId);
        return CommonResult.success(count, RlzyConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping(value = "noReportedReason")
    @ResponseBody
    @ApiOperation(value = "未报到原因", notes = "未报到原因", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "reason", name = "未报到原因", dataType = "integer", required = true)
    })
    public CommonResult noReportedReason(Integer reason, Integer signUpId) {
        int count = service.noReportedReason(reason, signUpId);
        return CommonResult.success(count, RlzyConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping(value = "rebate")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待返佣 返佣计划", notes = "招聘详情 待返佣 返佣计划", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "sex", name = "性别", dataType = "integer", required = true)
    })
    public Result<HrSignUp> rebate(Integer userId, Integer sex) {
        List<HrSignUp> list = service.rebate(userId, sex);
        Result<HrSignUp> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "noRebate")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待返佣 没有返佣", notes = "招聘详情 待返佣 没有返佣", httpMethod = "POST")
    @ApiImplicitParam(value = "rebateId", name = "返佣id", dataType = "integer", required = true)
    public CommonResult noRebate(Integer rebateId) {
        int count = service.noRebate(rebateId);
        return CommonResult.success(count, RlzyConstant.OPS_SUCCESS_MSG);

    }

    @RequestMapping(value = "alreadyRebate")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待返佣 返佣计划(如果返佣的是求职者本人 不需要signUpId 如果是推荐人推荐的求职者 则需要signUpId)", notes = "招聘详情 待返佣 返佣计划", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "招聘单位 id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "addMoney", name = "增加的钱 和 减少的钱 必须保持一致", dataType = "BigDecimal", required = true),
            @ApiImplicitParam(value = "subtraction", name = "减少的钱 和添加的钱必须保持一致", dataType = "BigDecimal", required = true),
            @ApiImplicitParam(value = "rebateId", name = "返佣id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "signUpId", name = "报名表id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "typeId", name = "身份id", dataType = "integer", required = true)

    })
    public CommonResult alreadyRebate(RebateQuery query) {
        int count = service.rebate(query);
        return CommonResult.success(count, RlzyConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping(value = "changeJobStatus")
    @ResponseBody
    @ApiOperation(value = "重置求职者报名状态", notes = "重置求职者报名状态", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "status", name = "重置的状态", dataType = "integer", required = true),
            @ApiImplicitParam(value = "currentState", name = "当前状态", dataType = "integer", required = true),
    })
    public CommonResult changeJobStatus(Integer signUpId, Integer status, Integer currentState) {
        int jobStatus = service.changeJobStatus(signUpId, status, currentState);
        return CommonResult.success(jobStatus, RlzyConstant.OPS_SUCCESS_MSG);
    }

    /**
     * 根据类型查询前端选项内容
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.Options>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:27 2019/7/8
     * @Param [type]
     **/
    @RequestMapping(value = "selectContentByType")
    @ResponseBody
    @ApiOperation(notes = "根据类型查询前端选项内容", value = "根据类型查询前端选项内容", httpMethod = "POST")
    @ApiImplicitParam(value = "type", name = "配置表类型", dataType = "Integer", required = true)
    public Result<HrDictionaryItem> selectContentByType(String type) {
        List<HrDictionaryItem> options = service.selectContentByType(type);
        Result<HrDictionaryItem> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(options);
        return result;

    }

    @RequestMapping(value = "editBriefchapterMyRelease")
    @ResponseBody()
    @ApiOperation(value = "招聘端 我的发布 编辑简章", notes = "招聘端 我的发布 编辑简章", httpMethod = "POST")
    @ApiImplicitParam(value = "query", name = "编辑简章 入参 详见 EditBriefchapter", dataType = "EditBriefchapter", required = true)
    public CommonResult editBriefchapterMyRelease(EditBriefchapterQuery query) {
        Integer count = service.editBriefchapterMyRelease(query);
        return CommonResult.success(count, RlzyConstant.OPS_SUCCESS_MSG);

    }

    @RequestMapping(value = "updateJobStatusInterviewed")
    @ResponseBody
    @ApiOperation(value = "把待面试盖为未面试", notes = "把待面试盖为未面试", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integer", required = true)
    public CommonResult updateJobStatusInterviewed(Integer signUpId) {
        Integer interviewed = service.updateJobStatusInterviewed(signUpId);
        return CommonResult.success(interviewed, RlzyConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping(value = "selectGroupName")
    @ResponseBody
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "type", name = "企业类型 5 招聘企业, 6 代招企业", dataType = "integer", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id 7是招聘企业 8 9 是代招企业")
    })
    public Result<HrGroup> selectGroupName(Integer type, Integer userId) {
        var list = service.selectGroupName(type, userId);
        var result = new Result<HrGroup>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(list);
        return result;


    }


}
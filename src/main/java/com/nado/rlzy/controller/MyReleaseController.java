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
import com.nado.rlzy.db.pojo.HrDictionaryItem;
import com.nado.rlzy.db.pojo.HrGroup;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.db.pojo.Province;
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
    @ApiOperation(value = "招聘详情 已报名", notes = "招聘详情 已报名 不合适", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true),
            @ApiImplicitParam(value = "type", name = "1 招聘详情 已报名 不合适 2 邀请面试 3 直接录取 ", dataType = "integet", required = true)
    })
    public ResultJson reportNotSuitable(Integer signUpId, Integer type) {
        ResultJson resultJson = new ResultJson();
        if (type.equals(1)) {
            //招聘详情 已报名 不合适
            int count = service.reportNotSuitable(signUpId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        } else if (type.equals(2)) {
            //招聘详情 已报名 邀请面试
            int count = service.recruitmentDetailsInvitationInterview(signUpId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        } else if (type.equals(3)) {
            //招聘详情 已报名 直接录取
            int count = service.recruitmentDetailsDirectAdmission(signUpId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);

        }
        return resultJson;
    }

    @RequestMapping(value = "recruitmentInterviewd")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待面试", notes = "招聘详情 待面试", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true),
            @ApiImplicitParam(value = "type", name = "1 招聘详情 待面试 已面试 2 未面试 3 已面试 面试未通过 |" +
                    "招聘详情 待面试 已面试 面试未通过 | 4 招聘详情 待面试 已面试 面试已通过",
                    dataType = "integet", required = true)
    })
    public ResultJson recruitmentInterviewd(Integer signUpId, Integer type) {
        ResultJson resultJson = new ResultJson();
        if (type.equals(1)) {
            // 招聘详情 待面试 已面试
            int count = service.recruitmentInterviewd(signUpId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        } else if (type.equals(2)) {
            //招聘详情 待面试 未面试
            int count = service.recruitmentNoInterviewd(signUpId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        } else if (type.equals(3)) {
            //招聘详情 待面试 已面试 面试未通过
            int count = service.recruitmentInterviewFailed(signUpId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        } else if (type.equals(4)) {
            //招聘详情 待面试 已面试 面试已通过
            int count = service.recruitmentInterviewSuccess(signUpId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        }
        return resultJson;
    }

    @RequestMapping(value = "notReported")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待报道", notes = "招聘详情 待报道", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true),
            @ApiImplicitParam(value = "type", name = "1 招聘详情 待报道 未报到 2 招聘详情 待报道 确认报道 3 未报到原因 ", dataType = "integer", required = true),
            @ApiImplicitParam(value = "reason", name = "未报到原因", dataType = "integer", required = true)
    })
    public ResultJson notReported(Integer signUpId, Integer type, Integer reason) {
        ResultJson resultJson = new ResultJson();
        if (type.equals(1)) {
        int count = service.notReported(signUpId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        } else if (type.equals(2)){
            int count = service.reported(signUpId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);

        } else if (type.equals(3)){
            int count = service.noReportedReason(reason, signUpId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        }
        return resultJson;
    }

    @RequestMapping(value = "rebatee")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待返佣", notes = "招聘详情 待返佣", httpMethod = "POST")
    @ApiImplicitParams({

            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "sex", name = "性别", dataType = "integer", required = true),
            @ApiImplicitParam(value = "type", name = "1 招聘详情 待返佣 查询返佣计划 2 招聘详情 待返佣 没有返佣" +
                    "3招聘详情 待返佣 返佣计划(如果返佣的是求职者本人 不需要signUpId 如果是推荐人推荐的求职者 则需要signUpId)", dataType = "integer", required = true),
            @ApiImplicitParam(value = "rebateId", name = "性别", dataType = "integer", required = true),
            @ApiImplicitParam(value = "下面是返佣计划的入参", name = "下面是返佣计划的入参", dataType = "string", required = true),
            @ApiImplicitParam(value = "userId", name = "招聘单位 id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "addMoney", name = "增加的钱 和 减少的钱 必须保持一致", dataType = "BigDecimal", required = true),
            @ApiImplicitParam(value = "subtraction", name = "减少的钱 和添加的钱必须保持一致", dataType = "BigDecimal", required = true),
            @ApiImplicitParam(value = "rebateId", name = "返佣id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "signUpId", name = "报名表id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "typeId", name = "身份id", dataType = "integer", required = true)
    })
    public ResultJson rebate(RebateQuery query) {
        ResultJson resultJson = new ResultJson();

        if (query.getType().equals(1)) {
        List<HrSignUp> list = service.rebatee(query.getUserId(), query.getSex());
        resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        resultJson.setData(list);
        } else if (query.getType().equals(2)){
            int count = service.noRebate(query.getRebateId());
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        } else if (query.getType().equals(3)){
            int count = service.rebate(query);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        }
        return resultJson;
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
    @ApiOperation(value = "查询招聘企业名称", notes = "查询招聘企业名称", httpMethod = "POST")
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
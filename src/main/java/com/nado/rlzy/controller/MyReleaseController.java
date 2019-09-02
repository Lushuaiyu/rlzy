package com.nado.rlzy.controller;

import com.nado.rlzy.base.BaseController;
import com.nado.rlzy.bean.model.*;
import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.bean.query.EditBriefchapterQuery;
import com.nado.rlzy.bean.query.RebateQuery;
import com.nado.rlzy.bean.query.ReleaseBriefcharpterQuery;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.JobSearchHomePageService;
import com.nado.rlzy.service.MyReleaseService;
import com.nado.rlzy.service.PersonCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.var;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
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
    @Resource
    private MyReleaseService service;

    @Resource
    private PersonCenterService centerService;

    @Resource
    private JobSearchHomePageService jobSearchHomePageService;


    @RequestMapping(value = "myRelease")
    @ResponseBody
    @ApiOperation(notes = "招聘端 我的发布 简章概览", value = "招聘端 我的发布 简章概览", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "status", name = "状态", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "type", name = "简章状态", dataType = "Integer", required = true)

    })
    public ResultJson myRelease(Integer userId, Integer status, Integer type) {
        if (type.equals(2)){
            //未通过

        }
        Map<String, Object> map = service.myRelease(userId, status);

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
    public ResultJson getPCA(@ModelAttribute("resJson") ResponseJson<String, Object> resJson) {
        ResultJson result = new ResultJson();
        List<Province> provinces = service.getPCA();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(provinces);
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
                    @ApiImplicitParam(name = "type", value = "身份, 5 代招单位 6 招聘单位", dataType = "Integer", required = true),
                    @ApiImplicitParam(name = "type", value = "身份, 代招单位还是招聘单位", dataType = "Integer", required = true)
            }
    )
    public ResultInfo save(@RequestBody ReleaseBriefcharpterQuery query, Integer type) {
        //图片上传
        String head = centerService.updateHead(query.getDescriptionJobPhotoFile());
        query.setDescriptionJobPhotoUrl(head);
        String updateHead = centerService.updateHead(query.getEmployerCertificatePhotoFile());
        query.setEmployerCertificatePhotoUrl(updateHead);
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
    @ApiOperation(notes = " 招聘端 查看求职表 判断报名人数是否满了, recruitingNo = 0 就说明满了", value = "招聘端 查看求职表 判断报名人数是否满了, recruitingNo = 0 就说明满了", httpMethod = "POST")
    @ApiImplicitParam(value = "briefchapter", name = "简章id", dataType = "Integer", required = true)
    public Result<HrBriefchapter> numberOfRecruitsFull(Integer briefchapter) {
        List<HrBriefchapter> signUpNumberDtos = service.numberOfRecruitsFull(briefchapter);
        Result<HrBriefchapter> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(signUpNumberDtos);
        return result;
    }


    @RequestMapping(value = "notSuitable")
    @ResponseBody
    @ApiOperation(notes = "招聘端 查看求职表 已经报名我的企业的求职者", value = "招聘端 查看求职表 已经报名我的企业的求职者", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true),
            @ApiImplicitParam(value = "type", name = "1 不合适 2 邀请面试", dataType = "integet", required = true)
    })
    public ResultJson notSuitable(Integer signUpId, Integer type) {
        ResultJson resultJson = new ResultJson();
        if (type.equals(1)) {
            //不合适
            int notSuitable = service.notSuitable(signUpId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(notSuitable);
        } else {
            //邀请面试
            int interview = service.invitationInterview(signUpId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(interview);
        }
        return resultJson;
    }

    @RequestMapping(value = "invitationToRegister")
    @ResponseBody
    @ApiOperation(notes = "邀请报名 && 直接录取 未报名我的企业的求职者", value = "邀请报名 && 直接录取 未报名我的企业的求职者", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "sex", name = "性别", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "type", name = "1 邀请报名 2 直接录取", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "briefchapter", name = "简章id", dataType = "Integer", required = true),
    })
    public ResultJson invitationToRegister(Integer signUpId, Integer userId, Integer sex, Integer type, Integer briefchapter) {
        ResultJson resultJson = new ResultJson();
        if (type.equals(1)) {
            //邀请报名
            int invitationToRegister = service.invitationToRegister(signUpId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(invitationToRegister);
        } else {
            //直接录取
            int admission = service.directAdmission(signUpId, userId, sex, briefchapter);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(admission);
        }
        return resultJson;
    }

    @RequestMapping(value = "reportNotSuitable")
    @ResponseBody
    @ApiOperation(value = "招聘详情 已报名", notes = "招聘详情 已报名", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true),
            @ApiImplicitParam(value = "type", name = "1 招聘详情 已报名 不合适 2 邀请面试 3 直接录取 ", dataType = "integet", required = true),
            @ApiImplicitParam(value = "briefChapterId", name = "简章id", dataType = "integet", required = true)
    })
    public ResultJson reportNotSuitable(Integer signUpId, Integer type, Integer briefChapterId) {
        ResultJson resultJson = new ResultJson();
        if (type.equals(1)) {
            //招聘详情 已报名 不合适
            int count = service.reportNotSuitable(signUpId, briefChapterId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        } else if (type.equals(2)) {
            //招聘详情 已报名 邀请面试
            int count = service.recruitmentDetailsInvitationInterview(signUpId, briefChapterId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        } else if (type.equals(3)) {
            //招聘详情 已报名 直接录取
            int count = service.recruitmentDetailsDirectAdmission(signUpId, briefChapterId);
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
            @ApiImplicitParam(value = "briefChapterId", name = "简章id", dataType = "integet", required = true),
            @ApiImplicitParam(value = "userId", name = "推荐人id | 本人id", dataType = "integet", required = true),

            @ApiImplicitParam(value = "type", name = "1 招聘详情 待面试 已面试 2 未面试 3 已面试 面试未通过 |" +
                    "招聘详情 待面试 已面试 面试未通过 | 4 招聘详情 待面试 已面试 面试已通过",
                    dataType = "integet", required = true),
            @ApiImplicitParam(value = "sex", name = "性别 0 女 1 男 type = 4 时 用得到", dataType = "int", required = true),
            @ApiImplicitParam(value = "signUpUserId", name = "求职者或者推荐人id", dataType = "int", required = true),
            @ApiImplicitParam(value = "busInessUserId", name = "企业用户id", dataType = "int", required = true)
    })
    public ResultJson recruitmentInterviewd(Integer signUpId, Integer type, Integer userId, Integer briefChapterId,
                                            Integer sex, Integer signUpUserId, Integer busInessUserId) {
        ResultJson resultJson = new ResultJson();
        if (type.equals(1)) {
            // 招聘详情 待面试 已面试
            int count = service.recruitmentInterviewd(signUpId, briefChapterId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        } else if (type.equals(2)) {
            //招聘详情 待面试 未面试
            Map<String, Object> map = service.recruitmentNoInterviewd(signUpId, userId, briefChapterId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(map);
        } else if (type.equals(3)) {
            //招聘详情 待面试 已面试 面试未通过
            int count = service.recruitmentInterviewFailed(signUpId, briefChapterId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        } else if (type.equals(4)) {
            //招聘详情 待面试 已面试 面试已通过
            int count = service.recruitmentInterviewSuccess(signUpId, briefChapterId, sex, signUpUserId, busInessUserId);
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
            @ApiImplicitParam(value = "typp", name = "1 招聘详情 待报道 未报到 2 招聘详情 待报道 确认报道 3 未报到原因 ", dataType = "integer", required = true),
            @ApiImplicitParam(value = "reason", name = "未报到原因", dataType = "integer", required = true),
            @ApiImplicitParam(value = "briefChapterId", name = "简章id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "sex", name = "性别 0 女 1 男 type = 2 时 用得到", dataType = "int", required = true),
            @ApiImplicitParam(value = "signUpUserId", name = "求职者或者推荐人id", dataType = "int", required = true),
            @ApiImplicitParam(value = "busInessUserId", name = "企业用户id", dataType = "int", required = true)
    })
    public ResultJson notReported(Integer signUpId, Integer typp, Integer reason, Integer briefChapterId,
                                  Integer userId, Integer sex, Integer signUpUserId, Integer busInessUserId) {
        ResultJson resultJson = new ResultJson();
        if (typp.equals(1)) {
            Map<String, Object> map = service.notReported(signUpId, briefChapterId, userId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(map);
        } else if (typp.equals(2)) {
            int count = service.reported(signUpId, briefChapterId, sex, signUpUserId, busInessUserId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);

        } else if (typp.equals(3)) {
            int count = service.noReportedReason(reason, signUpId, briefChapterId);
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
            @ApiImplicitParam(value = "type", name = "1 招聘详情 待返佣 查询返佣 2 招聘详情 待返佣 没有返佣" +
                    "3招聘详情 待返佣 (如果返佣的是求职者本人 不需要signUpId 如果是推荐人推荐的求职者 则需要signUpId)", dataType = "integer", required = true),
            @ApiImplicitParam(value = "下面是返佣计划的入参", name = "下面是返佣计划的入参", dataType = "string", required = true),
            @ApiImplicitParam(value = "userId", name = "招聘单位 id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "addMoney", name = "增加的钱 和 减少的钱 必须保持一致", dataType = "BigDecimal", required = true),
            @ApiImplicitParam(value = "subtraction", name = "减少的钱 和添加的钱必须保持一致", dataType = "BigDecimal", required = true),
            @ApiImplicitParam(value = "rebateId", name = "返佣id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "signUpId", name = "报名表id", dataType = "integer", required = true)
    })
    public ResultJson rebate(RebateQuery query) {
        ResultJson resultJson = new ResultJson();

        if (query.getType().equals(1)) {
            List<HrSignUp> list = service.rebatee(query.getSignUpId(), query.getSex());
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(list);
        } else if (query.getType().equals(2)) {
            int count = service.noRebate(query.getRebateId());
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
        } else if (query.getType().equals(3)) {
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
            @ApiImplicitParam(value = "briefChapterId", name = "简章id", dataType = "integer", required = true),
    })
    public CommonResult changeJobStatus(Integer signUpId, Integer status, Integer currentState, Integer briefChapterId) {
        int jobStatus = service.changeJobStatus(signUpId, status, currentState, briefChapterId);
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
    @ApiImplicitParams({
            @ApiImplicitParam(value = "query", name = "编辑简章 入参 详见 EditBriefchapter", dataType = "EditBriefchapter", required = true),
            @ApiImplicitParam(value = "query1", name = "编辑简章 入参 详见 ReleaseBriefcharpterQuery", dataType = "EditBriefchapter", required = true),
            @ApiImplicitParam(value = "typp", name = "1 正在招和未通过是一个接口 正在招 前台禁止用户填就可以 2 待审核 3 已结束", dataType = "EditBriefchapter", required = true)
    })
    public ResultJson editBriefchapterMyRelease(@RequestBody EditBriefchapterQuery query, Integer type) {
        HashMap<String, Object> map = new HashMap<>();
        ResultJson resultJson = new ResultJson();

            if (query.getTypp().equals(1)) {
                //正在招 代招单位
                //图片上传
                String descriptionJobPhoto = centerService.updateHead(query.getDescriptionJobPhotoUrl2());
                String employerCertificatePhoto = centerService.updateHead(query.getEmployerCertificatePhotoUrl2());
                query.setDescriptionJobPhotoUrl(descriptionJobPhoto);
                query.setEmployerCertificatePhotoUrl(employerCertificatePhoto);
                Integer count = service.editBriefchapterMyRelease(query);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
                map.put("editBriefchapterMyRelease", count);
                resultJson.setData(map);
            } else {
                //招聘单位
                //图片上传
                String descriptionJobPhoto = centerService.updateHead(query.getDescriptionJobPhotoUrl2());
                String employerCertificatePhoto = centerService.updateHead(query.getEmployerCertificatePhotoUrl2());
                query.setDescriptionJobPhotoUrl(descriptionJobPhoto);
                query.setEmployerCertificatePhotoUrl(employerCertificatePhoto);
                Integer count = service.editBriefchapterMyReleaseRecruitment(query);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
                map.put("editBriefchapterMyReleaseRecruitment", count);
            }
        return resultJson;
    }

    @RequestMapping(value = "selectEditBriefchapter")
    @ResponseBody
    @ApiOperation(value = "编辑简章时查询招聘简章", notes = "编辑简章时查询招聘简章", httpMethod = "POST")
    public ResultJson selectEditBriefchapter(Integer briefchapterId) {
        ResultJson resultJson = new ResultJson();
        BriefcharpterQuery query = new BriefcharpterQuery();
        query.setBriefcharpterId(briefchapterId);
        //查询简章详情
        Map<String, Object> map = jobSearchHomePageService.queryBriefcharpterListDetileByParams(query);
        resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        resultJson.setData(map);
        return resultJson;
    }

    @RequestMapping(value = "selectGroupName")
    @ResponseBody
    @ApiOperation(value = "查询招聘企业名称", notes = "查询招聘企业名称", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "type", name = "企业类型 5 招聘企业, 6 代招企业", dataType = "integer", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "int", required = true),
            @ApiImplicitParam(value = "status", name = "1 被招聘企业 2 招聘企业", dataType = "int", required = true)
    })
    public ResultJson selectGroupName(Integer type, Integer userId, Integer status) {
        var list = service.selectGroupName(type, userId, status);
        var result = new ResultJson();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(list);
        return result;
    }
}
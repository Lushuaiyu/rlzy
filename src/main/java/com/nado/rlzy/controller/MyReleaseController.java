package com.nado.rlzy.controller;

import com.alibaba.fastjson.JSONObject;
import com.nado.rlzy.base.BaseController;
import com.nado.rlzy.bean.model.ResponseJson;
import com.nado.rlzy.bean.model.Result;
import com.nado.rlzy.bean.model.ResultJson;
import com.nado.rlzy.bean.query.*;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.platform.exception.AssertException;
import com.nado.rlzy.service.JobSearchHomePageService;
import com.nado.rlzy.service.MyReleaseService;
import com.nado.rlzy.service.RecruitmentHomePageService;
import com.nado.rlzy.utils.AssertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private JobSearchHomePageService jobSearchHomePageService;

    @Resource
    private RecruitmentHomePageService homePageService;


    @RequestMapping(value = "myRelease")
    @ResponseBody
    @ApiOperation(notes = "招聘端 我的发布 简章概览", value = "招聘端 我的发布 简章概览", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "status", name = "状态 0待审核 1通过(正在招)  2未通过 3已结束", dataType = "Integer", required = true),

    })
    public ResultJson myRelease(Integer userId, Integer status) {
        ResultJson result = new ResultJson();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(userId)).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(s != null && !s.contains("38"), RlzyConstant.PERMISSION);
            Map<String, Object> map = service.myRelease(userId, status);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(map);
        } catch (AssertException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setCode(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(RlzyConstant.OPS_FAILED_MSG);
            result.setCode(RlzyConstant.OPS_FAILED_CODE);
        }
        return result;
    }

    @RequestMapping(value = "myReleaseSubAccount")
    @ResponseBody
    @ApiOperation(notes = "招聘端 我的发布 简章概览 子账号", value = "招聘端 我的发布 简章概览 子账号", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "status", name = "状态 0待审核 1通过(正在招)  2未通过 3已结束", dataType = "Integer", required = true),
    })
    public ResultJson myReleaseSubAccount(Integer userId, Integer status) {
        ResultJson result = new ResultJson();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(userId)).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(s != null && !s.contains("66"), RlzyConstant.PERMISSION);
            Map<String, Object> map = service.myReleaseSubAccount(userId, status);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(map);
        } catch (AssertException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setCode(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(RlzyConstant.OPS_FAILED_MSG);
            result.setCode(RlzyConstant.OPS_FAILED_CODE);
        }
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
    public ResultJson getPCA(@ModelAttribute("resJson") ResponseJson<String, Object> resJson, Integer userId) {
        ResultJson result = new ResultJson();

        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(userId)).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != s && s.contains("37"), RlzyConstant.PERMISSION);
            List<Province> provinces = service.getPCA();
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(provinces);
        } catch (AssertException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setCode(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(RlzyConstant.OPS_FAILED_MSG);
            result.setCode(RlzyConstant.OPS_FAILED_CODE);
        }
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
        result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(vals);
        return result;


    }*/

    /**
     * 编辑 re.signup_deliveryrecord_id 招聘端
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
    public ResultJson save(ReleaseBriefcharpterQuery query, @RequestBody(required = false) JSONObject rebateEntry) {
        ResultJson result = new ResultJson();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(query.getUserId())).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != s && s.contains("46"), RlzyConstant.PERMISSION);
            HrUser hrUser = homePageService.checkUserIdentity(query.getUserId());
            Integer type = Optional.ofNullable(hrUser).orElseGet(HrUser::new).getType();
            service.saveUser(query, type, rebateEntry);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);

        } catch (AssertException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setCode(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(RlzyConstant.OPS_FAILED_MSG);
            result.setCode(RlzyConstant.OPS_FAILED_CODE);
        }
        return result;


    }

    @RequestMapping(value = "recruitmentDetailsOverview")
    @ResponseBody
    @ApiOperation(value = "招聘详情 概览 待返佣部分 0:面试 1:报到 2:入职 0就是第一笔返佣 1是第二笔 2 入职 按照时间从早到晚排", notes = "招聘详情 概览 待返佣部分 0:面试 1:报到 2:入职 0就是第一笔返佣 1是第二笔 2 入职 按照时间从早到晚排", httpMethod = "POST")
    @ApiImplicitParam(value = "jobStatus", name = "报名状态", required = true)
    public Result<HrSignUp> recruitmentDetailsOverview(String jobStatus, Integer userId) {
        Result<HrSignUp> result = new Result<>();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(userId)).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(s != null && s.contains("39"), RlzyConstant.PERMISSION);
            List<HrSignUp> list = service.recruitmentDetailsOverview(jobStatus);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(list);
        } catch (AssertException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setCode(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(RlzyConstant.OPS_FAILED_MSG);
            result.setCode(RlzyConstant.OPS_FAILED_CODE);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "numberOfRecruitsFull")
    @ApiOperation(notes = " 招聘端 查看求职表 判断报名人数是否满了, recruitingNo = 0 就说明满了", value = "招聘端 查看求职表 判断报名人数是否满了, recruitingNo = 0 就说明满了", httpMethod = "POST")
    @ApiImplicitParam(value = "briefchapter", name = "简章id", dataType = "Integer", required = true)
    public ResultJson numberOfRecruitsFull(Integer briefchapter, Integer userId) {
        ResultJson result = new ResultJson();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(userId)).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != s && s.contains("50"), RlzyConstant.PERMISSION);
            Map<String, Object> map = service.numberOfRecruitsFull(briefchapter);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(map);
        } catch (AssertException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setCode(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(RlzyConstant.OPS_FAILED_MSG);
            result.setCode(RlzyConstant.OPS_FAILED_CODE);
        }
        return result;
    }


    @RequestMapping(value = "notSuitable")
    @ResponseBody
    @ApiOperation(notes = "招聘端 查看求职表 已经报名我的企业的求职者", value = "招聘端 查看求职表 已经报名我的企业的求职者", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true),
            @ApiImplicitParam(value = "type", name = "1 不合适 2 邀请面试", dataType = "integet", required = true)
    })
    public ResultJson notSuitable(Integer signUpId, Integer type, Integer userId) {
        ResultJson resultJson = new ResultJson();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(userId)).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != s && !s.contains("45"), RlzyConstant.PERMISSION);
            if (type.equals(1)) {
                //不合适
                int notSuitable = service.notSuitable(signUpId);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(notSuitable);
            } else {
                //邀请面试
                int interview = service.invitationInterview(signUpId);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(interview);
            }

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
    public ResultJson invitationToRegister(Integer signUpId, Integer userId, Integer type, Integer briefchapter) {
        ResultJson resultJson = new ResultJson();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(userId)).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != s && s.contains("44"), RlzyConstant.PERMISSION);
            if (type.equals(1)) {
                //邀请报名
                int invitationToRegister = service.invitationToRegister(signUpId);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(invitationToRegister);
            } else {
                //直接录取
                int admission = service.directAdmission(signUpId, briefchapter);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(admission);
            }
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

    @RequestMapping(value = "reportNotSuitable")
    @ResponseBody
    @ApiOperation(value = "招聘详情 已报名", notes = "招聘详情 已报名", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true),
            @ApiImplicitParam(value = "type", name = "1 招聘详情 已报名 不合适 2 邀请面试 3 直接录取 ", dataType = "integet", required = true),
            @ApiImplicitParam(value = "briefChapterId", name = "简章id", dataType = "integet", required = true)
    })
    public ResultJson reportNotSuitable(Integer signUpId, Integer type, Integer briefChapterId, Integer userId) {
        ResultJson resultJson = new ResultJson();

        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(userId)).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != s && !s.contains("40"), RlzyConstant.PERMISSION);
            if (type.equals(1)) {
                //招聘详情 已报名 不合适
                int count = service.reportNotSuitable(signUpId, briefChapterId);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(count);
            } else if (type.equals(2)) {
                //招聘详情 已报名 邀请面试
                int count = service.recruitmentDetailsInvitationInterview(signUpId, briefChapterId);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(count);
            } else if (type.equals(3)) {
                //招聘详情 已报名 直接录取
                int count = service.recruitmentDetailsDirectAdmission(signUpId, briefChapterId);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(count);

            }
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

    @RequestMapping(value = "recruitmentInterviewd")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待面试", notes = "招聘详情 待面试", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true),
            @ApiImplicitParam(value = "briefChapterId", name = "简章id", dataType = "integet", required = true),
            @ApiImplicitParam(value = "userId", name = "推荐人id | 本人id", dataType = "integet", required = true),

            @ApiImplicitParam(value = "type", name = "1 招聘详情 待面试 已面试 2 未面试 3 招聘详情 待面试 已面试 面试未通过  4 招聘详情 待面试 已面试 面试已通过",
                    dataType = "integet", required = true),
            @ApiImplicitParam(value = "signupDeliveryrecordId", name = "报名投递表id", dataType = "int", required = true)
    })
    public ResultJson recruitmentInterviewd(Integer signUpId, Integer type, Integer userId, Integer briefChapterId,
                                            Integer signupDeliveryrecordId) {
        ResultJson resultJson = new ResultJson();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(userId)).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != s && s.contains("41"), RlzyConstant.PERMISSION);
            if (type.equals(1)) {
                // 招聘详情 待面试 已面试
                int count = service.recruitmentInterviewd(signUpId, briefChapterId);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(count);
            } else if (type.equals(2)) {
                //招聘详情 待面试 未面试
                Map<String, Object> map = service.recruitmentNoInterviewd(signUpId, userId, briefChapterId);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(map);
            } else if (type.equals(3)) {
                //招聘详情 待面试 已面试 面试未通过
                int count = service.recruitmentInterviewFailed(signUpId, briefChapterId);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(count);
            } else if (type.equals(4)) {
                //招聘详情 待面试 已面试 面试已通过
                int count = service.recruitmentInterviewSuccess(signUpId, briefChapterId, userId, signupDeliveryrecordId);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(count);
            }
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

    @RequestMapping(value = "notReported")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待报道", notes = "招聘详情 待报道", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "integet", required = true),
            @ApiImplicitParam(value = "typp", name = "1 招聘详情 待报道 未报到 2 招聘详情 待报道 确认报道 3 未报到原因 ", dataType = "integer", required = true),
            @ApiImplicitParam(value = "reason", name = "未报到原因", dataType = "integer", required = true),
            @ApiImplicitParam(value = "briefChapterId", name = "简章id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "signupDeliveryrecordId", name = "报名投递表Id", dataType = "int", required = true)
    })
    public ResultJson notReported(Integer signUpId, Integer typp, Integer reason, Integer briefChapterId,
                                  Integer userId, Integer signupDeliveryrecordId) {
        ResultJson resultJson = new ResultJson();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(userId)).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != s && s.contains("42"), RlzyConstant.PERMISSION);
            if (typp.equals(1)) {
                Map<String, Object> map = service.notReported(signUpId, briefChapterId, userId);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(map);
            } else if (typp.equals(2)) {
                int count = service.reported(signUpId, briefChapterId, userId, signupDeliveryrecordId);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(count);

            } else if (typp.equals(3)) {
                int count = service.noReportedReason(reason, signUpId, briefChapterId);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(count);
            }
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

    @RequestMapping(value = "rebatee")
    @ResponseBody
    @ApiOperation(value = "招聘详情 待返佣", notes = "招聘详情 待返佣", httpMethod = "POST")
    @ApiImplicitParams({

            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "sex", name = "性别", dataType = "integer", required = true),
            @ApiImplicitParam(value = "type", name = "1 招聘详情 待返佣 查询返佣 2招聘详情 待返佣 返佣过程 需要signUpId 知道是返佣给哪个报名者", dataType = "integer", required = true),
            @ApiImplicitParam(value = "下面是返佣计划的入参", name = "下面是返佣计划的入参", dataType = "string", required = true),
            @ApiImplicitParam(value = "userId", name = "招聘单位 id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "rebateMon", name = "增加的钱 和 减少的钱 必须保持一致", dataType = "BigDecimal", required = true),
            @ApiImplicitParam(value = "subtraction", name = "减少的钱 和添加的钱必须保持一致", dataType = "BigDecimal", required = true),
            @ApiImplicitParam(value = "rebateId", name = "返佣id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "signUpId", name = "报名表id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "briefchapterId", name = "简章id", dataType = "integer", required = true)
    })
    public ResultJson rebate(RebateQuery query) {
        ResultJson resultJson = new ResultJson();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(query.getUserId())).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != null && !s.contains("43"), RlzyConstant.PERMISSION);
            if (query.getType().equals(1)) {
                List<HrRebaterecord> list = service.rebatee(query.getSignUpId(), query.getBriefchapterId());
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(list);
            } else if (query.getType().equals(2)) {
                int count = service.rebate(query);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(count);
            }
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

    @RequestMapping(value = "changeJobStatus")
    @ResponseBody
    @ApiOperation(value = "重置求职者报名状态", notes = "重置求职者报名状态", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "hsdId", name = "报名id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "status", name = "重置的状态", dataType = "integer", required = true),
            @ApiImplicitParam(value = "currentState", name = "当前状态", dataType = "integer", required = true)
    })
    public ResultJson changeJobStatus(String hsdId, String status, String currentState, String userId) {
        ResultJson resultJson = new ResultJson();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(Integer.valueOf(userId))).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != s && !s.contains("35"), RlzyConstant.PERMISSION);
            int jobStatus = service.changeJobStatus(hsdId, status, currentState);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(jobStatus);
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
    @ApiImplicitParam(value = "query", name = "配置表参数", dataType = "Integer", required = true)
    public ResultJson selectContentByType(DictionaryQuery query) {
        ResultJson result = new ResultJson();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(query.getUserId())).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != s && s.contains("48"), RlzyConstant.PERMISSION);
            Map<String, Object> stringObjectMap = service.selectContentByType(query);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(stringObjectMap);
        } catch (AssertException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setCode(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(RlzyConstant.OPS_FAILED_MSG);
            result.setCode(RlzyConstant.OPS_FAILED_CODE);
        }
        return result;

    }

    /**
     * 前台选择页面 通用模板
     *
     * @return com.nado.rlzy.bean.model.ResultJson
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 09:48 2019-09-09
     * @Param [type]
     */
    @RequestMapping(value = "selectFrontEnd")
    @ResponseBody
    @ApiOperation(notes = "根据类型查询前端选项内容", value = "根据类型查询前端选项内容", httpMethod = "POST")
    @ApiImplicitParam(value = "query", name = "配置表参数", dataType = "Integer", required = true)
    public ResultJson selectFrontEnd(Integer type, Integer userId) {
        ResultJson result = new ResultJson();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(userId)).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != s && !s.contains("47"), RlzyConstant.PERMISSION);
            List<HrDictionaryItem> items = service.selectFrontEnd(type);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(items);
        } catch (AssertException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setCode(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(RlzyConstant.OPS_FAILED_MSG);
            result.setCode(RlzyConstant.OPS_FAILED_CODE);
        }
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
    public ResultJson editBriefchapterMyRelease(EditBriefchapterQuery query, Integer userId) {
        HashMap<String, Object> map = new HashMap<>();
        ResultJson resultJson = new ResultJson();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(userId)).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != s && !s.contains("36"), RlzyConstant.PERMISSION);
            if (query.getTypp().equals(1)) {
                //正在招 | 未通过 代招单位
                Integer count = service.editBriefchapterMyRelease(query);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                map.put("editBriefchapterMyRelease", count);
                resultJson.setData(map);
            } else {
                //正在招 | 未通过 招聘单位
                Integer count = service.editBriefchapterMyReleaseRecruitment(query);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                map.put("editBriefchapterMyReleaseRecruitment", count);
            }
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

    @RequestMapping(value = "selectEditBriefchapter")
    @ResponseBody
    @ApiOperation(value = "编辑简章时查询招聘简章", notes = "编辑简章时查询招聘简章", httpMethod = "POST")
    public ResultJson selectEditBriefchapter(Integer briefchapterId, Integer userId) {
        ResultJson resultJson = new ResultJson();

        try {
            BriefcharpterQuery query = new BriefcharpterQuery();
            query.setBriefcharpterId(briefchapterId);
            //子账号拥有的权限
            String permission = Optional.ofNullable(homePageService.subAccountPermission(userId)).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != permission && !permission.contains("65"), RlzyConstant.PERMISSION);
            //查询简章详情
            Map<String, Object> map = jobSearchHomePageService.queryBriefcharpterListDetileByParams(query);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(map);
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

    @RequestMapping(value = "selectGroupName")
    @ResponseBody
    @ApiOperation(value = "查询招聘企业名称", notes = "查询招聘企业名称", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "int", required = true),
            @ApiImplicitParam(value = "status", name = "1 被招聘企业 2 招聘企业", dataType = "int", required = true)
    })
    public ResultJson selectGroupName(Integer userId, Integer status) {
        ResultJson result = new ResultJson();
        try {
            String s = Optional.ofNullable(homePageService.subAccountPermission(userId)).orElseGet(HrUser::new).getInterfaceId();
            AssertUtil.isTrue(null != s && s.contains("49"), RlzyConstant.PERMISSION);
            HrUser hrUser = homePageService.checkUserIdentity(userId);
            Integer type = Optional.ofNullable(hrUser).orElseGet(HrUser::new).getType();
            Map<String, Object> list = service.selectGroupName(type, userId, status);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(list);
        } catch (AssertException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setCode(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(RlzyConstant.OPS_FAILED_MSG);
            result.setCode(RlzyConstant.OPS_FAILED_CODE);
        }
        return result;
    }

    @RequestMapping(value = "editBriefchapterEcho")
    @ResponseBody
    @ApiOperation(value = "编辑简章时回显 代招单位", notes = "编辑简章时回显 代招单位")
    public ResultJson editBriefchapterEcho(Integer briefchapter) {
        ResultJson result = new ResultJson();
        try {
            List<HrBriefchapter> list = service.editBriefchapterEcho(briefchapter);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(list);
        } catch (AssertException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setCode(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(RlzyConstant.OPS_FAILED_MSG);
            result.setCode(RlzyConstant.OPS_FAILED_CODE);
        }
        return result;
    }

    @RequestMapping(value = "editBriefchapterEchoRecruitment")
    @ResponseBody
    @ApiOperation(value = "编辑简章时回显 代招单位", notes = "编辑简章时回显 代招单位")
    public ResultJson editBriefchapterEchoRecruitment(Integer briefchapter) {
        ResultJson result = new ResultJson();
        try {
            List<HrBriefchapter> list = service.editBriefchapterEchoRecruitment(briefchapter);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(list);
        } catch (AssertException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setCode(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(RlzyConstant.OPS_FAILED_MSG);
            result.setCode(RlzyConstant.OPS_FAILED_CODE);
        }
        return result;

    }
}
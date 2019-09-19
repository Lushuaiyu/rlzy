package com.nado.rlzy.controller;

import com.nado.rlzy.base.BaseController;
import com.nado.rlzy.bean.frontEnd.JobListtFront;
import com.nado.rlzy.bean.model.Result;
import com.nado.rlzy.bean.model.ResultJson;
import com.nado.rlzy.bean.query.JobListQuery;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.db.pojo.HrUser;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.platform.exception.AssertException;
import com.nado.rlzy.service.RecruitmentHomePageService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName 招聘版首页controller
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/2 14:38
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "recruitmentHomePage")
@Api(description = "招聘版首页")
public class RecruitmentHomePageController extends BaseController {

    @Resource
    private RecruitmentHomePageService service;


    /**
     * 推荐人名下的求职者 -- 查询求职列表概览
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.bean.frontEnd.JobListtFront>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:39 2019/7/2
     * @Param [query]
     **/
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sex", value = "性别", dataType = "integer", required = true),
            @ApiImplicitParam(name = "education", value = "学历", dataType = "string", required = true),
            @ApiImplicitParam(name = "profession", value = "专业", dataType = "string", required = true),
            @ApiImplicitParam(name = "age", value = "年龄", dataType = "string", required = true),
            @ApiImplicitParam(name = "arrivalTime", value = "到岗时间 随时 随时是什么时候?  一周内 7  一月内 30  ", dataType = "date", required = true),
            @ApiImplicitParam(name = "graduationTime", value = "毕业时间 今年 0 去年 1 前年 2 更早 3", dataType = "date", required = true),
            @ApiImplicitParam(name = "relation", value = "与推荐人关系", dataType = "integer", required = true),
            @ApiImplicitParam(name = "type", value = "1 求职列表 2 推荐人列表", dataType = "integer", required = true)
    })
    @ApiOperation(value = "查询求职列表概览 | 查询推荐人概览", notes = "查询求职列表概览 | 查询推荐人概览", httpMethod = "POST")

    @RequestMapping(value = "selectJobListOverview")
    @ResponseBody
    public ResultJson selectJobListOverview(JobListQuery query) {
        Map<String, Object> map = new HashMap<>();
        ResultJson resultJson = new ResultJson();
        try {
            if (query.getType().equals(1)) {
                //求职列表
                List<HrSignUp> list = service.selectJobListOverview(query);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                map.put("selectJobListOverview", list);
                resultJson.setData(map);
            } else {
                //推荐人列表
                List<HrUser> users = service.referrer(query);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                map.put("referrer", users);
                resultJson.setData(map);
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

    /**
     * 推荐人名下的求职者 -- 查询求职列表详情
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.bean.frontEnd.JobListtFront>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:39 2019/7/2
     * @Param [query]
     **/
    @ApiImplicitParam(name = "id", value = "求职表id", dataType = "integer", required = true)
    @ApiOperation(value = "查询求职列表详情 就是报名表详情  ", notes = "查询求职列表详情", httpMethod = "POST")
    @RequestMapping(value = "selectJobList")
    @ResponseBody
    public Result<JobListtFront> selectJobList(JobListQuery query) {
        Result<JobListtFront> result = new Result<>();
        try {
            List<JobListtFront> fronts = service.selectJobList(query);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);

            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(fronts);


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

    @RequestMapping(value = "referrerDetails")
    @ResponseBody
    @ApiOperation(notes = "招聘端首页 推荐人列表详情", value = "招聘端首页 推荐人列表详情", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "推荐人id", required = true)
    })
    public ResultJson referrerDetails(Integer userId) {
        ResultJson resultJson = new ResultJson();
        try {
            List<HrUser> hrUsers = service.referrerDetails(userId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(hrUsers);

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


   /* @ApiImplicitParam(name = "", value = "", dataType = "", required = true)
    @ApiOperation(value = "", notes = "")
    @RequestMapping(value = "selectCollectList")
    @ResponseBody
    public Result<JobListtFront> selectCollectList(){
        List<JobListtFront> fronts = service.selectCollectList();
        Result<JobListtFront> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(fronts);
        return result;
    }*/


    @RequestMapping(value = "recruitmentBriefchapter")
    @ResponseBody
    @ApiOperation(value = "招聘端首页 简章概览", notes = "招聘端首页 简章概览", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "int", required = true)
    })
    public ResultJson recruitmentBriefchapter(Integer userId) {
        ResultJson resultJson = new ResultJson();
        try {
            Map<String, Object> map = service.recruitmentBriefchapter(userId);
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

    @RequestMapping(value = "representativeUnitSubAccount")
    @ResponseBody
    @ApiOperation(value = "招聘端首页 简章概览 子账号", notes = "招聘端首页 简章概览 子账号", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "int", required = true)
    })
    public ResultJson representativeUnitSubAccount(Integer userId) {
        ResultJson resultJson = new ResultJson();
        try {
            Map<String, Object> map = service.representativeUnitSubAccount(userId);
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

    @RequestMapping(value = "addOrCancelCollect")
    @ResponseBody
    @ApiOperation(value = "招聘端求职列表 添加|取消搜藏", notes = "招聘端求职列表 添加|取消搜藏", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true),
            @ApiImplicitParam(name = "signUpId", value = "报名id", required = true),
            @ApiImplicitParam(name = "id", value = "collect table的主键id", required = true),
            @ApiImplicitParam(name = "type", value = "1 add collect 2  cancel collect", required = true)
    })
    public ResultJson addOrCancelCollect(Integer userId, Integer signUpId, Integer id, Integer type) {
        ResultJson resultJson = new ResultJson();
        try {
            HashMap<String, Object> map = new HashMap<>();
            if (type.equals(1)) {
                //add collect
                int sign = service.collectSignUPTable(userId, signUpId);
                map.put("collectSignUPTable", sign);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(map);
            } else {
                //cancel collect
                int cancel = service.collectCancel(id);
                map.put("collectCancel", cancel);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(map);
            }
            return resultJson;
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

    @RequestMapping(value = "addOrCancelCollectReferrer")
    @ResponseBody
    @ApiOperation(value = "招聘端推荐人列表 添加|取消搜藏", notes = "招聘端推荐人列表 添加|取消搜藏", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true),
            @ApiImplicitParam(name = "id", value = "collect table的主键id", required = true),
            @ApiImplicitParam(name = "type", value = "1 add collect 2  cancel collect", required = true)
    })
    public ResultJson addOrCancelCollectReferrer(Integer userId, Integer id, Integer type) {
        ResultJson resultJson = new ResultJson();
        try {
            HashMap<String, Object> map = new HashMap<>();
            if (type.equals(1)) {
                int referrer = service.collectReferrer(userId);
                map.put("collectReferrer", referrer);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(map);
            } else {
                int cancel = service.collectCancel(id);
                map.put("collectCancel", cancel);
                resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                resultJson.setData(map);
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

    @ResponseBody
    @RequestMapping(value = "selectCoCertificationStatus")
    @ApiOperation(value = "查询企业认证状态", notes = "查询企业认证状态")
    public ResultJson selectCoCertificationStatus(Integer userId) {
        ResultJson resultJson = new ResultJson();
        try {
            Integer status = service.selectCoCertificationStatus(userId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(status);
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
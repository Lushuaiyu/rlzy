package com.nado.rlzy.controller;

import com.nado.rlzy.base.BaseController;
import com.nado.rlzy.bean.frontEnd.JobListtFront;
import com.nado.rlzy.bean.model.Result;
import com.nado.rlzy.bean.model.ResultInfo;
import com.nado.rlzy.bean.query.JobListQuery;
import com.nado.rlzy.db.pojo.Collect;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.RecruitmentHomePageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @Autowired
    private RecruitmentHomePageService service;


    /**
     * 查询求职列表概览
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.bean.frontEnd.JobListtFront>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:39 2019/7/2
     * @Param [query]
     **/
    @ApiImplicitParams({
      @ApiImplicitParam(name = "briefChapterId", value = "简章id", dataType = "integer", required = true),
      @ApiImplicitParam(name = "sex", value = "性别", dataType = "integer", required = true),
      @ApiImplicitParam(name = "education", value = "学历", dataType = "string", required = true),
      @ApiImplicitParam(name = "profession", value = "专业", dataType = "string", required = true),
      @ApiImplicitParam(name = "age", value = "年龄", dataType = "string", required = true),
      @ApiImplicitParam(name = "arrivalTime", value = "到岗时间", dataType = "date", required = true),
      @ApiImplicitParam(name = "graduationTime", value = "毕业时间", dataType = "date", required = true),
    })
    @ApiOperation(value = "查询求职列表概览", notes = "查询求职列表概览", httpMethod = "POST")

    @RequestMapping(value = "selectJobListOverview")
    @ResponseBody
    public Result<JobListtFront> selectJobListOverview(JobListQuery query) {
        List<JobListtFront> list = service.selectJobListOverview(query);
        Result<JobListtFront> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(list);
        return result;
    }

    /**
     * 查询求职列表详情
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.bean.frontEnd.JobListtFront>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:39 2019/7/2
     * @Param [query]
     **/
    @ApiImplicitParam(name = "id", value = "求职表id", dataType = "integer", required = true)
    @ApiOperation(value = "查询求职列表详情", notes = "查询求职列表详情", httpMethod = "POST")
    @RequestMapping(value = "selectJobList")
    @ResponseBody
    public Result<JobListtFront> selectJobList(JobListQuery query) {
        List<JobListtFront> fronts = service.selectJobList(query);
        Result<JobListtFront> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);

        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(fronts);
        return result;

    }

    /**
     * 添加收藏 概览
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.bean.frontEnd.JobListtFront>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:12 2019/7/8
     * @Param [userId]
     **/
    @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer", required = true)
    @ApiOperation(value = "userId", notes = "用户id" , httpMethod = "POST")
    @RequestMapping(value = "selectCollectListOverview")
    @ResponseBody
    public Result<JobListtFront> selectCollectListOverview(Integer userId) {
        List<JobListtFront> fronts = service.selectCollectListOverview(userId);
        Result<JobListtFront> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(fronts);
        return result;

    }
   /* @ApiImplicitParam(name = "", value = "", dataType = "", required = true)
    @ApiOperation(value = "", notes = "")
    @RequestMapping(value = "selectCollectList")
    @ResponseBody
    public Result<JobListtFront> selectCollectList(){
        List<JobListtFront> fronts = service.selectCollectList();
        Result<JobListtFront> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(fronts);
        return result;
    }
*/


    /**
     * 招聘端 添加收藏
     *
     * @return com.nado.rlzy.bean.model.ResultInfo
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:16 2019/7/8
     * @Param [briefcharpter, userId, signUpId]
     **/


    @RequestMapping(value = "collect")
    @ApiOperation(value = "招聘端 添加收藏", notes = "招聘端 添加收藏", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "briefchapterId", value = "简章id", dataType = "integer", required = true),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "integer", required = true),
            @ApiImplicitParam(name = "signUpId", value = "求职表id", dataType = "integer", required = true)
    })
    @ResponseBody
    public ResultInfo collect(Collect collect) {
        service.save(collect);
        return success(RlzyConstant.OPS_SUCCESS_CODE, RlzyConstant.OPS_SUCCESS_MSG);

    }

    /**
     * 招聘端 取消收藏
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:43 2019/7/8
     * @Param [collect]
     * @return com.nado.rlzy.bean.model.ResultInfo
     **/

    @RequestMapping(value = "updateSignUpCollectStatus")
    @ResponseBody
    @ApiOperation(value = "招聘端 取消收藏", notes = "招聘端 取消收藏", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "signUpId", name = "求职表id", dataType = "integer", required = true)
    })
    public ResultInfo updateSignUpCollectStatus(Collect collect){
        service.updateSignUpCollectStatus(collect);
        return success(RlzyConstant.OPS_SUCCESS_CODE, RlzyConstant.OPS_SUCCESS_MSG);

    }




}
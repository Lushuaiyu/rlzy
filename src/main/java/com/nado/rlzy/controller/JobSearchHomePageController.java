package com.nado.rlzy.controller;

import com.nado.rlzy.base.BaseController;
import com.nado.rlzy.bean.dto.ComplaintDto;
import com.nado.rlzy.bean.dto.ComplaintPage;
import com.nado.rlzy.bean.model.CommonResult;
import com.nado.rlzy.bean.model.Result;
import com.nado.rlzy.bean.model.ResultInfo;
import com.nado.rlzy.bean.model.ResultJson;
import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.bean.query.ComplaintQuery;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.JobSearchHomePageService;
import com.nado.rlzy.service.PersonCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName 求职端首页
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/6/28 17:04
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "homepage")
@Api(description = "求职端首页controller")
public class JobSearchHomePageController extends BaseController {

    @Autowired
    private JobSearchHomePageService service;

    @Autowired
    private PersonCenterService centerService;

    /**
     * 招聘简章查询接口 全部职位
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.bean.frontEnd.BriefcharpterFront>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:42 2019/7/3
     * @Param [pageNum, limit, query]
     **/
    @RequestMapping(value = "queryBriefcharpterDtoByParams")
    @ResponseBody
    @ApiOperation(notes = "招聘简章查询接口 全部职位", value = "招聘简章查询接口 全部职位", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "integer", required = true),
            @ApiImplicitParam(name = "typeId", value = "用户id", dataType = "integer", required = true),
            @ApiImplicitParam(name = "postId", value = "招聘岗位", dataType = "integer", required = true),
            @ApiImplicitParam(name = "recruitedCompany", value = "公司名字", dataType = "string", required = true)

    })
    public ResultJson queryBriefcharpterDtoByParams(BriefcharpterQuery query) {

        List<HrBriefchapter> vals = service.queryBriefcharpterDtoByParams(query);
        List<HrBriefchapter> list = service.queryBriefcharpterByParams(query);
        HashMap<String, Object> map = new HashMap<>();
        map.put("queryBriefcharpterDtoByParams", vals);
        map.put("queryBriefcharpterByParams", list);
        ResultJson result = new ResultJson();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(map);
        return result;

    }

    /**
     * 查询招聘简章详情
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:19 2019/7/4
     * @Param [query]
     **/
    @RequestMapping(value = "queryBriefcharpterDetileByParams")
    @ResponseBody
    @ApiOperation(notes = "查询招聘简章详情", value = "查询招聘简章详情", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "type", name = "登陆者身份 1 本人 2 推荐人", dataType = "int", required = true),
            @ApiImplicitParam(value = "sex", name = "1 男 2 女", dataType = "int", required = true),
            @ApiImplicitParam(value = "query", name = "其余参数见文档", dataType = "int", required = true)
    })
    public ResultJson queryBriefcharpterDetileByParams(BriefcharpterQuery query) {
        List<HrBriefchapter> list = service.queryBriefcharpterDetileByParams(query);
        List<HrBriefchapter> list1 = service.queryBriefcharpterDetileRecruitment(query);

        HashMap<String, Object> map = new HashMap<>();
        map.put("queryBriefcharpterDetileByParams", list);
        map.put("queryBriefcharpterDetileRecruitment", list1);
        ResultJson json = new ResultJson();
        json.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        json.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        json.setData(map);
        return json;
    }

    /**
     * 长白班按返费高低排
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:22 2019/7/4
     * @Param [query]
     **/
    @RequestMapping(value = "queryBriefcharpterByLongLive")
    @ResponseBody
    @ApiOperation(notes = "长白班按返费高低排", value = "长白班按返费高低排", httpMethod = "POST")
    @ApiImplicitParam(name = "query", value = "入参", dataType = "BriefcharpterQuery", required = true)
    public ResultJson queryBriefcharpterByLongLive(BriefcharpterQuery query) {
        List<HrBriefchapter> list = service.queryBriefcharpterByLongLive(query);
        List<HrBriefchapter> vals = service.queryBriefcharpterByLongLiveRecruitment(query);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("queryBriefcharpterByLongLive", list);
        map.put("queryBriefcharpterByLongLiveRecruitment", vals);
        ResultJson result = new ResultJson();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(map);
        return result;
    }


    /**
     * 有吃住按返费高低排
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:24 2019/7/4
     * @Param [query]
     **/
    @RequestMapping(value = "queryBriefcharpterByLongEat")
    @ResponseBody
    @ApiOperation(notes = "有吃住按返费高低排", value = "有吃住按返费高低排", httpMethod = "POST")
    @ApiImplicitParam(value = "query", name = "入参", dataType = "BriefcharpterQuery", required = true)
    public ResultJson queryBriefcharpterByLongEat(BriefcharpterQuery query) {
        List<HrBriefchapter> list = service.queryBriefcharpterByLongEat(query);
        List<HrBriefchapter> vals = service.queryBriefcharpterByLongEatRecruitment(query);
        HashMap<String, Object> map = new HashMap<>();
        map.put("queryBriefcharpterByLongEat", list);
        map.put("queryBriefcharpterByLongEatRecruitment", vals);
        ResultJson result = new ResultJson();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(map);
        return result;
    }

    /**
     * 推荐费top10
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:16 2019/7/4
     * @Param [query]
     **/
    @RequestMapping(value = "recommendedFeeTop10")
    @ResponseBody
    @ApiOperation(notes = "推荐费top10", value = "推荐费top10", httpMethod = "POST")
    @ApiImplicitParam(name = "query", value = "入参", dataType = "BriefcharpterQuery", required = true)
    public ResultJson recommendedFeeTop10(BriefcharpterQuery query) {
        List<HrBriefchapter> list = service.recommendedFeeTop10(query);
        List<HrBriefchapter> vals = service.recommendedFeeTop10Recruitment(query);

        HashMap<String, Object> map = new HashMap<>();
        map.put("recommendedFeeTop10", list);
        map.put("recommendedFeeTop10Recruitment", vals);
        ResultJson result = new ResultJson();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(map);
        return result;
    }

    /**
     * 学生专区
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:36 2019/7/5
     * @Param [query]
     **/
    @RequestMapping(value = "studentDivision")
    @ResponseBody
    @ApiOperation(notes = "学生专区", value = "学生专区", httpMethod = "POST")
    @ApiImplicitParam(name = "contractWayDetailId1", value = "用工方式", dataType = "BriefcharpterQuery", required = true)
    public ResultJson studentDivision(BriefcharpterQuery query) {
        List<HrBriefchapter> list = service.studentDivision(query);
        List<HrBriefchapter> vals = service.studentDivisionRecruitment(query);
        HashMap<String, Object> map = new HashMap<>();
        map.put("studentDivision", list);
        map.put("studentDivisionRecruitment", vals);
        ResultJson result = new ResultJson();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(map);
        return result;

    }

    /**
     * 工资排行榜 代招单位
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:36 2019/7/5
     * @Param [query]
     **/
    @RequestMapping(value = "salaryLeaderboard")
    @ResponseBody
    @ApiOperation(notes = "工资排行榜", value = "工资排行榜", httpMethod = "POST")
    @ApiImplicitParam(name = "query", value = "入参", dataType = "BriefcharpterQuery", required = true)
    public ResultJson salaryLeaderboard(BriefcharpterQuery query) {
        List<HrBriefchapter> list = service.salaryLeaderboard(query);
        List<HrBriefchapter> vals = service.salaryLeaderboardRecruitment(query);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("salaryLeaderboard", list);
        map.put("salaryLeaderboardRecruitment", vals);
        ResultJson json = new ResultJson();
        json.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        json.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        json.setData(map);
        return json;
    }


    /**
     * 企业直招
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:11 2019/7/8
     * @Param [query]
     **/
    @RequestMapping(value = "directBusiness")
    @ResponseBody
    @ApiOperation(notes = "企业直招", value = "企业直招", httpMethod = "POST")
    @ApiImplicitParam(name = "query", value = "入参", dataType = "BriefcharpterQuery", required = true)
    public ResultJson directBusiness(BriefcharpterQuery query) {
        List<HrBriefchapter> list = service.directBusiness(query);
        List<HrBriefchapter> vals = service.directBusinessRecruitment(query);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("directBusiness", list);
        map.put("directBusinessRecruitment", vals);
        ResultJson json = new ResultJson();
        json.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        json.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        json.setData(map);
        return json;
    }

    /**
     * 直接录取 代招单位
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:11 2019/7/8
     * @Param [query]
     **/
    @RequestMapping(value = "directAdmission")
    @ResponseBody
    @ApiOperation(notes = "直接录取 代招单位", value = "直接录取 代招单位", httpMethod = "POST")
    @ApiImplicitParam(name = "query", value = "入参", dataType = "BriefcharpterQuery", required = true)
    public ResultJson directAdmission(BriefcharpterQuery query) {
        List<HrBriefchapter> list = service.directAdmission(query);
        List<HrBriefchapter> vals = service.directAdmissionRecruitment(query);
        HashMap<String, Object> map = new HashMap<>();
        map.put("directAdmission", list);
        map.put("directAdmissionRecruitment", vals);
        ResultJson result = new ResultJson();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(map);
        return result;
    }

    @RequestMapping(value = "coHomePage")
    @ResponseBody
    @ApiOperation(notes = "求职端 首页 公司主页", value = "求职端 首页 公司主页", httpMethod = "POST")
    @ApiImplicitParam(value = "groupId", name = "代招单位id", dataType = "Integer", required = true)
    public Result<HrGroup> coHomePage(Integer groupId) {
        Result<HrGroup> result = new Result<>();
        List<HrGroup> groups = service.coHomePage(groupId);
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(groups);
        return result;

    }

    /**
     * 查询报名者的名字
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:42 2019/7/11
     * @Param []
     **/
    @RequestMapping("querySignUpUserName")
    @ResponseBody
    @ApiOperation(notes = "查询报名者的名字", value = "查询报名者的名字", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "type", name = "登录用户的身份", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "integer", required = true)
    })
    public Result<HrSignUp> querySignUpUserName(Integer type, Integer userId) {
        List<HrSignUp> ups = service.querySignUpUserName(type, userId);
        Result<HrSignUp> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(ups);
        return result;


    }

    /**
     * 根据报名者的名字查求职状态
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:22 2019/7/11
     * @Param [jobUserName]
     **/
    @RequestMapping("querySignUpStatus")
    @ResponseBody
    @ApiOperation(notes = "根据报名者的名字查求职状态", value = "根据报名者的名字查求职状态", httpMethod = "POST")
    @ApiImplicitParam(value = "jobUserName", name = "求职状态", dataType = "integer", required = true)

    public Result<HrSignUp> querySignUpStatus(String jobUserName) {
        List<HrSignUp> hrSignUps = service.querySignUpStatus(jobUserName);
        Result<HrSignUp> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(hrSignUps);
        return result;

    }

    /**
     * 1待面试 7已面试 进入到待面试的状态
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:09 2019/7/11
     * @Param [signUpStatus]
     **/
    @RequestMapping(value = "queryBriefchapterBySignUpStatus")
    @ResponseBody
    @ApiOperation(value = "根据求职状态查询简章 1待面试 7已面试 进入到待面试的状态", notes = "根据求职状态查询简章 1待面试 7已面试 进入到待面试的状态", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpStatus", name = "求职状态", dataType = "Integer", required = true)
    public ResultJson queryBriefchapterBySignUpStatus(Integer signUpStatus) {
        Map<Object, Object> map = service.queryBriefchapterBySignUpStatus(signUpStatus);
        ResultJson resultJson = new ResultJson();
        resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        resultJson.setData(map);
        return resultJson;
    }

    /**
     * 取消报名
     *
     * @return com.nado.rlzy.bean.model.ResultInfo
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:56 2019/7/12
     * @Param [signUpId]
     **/
    @RequestMapping(value = "cancelRegistration")
    @ResponseBody
    @ApiOperation(value = "取消报名", notes = "取消报名", httpMethod = "POST")
    @ApiImplicitParam(value = "signUpId", name = "报名表主键id", dataType = "Integer", required = true)
    public ResultInfo cancelRegistration(Integer signUpId) {
        service.cancelRegistration(signUpId);
        return success(RlzyConstant.OPS_SUCCESS_CODE, RlzyConstant.OPS_SUCCESS_MSG);

    }

    @RequestMapping(value = "IWantToSignUp")
    @ResponseBody
    @ApiOperation(value = "我要报名 身份是本人, 返回1 报名成功", notes = "我要报名 身份是本人, 返回1 报名成功", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "briefChapterId", name = "简章id", dataType = "Integer", required = true)
    })

    public CommonResult IWantToSignUp(Integer userId, Integer briefChapterId) {
        int count = service.IWantToSignUp(userId, briefChapterId);


        return CommonResult.success(count);


    }

    /**
     * 添加和取消收藏
     *
     * @return com.nado.rlzy.bean.model.ResultInfo
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:02 2019/7/8
     * @Param [collect]
     **/
    @PostMapping(value = "addCancelBriefchapter")
    @ResponseBody
    @ApiOperation(notes = "添加和取消收藏", value = "添加和取消收藏", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "briefchapterId", name = "简章id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "flag", name = "0 添加收藏, 1 取消收藏", dataType = "Integer", required = true)
    })
    public ResultInfo addCancelBriefchapter(Collect collect) {
        service.addCancelBriefchapter(collect);
        return success(RlzyConstant.OPS_SUCCESS_CODE, RlzyConstant.OPS_SUCCESS_MSG);

    }

    /**
     * 新增报名表
     *
     * @return com.nado.rlzy.bean.model.ResultInfo
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:13 2019/7/8
     * @Param []
     **/
    @RequestMapping(value = "addSignUp")
    @ResponseBody
    @ApiOperation(notes = "新增报名表", value = "新增报名表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userName", name = "求职者姓名", dataType = "string", required = true),
            @ApiImplicitParam(value = "sex", name = "求职者性别", dataType = "integer", required = true),
            @ApiImplicitParam(value = "education", name = "学历", dataType = "integer", required = true),
            @ApiImplicitParam(value = "graduationTime", name = "毕业时间", dataType = "date", required = true),
            @ApiImplicitParam(value = "profession", name = "专业", dataType = "string", required = true),
            @ApiImplicitParam(value = "registrationPositionId", name = "意向岗位id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "arrivalTime", name = "到岗时间", dataType = "date", required = true),
            @ApiImplicitParam(value = "expectedSalaryLower", name = "工资上限", dataType = "string", required = true),
            @ApiImplicitParam(value = "expectedSalaryUpper", name = "工资下限", dataType = "string", required = true),
            @ApiImplicitParam(value = "relation", name = "年龄", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "itIsPublic", name = "是否公开", dataType = "integer", required = true),
            @ApiImplicitParam(value = "agreePlatformHelp", name = "是否获取平台帮助", dataType = "integer", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "typeId", name = "身份id", dataType = "integer", required = true)

    })
    public ResultInfo addSignUp(HrSignUp signUp) {
        service.addSignUpTable(signUp);
        return success(RlzyConstant.OPS_SUCCESS_CODE, RlzyConstant.OPS_SUCCESS_MSG);
    }

    /**
     * 添加自定义分组
     *
     * @return com.nado.rlzy.bean.model.ResultInfo
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:42 2019/7/8
     * @Param [record]
     **/
    @RequestMapping(value = "insertSelective")
    @ResponseBody
    @ApiOperation(notes = "添加自定义分组", value = "添加自定义分组", httpMethod = "POST")
    @ApiImplicitParam(value = "groupName", name = "分组名称", dataType = "string", required = true)
    public ResultInfo insertSelective(MySignUpTable record) {
        service.insertSelective(record);
        return success(RlzyConstant.OPS_SUCCESS_CODE, RlzyConstant.OPS_SUCCESS_MSG);

    }


    /**
     * 添加投诉 求职端 首页 投诉 添加投诉
     *
     * @return com.nado.rlzy.bean.model.ResultInfo
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:09 2019/7/11
     * @Param [query, file]
     **/
    @RequestMapping(value = "addComplaint")
    @ResponseBody
    @ApiOperation(notes = "添加投诉", value = "添加投诉", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "name", name = "投诉人", dataType = "string", required = true),
            @ApiImplicitParam(value = "briefChapterId", name = "简章id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "complaintTypeId", name = "投诉类型", dataType = "string", required = true),
            @ApiImplicitParam(value = "description", name = "问题描述", dataType = "string", required = true),
            @ApiImplicitParam(value = "evidence", name = "投诉凭证", dataType = "string", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "integer", required = true)
    })
    public ResultJson addComplaint(ComplaintQuery query, MultipartFile file) {
        String head = centerService.updateHead(file);
        int complaint = service.addComplaint(query, head);
        ResultJson resultJson = new ResultJson();
        resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        resultJson.setData(complaint);
        return resultJson;
    }

    @RequestMapping(value = "atThePosition")
    @ResponseBody
    @ApiOperation(notes = "在招职位 可能废弃", value = "在招职位 可能废弃", httpMethod = "POST")
    @ApiImplicitParam(value = "groupId", name = "公司id", dataType = "Integer", required = true)
    public Result<HrBriefchapter> atThePosition(Integer groupId) {
        List<HrBriefchapter> list = service.atThePosition(groupId);
        Result<HrBriefchapter> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(list);
        return result;

    }

    /**
     * 投诉撤回
     *
     * @return com.nado.rlzy.bean.model.ResultInfo
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:04 2019/7/11
     * @Param [query]
     **/
    @RequestMapping(value = "complaintWithdrawn")
    @ResponseBody
    @ApiOperation(notes = "投诉撤回", value = "投诉撤回", httpMethod = "POST")
    @ApiImplicitParam(value = "id", name = "投诉id", dataType = "integer", required = true)
    public ResultInfo complaintWithdrawn(ComplaintQuery query) {
        service.complaintWithdrawn(query);
        return success(RlzyConstant.OPS_SUCCESS_CODE, RlzyConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping(value = "searchGroupingInformation")
    @ResponseBody
    @ApiOperation(notes = "查询我的求职表分组", value = "查询我的求职表分组", httpMethod = "POST")
    public Result<MySignUpTable> searchGroupingInformation() {
        List<MySignUpTable> tables = service.searchGroupingInformation();
        Result<MySignUpTable> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(tables);
        return result;

    }

    @RequestMapping(value = "grouper")
    @ResponseBody
    @ApiOperation(notes = "查询 每一个分组的求职者", value = "查询 每一个分组的求职者", httpMethod = "POST")
    @ApiImplicitParam(value = "status", name = "求职端 我的求职表 分组类型", dataType = "integer", required = true)
    public Result<HrSignUp> grouper(Integer status) {
        List<HrSignUp> grouper = service.grouper(status);
        Result<HrSignUp> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(grouper);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "selectSignUpTableBySignUpName")
    @ApiOperation(value = "求职端 求职表 我的求职表 通过姓名 搜索 报名表", notes = "求职端 求职表 我的求职表 通过姓名 搜索 报名表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signUpName", name = "求职者姓名", dataType = "string", required = true),
            @ApiImplicitParam(value = "status", name = "分组类型", dataType = "integer", required = true)
    })
    public Result<HrSignUp> selectSignUpTableBySignUpName(String signUpName, Integer status) {
        List<HrSignUp> ups = service.selectSignUpTableBySignUpName(signUpName, status);
        Result<HrSignUp> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(ups);
        return result;
    }

    @RequestMapping(value = "confirmRegistration")
    @ResponseBody
    @ApiOperation(value = "求职端 求职表 我的求职表 确认报名", notes = "求职端 求职表 我的求职表 确认报名", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "briefChapterId", name = "简章id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "id", name = "报名表id", dataType = "integer []", required = true)
    })
    public CommonResult confirmRegistration(Integer briefChapterId, Integer[] id) {
        int registration = service.confirmRegistration(briefChapterId, id);
        return CommonResult.success(registration, RlzyConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping(value = "creditCenter")
    @ResponseBody
    @ApiImplicitParam(value = "status", name = "投诉状态", dataType = "integer", required = true)
    @ApiOperation(notes = "招聘端 信用中心 投诉待处理 已撤销", value = "招聘端 信用中心 投诉待处理 已撤销", httpMethod = "POST")
    public Result<ComplaintDto> creditCenter(Integer status) {
        var list = service.creditCenter(status);
        var result = new Result<ComplaintDto>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "complaintPage")
    @ResponseBody
    @ApiOperation(notes = "投诉页面 前端选择内容", value = "投诉页面 前端选择内容", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "typeId", name = "用户身份id  1 本人 2 推荐人", dataType = "integer", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id ", dataType = "integer", required = true),
            @ApiImplicitParam(value = "brieId", name = "简章id ", dataType = "integer", required = true),
            @ApiImplicitParam(value = "dictionary", name = "投诉类型id  dictionary = 24 ", dataType = "integer", required = true)
    })
    public ResultJson complaintPage(Integer typeId, Integer userId, Integer brieId, Integer dictionary) {
        Map<String, List<ComplaintPage>> map = service.complaintPage(typeId, userId, brieId, dictionary);
        ResultJson json = new ResultJson();
        json.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        json.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        json.setData(map);
        return json;
    }


}
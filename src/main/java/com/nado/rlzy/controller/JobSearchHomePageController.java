package com.nado.rlzy.controller;

import com.nado.rlzy.base.BaseController;
import com.nado.rlzy.bean.model.ResultInfo;
import com.nado.rlzy.bean.model.ResultJson;
import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.bean.query.ComplaintQuery;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.JobSearchHomePageService;
import com.nado.rlzy.service.JobSeekingPersonalCenterService;
import com.nado.rlzy.service.PersonCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private JobSeekingPersonalCenterService jobSeekingPersonalCenterService;

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
            @ApiImplicitParam(name = "type", value = "1 本人 2 推荐人", dataType = "integer", required = true),
            @ApiImplicitParam(name = "postName", value = "招聘岗位", dataType = "integer", required = true),
            @ApiImplicitParam(name = "recruitedCompany", value = "公司名字", dataType = "string", required = true)

    })
    public ResultJson queryBriefcharpterDtoByParams(BriefcharpterQuery query) {

        Map<String, Object> map1 = service.queryBriefcharpterDtoByParams(query);
        Map<String, Object> map2 = service.queryBriefcharpterByParams(query);
        HashMap<String, Object> map = new HashMap<>();
        map.put("queryBriefcharpterDtoByParams", map1);
        map.put("queryBriefcharpterByParams", map2);
        ResultJson result = new ResultJson();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(map);
        return result;

    }

    /**
     * 求职端 首页 简章列表 查询招聘简章详情
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:19 2019/7/4
     * @Param [query]
     **/
    @RequestMapping(value = "queryBriefcharpterDetileByParams")
    @ResponseBody
    @ApiOperation(notes = "type = 0:求职端 首页 简章列表 查询招聘简章详情 | type = 1:query 除了 求职端首页 简章列表以外的简章详情",
            value = "type = 0:求职端 首页 简章列表 查询招聘简章详情 | type = 1:query 除了 求职端首页 简章列表以外的简章详情", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "type1", name = "0 简章列表的详情 1 除了简章列表外的详情", dataType = "int", required = true),
            @ApiImplicitParam(value = "briefcharpterId", name = "简章id", dataType = "int", required = true),
            @ApiImplicitParam(value = "recruitedCompany", name = "用人单位", dataType = "int", required = true),
    })
    public ResultJson queryBriefcharpterDetileByParams(BriefcharpterQuery query) {
        ResultJson json = new ResultJson();
        HashMap<String, Object> map = new HashMap<>();
        if (query.getType1().equals(0)) {
            List<HrBriefchapter> list = service.queryBriefcharpterDetileByParams(query);
            List<HrBriefchapter> list1 = service.queryBriefcharpterDetileRecruitment(query);
            //推荐单位 代招单位
            List<HrBriefchapter> hrBriefchapters = service.recommendAPosition(query.getRecruitedCompany());
            //推荐单位 招聘单位
            List<HrBriefchapter> hrBriefchapters1 = service.recommendAPositionRecruitment(query.getRecruitedCompany());

            //招聘单位
            map.put("queryBriefcharpterDetileRecruitment", list1);
            map.put("recommendAPositionRecruitment", hrBriefchapters1);
            //代招单位
            map.put("queryBriefcharpterDetileByParams", list);
            map.put("recommendAPosition", hrBriefchapters);
            json.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            json.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            json.setData(map);

            json.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            json.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            json.setData(map);
        } else if (query.getType1().equals(1)) {
            // 除了求职端首页简章列表以外的简章详情
            Map<String, Object> detile = service.queryBriefcharpterListDetileByParams(query);
            json.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            json.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            json.setData(detile);
        }
        return json;
    }

    /**
     * 长白班按返费高低排
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:22 2019/7/4
     * @Param [query]
     **/
    @RequestMapping(value = "queryBriefcharpterSeven")
    @ResponseBody
    @ApiOperation(notes = "除了简章列表以外的简章概览", value = "除了简章列表以外的简章概览", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type1", value = "0 长白班按返费高低排 ", dataType = "BriefcharpterQuery", required = true),
            @ApiImplicitParam(name = "type1", value = "1 有吃住按返费高低排 ", dataType = "BriefcharpterQuery", required = true),
            @ApiImplicitParam(name = "type1", value = "2 推荐费top10 ", dataType = "BriefcharpterQuery", required = true),
            @ApiImplicitParam(name = "type1", value = "3 学生专区 ", dataType = "BriefcharpterQuery", required = true),
            @ApiImplicitParam(name = "contractWayDetailId1", value = "用工形式学生专区 type1 = 3 contractWayDetailId1: 4, 5  ", dataType = "BriefcharpterQuery", required = true),
            @ApiImplicitParam(name = "type1", value = "4 工资排行榜 ", dataType = "BriefcharpterQuery", required = true),
            @ApiImplicitParam(name = "type1", value = "5 企业直招 ", dataType = "BriefcharpterQuery", required = true),
            @ApiImplicitParam(name = "type1", value = "6 直接录取 ", dataType = "BriefcharpterQuery", required = true),

    })
    public ResultJson queryBriefcharpterByLongLive(BriefcharpterQuery query) {
        ResultJson result = new ResultJson();
        HashMap<String, Object> map = new HashMap<>();
        if (query.getType1().equals(0)) {
            // 长白班按返费高低排
            List<HrBriefchapter> list = service.queryBriefcharpterByLongLive(query);
            List<HrBriefchapter> vals = service.queryBriefcharpterByLongLiveRecruitment(query);
            map.put("queryBriefcharpterByLongLive", list);
            map.put("queryBriefcharpterByLongLiveRecruitment", vals);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(map);
        } else if (query.getType1().equals(1)) {
            // 有吃住按返费高低排
            List<HrBriefchapter> list = service.queryBriefcharpterByLongEat(query);
            List<HrBriefchapter> vals = service.queryBriefcharpterByLongEatRecruitment(query);
            map.put("queryBriefcharpterByLongEat", list);
            map.put("queryBriefcharpterByLongEatRecruitment", vals);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(map);
        } else if (query.getType1().equals(2)) {
            // 推荐费top10
            List<HrBriefchapter> list = service.recommendedFeeTop10(query);
            List<HrBriefchapter> vals = service.recommendedFeeTop10Recruitment(query);
            map.put("recommendedFeeTop10", list);
            map.put("recommendedFeeTop10Recruitment", vals);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(map);
        } else if (query.getType1().equals(3)) {
            // 学生专区
            List<HrBriefchapter> list = service.studentDivision(query);
            List<HrBriefchapter> vals = service.studentDivisionRecruitment(query);
            map.put("studentDivision", list);
            map.put("studentDivisionRecruitment", vals);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(map);
        } else if (query.getType1().equals(4)) {
            // 工资排行榜
            List<HrBriefchapter> list = service.salaryLeaderboard(query);
            List<HrBriefchapter> vals = service.salaryLeaderboardRecruitment(query);
            map.put("salaryLeaderboard", list);
            map.put("salaryLeaderboardRecruitment", vals);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(map);
        } else if (query.getType1().equals(5)) {
            // 企业直招
            List<HrBriefchapter> list = service.directBusiness(query);
            List<HrBriefchapter> vals = service.directBusinessRecruitment(query);
            map.put("directBusiness", list);
            map.put("directBusinessRecruitment", vals);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(map);
        } else if (query.getType1().equals(6)) {
            // 直接录取
            List<HrBriefchapter> list = service.directAdmission(query);
            List<HrBriefchapter> vals = service.directAdmissionRecruitment(query);
            map.put("directAdmission", list);
            map.put("directAdmissionRecruitment", vals);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(map);
        }
        return result;
    }

    @RequestMapping(value = "coHomePage")
    @ResponseBody
    @ApiOperation(notes = "求职端 首页 公司主页 代招单位", value = "求职端 首页 公司主页", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "groupId", name = "代招单位id || 招聘单位id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "type", name = "类型 1 基本信息 2 在招职位 3 历史记录", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "briefchapterId", name = "简章id", dataType = "Integer", required = true)
    })
    public ResultJson coHomePage(Integer groupId, Integer type, Integer briefchapterId) {
        ResultJson result = new ResultJson();
        Map<String, Object> map = new HashMap<>();
        if (type.equals(1)) {
            //求职端公司主页基本信息 代招单位 || 招聘单位
            List<HrGroup> groups = service.coHomePage(groupId);
            map.put("coHomePage", groups);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(map);
        } else if (type.equals(2)) {
            // 求职端公司主页在招职位 代招单位
            List<HrBriefchapter> list = service.atThePosition(groupId);
            map.put("atThePosition", list);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(list);
        } else {
            //历史记录
            //违规记录
            List<HrComplaint> list = service.violationRecord(groupId);
            //简章 代招单位
            List<HrBriefchapter> hrBriefchapters = service.companyHomeHistory(groupId);
            //人数
            Map<String, Object> entry = service.interviewReportEntry(briefchapterId);
            map.put("violationRecord", list);
            map.put("companyHomeHistory", hrBriefchapters);
            map.put("interviewReportEntry", entry);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(map);
        }
        return result;
    }

    @RequestMapping(value = "coHomePageRecruitment")
    @ResponseBody
    @ApiOperation(value = "求职端 首页 公司主页 招聘单位", notes = "求职端 首页 公司主页 代招单位 招聘单位")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "groupId", name = "代招单位id || 招聘单位id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "type", name = "类型 1 基本信息 2 在招职位 3 历史记录", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "briefchapterId", name = "简章id", dataType = "Integer", required = true)
    })
    public ResultJson coHomePageRecruitment(Integer groupId, Integer type, Integer briefchapterId){
        ResultJson result = new ResultJson();
        Map<String, Object> map = new HashMap<>();
        if (type.equals(1)) {
            //求职端公司主页基本信息 代招单位 || 招聘单位
            List<HrGroup> groups = service.coHomePage(groupId);
            map.put("coHomePage", groups);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(map);
        } else if (type.equals(2)) {
            // 求职端公司主页在招职位 招聘单位
            List<HrBriefchapter> list = service.atThePositionRecruitment(groupId);
            map.put("atThePosition", list);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(list);
        } else {
            //历史记录
            //违规记录
            List<HrComplaint> list = service.violationRecord(groupId);
            //简章 招聘单位
            List<HrBriefchapter> hrBriefchapters = service.compayHomeHistoryRecruitment(groupId);
            Map<String, Object> entry = service.interviewReportEntry(briefchapterId);
            map.put("violationRecord", list);
            map.put("companyHomeHistory", hrBriefchapters);
            map.put("interviewReportEntry", entry);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(map);
        }
        return result;


    }

    /**
     * 求职端 我的工作 查询求职者名字 | 查询求职状态 | 查询简章 ps  jobStatus : 1待面试 7已面试 是进入到待面试阶段
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:09 2019/7/11
     * @Param [signUpStatus]
     **/
    @RequestMapping(value = "queryBriefchapterBySignUpStatus")
    @ResponseBody
    @ApiOperation(value = "求职端 我的工作 查询求职者名字 | 查询求职状态 | 查询简章 ps  jobStatus : 1待面试 7已面试 是进入到待面试阶段",
            notes = "求职端 我的工作 查询求职者名字 | 查询求职状态 | 查询简章 ps  jobStatus : 1待面试 7已面试 是进入到待面试阶段", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "type", name = "登录者身份", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true)
    })
    public ResultJson queryBriefchapterBySignUpStatus(Integer type, Integer userId) {
        Map<Object, Object> map = service.queryBriefchapterBySignUpStatus(type, userId);
        ResultJson resultJson = new ResultJson();
        resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        resultJson.setData(map);
        return resultJson;
    }


    @RequestMapping(value = "IWantToSignUp")
    @ResponseBody
    @ApiOperation(value = "我要报名 | 推荐人报名", notes = "我要报名 | 推荐人报名", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "briefChapterId", name = "简章id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "signupId", name = "报名id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "type", name = "1 我要报名 2 推荐人报名", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "number", name = "前台传数组到后台", dataType = "Integer", required = true)
    })

    public ResultJson IWantToSignUp(HrSignupDeliveryrecord deliveryrecord) {
        ResultJson resultJson = new ResultJson();
        if (deliveryrecord.getType().equals(1)) {
            //我要报名 本人
            int count = service.IWantToSignUp(deliveryrecord);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
            return resultJson;
        }
        if (deliveryrecord.getType().equals(2)) {
            //推荐人给被推荐人报名
            int count = service.referrerToSIgnUp(deliveryrecord);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(count);
            return resultJson;
        }
        return null;
    }

    /**
     * 求职端 添加和取消收藏
     *
     * @return com.nado.rlzy.bean.model.ResultInfo
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:02 2019/7/8
     * @Param [collect]
     **/
    @RequestMapping(value = "addCancelBriefchapter")
    @ResponseBody
    @ApiOperation(notes = " 求职端 添加和取消收藏 type = 0 添加搜藏 1 取消搜藏", value = "添加和取消收藏", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "briefchapterId", name = "简章id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "signUpId", name = "报名id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "type", name = "0 添加收藏, 1 取消收藏", dataType = "Integer", required = true)
    })
    public ResultJson addCancelBriefchapter(Collect collect) {
        Map<String, Object> map = new HashMap<>();
        ResultJson resultJson = new ResultJson();
        if (collect.getType().equals(0)) {
            int i = service.addCancelBriefchapter(collect);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            map.put("addCancelBriefchapter", i);
            resultJson.setData(map);
        } else {
            int ii = service.updateCollect(collect);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
            map.put("updateCollect", ii);
            resultJson.setData(map);
        }
        return resultJson;
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
            @ApiImplicitParam(value = "typeId", name = "身份id", dataType = "integer", required = true),
            @ApiImplicitParam(name = "mySignUpTableId", value = "我的报名表id", dataType = "integer", required = true)

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
    @ApiImplicitParams({
            @ApiImplicitParam(value = "groupName", name = "分组名称", dataType = "string", required = true),
            @ApiImplicitParam(value = "userId", name = "推荐人id", dataType = "string", required = true),
    })
    public ResultJson insertSelective(MySignUpTable record) {
        int i = service.insertSelective(record);
        ResultJson resultJson = new ResultJson();
        resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        resultJson.setData(i);
        return resultJson;
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
    public ResultJson addComplaint(ComplaintQuery query) {
        String head = centerService.updateHead(query.getFile());
        int complaint = service.addComplaint(query, head);
        ResultJson resultJson = new ResultJson();
        resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        resultJson.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        resultJson.setData(complaint);
        return resultJson;
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
        Map<String, Object> map = service.complaintPage(typeId, userId, brieId, dictionary);
        ResultJson json = new ResultJson();
        json.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        json.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        json.setData(map);
        return json;
    }


    @RequestMapping(value = "searchGroupingInformation")
    @ResponseBody
    @ApiOperation(notes = "查询我的求职表分组 推荐人身份才能添加分组", value = "查询我的求职表分组 推荐人身份才能添加分组", httpMethod = "POST")
    @ApiImplicitParam(value = "userId", name = "用户id", dataType = "int", required = true)
    public ResultJson searchGroupingInformation(Integer userId) {
        Map<String, Object> tables = service.searchGroupingInformation(userId);
        ResultJson result = new ResultJson();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(tables);
        return result;
    }

    @RequestMapping(value = "grouper")
    @ResponseBody
    @ApiOperation(notes = "查询 求职端 身份是推荐人 首页 我的求职表 分组下的 被推荐人的报名表 | 查询求职端身份是推荐人 首页 我的求职表下被推荐人的求职表",
            value = "查询 求职端 身份是推荐人 首页 我的求职表 分组下的 被推荐人的报名表 | 查询求职端身份是推荐人 首页 我的求职表下被推荐人的求职表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "groupName", name = "自定义分组名字", dataType = "int", required = true),
            @ApiImplicitParam(value = "signUpName", name = "被推荐人名字", dataType = "int", required = true),
            @ApiImplicitParam(name = "type", value = "1 我的求职表下的被推荐人的报名表 2 自定义分组下的被推荐人的报名表", dataType = "int", required = true),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "int", required = true)
    })
    public ResultJson grouper(String groupName, String signUpName, Integer type, Integer userId) {
        Map<String, Object> grouper = service.grouper(groupName, signUpName, type, userId);
        ResultJson result = new ResultJson();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(grouper);
        return result;
    }

   /* @RequestMapping(value = "confirmRegistration")
    @ResponseBody
    @ApiOperation(value = "求职端 求职表 我的求职表 确认报名 废弃", notes = "求职端 求职表 我的求职表 确认报名废弃", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "briefChapterId", name = "简章id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "id", name = "报名表id", dataType = "integer []", required = true)
    })
    public CommonResult confirmRegistration(Integer briefChapterId, Integer[] id) {
        int registration = service.confirmRegistration(briefChapterId, id);
        return CommonResult.success(registration, RlzyConstant.OPS_SUCCESS_MSG);
    }*/


}
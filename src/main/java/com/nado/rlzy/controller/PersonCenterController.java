package com.nado.rlzy.controller;

import com.alibaba.fastjson.JSONObject;
import com.nado.rlzy.base.BaseController;
import com.nado.rlzy.bean.dto.ComplaintDto;
import com.nado.rlzy.bean.frontEnd.PersonCoFront;
import com.nado.rlzy.bean.model.CommonResult;
import com.nado.rlzy.bean.model.Result;
import com.nado.rlzy.bean.model.ResultInfo;
import com.nado.rlzy.bean.query.AddCoQuery;
import com.nado.rlzy.bean.query.EditPersonDataQuery;
import com.nado.rlzy.db.pojo.Feedback;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.db.pojo.HrUser;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.PersonCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName 招聘端 个人中心controller
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/1 15:06
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "personCenter")
@Api(description = "个人中心controller")
public class PersonCenterController extends BaseController {
    @Autowired
    private PersonCenterService service;

    public static final Logger logger = LoggerFactory.getLogger(PersonCenterController.class);


    /**
     * 查询我的企业
     *
     * @return java.util.List<com.nado.rlzy.bean.frontEnd.PersonCoFront>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:07 2019/7/1
     * @Param [status] 状态
     **/
    @RequestMapping(value = "queryPersonCo")
    @ResponseBody
    @ApiImplicitParam(name = "status", value = "登陆者身份", dataType = "Integer", required = true)
    @ApiOperation(value = "查询我的企业", notes = "代招单位还是招聘单位 5 是招聘单位 6是代招单位", httpMethod = "POST")
    Result<PersonCoFront> queryPersonCo(Integer status) {
        List<PersonCoFront> personCoFronts = service.queryPersonCo(status);

        Result<PersonCoFront> personCoFrontResult = new Result<>();
        personCoFrontResult.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        personCoFrontResult.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        personCoFrontResult.setData(personCoFronts);
        return personCoFrontResult;


    }

    /**
     * 添加企业
     *
     * @return com.nado.rlzy.bean.model.ResultInfo
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:10 2019/7/1
     * @Param []
     **/
    @RequestMapping(value = "addCo", headers = "content-type=multipart/form-date")
    @ResponseBody
    @ApiOperation(value = "添加企业", notes = "添加企业", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coName", value = "公司名称", dataType = "string", required = true),
            @ApiImplicitParam(name = "coAddress", value = "公司地址", dataType = "string", required = true),
            @ApiImplicitParam(name = "companyProfil", value = "公司简介", dataType = "string", required = true),
            @ApiImplicitParam(value = "file", name = "上传图片", dataType = "MultipartFile", required = true)
    })
    public ResultInfo addCo(AddCoQuery query, MultipartFile file) {
        String url = service.updateHead(file);
        service.addCo(query, url);
        return success(RlzyConstant.OPS_SUCCESS_CODE, RlzyConstant.ADD_SUCESSFUL);

    }


    /* */

    /**
     * 图片上传demo
     *
     * @return java.lang.String
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:38 2019/7/2
     * @Param [request, file]
     **/
    @RequestMapping(value = "headImgUpload", produces = "text/html;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "图片上传 demo", notes = "图片上传 demo", httpMethod = "POST")

    public String headImgUpload(HttpServletRequest request, MultipartFile file) {
        Map<String, Object> value = new HashMap<String, Object>();
        try {
            String url = service.updateHead(file);
            logger.debug("图片路径{}", url);
            value.put("data", url);
            value.put("code", 0);
            value.put("msg", "图片上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            value.put("code", 2000);
            value.put("msg", "图片上传成功");
        }
        return JSONObject.toJSONString(value);
    }

    /**
     * 求职端 我的个人中心
     *
     * @param userId 用户id
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:53 2019/7/9
     **/
    @RequestMapping(value = "personalInformation")
    @ResponseBody
    @ApiOperation(notes = "求职端 我的个人中心", value = "求职端 我的个人中心", httpMethod = "POST")
    @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true)
    public Result<HrSignUp> personalInformation(Integer userId) {
        Result<HrSignUp> result = new Result<>();
        List<HrSignUp> ups = service.personalInformation(userId);
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(ups);
        return result;
    }

    @RequestMapping("personalInformationReferrer")
    @ResponseBody
    @ApiOperation(notes = "推荐人 个人信息", value = "推荐人 个人信息", httpMethod = "POST")
    @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true)
    public Result<HrSignUp> personalInformationReferrer(Integer userId) {
        List<HrSignUp> signUps = service.personalInformationReferrer(userId);
        Result<HrSignUp> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(signUps);
        return result;
    }

    /**
     * 编辑个人信息 个人
     *
     * @return com.nado.rlzy.bean.model.ResultInfo
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:29 2019/7/9
     * @Param [query]
     **/
    @RequestMapping(value = "editPersonData")
    @ResponseBody
    @ApiOperation(value = "编辑个人信息 个人", notes = "编辑个人信息 个人", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "typeId", name = "身份id 登录者是求职者还是推荐者", dataType = "integer", required = true),
            @ApiImplicitParam(value = "sex", name = "性别 0 女 1 男", dataType = "integer", required = true),
            @ApiImplicitParam(value = "education", name = "学历", dataType = "string", required = true),
            @ApiImplicitParam(value = "graduationTime", name = "毕业时间", dataType = "date", required = true),
            @ApiImplicitParam(value = "Profession", name = "专业", dataType = "string", required = true),
            @ApiImplicitParam(value = "registrationPositionId", name = "报名岗位id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "ArrivalTime", name = "到岗时间", dataType = "date", required = true),
            @ApiImplicitParam(value = "ExpectedSalaryLower", name = "期望薪资下限", dataType = "string", required = true),
            @ApiImplicitParam(value = "ExpectedSalaryUpper", name = "期望薪资上限", dataType = "string", required = true),
            @ApiImplicitParam(value = "ItIsPublic", name = "是否公开", dataType = "integer", required = true),
            @ApiImplicitParam(value = "AgreePlatformHelp", name = "是否需要平台帮助", dataType = "integer", required = true)
    })
    public ResultInfo editPersonData(EditPersonDataQuery query, MultipartFile file) {
        String url = service.updateHead(file);
        service.editPersonData(query, url);
        return success(RlzyConstant.OPS_SUCCESS_CODE, RlzyConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping(value = "editPersonDataRecommend")
    @ResponseBody
    @ApiOperation(value = "编辑资料 推荐人", notes = "编辑资料 推荐人", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "recommenderId", name = "推荐人id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "postIdStr", name = "推荐人的意向岗位", dataType = "string", required = true),
            @ApiImplicitParam(value = "RecommendNoLower", name = "推荐人数量下限", dataType = "integer", required = true),
            @ApiImplicitParam(value = "RecommendNoUpper", name = "推荐人数量上限", dataType = "integer", required = true),
            @ApiImplicitParam(value = "recommendInfo", name = "推荐说明", dataType = "string", required = true),
            @ApiImplicitParam(value = "itIsPublic", name = "是否公开", dataType = "integer", required = true),
            @ApiImplicitParam(value = "file", name = "上传的文件", dataType = "MultipartFile", required = true)
    })
    public ResultInfo editPersonDataRecommend(EditPersonDataQuery query, MultipartFile file) {
        String head = service.updateHead(file);
        query.setUrl(head);
        service.editPersonDataRecommend(query);
        return success(RlzyConstant.OPS_SUCCESS_CODE, RlzyConstant.OPS_SUCCESS_MSG);
    }

    /**
     * 查询投诉记录
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.bean.dto.ComplaintDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:14 2019/7/10
     * @Param [userId, typeId]
     **/
    @RequestMapping(value = "searchComplaintRecord")
    @ResponseBody
    @ApiOperation(value = "查询投诉记录", notes = "查询投诉记录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "typeId", name = "类型id", dataType = "Integer", required = true)
    })
    public Result<ComplaintDto> searchComplaintRecord(Integer userId, Integer typeId) {
        List<ComplaintDto> dtos = service.searchComplaintRecord(userId, typeId);
        Result<ComplaintDto> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(dtos);
        return result;
    }

    /**
     * 投诉记录详情
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.bean.dto.ComplaintDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:57 2019/7/10
     * @Param [complaintId]
     **/
    @RequestMapping(value = "complaint")
    @ResponseBody
    @ApiOperation(value = "投诉记录排行", notes = "查询投诉记录", httpMethod = "POST")
    @ApiImplicitParam(value = "complaintId", name = "投诉记录id", dataType = "Integer", required = true)
    public Result<ComplaintDto> complaint(Integer complaintId) {
        List<ComplaintDto> complaint = service.complaint(complaintId);
        Result<ComplaintDto> result = new Result<>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(complaint);
        return result;
    }

    @RequestMapping(value = "selectReferrerInfo")
    @ResponseBody
    @ApiOperation(value = "招聘端 首页 推荐人信息", notes = "招聘端 首页 推荐人信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "推荐人id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "typeId", name = "推荐人类型id", dataType = "integer", required = true)
    })
    public Result<HrUser> selectReferrerInfo(Integer userId, Integer typeId) {
        var list = service.selectReferrerInfo(userId, typeId);
        var result = new Result<HrUser>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(list);
        return result;

    }

    @RequestMapping(value = "feedback")
    @ResponseBody
    @ApiOperation(notes = "招聘端 求知与反馈", value = "招聘端 求知与反馈", httpMethod = "POST")
    public Result<Feedback> feedback() {
        var list = service.feedback();
        var result = new Result<Feedback>();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMsg(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(list);
        return result;

    }


    @RequestMapping(value = "myFeedback")
    @ResponseBody
    @ApiOperation(value = "招聘端 个人中心 我的反馈", notes = "我的反馈", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "content", name = "投诉内容", dataType = "string", required = true),
            @ApiImplicitParam(value = "userId", name = "投诉人id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "name", name = "投诉人姓名", dataType = "integer", required = true),
            @ApiImplicitParam(value = "phone", name = "投诉人手机号", dataType = "integer", required = true)
    })
    public CommonResult myFeedback(String content, Integer userId, String name, String phone) {
        int feedback = service.myFeedback(content, userId, name, phone);
        return CommonResult.success(feedback, RlzyConstant.OPS_SUCCESS_MSG);

    }


}
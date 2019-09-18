package com.nado.rlzy.controller;

import com.alibaba.fastjson.JSONObject;
import com.nado.rlzy.base.BaseController;
import com.nado.rlzy.bean.dto.ComplaintDto;
import com.nado.rlzy.bean.model.Result;
import com.nado.rlzy.bean.model.ResultJson;
import com.nado.rlzy.bean.query.AddCoQuery;
import com.nado.rlzy.bean.query.ComplaintQuery;
import com.nado.rlzy.bean.query.EditPersonDataQuery;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.platform.exception.AssertException;
import com.nado.rlzy.service.JobSearchHomePageService;
import com.nado.rlzy.service.PersonCenterService;
import com.nado.rlzy.service.RecruitmentHomePageService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName 招聘端个人中心controller
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/1 15:06
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "personCenter")
@Api(description = "个人中心controller")
public class PersonCenterController extends BaseController {
    @Resource
    private PersonCenterService service;

    @Resource
    private RecruitmentHomePageService homePageService;

    @Resource
    private JobSearchHomePageService jobSearchHomePageService;


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
    @ApiOperation(value = "查询我的企业 招聘单位 | 代招单位", notes = "查询我的企业 招聘单位 | 代招单位", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "integer", required = true)
    })
    public ResultJson queryPersonCo(Integer userId) {
        ResultJson result = new ResultJson();
        try {
            List<HrGroup> hrGroups = service.queryPersonCo(userId);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(hrGroups);
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

    @RequestMapping(value = "subAccountCompany")
    @ResponseBody
    @ApiOperation(value = "子账号负责的企业", notes = "子账号负责的企业")
    public ResultJson subAccountCompany(Integer userId) {
        ResultJson result = new ResultJson();
        try {
            List<HrGroup> hrGroups = service.subAccountCompany(userId);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(hrGroups);
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
     * 添加企业 招聘端 代招单位添加企业
     * headers = "content-type=multipart/form-date")
     *
     * @return com.nado.rlzy.bean.model.ResultInfo
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:10 2019/7/1
     * @Param []
     **/
    @RequestMapping(value = "addCo")
    @ResponseBody
    @ApiOperation(value = "添加企业 | 企业认证失败说明", notes = "添加企业 | 企业认证失败说明", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coName", value = "公司名称", dataType = "string", required = true),
            @ApiImplicitParam(name = "coAddress", value = "公司地址", dataType = "string", required = true),
            @ApiImplicitParam(name = "CompanyProfile", value = "公司简介", dataType = "string", required = true),
            @ApiImplicitParam(value = "file", name = "上传图片", dataType = "MultipartFile", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "int", required = true),
            @ApiImplicitParam(value = "type", name = "1 添加企业 | 2 身份认证失败说明", dataType = "int", required = true),
            @ApiImplicitParam(value = "data", name = "返回给前台的企业的id", dataType = "int", required = true),
            @ApiImplicitParam(value = "helpProve", name = "代招证明", required = true)
    })
    public ResultJson addCo(AddCoQuery query) {
        ResultJson resultJson = new ResultJson();

        try {
            Map<String, Object> co = service.addCo(query);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(co);
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
    @ApiOperation(notes = "求职端 个人中心 个人资料", value = "求职端 个人中心 个人资料", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "type", name = "身份", dataType = "Integer", required = true)
    })
    public Result<HrUser> personalInformation(Integer userId, Integer type) {
        Result<HrUser> result = new Result<>();
        try {
            if (type.equals(1)) {
                List<HrUser> ups = service.personalInformation(userId);
                result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                result.setData(ups);
                return result;
            }
            if (type.equals(2)) {
                List<HrUser> signUps = service.personalInformationReferrer(userId);
                result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                result.setData(signUps);
                return result;
            }
        } catch (AssertException e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setCode(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(RlzyConstant.OPS_FAILED_MSG);
            result.setCode(RlzyConstant.OPS_FAILED_CODE);
        }

        return null;
    }

    /**
     * 编辑个人信息
     *
     * @return com.nado.rlzy.bean.model.ResultInfo
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:29 2019/7/9
     * @Param [query]
     **/
    @RequestMapping(value = "editPersonData")
    @ResponseBody
    @ApiOperation(value = "求职端 个人中心 编辑资料", notes = "求职端 个人中心 编辑资料", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "typeId", name = "身份id 1 本人 2 推荐者", dataType = "integer", required = true),
            @ApiImplicitParam(value = "sex", name = "性别 0 女 1 男", dataType = "integer", required = true),
            @ApiImplicitParam(value = "education", name = "学历", dataType = "string", required = true),
            @ApiImplicitParam(value = "graduationTime", name = "毕业时间", dataType = "date", required = true),
            @ApiImplicitParam(value = "profession", name = "专业", dataType = "string", required = true),
            @ApiImplicitParam(value = "registrationPositionId", name = "报名岗位id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "ArrivalTime", name = "到岗时间", dataType = "date", required = true),
            @ApiImplicitParam(value = "ExpectedSalary", name = "期望薪资", dataType = "string", required = true),
            @ApiImplicitParam(value = "ItIsPublic", name = "是否公开 0 公开 1 不公开", dataType = "integer", required = true),
            @ApiImplicitParam(value = "AgreePlatformHelp", name = "是否需要平台帮助 0 是 1 否", dataType = "integer", required = true),
            @ApiImplicitParam(value = "=====下面是推荐人信息=====", name = "下面是推荐人信息"),
            @ApiImplicitParam(value = "recommenderId", name = "推荐人id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "postIdStr", name = "推荐人的意向岗位", dataType = "string", required = true),
            @ApiImplicitParam(value = "RecommendNo", name = "推荐人数量上限", dataType = "integer", required = true),
            @ApiImplicitParam(value = "recommendInfo", name = "推荐说明", dataType = "string", required = true),
            @ApiImplicitParam(value = "itIsPublic", name = "是否公开", dataType = "integer", required = true),
            @ApiImplicitParam(value = "file", name = "上传的文件", dataType = "MultipartFile", required = true)
    })
    public ResultJson editPersonData(EditPersonDataQuery query) {
        ResultJson result = new ResultJson();
        try {
            //本人
            if (query.getTypeId().equals(1)) {

                service.editPersonData(query);
                result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            }
            //推荐人
            if (query.getTypeId().equals(2)) {
                service.editPersonDataRecommend(query);
                result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            }
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
     * 求职端 信用中心 查看投诉详情 || 撤销投诉
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.bean.dto.ComplaintDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:57 2019/7/10
     * @Param [complaintId]
     **/
    @RequestMapping(value = "complaint")
    @ResponseBody
    @ApiOperation(value = "求职端 信用中心 查看投诉详情 & 撤销投诉", notes = "求职端 信用中心 查看投诉详情 & 撤销投诉", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "complaintId", name = "投诉记录id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "type", name = "类型id 1 代招单位下的单位 2 招聘单位", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "typ1", name = "1 查看投诉详情 2 撤销投诉", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "id", name = "投诉id", dataType = "integer", required = true)
    })
    public ResultJson complaint(Integer complaintId, Integer type, Integer typ1, Integer id) {
        ResultJson result = new ResultJson();
        try {
            if (typ1.equals(1)) {
                //投诉详情
                HashMap<String, Object> complaint = service.complaint(complaintId, type);
                result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                result.setData(complaint);
            } else {
                //撤销投诉
                ComplaintQuery query = new ComplaintQuery();
                query.setId(id);
                int i = jobSearchHomePageService.complaintWithdrawn(query);
                result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                result.setData(i);
            }
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

    @RequestMapping(value = "selectReferrerInfo")
    @ResponseBody
    @ApiOperation(value = "招聘端 首页 推荐人信息", notes = "招聘端 首页 推荐人信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "推荐人id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "typeId", name = "推荐人类型id", dataType = "integer", required = true)
    })
    public Result<HrUser> selectReferrerInfo(Integer userId, Integer typeId) {
        Result<HrUser> result = new Result<HrUser>();
        try {
            List<HrUser> list = service.selectReferrerInfo(userId, typeId);
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

    @RequestMapping(value = "feedback")
    @ResponseBody
    @ApiOperation(notes = "招聘端 帮助与反馈", value = "招聘端 帮助与反馈", httpMethod = "POST")
    public Result<Feedback> feedback() {
        Result<Feedback> result = new Result<Feedback>();
        try {
            List<Feedback> list = service.feedback();
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

    @RequestMapping(value = "myFeedback")
    @ResponseBody
    @ApiOperation(value = "招聘端 个人中心 我要反馈", notes = "我的反馈", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "content", name = "投诉内容", dataType = "string", required = true),
            @ApiImplicitParam(value = "userId", name = "投诉人id", dataType = "integer", required = true),
            @ApiImplicitParam(value = "name", name = "投诉人姓名", dataType = "integer", required = true),
            @ApiImplicitParam(value = "phone", name = "投诉人手机号", dataType = "integer", required = true)
    })
    public ResultJson myFeedback(String content, Integer userId, String name, String phone) {
        ResultJson result = new ResultJson();
        try {
            int feedback = service.myFeedback(content, userId, name, phone);
            result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            result.setData(feedback);
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
     * 招聘端 个人中心 我的收藏 报名表信息概览 | 推荐人信息概览
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.bean.frontEnd.JobListtFront>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:12 2019/7/8
     * @Param [userId]
     **/

    @RequestMapping(value = "selectCollectListOverview")
    @ResponseBody
    @ApiOperation(value = "招聘端 个人中心 我的收藏 报名表信息 概览", notes = "招聘端 个人中心 我的收藏 报名表信息 概览", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true),
            @ApiImplicitParam(name = "type", value = "1 推荐人信息 2 报名表信息 ", required = true)
    })
    public ResultJson selectCollectListOverview(Integer userId, Integer type) {
        Map<String, Object> map = new HashMap<>();
        ResultJson result = new ResultJson();
        try {
            if (type.equals(1)) {
                List<HrUser> hrUsers = service.collectReferrer(userId);
                map.put("collectReferrer", hrUsers);
                result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                result.setData(map);
            } else {
                List<HrSignUp> fronts = homePageService.selectCollectListOverview(userId);
                map.put("selectCollectListOverview", fronts);
                result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                result.setData(map);
            }
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

    @RequestMapping(value = "creditCenter")
    @ResponseBody
    @ApiOperation(notes = "招聘端 个人中心 信用中心 投诉待处理 已撤销", value = "招聘端 个人中心 信用中心 投诉待处理 已撤销", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "status", name = "投诉状态 1, 3 待处理 | 已处理  2 已撤销 ", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", required = true)
    })
    public Result<ComplaintDto> creditCenter(Integer[] status, Integer userId) {
        Result<ComplaintDto> result = new Result<ComplaintDto>();

        try {
            List<ComplaintDto> list = jobSearchHomePageService.creditCenter(status, userId);
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

    @RequestMapping(value = "selectComplaint")
    @ResponseBody
    @ApiOperation(value = "招聘端 个人中心 信用中心 查看投诉", notes = "招聘端 个人中心 信用中心 查看投诉", httpMethod = "POST")
    @ApiImplicitParam(name = "coId", value = "简章id", required = true)
    public ResultJson selectComplaint(Integer coId) {
        ResultJson resultJson = new ResultJson();
        try {
            List<HrComplaint> hrComplaints = jobSearchHomePageService.selectComplaint(coId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(hrComplaints);

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

    @RequestMapping(value = "updateHeadImage")
    @ResponseBody
    @ApiOperation(value = "招聘端 个人中心 修改昵称和头像", notes = "招聘端 个人中心 修改昵称和头像")
    public ResultJson updateHeadImage(String userId, String headImage, String userName, Integer type) {
        ResultJson resultJson = new ResultJson();
        try {
            int i = service.updateHeadImage(userId, headImage, userName, type);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(i);
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
     * 查询头像 昵称 身份证 if 子账号 不显示 idCard
     *
     * @return com.nado.rlzy.bean.model.ResultJson
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:43 2019/9/18
     * @Param [userId]
     **/
    @RequestMapping(value = "selectHeadUserNameIdCard")
    @ResponseBody
    @ApiOperation(value = "查询头像 昵称 身份证 if 子账号 不显示 idCard", notes = "查询头像 昵称 身份证 if 子账号 不显示 idCard")
    public ResultJson selectHeadUserNameIdCard(Integer userId) {
        ResultJson resultJson = new ResultJson();
        try {
            HrUser user = service.selectHeadUserNameIdCard(userId);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(user);
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





  /*  @RequestMapping(value = "queryTheAuditFailed")
    @ResponseBody
    @ApiOperation(value = "认证失败说明", notes = "认证失败说明", httpMethod = "POST")
    @ApiImplicitParam(name = "用户id", value = "userId", required = true)
    public ResultJson queryTheAuditFailed(Integer userId) {
        Map<String, List<PersonCoDto>> map = service.queryTheAuditFailed(userId);
        ResultJson resultJson = new ResultJson();
        resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
        resultJson.setData(map);
        return resultJson;
    }
*/
}
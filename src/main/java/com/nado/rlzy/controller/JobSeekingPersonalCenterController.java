package com.nado.rlzy.controller;

import com.nado.rlzy.bean.model.Result;
import com.nado.rlzy.bean.model.ResultJson;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.db.pojo.HrUser;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.platform.exception.AssertException;
import com.nado.rlzy.service.JobSeekingPersonalCenterService;
import com.nado.rlzy.service.PersonCenterService;
import com.nado.rlzy.service.RecruitmentHomePageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName 求职端 个人中心 Controller
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/22 15:04
 * @Version 1.0
 */
@Controller
@Api(description = "求职端 个人中心 Controller")
@RequestMapping(value = "personalCenter")
public class JobSeekingPersonalCenterController {
    @Resource
    private JobSeekingPersonalCenterService service;

    @Resource
    private PersonCenterService personCenterService;

    @Resource
    private RecruitmentHomePageService homePageService;


    /**
     * 简章收藏
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:34 2019/7/5
     * @Param []
     **/
    @RequestMapping(value = "recruitmentBrochureCollection")
    @ResponseBody
    @ApiOperation(notes = "简章收藏概览", value = "简章收藏概览", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true)
    })
    public ResultJson recruitmentBrochureCollection(Integer userId) {
        ResultJson resultJson = new ResultJson();
        HrUser hrUser = homePageService.checkUserIdentity(userId);
        Integer type = Optional.ofNullable(hrUser).orElseGet(HrUser::new).getType();
        try {

            List<HrBriefchapter> list = service.recruitmentBrochureCollection(userId, type);
            List<HrBriefchapter> vals = service.recruitmentBrochureCollectionRecruitment(userId, type);
            HashMap<Object, Object> map = new HashMap<>();
            map.put("recruitmentBrochureCollection", list);
            map.put("recruitmentBrochureCollectionRecruitment", vals);
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

    @RequestMapping(value = "deleteCOllect")
    @ResponseBody
    @ApiOperation(value = "", notes = "求职端删除简章收藏")
    public ResultJson deleteCOllect() {
        ResultJson resultJson = new ResultJson();

        return resultJson;

    }

    /**
     * 求职端个人中心 查询我的报名表详情
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:32 2019/7/8
     * @Param [signId, userId]
     **/
    @RequestMapping(value = "selectSignUpTable")
    @ResponseBody
    @ApiOperation(notes = "求职端个人中心 查询我的报名表详情", value = "求职端个人中心 查询我的报名表详情", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "signId", name = "求职id", dataType = "Integer", required = true)
    })
    public ResultJson selectSignUpTable(Integer signId) {
        ResultJson result = new ResultJson();
        try {
            HrSignUp hrSignUp = service.selectSignUpTable(signId);
            Map<String, Object> map = new HashMap<>();
            map.put("hrsign", hrSignUp);
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
     * 求职端个人中心 查询我的报名表概览
     *
     * @return com.nado.rlzy.bean.model.Result<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:49 2019/7/8
     * @Param [userId]
     **/
    @RequestMapping(value = "selectSignUp")
    @ResponseBody
    @ApiOperation(notes = "求职端个人中心 查询我的报名表概览", value = "求职端个人中心 查询我的报名表概览", httpMethod = "POST")
    @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true)
    public Result<HrSignUp> selectSignUp(Integer userId) {
        Result<HrSignUp> result = new Result<>();
        try {
            List<HrSignUp> list = service.selectSignUp(userId);
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

    @RequestMapping(value = "queryMyselfVillation")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "用户id", required = true),
            @ApiImplicitParam(name = "type", value = "1 查询本人违规 2 推荐人违规", required = true),
            @ApiImplicitParam(name = "typ", value = "1投诉记录 2 违规记录", required = true)
    })
    @ApiOperation(notes = "求职端个人中心 信用中心 查询本人的违规 & 推荐人推荐的求职者违规",
            value = "求职端个人中心 信用中心 查询本人的违规 & 推荐人推荐的求职者违规", httpMethod = "POST")
    public ResultJson queryMyselfVillation(Integer userId, Integer type, Integer typ) {

        ResultJson result = new ResultJson();
        try {
            if (typ.equals(1)) {
                //投诉记录
                HashMap<Object, Object> map = personCenterService.searchComplaintRecord(userId);
                result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                result.setData(map);
            } else {
                //违规记录
                if (type.equals(1)) {
                    //本人违规
                    List<HrUser> hrUsers = service.queryMyselfVillation(userId);
                    result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                    result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                    result.setData(hrUsers);
                } else {
                    //推荐人违规
                    List<HrUser> users = service.queryReferrerVillation(userId);
                    result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                    result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                    result.setData(users);
                }
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

   /* @RequestMapping(value = "searchSignUpUserName")
    @ResponseBody
    @ApiOperation(notes = "查询投诉人 投诉人是报名该简章的求职者 typeid = 1 本人 2 推荐人", value = "查询投诉人 投诉人是报名该简章的求职者", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "briefChapterId", name = "简章id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "typeId", name = "用户身份id", dataType = "Integer", required = true)
    })
    public ResultJson searchSignUpUserName(Integer briefChapterId, Integer userId, Integer typeId) {
        Map<Object, Object> map = service.searchSignUpUserName(briefChapterId, userId, typeId);
        ResultJson result = new ResultJson();
        result.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        result.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
        result.setData(map);
        return result;

    }*/


}
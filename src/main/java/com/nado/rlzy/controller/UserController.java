package com.nado.rlzy.controller;

import com.nado.rlzy.base.BaseController;
import com.nado.rlzy.bean.model.ResultJson;
import com.nado.rlzy.bean.query.RecruitmentSideRegisterHobHuntingQuery;
import com.nado.rlzy.bean.query.RecruitmentSideRegisterQuery;
import com.nado.rlzy.db.pojo.HrUser;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.platform.exception.AssertException;
import com.nado.rlzy.service.MessageService;
import com.nado.rlzy.service.PersonCenterService;
import com.nado.rlzy.service.TokenService;
import com.nado.rlzy.service.UserService;
import com.nado.rlzy.utils.AssertUtil;
import com.nado.rlzy.utils.MD5;
import com.nado.rlzy.utils.OssUtilOne;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/**
 * @ClassName 用户相关 controller
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-06-29 17:47
 * @Version 1.0
 */
@Api(description = "登录注册等用户相关操作")
@RequestMapping(value = "user")
@Controller
public class UserController extends BaseController {

    @Resource
    private UserService service;

    @Resource
    private MessageService messageService;

    @Resource
    private TokenService tokenService;

    @Resource
    private PersonCenterService centerService;


    @RequestMapping(value = "sendMessage")
    @ResponseBody
    @ApiOperation(value = "发送短信", notes = "发送短信", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "phone", name = "手机号码", dataType = "string", required = true),
            @ApiImplicitParam(value = "type", name = "身份类型", dataType = "integer", required = true)
    })
    public ResultJson sendMessage(String phone, Integer type) {
        ResultJson resultJson = new ResultJson();
        try {
            messageService.sendMessage(phone, type);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
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

    @RequestMapping(value = "changePassword")
    @ResponseBody
    @ApiOperation(value = "修改密码", notes = "修改密码", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "phone", name = "手机号码", dataType = "String", required = true),
            @ApiImplicitParam(value = "code", name = "验证码", dataType = "String", required = true),
            @ApiImplicitParam(value = "passWord", name = "密码", dataType = "String", required = true),
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true),
    })
    public ResultJson changePassword(String phone, String code, String passWord, Integer userId) {

        ResultJson resultJson = new ResultJson();
        try {

            int i = service.changePasswoed(phone, code, passWord, userId);
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


    @ResponseBody
    @RequestMapping(value = "switchIdentity")
    @ApiOperation(value = "求职端用户切换身份", notes = "求职端用户切换身份", httpMethod = "POST")

    public ResultJson switchIdentity(RecruitmentSideRegisterHobHuntingQuery query) {
        ResultJson resultJson = new ResultJson();
        try {
            int userId = service.switchIdentity(query);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(userId);
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

    @RequestMapping(value = "login")
    @ResponseBody
    @ApiOperation(value = "登录", notes = "登录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "phone", name = "手机号", dataType = "string", required = true),
            @ApiImplicitParam(value = "password", name = "密码", dataType = "string", required = true)
    })
    public ResultJson login(String phone, String password) {
        ResultJson resultJson = new ResultJson();
        try {
            HrUser phone1 = service.findByPhone(phone);
            HrUser hrUser = service.queryUser(phone, password);
            AssertUtil.isTrue(null != hrUser, "用户已禁用");

            if (null == phone1) {
                resultJson.setCode(RlzyConstant.OPS_FAILED_CODE);
                resultJson.setMessage("登录失败,用户不存在");
                return resultJson;
            } else {

                if (!(phone1.getPassword().equals(MD5.getMD5(password + RlzyConstant.PASSWORD_SALT)))) {
                    resultJson.setCode(RlzyConstant.OPS_FAILED_CODE);
                    resultJson.setMessage("登录失败,密码错误");
                    return resultJson;
                } else {
                    //可以登录
                    HrUser login = service.login(phone, password);
                    Integer userId = login.getUserId();
                    //查询拉黑的用户
                    int i = service.selectEnterPriseBlacakList(userId);
                    int i1 = service.selectPlatformlack(userId);
                    int i2 = service.selectplatformBlackRecruitmentEnd(userId);
                    if (i1 != 0) {
                        AssertUtil.isTrue(i1 != 0, "您已被平台拉黑, 请联系平台处理");
                        resultJson.setCode(RlzyConstant.OPS_FAILED_CODE);
                        resultJson.setMessage(RlzyConstant.OPS_FAILED_MSG);
                    }
                    if (i != 0) {
                        AssertUtil.isTrue(i != 0, "您已被企业拉黑, 请联系企业处理");
                        resultJson.setCode(RlzyConstant.OPS_FAILED_CODE);
                        resultJson.setMessage(RlzyConstant.OPS_FAILED_MSG);
                    }
                    if (i2 != 0) {
                        AssertUtil.isTrue(i2 != 0, "您的企业已被拉黑, 无法登陆, 请联系平台处理");
                        resultJson.setCode(RlzyConstant.OPS_FAILED_CODE);
                        resultJson.setMessage(RlzyConstant.OPS_FAILED_MSG);
                    }

                    resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
                    resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
                    resultJson.setData(login);
                    return resultJson;
                }
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

    @RequestMapping(value = "registerUser")
    @ResponseBody
    @ApiOperation(value = "招聘端完善信息 在注册或者登陆时", notes = "招聘端完善信息 在注册或者登陆时", httpMethod = "POST")
    @ApiImplicitParam(value = "query", name = "入参, 具体参数见上面", dataType = "RecruitmentSideRegisterQuery", required = true)
    public ResultJson registerUser(RecruitmentSideRegisterQuery query) {
        ResultJson resultJson = new ResultJson();
        try {

            int registerUser = service.registerUser(query);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(registerUser);

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

    @RequestMapping(value = "registerJobHunt")
    @ResponseBody
    @ApiOperation(value = "求职端完善信息 在注册或者登陆时", notes = "求职端完善信息 在注册或者登陆时", httpMethod = "POST")
    @ApiImplicitParam(value = "query", name = "入参, 具体参数见上面", dataType = "RecruitmentSideRegisterHobHuntingQuery", required = true)
    public ResultJson registerJobHunting(RecruitmentSideRegisterHobHuntingQuery query) {
        ResultJson resultJson = new ResultJson();
        try {

            int registerJobHunting = service.registerJobHunting(query);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(registerJobHunting);

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

    @RequestMapping(value = "register")
    @ResponseBody
    @ApiOperation(value = "求职端|招聘端注册")
    public ResultJson register(RecruitmentSideRegisterHobHuntingQuery query) {

        ResultJson resultJson = new ResultJson();

        try {
            int register = service.register(query);
            resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
            resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
            resultJson.setData(register);

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

    @RequestMapping(value = "test3")
    @ResponseBody
    public ResultJson test3(String image) throws Exception {


        String s = OssUtilOne.picUpload(image, "0");
        ResultJson resultJson = new ResultJson();
        resultJson.setCode(RlzyConstant.OPS_SUCCESS_CODE);
        resultJson.setMessage(RlzyConstant.OPS_SUCCESS_MSG);
        resultJson.setData(s);
        return resultJson;
    }


}

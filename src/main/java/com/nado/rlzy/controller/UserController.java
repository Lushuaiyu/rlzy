package com.nado.rlzy.controller;

import com.alibaba.fastjson.JSONObject;
import com.nado.rlzy.base.BaseController;
import com.nado.rlzy.bean.model.CommonResult;
import com.nado.rlzy.bean.model.ResultInfo;
import com.nado.rlzy.bean.query.RecruitmentSideRegisterHobHuntingQuery;
import com.nado.rlzy.bean.query.RecruitmentSideRegisterQuery;
import com.nado.rlzy.db.pojo.HrUser;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.MessageService;
import com.nado.rlzy.service.PersonCenterService;
import com.nado.rlzy.service.TokenService;
import com.nado.rlzy.service.UserService;
import com.nado.rlzy.utils.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService service;

    @Resource
    private MessageService messageService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PersonCenterService centerService;


    @RequestMapping(value = "sendMessage")
    @ResponseBody
    @ApiOperation(value = "发送短信", notes = "发送短信", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "phone", name = "手机号码", dataType = "string", required = true),
            @ApiImplicitParam(value = "type", name = "身份类型", dataType = "integer", required = true)
    })
    public ResultInfo sendMessage(String phone, Integer type) {
        messageService.sendMessage(phone, type);
        return success(RlzyConstant.OPS_SUCCESS_CODE, RlzyConstant.OPS_SUCCESS_MSG);
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
    public ResultInfo changePassword(String phone, String code, String passWord, Integer userId) {
        service.changePasswoed(phone, code, passWord, userId);
        return success(RlzyConstant.OPS_SUCCESS_CODE, RlzyConstant.OPS_SUCCESS_MSG);

    }

    @ResponseBody
    @RequestMapping(value = "switchIdentity")
    @ApiOperation(value = "用户切换身份", notes = "用户切换身份", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", dataType = "Integer", required = true),
            @ApiImplicitParam(value = "type", name = "身份类型", dataType = "Integer", required = true)
    })
    public ResultInfo switchIdentity(Integer userId, Integer type) {
        service.switchIdentity(userId, type);

        return success(RlzyConstant.OPS_SUCCESS_CODE, RlzyConstant.OPS_SUCCESS_MSG);


    }

    @RequestMapping(value = "login")
    @ResponseBody
    @ApiOperation(value = "登录", notes = "登录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "phone", name = "手机号", dataType = "string", required = true),
            @ApiImplicitParam(value = "password", name = "密码", dataType = "string", required = true)
    })
    public Object login(String phone, String password) {
        JSONObject jsonObject = new JSONObject();
        HrUser phone1 = service.findByPhone(phone);
        if (null == phone) {
            jsonObject.put("message", "登录失败,用户不存在");
            return jsonObject;
        } else {
            //密码先不加 salt
            if (!(phone1.getPassword().equals(MD5.getMD5(password + RlzyConstant.PASSWORD_SALT)))) {
                jsonObject.put("message", "登录失败,密码错误");
                return jsonObject;
            } else {
                String token = tokenService.getToken(phone1);
                jsonObject.put("token", token);
                jsonObject.put("user", phone);
                return jsonObject;
            }
        }
    }

    @RequestMapping(value = "registerUser")
    @ResponseBody
    @ApiOperation(value = "招聘端注册", notes = "招聘端注册", httpMethod = "POST")
    @ApiImplicitParam(value = "query", name = "入参, 具体参数见上面", dataType = "RecruitmentSideRegisterQuery", required = true)
    public CommonResult registerUser(RecruitmentSideRegisterQuery query) {
        //图片上传
        String head = centerService.updateHead(query.getFile());
        query.setImageHead(head);
        int registerUser = service.registerUser(query);
        return CommonResult.success(registerUser, RlzyConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping(value = "registerJobHunt")
    @ResponseBody
    @ApiOperation(value = "求职端注册 完善信息", notes = "求职端注册 完善信息", httpMethod = "POST")
    @ApiImplicitParam(value = "query", name = "入参, 具体参数见上面", dataType = "RecruitmentSideRegisterHobHuntingQuery", required = true)
    public CommonResult registerJobHunting(RecruitmentSideRegisterHobHuntingQuery query) {
        String head = centerService.updateHead(query.getFile());
        // photo upload
        query.setImageHead(head);
        int registerJobHunting = service.registerJobHunting(query);
        return CommonResult.success(registerJobHunting, RlzyConstant.OPS_SUCCESS_MSG);
    }






}

package com.nado.rlzy.service.impl;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.MessageService;
import com.nado.rlzy.utils.AssertUtil;
import com.nado.rlzy.utils.PhoneUtil;
import com.nado.rlzy.utils.RandomCodesUtils;
import com.nado.rlzy.utils.telMessage.Title;
import com.nado.rlzy.utils.telMessage.TitleDateUtil;
import com.nado.rlzy.utils.ValidateMsgUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @ClassName 发送短信 service 实现类
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/16 10:47
 * @Version 1.0
 */
@Service
public class SendMessageServiceImpl implements MessageService {
    @Value("${yuntongxun.accountSid}")
    private String accountSid;

    @Value("${yuntongxun.accountToken}")
    private String accountToken;

    @Value("${yuntongxun.appId}")
    private String appId;

    @Value("${yuntongxun.templateId}")
    private String templateId;


    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void sendMessage(String phone, Integer type) {
        /*
        参数校验
        phone: 手机号码
        type: 短信类型
            注册 修改密码 体现
         */
        checkParams(phone, type);

        //设置验证码

        String code = RandomCodesUtils.createRandom(true, 6);
        //注册
        if (RlzyConstant.SMS_REGISTER_TYPE.equals(type)) {
            //注册

            sendMessage(accountSid, accountToken, appId, templateId, phone, code, type);
            //redisTemplate.opsForValue().set("phone::" + phone + "templateCode::" + templateId, code, 600, TimeUnit.SECONDS);
        } else if (RlzyConstant.SMS_WITHDRAW_TYPE.equals(type)) {
            //提现

            sendMessage(accountSid, accountToken, appId, templateId, phone, code, type);
            //redisTemplate.opsForValue().set("phone::" + phone + "templateCode::" + templateId, code, 600, TimeUnit.SECONDS);
        } else if (RlzyConstant.SMS_CHANGE_PASSWORD.equals(type)) {
            //修改密码

            sendMessage(accountSid, accountToken, appId, templateId, phone, code, type);
            // redisTemplate.opsForValue().set("phone::" + phone + "templateCode::" + templateId, code, 600, TimeUnit.SECONDS);
        } else if (RlzyConstant.SMS_FORGET_PASSWORD.equals(type)) {
            //忘记密码

            sendMessage(accountSid, accountToken, appId, templateId, phone, code, type);
            // redisTemplate.opsForValue().set("phone::" + phone + "templateCode::" + templateId, code, 600, TimeUnit.SECONDS);
        }

    }

    private void checkParams(String phone, Integer type) {
        AssertUtil.isTrue(StringUtils.isBlank(phone), RlzyConstant.PHONE_NULL);
        PhoneUtil.phone(phone);
        AssertUtil.isTrue(null == type, RlzyConstant.SMS_MESSAGE_NULL);
        boolean operatingType = RlzyConstant.SMS_WITHDRAW_TYPE.equals(type) ||
                RlzyConstant.SMS_REGISTER_TYPE.equals(type) ||
                RlzyConstant.SMS_CHANGE_PASSWORD.equals(type) || RlzyConstant.SMS_FORGET_PASSWORD.equals(type);
        AssertUtil.isTrue(!(operatingType), RlzyConstant.SMS_MESSAGE_ILLEGALITY);
    }

    private static int sendMessage(String accountSid, String accountToken, String appId, String templateId, String phone, String code, Integer type) {
        HashMap<String, Object> result = null;
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        restAPI.init("app.cloopen.com", "8883");
        restAPI.setAccount(accountSid, accountToken);
        restAPI.setAppId(appId);
        result = restAPI.sendTemplateSMS(phone, templateId, new String[]{code, "10"});
        System.out.println("SDKTestGetSubAccounts result=" + result);
        if ("000000".equals(result.get("statusCode"))) {
            //正常返回输出data包体信息（map）
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            @SuppressWarnings("unchecked")
            HashMap<String, Object> templateSMS = (HashMap<String, Object>) data.get("templateSMS");
            if (type.equals(RlzyConstant.SMS_REGISTER_TYPE)) {

                Title t = TitleDateUtil.getTitle(phone, (String) templateSMS.get("dateCreated"), String.valueOf(type), code, "10");
                ValidateMsgUtil.addTitle(t);
                System.out.println(t);
            } else if (type.equals(RlzyConstant.SMS_CHANGE_PASSWORD)){
                Title t = TitleDateUtil.getTitle(phone, (String) templateSMS.get("dateCreated"), String.valueOf(type), code, "10");
                ValidateMsgUtil.addTitle(t);
                System.out.println(t);
            } else if (type.equals(RlzyConstant.SMS_FORGET_PASSWORD)){
                Title t = TitleDateUtil.getTitle(phone, (String) templateSMS.get("dateCreated"), String.valueOf(type), code, "10");
                ValidateMsgUtil.addTitle(t);
                System.out.println(t);
            } else{
                Title t = TitleDateUtil.getTitle(phone, (String) templateSMS.get("dateCreated"), String.valueOf(type), code, "10");
                ValidateMsgUtil.addTitle(t);
                System.out.println(t);


            }
            return 0;
        } else {
            //异常返回输出错误码和错误信息
            //System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
        }
        return 2;
    }

    public static void main(String[] args) {

    }
}
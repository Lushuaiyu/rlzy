package com.nado.rlzy;

import cn.hutool.core.lang.Assert;
import com.cloopen.rest.sdk.CCPRestSmsSDK;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.pojo.Collect;
import com.nado.rlzy.db.pojo.HrRebaterecord;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


import java.math.BigDecimal;
import java.util.*;




public class RlzyApplicationTests extends BaseTest {
    @Autowired
    private HrBriefchapterMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void test2() {
        String str = "五险一金,包吃包住";
        String[] split = str.split(",");
        System.out.println(Arrays.toString(split));
    }





    @Test
    public void test4() {
        String phone = "";

        StringUtil.isBank(phone, "手机号码不能为空");

    }

    @Test
    public void test5(){
        HashMap<String, Object> result = null;

        //初始化SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

        //******************************注释*********************************************
        //*初始化服务器地址和端口                                                       *
        //*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
        //*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
        //*******************************************************************************
        restAPI.init("app.cloopen.com", "8883");

        //******************************注释*********************************************
        //*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
        //*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
        //*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
        //*******************************************************************************
        restAPI.setAccount("8a216da86715511501673018a5e60f83", "d1bb0f75c7424f36a855a04fa46f32db");


        //******************************注释*********************************************
        //*初始化应用ID                                                                 *
        //*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
        //*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
        //*******************************************************************************
        restAPI.setAppId("8a216da86715511501673018a62f0f89");


        //******************************注释****************************************************************
        //*调用发送模板短信的接口发送短信                                                                  *
        //*参数顺序说明：                                                                                  *
        //*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
        //*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
        //*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
        //*第三个参数是要替换的内容数组。  																														       *
        //**************************************************************************************************

        //**************************************举例说明***********************************************************************
        //*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
        //*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
        //*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
        //*********************************************************************************************************************
        result = restAPI.sendTemplateSMS("18620925694","445678" ,new String[]{"8286","5"});

        System.out.println("SDKTestGetSubAccounts result=" + result);
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
    }


    @Test
    public void test(){
        String str = "陆帅宇";
        Integer sexN = 0;
        Integer sexF = 1;
        Integer no = 0;
        String str1 = "沈尚琳";

        if (no.equals(0)) {
            if (sexN.equals(0)){
                String s = str.substring(0, 1) + "先生";
                System.out.println(s.toString());
            }
            if (sexF.equals(1)){
                String s = str1.substring(0, 1) + "女士";
                System.out.println(s.toString());
            }
        }



    }


    @Test
    public void tezt6(){
        HrRebaterecord hrRebaterecord = new HrRebaterecord();
        hrRebaterecord.setRebateOne(BigDecimal.valueOf(4));
        BigDecimal rebateOne = hrRebaterecord.getRebateOne();
        System.out.println(rebateOne);
    }

    @Test
    public void test7(){

        Collect collect = new Collect();
        collect.setCreateTime(new Date());
        System.out.println(collect);

    }

    @Test
    public void test8(){
        String a = null;
        Assert.isFalse(StringUtils.isBlank(a), RlzyConstant.NULL_PARAM);

    }



    @Test
    public void contextLoad(){
        //redisTemplate.opsForValue().set("test", "hello word");
        String test = (String) redisTemplate.opsForValue().get("phone::18620925694templateCode::457666");
        System.out.println(test);
        System.out.println(redisTemplate.hasKey("test"));

    }






}

package com.nado.rlzy;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Assert;
import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.db.mapper.*;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.db.pojo.test.Account;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.MessageService;
import com.nado.rlzy.service.RedisService;
import com.nado.rlzy.utils.Base64Utils;
import com.nado.rlzy.utils.CollectorsUtil;
import com.nado.rlzy.utils.StringUtil;
import com.nado.rlzy.utils.ValidationUtil;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/12 10:50
 * @Version 1.0
 */
public class Test extends BaseTest {

    @Resource
    private HrBriefchapterMapper hrBriefchapterMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedisService service;

    @Resource
    private HrSignUpMapper signUpMapper;

    @Resource
    private HrUserMapper userMapper;

    @Resource
    private HrComplaintMapper complaintMapper;

    @Resource
    private MessageService messageService;

    @Resource
    private HrGroupMapper groupMapper;

    @Resource
    private RedisTemplate<String, Object> template;

    @org.junit.Test
    public void test1() {
        System.out.println("aaaaa");
    }


    @org.junit.Test
    public void test2() {
        BriefcharpterQuery query = new BriefcharpterQuery();
        List<HrBriefchapter> list = hrBriefchapterMapper.queryBriefcharpterDtoByParams(query);
        list.stream().map(
                c -> {
                    //rebat是对象, 所以对rebat进行stream处理
                    Map<Integer, BigDecimal> collect = c.getRebat().stream()
                            //分组 再求和 groupingBy是分组操作 summingBigDecimal是对 stream 的自定义扩展
                            .collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    System.out.println(collect);

                    //foreach 遍历集合
                    collect.forEach((k, v) -> {
                        c.getRebat().stream().map(val -> {
                            //把遍历完的值set 到 对象里面
                            val.setRebateOne(v);
                            return v;
                        }).collect(Collectors.toList());
                    });
                    return c;
                }).collect(Collectors.toList());

    }

    @org.junit.Test
    public void test3() {
        // redisTemplate.opsForValue().set("aaa", "bbb");

        System.out.println(service.get("aaa"));
        System.out.println(service.get("phone::17326122358templateCode445678"));

    }

    @org.junit.Test
    public void test5() throws IOException {
        Account account = new Account();
        //account.setAlias("kalakala");
        account.setUserName("wokalakala");
        account.setPassWord("safsdfdf223A");
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(account);
        if (validResult.hasErrors()) {
            String errors = validResult.getErrors();
            System.out.println(errors);
        }

    }

    @org.junit.Test
    public void test6() {

        Integer a = 1;
        Integer b = 0;
        Integer c = 4;

        HrSignUp hrSignUp = signUpMapper.SearchdirectAdmission(a, b, c);
        BigDecimal value = hrSignUp.getValue();
        Date interviewTime = hrSignUp.getInterviewTime();
        System.out.println(interviewTime);

        // System.out.println(value.toString());


    }

    @org.junit.Test
    public void test7() {
        String ss = "2019-03-02";
        String sss = "2019-03-02";
        String s = "2019-03-06";
        String a = "2019-04-01";
        int i = sss.compareTo(a);
        int b = ss.compareTo(sss);
        int c = a.compareTo(s);
        System.out.println(i);
        System.out.println(b);
        System.out.println(c);
    }

    public void test8(int[] sortArray) {
        int[] arr = Arrays.copyOf(sortArray, sortArray.length);
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;

            }
            if (j != i) {
                arr[j] = temp;

            }
            System.out.println();

        }

    }

    @org.junit.Test
    public void test9() {
        Integer[] arr = {1, 2, 3, 4};
        List<Integer> collect = Stream.of(arr).collect(Collectors.toList());
        System.out.println(collect);
    }


    @org.junit.Test
    public void test10() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar cale = Calendar.getInstance();// 取当前日期。

        Calendar calendar = new GregorianCalendar(cale.get(Calendar.YEAR),
                cale.get(Calendar.MONTH), cale.get(Calendar.DAY_OF_MONTH), 20, 0, 0);
        Date date = calendar.getTime();
        String s = format.format(new Date());
        String s1 = format.format(date);
        int i = s.compareTo(s1);
        System.out.println(i);

    }




    @org.junit.Test
    public void test12() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // creates calendar
        Calendar cal = Calendar.getInstance();
        // sets calendar time/date
        cal.setTime(new Date());
        // adds one hour
        cal.add(Calendar.HOUR_OF_DAY, 72);
        String s = format.format(cal.getTime());
        System.out.println(s);


    }

    @org.junit.Test
    public void test13() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = "2010-10-12 12:23:56";
        Date date = StringUtil.StrToDate(s);

        Date date1 = new Date();
        String format1 = format.format(date1);
        Date date2 = StringUtil.StrToDate(format1);
        long l = date1.getTime() - date2.getTime();
        System.out.println(l);

    }

    @org.junit.Test
    public void test14() {
        List<HrSignUp> hrSignUps = signUpMapper.recruitmentInterviewOver();
        hrSignUps.stream().map(t -> {
            System.out.println(t.getRegisterTime());
            return t;
        }).collect(Collectors.toList());

    }

    @org.junit.Test
    public void test15() {
        List<HrUser> users = userMapper.selectReferrerInfo(2, 2);
        System.out.println(users.stream().collect(Collectors.toList()));


    }

    @org.junit.Test
    public void test16() {
        var id = complaintMapper.queryParams();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        id.stream().map(u -> {
            // 现在系统时间
            var timeNow = LocalDateTime.now();
            //格式化当前时间
            var stringTimeNow = format.format(timeNow);
            //得到投诉创建时间
            var createTime = u.getCreateTime();
            // 投诉 7天后
            var sevenDaysLater = createTime.plusDays(7);

            //格式化 投诉7天后的时间
            var format2 = format.format(sevenDaysLater);
            //如果 系统时间 > 投诉7天后的时间 平台后台处理
            if (stringTimeNow.compareTo(format2) > 0) {
                var complaintId = u.getId();
                complaintMapper.updateStatusById(complaintId);
            }
            return u;


        }).collect(Collectors.toList());


    }



    @org.junit.Test
    public void test18() {

        /*HrUser hrUser = userMapper.finrdUserById("3");
        System.out.println(hrUser);*/

        //messageService.sendMwssage("18620925694", 3);

        String a = "";

        if (StringUtils.isBlank(a)) {
            System.out.println();
        }
    }

    @org.junit.Test
    public void test19() {
        HrGroup group = new HrGroup();
        group.setId(1);
        HrGroup hrGroup = groupMapper.selectOne(group);
        System.out.println(hrGroup);

        HrUser hrUser = new HrUser();
        hrUser.setMobile("12546852156");
        HrUser one = userMapper.selectOne(hrUser);

        if (null == one) {
            hrUser.setStatus(1);
            hrUser.setUserName("dfdfdf");
            userMapper.insert(hrUser);
        }


    }

    @org.junit.Test
    public void test20() {

        String s = Base64Utils.imageToBase64ByOnline("https://lushuaiyu.oss-cn-shanghai.aliyuncs.com/blog/2018-12-31-145741.jpg");
        //System.out.println(s);

        String decode = Base64.encodeUrlSafe(s);
        System.out.println(decode);
    }

    @org.junit.Test
    public void test21(){
        //PhoneUtil.phone("18620925694");

        String phone = "123654";
        String key = "phone::" + phone + "templateCode";
        String code = "";
        Assert.isFalse(!(redisTemplate.hasKey(key)), RlzyConstant.SMS_CODE_STALEDATED);
        Assert.isFalse(!(redisTemplate.opsForValue().get(key).toString().equals(code)), RlzyConstant.SMS_MESSAGE_FAILED);

    }


    @org.junit.Test
    public void aa(){
        redisTemplate.opsForValue().set("aa", "bb");

    }


}
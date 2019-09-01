package com.nado.rlzy;

import cn.hutool.core.codec.Base64;
import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.db.mapper.*;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.utils.Base64Utils;
import com.nado.rlzy.utils.StringUtil;
import lombok.var;
import org.junit.Test;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName Junit
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/30 10:46
 * @Version 1.0
 */
public class Test2 extends BaseTest {

    @Resource
    private HrSignUpMapper mapper;

    @Resource
    private HrAcctMapper acctMapper;

    @Resource
    private HrUserMapper userMapper;

    @Resource
    private AttentionMapper attentionMapper;

    @Resource
    private HrBriefchapterMapper briefchapterMapper;

    @Resource
    private HrDictionaryItemMapper dictionaryItemMapper;

    @Resource
    private HrGroupMapper groupMapper;

    @Resource
    private CollectMapper collectMapper;

    @Resource
    private HrComplaintMapper complaintMapper;

    @Resource
    private HrSignupDeliveryrecordMapper deliveryrecordMapper;

    @Resource
    private MySignUpTableMapper tableMapper;

    @Test
    public void test1() {
        List<HrSignUp> ups = mapper.signUpId();

        List<HrSignUp> u = mapper.signUpIdByReport();
        //System.out.println(ups.stream().collect(Collectors.toList()));

        System.out.println(u.stream().collect(Collectors.toList()));
    }

    @Test
    public void test2() {
        /*List<HrSignUp> ups = mapper.recruitmentInterviewOver();
        System.out.println(ups.stream().collect(Collectors.toList()));*/

        System.out.println(mapper.updateJobStatus(2));
    }

    @Test
    public void test3() {
        HrAcct hrAcct = new HrAcct();
        hrAcct.setSignUpId(1);
        System.out.println(acctMapper.insertSelective(hrAcct));
    }

    @Test
    public void test4() {
        /*HrUser user = new HrUser();
        System.out.println(userMapper.selectAll());*/

        Attention attention = new Attention();
        //query.setStatus(1);
        attention.setUseredid(1);
        //query.setUseredId(4);
        System.out.println(attentionMapper.updateAttention(attention));

    }

    @Test
    public void test5() {
        System.out.println(userMapper.personalInformationReferrer(2));
    }

    @Test
    public void test6() {
        // 创建Example
        Example example = new Example(HrDictionaryItem.class);
        // 创建Criteria
        Example.Criteria criteria = example.createCriteria();
        // 添加条件
        criteria.andLike("pid", "1");
        List<HrDictionaryItem> items = dictionaryItemMapper.selectByExample(example);
        List<HrDictionaryItem> collect = items.stream().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test7() {
        //System.out.println(groupMapper.selectGroupName(6, 8));
        BriefcharpterQuery query = new BriefcharpterQuery();
        query.setCertifier("苏州");
        System.out.println(briefchapterMapper.queryBriefcharpterByParams(query));
    }

    @Test
    public void test8() {
      /*  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.of(1986, Month.APRIL, 8, 12, 30);
        String formattedDateTime = dateTime.format(formatter);
        System.out.println(formattedDateTime);*/

       /* String str = "1986-04-08 12:30:45";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        String format = dateTime.format(formatter);
        System.out.println(format);*/

        var str = "1996-04-02 12:03:23";
        var st = "2000-02-03";
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var dateTime = LocalDateTime.parse(str, formatter);
        var format = dateTime.format(formatter);

        System.out.println(format);
    }

    @Test
    public void test9() {
        BriefcharpterQuery query = new BriefcharpterQuery();
        System.out.println(briefchapterMapper.queryBriefcharpterByLongLiveRecruitment(query));

    }



    @Test
    public void test11() {
        System.out.println(complaintMapper.complaintRecruitment(5));
    }

    @Test
    public void test12() {
        System.out.println(dictionaryItemMapper.dictionary(3));


    }

    @Test
    public void test13() {
        String s = Base64Utils.imageToBase64ByOnline("https://lushuaiyu.oss-cn-shanghai.aliyuncs.com/blog/2018-12-31-145741.jpg");
        //System.out.println(s);

        String decode = Base64.encodeUrlSafe(s);
        System.out.println(decode);
    }

    @Test
    public void test14() {
        String str = "你好";
        //string 转 itn[]
        int[] ints = Arrays.stream(str.split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
        //int[] to List<Integer>
        List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
        List<Integer> list1 = list.stream()
                .collect(Collectors.toList());
        System.out.println(list1);
    }

    @Test
    public void test15() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String s = "2010-10-12";
        Date date1 = new Date();
        String format1 = format.format(date1);
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(format.parse(s));
        end.setTime(format.parse(format1));
        int i = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
        System.out.println(i);
    }

    @Test
    public void test16() {
        BriefcharpterQuery query = new BriefcharpterQuery();
        List<HrBriefchapter> val = briefchapterMapper.queryBriefcharpterDetileByParams(query);
        val.stream().map(dto -> {
            String experienceId = dto.getExperienceId();
            String[] split = experienceId.split("-");
            List<String> strings = Arrays.asList(split);

            String s = "";
            String s1 = "";
            String ss = "5";
            String xx = "1";

            if (strings.size() == 2) {
                s = strings.get(0);
                s1 = strings.get(1);
                if (ss.compareTo(s) > 0 && ss.compareTo(s1) <= 0) {
                    System.out.println(experienceId);
                }
            }
            if (strings.size() == 1) {
                s = strings.get(0);
                if (xx.compareTo(s) > 0 || xx.compareTo(s) <= 0) {
                    System.out.println(s);
                }
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Test
    public void test17() {
        List<HrUser> hrUsers = userMapper.personalInformation(4);
        hrUsers.stream()
                .map(dto -> {
                    Double salaryLower = dto.getExpectedSalaryLower().doubleValue();
                    System.out.println(salaryLower);
                    Double salaryUpper = dto.getExpectedSalaryUpper().doubleValue();
                    System.out.println(salaryUpper);
                    String format = StringUtil.decimalFormat(salaryLower);
                    String format1 = StringUtil.decimalFormat(salaryUpper);
                    String s = format + "k" + "-" + format1;
                    dto.setExpectedSalary(s);
                    return dto;
                }).collect(Collectors.toList());
    }

    @Test
    public void test18(){
       String a = "0-40";
       String aa = "0-40";
        if (a.compareTo(aa) == 0) {
            System.out.println("aa");
        }
    }

    @Test
    public void test19(){
        List<MySignUpTable> mySignUpTables = tableMapper.searchGroupingdefault();
        System.out.println(mySignUpTables);
    }

    @Test
    public void test20(){
        BriefcharpterQuery query = new BriefcharpterQuery();
        System.out.println(briefchapterMapper.studentDivision(query));
    }
}
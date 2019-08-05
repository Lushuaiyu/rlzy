package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.db.mapper.*;
import com.nado.rlzy.db.pojo.*;
import lombok.var;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private HrSignUpMapper mapper;

    @Autowired
    private HrAcctMapper acctMapper;

    @Autowired
    private HrUserMapper userMapper;

    @Autowired
    private AttentionMapper attentionMapper;

    @Autowired
    private HrBriefchapterMapper briefchapterMapper;

    @Autowired
    private HrDictionaryItemMapper dictionaryItemMapper;

    @Autowired
    private HrGroupMapper groupMapper;

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private HrComplaintMapper complaintMapper;

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
        System.out.println(mapper.personalInformationReferrer(2));
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
       var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       var dateTime = LocalDateTime.parse(str, formatter);
       var format = dateTime.format(formatter);
        System.out.println(format);
    }

    @Test
    public void test9(){
        BriefcharpterQuery query = new BriefcharpterQuery();
        System.out.println(briefchapterMapper.queryBriefcharpterByLongLiveRecruitment(query));

    }

    @Test
    public void test10(){
        //System.out.println(briefchapterMapper.queryBriefchapterBySignUpstatusRecruitment(0));
        System.out.println(briefchapterMapper.myReleaseRecruitment(7, 5, 2));
    }
    @Test
    public void test11(){
        System.out.println(complaintMapper.complaintRecruitment(5));
    }

    @Test
    public void test12(){
        System.out.println(dictionaryItemMapper.dictionary(3));


    }
}
package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.mapper.HrSignupDeliveryrecordMapper;
import com.nado.rlzy.db.pojo.HrSignupDeliveryrecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @ClassName 测试3
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/8/9 16:04
 * @Version 1.0
 */
public class Test3 extends BaseTest {

    @Autowired
    private HrSignupDeliveryrecordMapper deliveryrecordMapper;

    @Autowired
    private HrBriefchapterMapper mapper;

    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list.toString());
        System.out.println(list.stream().count());
    }

    @Test
    public void test2(){
        HrSignupDeliveryrecord deliveryrecord = new HrSignupDeliveryrecord();
        deliveryrecord.setSignupId(1);
        deliveryrecord.setBriefChapterId(66);
        deliveryrecord.setJobStatus(0);
        deliveryrecord.setCreateTime(LocalDateTime.now());
        ArrayList<HrSignupDeliveryrecord> list = new ArrayList<>();
        list.add(deliveryrecord);
        int i = deliveryrecordMapper.referrerToSIgnUp(list);
        System.out.println(i);
    }

    @Test
    public void test3(){
        mapper.remainingQuota(1,6);

    }
}
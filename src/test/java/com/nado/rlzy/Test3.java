package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.*;
import com.nado.rlzy.db.pojo.Collect;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.db.pojo.HrSignupDeliveryrecord;
import org.junit.Test;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName 测试3
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/8/9 16:04
 * @Version 1.0
 */
public class Test3 extends BaseTest {

    @Resource
    private HrSignupDeliveryrecordMapper deliveryrecordMapper;

    @Resource
    private HrBriefchapterMapper mapper;

    @Resource
    private HrSignUpMapper signUpMapper;

    @Resource
    private HrGroupMapper groupMapper;

    @Resource
    private HrUserMapper userMapper;

    @Resource
    private CollectMapper collectMapper;

    @Resource
    private HrComplaintMapper complaintMapper;

    @Test
    public void test1() {
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
    public void test2() {
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
    public void test5() {
        int interviewed = userMapper.interviewed(2);
        int i = userMapper.arReported(2);
        int i1 = userMapper.noReported(2);
        int i2 = userMapper.noInterview(2);
        int jobSeeker = userMapper.jobSeeker(2);

        System.out.println(jobSeeker);
    }

    @Test
    public void test6() {
        Collect collect = new Collect();
        collect.setUserId(7);
        collect.setSignUpId(9);
        System.out.println(collectMapper.insertSelective(collect));
    }

    @Test
    public void test7() {
        final boolean[] flag = {false};
        List<HrSignUp> hrSignUps = signUpMapper.threeNoInterview(1);
        hrSignUps.stream().map(dto -> {
            Integer jobStatus = dto.getJobStatus();
            if (jobStatus != 8) {
                flag[0] = flag[0];

            } else {
                //业务逻辑
                flag[0] = true;
            }
            return dto;
        }).collect(Collectors.toList());
        if (flag[0] = true) {
            System.out.println("afafdfddffdf");
        }
    }



}
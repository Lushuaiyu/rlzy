package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.pojo.HrSignUp;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Test
    public void test1(){
        List<HrSignUp> ups = mapper.signUpId();

        List<HrSignUp> u = mapper.signUpIdByReport();
        //System.out.println(ups.stream().collect(Collectors.toList()));

        System.out.println(u.stream().collect(Collectors.toList()));
    }

    @Test
    public void test2(){
        /*List<HrSignUp> ups = mapper.recruitmentInterviewOver();
        System.out.println(ups.stream().collect(Collectors.toList()));*/

        System.out.println(mapper.updateJobStatus(2));
    }
}
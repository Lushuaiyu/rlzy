package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.FeedbackMapper;
import com.nado.rlzy.db.mapper.HrUserMapper;
import com.nado.rlzy.db.pojo.Feedback;
import com.nado.rlzy.db.pojo.HrUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName 通用mapper的实际使用
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/25 17:13
 * @Version 1.0
 */
@Import(RlzyApplication.class)
public class CommonMapperTest extends BaseTest {

    @Autowired
    private FeedbackMapper mapper;

    @Autowired
    private HrUserMapper userMapper;

    @Test
    public void test1(){
        //创建Example
        Example example = new Example(Feedback.class);
        //创建Criteria
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteFlag", 0);
        List<Feedback> list = mapper.selectByExample(example);
        System.out.println(list.stream().collect(Collectors.toList()));



    }

    @Test
    public void test2(){
        HrUser user = new HrUser();
        user.setMobile("15152899877");
        HrUser one = userMapper.selectOne(user);
        System.out.println(one);


    }
}
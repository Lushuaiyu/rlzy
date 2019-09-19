package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.HrGroupMapper;
import com.nado.rlzy.db.mapper.HrUserMapper;
import com.nado.rlzy.db.pojo.HrGroup;
import com.nado.rlzy.db.pojo.HrLabel;
import com.nado.rlzy.db.pojo.HrUser;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/9/18 14:01
 * @Version 1.0
 */
public class Test10 extends BaseTest {

    @Resource
    private HrUserMapper userMapper;

    @Resource
    private HrGroupMapper groupMapper;

    @Test
    public void test1() {

        HrLabel hrLabel = new HrLabel(1, null, "4", 0, new Date());
        Integer groupid = Optional.ofNullable(hrLabel).orElseGet(HrLabel::new).getGroupid();
        System.out.println(groupid);
    }

    @Test
    public void test2() {
        Integer userId = 1;
        HrUser hrUser = userMapper.checkUserIdentity(userId);
        Integer type = hrUser.getType();
        System.out.println(type);
    }

    @Test
    public void test3() {
        HrGroup group = groupMapper.selectCoCertificationStatus(7, 6);
        Integer status = Optional.ofNullable(group).orElseGet(HrGroup::new).getStatus();
        System.out.println(status);
    }

    @Test
    public void test4() {
        String s = userMapper.subAccountPermission(47);
        if (s.contains("16")) {
            System.out.println("asdf");
        }


    }

    @Test
    public void test5() {
        ArrayList<Integer> list = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++){
            list.add(1);
        }
        System.out.println(list);
    }


}
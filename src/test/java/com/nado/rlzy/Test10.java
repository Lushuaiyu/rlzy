package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.pojo.HrLabel;
import org.junit.Test;

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
    @Test
    public void test1(){

        HrLabel hrLabel = new HrLabel(1, null, "4", 0, new Date());
        Integer groupid = Optional.ofNullable(hrLabel).orElseGet(HrLabel::new).getGroupid();
        System.out.println(groupid);


    }
}
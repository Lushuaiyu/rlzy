package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.HrGroupMapper;
import com.nado.rlzy.db.pojo.HrGroup;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-09-14 08:50
 * @Version 1.0
 */
public class Test9 extends BaseTest {

    @Resource
    private HrGroupMapper groupMapper;

    @Test
    public void test1(){

        List<HrGroup> hrGroups = groupMapper.coHomePageUpward(16);
        System.out.println(hrGroups);
    }

    @Test
    public void test2(){
        Integer a = 1;
        Integer b = 4;
        Integer c = 3;
        Integer d = 6;

        if (a.equals(1) && (!b.equals(2) || !c.equals(3))){
            System.out.println("fgfg");
        }
    }
}

package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.HrGroupMapper;
import com.nado.rlzy.db.pojo.HrGroup;
import com.nado.rlzy.utils.StringUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public void test1() {

        List<HrGroup> hrGroups = groupMapper.coHomePageUpward(16);
        System.out.println(hrGroups);
    }

    @Test
    public void test2() {
        Integer a = 1;
        Integer b = 4;
        Integer c = 3;
        Integer d = 6;

        if (a.equals(1) && (!b.equals(2) || !c.equals(3))) {
            System.out.println("fgfg");
        }
    }

    @Test
    public void test3() {
        HrGroup group = groupMapper.queryAgentEnterprisePid(8);
        Integer id = group.getId();
        System.out.println(id);
    }

    @Test
    public void test4() {
        String s = StringUtil.imageToBase64Str("C:\\Users\\lushuaiyu\\Desktop\\Snipaste_2019-07-16_09-30-28.png");
        System.out.println(s);
        String restring = "1111";



        String filePath = "C:\\work\\1.txt";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
        fos.write(s.getBytes());
        fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

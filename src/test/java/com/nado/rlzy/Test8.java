package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.HrDictionaryItemMapper;
import com.nado.rlzy.db.pojo.HrDictionaryItem;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-09-08 16:00
 * @Version 1.0
 */
public class Test8 extends BaseTest {

    @Resource
    private HrDictionaryItemMapper itemMapper;
    @Test
    public void test1(){
        String s = "112233";
        int length = s.length();
        for ( int i = 0; i < length / 2; i++)
            if (s.charAt(i) != s.charAt(length - i - 1))
                System.out.println("false");
            System.out.println("true");
    }


}

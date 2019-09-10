package com.nado.rlzy;

import com.alibaba.fastjson.JSONObject;
import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.HrDictionaryItemMapper;
import com.nado.rlzy.db.mapper.HrEnterpriseblacklistMapper;
import com.nado.rlzy.db.pojo.HrEnterpriseblacklist;
import com.nado.rlzy.service.PersonCenterService;
import com.nado.rlzy.utils.Base64Util;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Resource
    private HrEnterpriseblacklistMapper enterpriseblacklistMapper;

    @Resource
    private PersonCenterService centerService;

    @Test
    public void test1() {
        String s = "112233";
        int length = s.length();
        for (int i = 0; i < length / 2; i++)
            if (s.charAt(i) != s.charAt(length - i - 1))
                System.out.println("false");
        System.out.println("true");
    }

    @Test
    public void test2() {
        // 32 / 2 = 16 16 / 2 = 8 8 / 2 = 4 4 / 2 = 2
        byte a = Byte.parseByte("32");
        System.out.println(a >> 4);
    }

    @Test
    public void test3() {
        String[] str = new String[]{"aa", "bb", "cc", "dd", "ee"};
        List<String> collect = Stream.of(str).collect(Collectors.toList());
        String s = collect.stream().collect(Collectors.joining(","));
        List<HrEnterpriseblacklist> list = new ArrayList<>();
        HrEnterpriseblacklist enterpriseblacklist = new HrEnterpriseblacklist();
        enterpriseblacklist.setIdcard(s);
        enterpriseblacklistMapper.insertSelective(enterpriseblacklist);



       /* collect.stream()
                .map(dto -> {
                    HrEnterpriseblacklist enterpriseblacklist = new HrEnterpriseblacklist();
                    enterpriseblacklist.setIdcard(dto);
                    enterpriseblacklistMapper.insertSelective(enterpriseblacklist);
                    return dto;
                }).collect(Collectors.toList());*/


    }

    @Test
    public void test4() {
        /*String image = "";
        MultipartFile multipartFile = Base64Util.base64ToMultipart(image);
        String head = centerService.updateHead(multipartFile);*/




    }


}

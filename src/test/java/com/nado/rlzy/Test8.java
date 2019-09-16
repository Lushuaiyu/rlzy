package com.nado.rlzy;

import com.alibaba.fastjson.JSON;
import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.db.mapper.*;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.service.PersonCenterService;
import com.nado.rlzy.utils.NetEaseSendUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Resource
    private HrUserMapper userMapper;

    @Resource
    private HrBriefchapterMapper briefchapterMapper;

    @Resource
    private HrSignUpMapper signUpMapper;

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
        BriefcharpterQuery query = new BriefcharpterQuery();
        List<HrBriefchapter> hrBriefchapters = briefchapterMapper.queryBriefcharpterByParams(query);
        System.out.println(hrBriefchapters);
        List<HrBriefchapter> collect = hrBriefchapters.stream().filter(s -> s.getManAgeId().compareTo(20) > 0)
                .filter(v -> v.getRebate().compareTo(1) == 0)
                .collect(Collectors.toList());
        System.out.println(collect);

    }

    /**
     * json 转 list
     *
     * @return void
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 12:22 2019-09-11
     * @Param []
     */
    @Test
    public void test5() {
        //模拟前台传来的 json 字符串
        String str = "\t[\n" +
                "\t\t{\n" +
                "\t\t\t\"rebateMaleEntry\" : \"100\",\n" +
                "\t\t\t\"rebateFemaleEntry\" : \"200\",\n" +
                "\t\t\t\"rebateTime\" : \"2019-10-12 12:12:23\",\n" +
                "\t\t\t\"type\" : \"2\"\n" +
                "\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\"rebateMaleEntry\" : \"100\",\n" +
                "\t\t\t\"rebateFemaleEntry\" : \"200\",\n" +
                "\t\t\t\"rebateTime\" : \"2019-10-12 12:12:23\",\n" +
                "\t\t\t\"type\" : \"2\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"rebateMaleEntry\" : \"100\",\n" +
                "\t\t\t\"rebateFemaleEntry\" : \"200\",\n" +
                "\t\t\t\"rebateTime\" : \"2019-10-12 12:12:23\",\n" +
                "\t\t\t\"type\" : \"2\"\n" +
                "\t\t}\n" +
                "\t]";

        List<EntryResignation> users = JSON.parseArray(str, EntryResignation.class);
        System.out.println(users);
    }

    @Test
    public void test6() {
        int i = userMapper.selectplatformBlackRecruitmentEnd(8);
        System.out.println(i);
    }

    @Test
    public void test7() {
        /*String s = userMapper.selectReferrerIntentionalPost(3);
        System.out.println(s);*/
        List<HrSignUp> hrSignUps = signUpMapper.queryAll(25);
        System.out.println(hrSignUps);
    }

    @Test
    public void test8() {
        String str = "1,2,3";
        int[] ints = Arrays.stream(str.split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
        //List<Integer> collect = Arrays.stream(ints).boxed().collect(Collectors.toList());

        List<HrSignUp> aaa = signUpMapper.aaa(str);
        System.out.println(aaa);
    }

    @Test
    public void test9() {
       /* HrUser user = new HrUser();
        user.setMobile("18620925694");
        user.setPassword(MD5.getMD5("123456" + RlzyConstant.PASSWORD_SALT));
        user.setRegisterTime(LocalDateTime.now());
        user.setImproveInformation(1);
        userMapper.insertSelective(user);*/

        String s = null;
        try {
            s = NetEaseSendUtil.create(String.valueOf(40), null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }


}

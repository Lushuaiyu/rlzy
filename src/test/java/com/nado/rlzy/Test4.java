package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.mapper.HrRebaterecordMapper;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.mapper.HrUserMapper;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.db.pojo.HrRebaterecord;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.db.pojo.HrUser;
import org.junit.Test;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/8/15 16:01
 * @Version 1.0
 */
public class Test4 extends BaseTest {
    @Resource
    private HrSignUpMapper signUpMapper;

    @Resource
    private HrUserMapper userMapper;

    @Resource
    private HrRebaterecordMapper rebaterecordMapper;

    @Resource
    private HrBriefchapterMapper briefchapterMapper;



    @Test
    public void test2() {
        System.out.println(signUpMapper.SearchdirectAdmission(3, 0, 4));
    }

    @Test
    public void test3() {
        List<HrUser> list = userMapper.queryReferrerVillation(3);
        List<HrUser> collect = list.stream()
                .distinct().collect(Collectors.toList());
        System.out.println(collect);

    }



    @Test
    public void test5() {


       /* ReleaseBriefcharpterQuery record = new ReleaseBriefcharpterQuery();
        record.setRebateMale("100");
        record.setRebateFemale("200");
        record.setRebateTime(new Date());
        record.setRebateType(0);
        record.setBriefcharpterId(3);

        ReleaseBriefcharpterQuery re = new ReleaseBriefcharpterQuery();
        re.setRebateMale("100");
        re.setRebateFemale("400");
        re.setRebateTime(new Date());
        re.setRebateType(1);
        re.setBriefcharpterId(3);
        ReleaseBriefcharpterQuery ree = new ReleaseBriefcharpterQuery();
        record.setRebateMale("100");
        record.setRebateFemale("500");
        ree.setRebateTime(new Date());
        ree.setRebateType(2);
        ree.setBriefcharpterId(3);
        List<ReleaseBriefcharpterQuery> list = new ArrayList<>();
        list.add(record);
        list.add(re);
        list.add(ree);

        System.out.println(rebaterecordMapper.insertSelective(list));*/
    }

    @Test
    public void test6(){
        HrBriefchapter briefchapter = new HrBriefchapter();
        briefchapter.setPostId(4);
        briefchapter.setEmployerCertificatePhotoUrl("sdafdfdafdfdff");
        briefchapter.setRebateType(4);
        briefchapterMapper.save(briefchapter);
    }

    @Test
    public void test7(){
        HrRebaterecord hrRebaterecord = new HrRebaterecord();
        hrRebaterecord.setBriefchapterId(3);
        hrRebaterecord.setRebateType(1);
        System.out.println(rebaterecordMapper.selectReId(hrRebaterecord));
    }


    @Test
    public void test8(){
        String str = "2";
        String s = "1,2,3";
        String[] split = s.split(",");

        if (s.contains(str)) {
            System.out.println("afdfdf");
        }
    }
    @Test
    public void test9(){
        List<HrSignUp> hrSignUps = signUpMapper.queryAll(4);
        hrSignUps.stream()
                .map(dto -> {
                    Date graduationTime = dto.getGraduationTime();
                    SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd");
                    Date date1 = new Date();
                    String nowTime = formatt.format(date1);
                    Calendar startTime = Calendar.getInstance();
                    Calendar endTime = Calendar.getInstance();
                    Integer time = null;
                    String exTime = "";

                    try {
                        //毕业时间
                        startTime.setTime(graduationTime);
                        endTime.setTime(formatt.parse(nowTime));
                        //毕业了几年
                        time = endTime.get(Calendar.YEAR) - startTime.get(Calendar.YEAR);
                        exTime = String.valueOf(time);
                        System.out.println(exTime);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return dto;
                }).collect(Collectors.toList());

        System.out.println(hrSignUps);

    }

    @Test
    public void test10(){
        String a = "1";
        String b = "3";
        String c = "5";
        String d = "5";
        String e = "10";
        String f = "10";
        String g = "8";
        if (g.compareTo(a) < 0 && g.compareTo("0") > 0) {
            System.out.println("a");
        } else if (g.compareTo(b) > 0 && g.compareTo(c) < 0){
            System.out.println("b");

        } else if (g.compareTo(d) > 0 && g.compareTo(f) < 0){

            System.out.println("c");

        } else if (d.compareTo(e) > 1){
            System.out.println("d");

        }
        System.out.println(3 < 5 && 3 > 2);

        System.out.println(d.compareTo(e) );
    }


}
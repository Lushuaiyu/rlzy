package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.bean.query.EditBriefchapterQuery;
import com.nado.rlzy.bean.query.ReleaseBriefcharpterQuery;
import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.mapper.HrRebaterecordMapper;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.mapper.HrUserMapper;
import com.nado.rlzy.db.pojo.HrRebaterecord;
import com.nado.rlzy.db.pojo.HrUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
    @Autowired
    private HrSignUpMapper signUpMapper;

    @Autowired
    private HrUserMapper userMapper;

    @Autowired
    private HrRebaterecordMapper rebaterecordMapper;

    @Autowired
    private HrBriefchapterMapper briefchapterMapper;

    @Test
    public void test1() {
        signUpMapper.changeJobStatus(4, 2, 4);
    }

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
    public void test4() {
        EditBriefchapterQuery query = new EditBriefchapterQuery();
        query.setManNumNow(10);
        query.setWomenNumNow(23);
        query.setBriefchapter(4);
        briefchapterMapper.updateAA(query);
    }

    @Test
    public void test5() {


        ReleaseBriefcharpterQuery record = new ReleaseBriefcharpterQuery();
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

        System.out.println(rebaterecordMapper.insertSelective(list));
    }

    @Test
    public void test6(){
        ReleaseBriefcharpterQuery query = new ReleaseBriefcharpterQuery();
        query.setPostId(4);
        query.setEmployerCertificatePhotoUrl("sdafdfdafdfdff");
        query.setRebateType(4);
        briefchapterMapper.save(query);
    }

    @Test
    public void test7(){
        HrRebaterecord hrRebaterecord = new HrRebaterecord();
        hrRebaterecord.setBriefchapterId(3);
        hrRebaterecord.setRebateType(1);
        System.out.println(rebaterecordMapper.selectReId(hrRebaterecord));
    }
}
package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.mapper.HrComplaintMapper;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/8/24 10:34
 * @Version 1.0
 */
public class Test5 extends BaseTest {

    @Autowired
    private HrBriefchapterMapper mapper;

    @Autowired
    private HrSignUpMapper signUpMapper;

    @Autowired
    private HrComplaintMapper complaintMapper;

    @Test
    public void test1() {
        HrBriefchapter query = new HrBriefchapter();
        query.setRecruitedcompanyId(4);
        query.setPostId(2);
        query.setRecruitingNo(4);
        query.setAvgSalary(BigDecimal.valueOf(10000));
        query.setDetailSalary(BigDecimal.valueOf(100));
        query.setDetailSalaryWay("2");
        query.setEducationId("3");
        query.setExperienceId("2");
        query.setWelfareId("1,3");
        query.setPostDetail("这个职位十分的适合你么");
        query.setRegisterTime(new Date());
        query.setContractWay(0);
        query.setContractWayDetailId(2);
        //合同到期时间
        query.setContractTime(LocalDateTime.now());
        query.setHireWay(0);
        query.setRebate(1);
        query.setUserId(9);
        query.setStatus(0);
        query.setCreatetime(LocalDateTime.now());
        query.setManAgeId(1);
        query.setWomenAgeId(2);
        query.setWorkWayId("1");
        query.setWorkTimeArrangeId("1");
        query.setClothingReguirementId("2");
        query.setHobbyId("2");
        query.setOvertimeTimeId(3);
        query.setDescriptionJobPhotoUrl("fdfsdaf");
        query.setAcceptRecommendedResume(0);
        query.setEmployerCertificatePhotoUrl("fsdfdfdf");
        query.setManNum(10);
        query.setWomenNum(20);
        query.setInterviewAddress("香榭街道");
        query.setNoEmployerAddress("miao streat");
        query.setProfessionId("3");
        query.setWorkAddress("wang streat");
        query.setInterviewTime(LocalDateTime.now());
        query.setBriefChapterStatus(0);
        query.setRebateMaleInterview(BigDecimal.valueOf(100));
        query.setRebateMaleReport(BigDecimal.valueOf(200));
        query.setRebateMaleReport(BigDecimal.valueOf(400));
        query.setRebateMaleEntry(BigDecimal.valueOf(522));
        query.setRebateMaleEntry(BigDecimal.valueOf(123));
        query.setRebateFemaleInterview(BigDecimal.valueOf(52));
        query.setRebateFemaleReport(BigDecimal.valueOf(552));
        query.setRebateFemaleEntry(BigDecimal.valueOf(523));
        query.setRebateTimeEntry(new Date());
        System.out.println(mapper.save(query));
    }

    @Test
    public void test2() {
        //System.out.println(signUpMapper.rebateEntry(3));
        System.out.println(complaintMapper.violationRecord(1));

    }

    @Test
    public void test3() {
        BigDecimal num1 = new BigDecimal(1);
        BigDecimal num2 = new BigDecimal(2);
        BigDecimal num3 = new BigDecimal(3);
        BigDecimal num4 = new BigDecimal(4);
        BigDecimal num5 = new BigDecimal(5);
        BigDecimal num6 = new BigDecimal(6);
        BigDecimal num7 = new BigDecimal(7);
        BigDecimal num8 = new BigDecimal(8);

        BigDecimal add = num1.add(num2)
                .add(num3)
                .add(num4);
        BigDecimal add1 = num5.add(num6)
                .add(num7)
                .add(num8);
        BigDecimal n = add.compareTo(add1) > -1 ? add : add1;
        System.out.println(n);
    }
}
package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.db.mapper.*;
import com.nado.rlzy.db.pojo.EntryResignation;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.utils.CollectorsUtil;
import org.junit.Test;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/8/24 10:34
 * @Version 1.0
 */
public class Test5 extends BaseTest {

    @Resource
    private HrBriefchapterMapper mapper;

    @Resource
    private HrSignUpMapper signUpMapper;

    @Resource
    private HrComplaintMapper complaintMapper;

    @Resource
    private EntryResignationMapper resignationMapper;

    @Resource
    private HrAcctMapper acctMapper;

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

    @Test
    public void test4() {
        List<EntryResignation> entryResignations = resignationMapper.selectByAll(1);

        Map<Integer, BigDecimal> collect = entryResignations.stream().
                collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                        CollectorsUtil.summingBigDecimal(EntryResignation::getRebateMaleEntry)));
        System.out.println(collect);
    }

    @Test
    public void test5() {
        BriefcharpterQuery query = new BriefcharpterQuery();
        query.setBriefcharpterId(1);
        final List<EntryResignation>[] collect = new List[]{null};
        List<HrBriefchapter> list = mapper.queryBriefcharpterDetileByParams(query);
        list.stream()
                .map(dto -> {
                    collect[0] = dto.getRebateEntryResignation().stream().collect(Collectors.toList());
                    return dto;
                }).collect(Collectors.toList());
        System.out.println(collect[0]);
    }

    @Test
    public void test6() {
        new BigDecimal(0);
    }

    @Test
    public void test7() {
        List<EntryResignation> list = new ArrayList<>();
        EntryResignation resignation = new EntryResignation();
        resignation.setRebateMaleEntry(BigDecimal.valueOf(122));
        resignation.setRebateFemaleEntry(BigDecimal.valueOf(252));
        resignation.setRebateTime(new Date());
        resignation.setType(2);
        EntryResignation resignation2 = new EntryResignation();
        resignation2.setRebateMaleEntry(BigDecimal.valueOf(456));
        resignation2.setRebateFemaleEntry(BigDecimal.valueOf(215));
        resignation2.setRebateTime(new Date());
        resignation2.setType(2);
        EntryResignation resignation3 = new EntryResignation();
        resignation3.setRebateMaleEntry(BigDecimal.valueOf(412));
        resignation3.setRebateFemaleEntry(BigDecimal.valueOf(789));
        resignation3.setRebateTime(new Date());
        EntryResignation resignation4 = new EntryResignation();
        resignation4.setRebateMaleEntry(BigDecimal.valueOf(749));
        resignation4.setRebateFemaleEntry(BigDecimal.valueOf(954));
        resignation4.setRebateTime(new Date());
        list.add(resignation);
        list.add(resignation2);
        list.add(resignation3);
        list.add(resignation4);
        resignationMapper.insertList(list);
    }

    @Test
    public void test8() {
        EntryResignation resignation = new EntryResignation();
        resignation.setRebateMaleEntry(BigDecimal.valueOf(100));
        resignation.setRebateFemaleEntry(BigDecimal.valueOf(500));
        resignation.setType(2);
        resignation.setBriefChapterId(4);
        resignation.setRebateTime(new Date());

        EntryResignation resignation1 = new EntryResignation();
        resignation1.setRebateMaleEntry(BigDecimal.valueOf(210));
        resignation1.setRebateFemaleEntry(BigDecimal.valueOf(320));
        resignation1.setType(2);
        resignation1.setBriefChapterId(4);
        resignation1.setRebateTime(new Date());

        EntryResignation resignation2 = new EntryResignation();
        resignation2.setRebateMaleEntry(BigDecimal.valueOf(120));
        resignation2.setRebateFemaleEntry(BigDecimal.valueOf(145));
        resignation2.setType(2);
        resignation2.setBriefChapterId(4);
        resignation2.setRebateTime(new Date());

        EntryResignation resignation4 = new EntryResignation();
        resignation4.setRebateMaleEntry(BigDecimal.valueOf(152));
        resignation4.setRebateFemaleEntry(BigDecimal.valueOf(568));
        resignation4.setType(2);
        resignation4.setBriefChapterId(4);
        resignation4.setRebateTime(new Date());

        List<EntryResignation> list = new ArrayList<>();
        list.add(resignation);
        list.add(resignation1);
        list.add(resignation2);
        list.add(resignation4);
        Map<Integer, BigDecimal> collect = list.stream().collect(Collectors.groupingBy(EntryResignation::getBriefChapterId, CollectorsUtil.summingBigDecimal(EntryResignation::getRebateMaleEntry)));
        System.out.println(collect);
    }

    @Test
    public void test9() {
        List<Integer> list = new ArrayList<>();
    }

    @Test
    public void test10() {
        ArrayList<String> list = new ArrayList<>();
        list.add("3" +
                "");
        System.out.println(mapper.queryBriefchapterBySignUpstatusRecruitment(list, 1));
    }

    @Test
    public void test11() {
        System.out.println(mapper.interviewRebateOrReportRebate(1));
    }


}
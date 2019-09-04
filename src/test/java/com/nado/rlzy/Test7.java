package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.EntryResignationMapper;
import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.mapper.HrRebaterecordMapper;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.pojo.EntryResignation;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.db.pojo.HrRebaterecord;
import com.nado.rlzy.utils.StringUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/9/2 18:11
 * @Version 1.0
 */
public class Test7 extends BaseTest {
    @Resource
    private HrRebaterecordMapper rebaterecordMapper;

    @Resource
    private HrBriefchapterMapper mapper;

    @Resource
    private EntryResignationMapper resignationMapper;

    @Resource
    private HrSignUpMapper signUpMapper;


    @Test
    public void test1() {
        HrRebaterecord rebaterecord = new HrRebaterecord();
        rebaterecord.setBriefchapterId(5);
        rebaterecord.setRebateMale(BigDecimal.valueOf(566));
        rebaterecord.setRebateFemale(BigDecimal.valueOf(33));
        rebaterecord.setStatus(2);

        HrRebaterecord rebaterecord2 = new HrRebaterecord();
        rebaterecord2.setBriefchapterId(5);
        rebaterecord2.setRebateMale(BigDecimal.valueOf(566));
        rebaterecord2.setRebateFemale(BigDecimal.valueOf(33));
        rebaterecord2.setStatus(2);
        List<HrRebaterecord> hrRebaterecords = new ArrayList<>();
        hrRebaterecords.add(rebaterecord);
        hrRebaterecords.add(rebaterecord2);

        rebaterecordMapper.insertListt(hrRebaterecords);
    }

    @Test
    public void test2() {
        List<HrBriefchapter> hrBriefchapters = mapper.interviewRebateOrReportRebate(1);
        hrBriefchapters.stream().map(dto -> {
            BigDecimal rebateMaleInterview = dto.getRebateMaleInterview();
            BigDecimal rebateFemaleInterview = dto.getRebateFemaleInterview();
            System.out.println(rebateMaleInterview);
            System.out.println(rebateFemaleInterview);
            return dto;
        }).collect(Collectors.toList());
    }

    @Test
    public void test3() {
        /*Integer[] number = new Integer[] {1};
        List<Integer> list = Arrays.asList(number);
        long count = list.stream().count();
        Integer count1 = (int) count;
        System.out.println(count1);*/
        //简章面试时间过了 进入已结束
        // 现在时间 > 面试时间 把简章状态改为已过期(已结束)
    }

    @Test
    public void test4() {

        List<Integer> list = mapper.selectIdByBriefchapterId();
        list.stream()
                .map(d -> {
                    List<EntryResignation> map = resignationMapper.selectEntryStatusOver(d);
                    map.stream().map(dto -> {
                        Date rebateTimeStart = dto.getRebateTimeStart();
                        Date rebateTimeEnd = dto.getRebateTimeEnd();
                        Date date = new Date();
                        if (date.compareTo(rebateTimeStart) > 0 && date.compareTo(rebateTimeEnd) < 0) {
                            mapper.rebating(d);

                        }
                        return dto;
                    }).collect(Collectors.toList());
                    return d;
                }).collect(Collectors.toList());
    }

    @Test
    public void test5() {
        List<Integer> list = mapper.selectIdByBriefchapterId();
        list.stream()
                .map(dto -> {
                    List<EntryResignation> entryResignations = resignationMapper.selectLastRebatetimeByBriefchapterId(dto);
                    entryResignations.stream()
                            .map(d -> {
                                Date rebateTimeEnd = d.getRebateTimeEnd();
                                Date date = new Date();
                                if (date.compareTo(rebateTimeEnd) > 0) {
                                    mapper.alreadyRebate(dto);
                                }
                                return d;
                            }).collect(Collectors.toList());
                    return dto;
                }).collect(Collectors.toList());
    }


    @Test
    public void test6() {
        BigDecimal female = new BigDecimal(100);
        String a = "金额: ¥" + female;

        String substring = a.substring(5, 8);
        BigDecimal decimal = StringUtil.decimal(substring);
        //System.out.println(decimal);
        List<HrRebaterecord> hrRebaterecords = rebaterecordMapper.selectRebateTime();
        //System.out.println(hrRebaterecords);
        hrRebaterecords.stream()
                .map(dto -> {
                    Integer rebateHour = dto.getRebateHour();
                    Integer id = dto.getId();
                    Integer dtoUserId = dto.getUserId();
                    Integer businessUserId = dto.getBusinessUserId();
                    BigDecimal rebateMale = dto.getRebateMale();
                    BigDecimal rebateFemale = dto.getRebateFemale();
                    Integer sex = dto.getSex();
                    if (rebateHour.compareTo(72) > 0){
                       System.out.println(rebateHour + "===" + id + "===" + dtoUserId + "===" + businessUserId +
                               rebateMale + "===" + rebateFemale + "===" + sex);
                   }
                    return dto;
                }).collect(Collectors.toList());
    }

    @Test
    public void test7(){
        List<HrRebaterecord> list = rebaterecordMapper.signUpIdByReport();

        System.out.println(list);
    }

}
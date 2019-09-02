package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.mapper.HrRebaterecordMapper;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.db.pojo.HrRebaterecord;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void test1(){
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
    public void test2(){
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
    public void test3(){
        /*Integer[] number = new Integer[] {1};
        List<Integer> list = Arrays.asList(number);
        long count = list.stream().count();
        Integer count1 = (int) count;
        System.out.println(count1);*/
        //简章面试时间过了 进入已结束
        // 现在时间 > 面试时间 把简章状态改为已过期(已结束)

    }
}
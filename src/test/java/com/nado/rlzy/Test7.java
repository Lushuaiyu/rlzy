package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import com.nado.rlzy.db.mapper.*;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.utils.Base64Util;
import com.nado.rlzy.utils.DateUtil;
import com.nado.rlzy.utils.StringUtil;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    @Resource
    private HrUserMapper userMapper;

    @Resource
    private HrSignupDeliveryrecordMapper signupDeliveryrecordMapper;

    @Resource
    private MessageMapper messageMapper;


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
                    if (rebateHour.compareTo(72) > 0) {
                        System.out.println(rebateHour + "===" + id + "===" + dtoUserId + "===" + businessUserId +
                                rebateMale + "===" + rebateFemale + "===" + sex);
                    }
                    return dto;
                }).collect(Collectors.toList());
    }

    @Test
    public void test7() {
        List<HrRebaterecord> list = rebaterecordMapper.signUpIdByReport();

        System.out.println(list);
    }

    @Test
    public void test8() {
        HrUser hrUser = new HrUser();
        hrUser.setRecommendInfo("afdsff");
        hrUser.setId(String.valueOf(6));
        userMapper.updateByPrimaryKey(hrUser);
    }

    @Test
    public void test9() {
        //rebaterecordMapper.rebateOne(7, 4);

      /*  List<HrBriefchapter> hrBriefchapters = mapper.interviewAllPerson();
        System.out.println(hrBriefchapters);*/
        List<HrBriefchapter> list = mapper.interviewAllPerson();
        list.stream()
                .map(dto -> {
                    Integer brId = dto.getBrId();
                    Integer signUpId = dto.getSignUpId();
                    Integer dtoUserId = dto.getUserId();
                    LocalDateTime interviewTime = dto.getInterviewTime();
                    LocalDateTime today_start = LocalDateTime.of(LocalDate.from(interviewTime), LocalTime.MIN);
                    String td_st_str = today_start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH : mm : ss"));
                    System.out.println(td_st_str);
                    String format = today_start.plusHours(-4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH : mm : ss"));
                    System.out.println(format);


                    return dto;
                }).collect(Collectors.toList());
    }

    @Test
    public void test10() {
        List<HrBriefchapter> hrBriefchapters = mapper.intervieweAllPersonReferrer();
        hrBriefchapters.stream().map(dto -> {
            Integer id = dto.getId();
            Integer signUpId = dto.getSignUpId();
            Integer userId = dto.getUserId();
            System.out.println(id + "===" + signUpId + "===" + userId);
            return dto;
        }).collect(Collectors.toList());
    }

    @Test
    public void test11() {
        HrUser hrUser = new HrUser();
        hrUser.setType(54);
        hrUser.setId(String.valueOf(4));
        userMapper.updateByPrimaryKey(hrUser);
    }

    @Test
    public void test12() {
        List<HrBriefchapter> list = mapper.interviewAllPerson();
        System.out.println(list);
        list.stream()
                .map(dto -> {
                    Integer id = dto.getId();
                    Integer signUpId = dto.getSignUpId();
                    Integer userId = dto.getUserId();
                    LocalDateTime interviewTime = dto.getInterviewTime();
                    Date registerTime = dto.getRegisterTime();
                    System.out.println(id + "===" + signUpId + "===" + userId + "===" + interviewTime + "===" + registerTime);
                    return dto;
                }).collect(Collectors.toList());
    }

    @Test
    public void test13() {
        rebaterecordMapper.selectRebateInterruptMyselfRepresentative().stream()
                .filter(s -> s.getStatus().equals(2))
                .map(dto -> {
                    Integer briefchapterId = dto.getBriefchapterId();
                    Integer signUpId = dto.getSignUpId();
                    Integer userId = dto.getUserId();
                    Integer status = dto.getStatus();
                    Date rebateTime = dto.getRebateTime();
                    LocalDateTime localDateTime = DateUtil.convertToLocalDateTimeViaInstant(rebateTime);
                    System.out.println(briefchapterId + "===" + signUpId + "===" + userId + "===" + status + "===" + localDateTime);
                    return dto;
                }).collect(Collectors.toList());
    }

    @Test
    public void test14(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("data:image/jpeg;base64,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");
        StringBuffer stringBuffer1 = stringBuffer;


    }

    @Test
    public void test15(){
        List<HrBriefchapter> list = mapper.intervieweAllPersonReferrer();
        list.stream()
                .map(dto -> {
                    Integer id = dto.getId();
                    Integer signUpId = dto.getSignUpId();
                    Integer userId = dto.getUserId();
                    LocalDateTime interviewTime = dto.getInterviewTime();
                    String userName = dto.getUserName();
                    //获得面试那天0点的时间
                    LocalDateTime zeroPoint = LocalDateTime.of(LocalDate.from(interviewTime), LocalTime.MIN);
                    String format = zeroPoint.plusHours(-4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    LocalDateTime now = LocalDateTime.now();
                    String nowTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    List<Message> allList = messageMapper.selectAllList();
                    allList.stream()
                            .map(t -> {
                                Integer briefchapterId = t.getBriefchapterId() != null ? t.getBriefchapterId() : -1;
                                System.out.println(briefchapterId);
                                String signUpName = t.getSignUpName() != null && t.getSignUpName() != "" ? t.getSignUpName() : "-1";
                                System.out.println(signUpName);
                                if (nowTime.compareTo(format) > 0 && id.compareTo(briefchapterId) != 0 && userName.compareTo(signUpName) != 0 ) {
                                    Message message = new Message();
                                    message.setUserId(userId);
                                    message.setSignUpId(signUpId);
                                    message.setBriefchapterId(id);
                                    message.setCreateTime(LocalDateTime.now());
                                    message.setType(1);
                                    message.setSignUpName(userName);
                                    List<Message> messages = new ArrayList<>();
                                    messages.add(message);
                                    //messageMapper.insertList(messages);
                                }
                                return t;

                            }).collect(Collectors.toList());

                    return dto;
                }).collect(Collectors.toList());
    }

    @Test
    public void test16(){

        int i = userMapper.selectEnterPriseBlacakList(22);
        System.out.println(i);

    }



}
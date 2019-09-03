package com.nado.rlzy.config;

import com.nado.rlzy.db.mapper.*;
import com.nado.rlzy.db.pojo.EntryResignation;
import com.nado.rlzy.db.pojo.HrRebaterecord;
import com.nado.rlzy.db.pojo.HrSignUp;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/18 15:17
 * @Version 1.0
 */
@Component
@Slf4j
@EnableScheduling
public class ScheduledTask {
    @Resource
    private HrSignUpMapper mapper;

    @Resource
    private HrComplaintMapper complaintMapper;

    @Resource
    private HrRebaterecordMapper rebaterecordMapper;

    @Resource
    private HrBriefchapterMapper briefchapterMapper;

    @Resource
    private EntryResignationMapper resignationMapper;

    /**
     * 面试
     */
    @Scheduled(cron = "0 0 20 * * ? ")
    @Transactional(rollbackFor = Exception.class)
    public void task1() {
        List<HrSignUp> ups = mapper.signUpId();

        ups.stream().map(t -> {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //现在时间
            String nowTime = format.format(new Date());
            // 取当前日期。
            Calendar cale = Calendar.getInstance();
            //晚上8点的时间
            Calendar calendar = new GregorianCalendar(cale.get(Calendar.YEAR),
                    cale.get(Calendar.MONTH), cale.get(Calendar.DAY_OF_MONTH), 20, 0, 0);
            Date date = calendar.getTime();
            String s1 = format.format(date);
            //现在时间 > 晚上8点 执行定时任务
            if (nowTime.compareTo(s1) > 0) {
                mapper.updateJobStatus(t.getId());
            }
            return t;
        }).collect(Collectors.toList());


    }

    /**
     * 报道
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:25 2019/7/18
     * @Param []
     **/
    @Scheduled(cron = "0 0 20 * * ? ")
    @Transactional(rollbackFor = Exception.class)
    public void task2() {
        List<HrSignUp> ups = mapper.signUpIdByReport();
        ups.stream().map(s -> {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //现在时间
            String nowTime = format.format(new Date());

            // 取当前日期。
            Calendar cale = Calendar.getInstance();
            //晚上8点的时间
            Calendar calendar = new GregorianCalendar(cale.get(Calendar.YEAR),
                    cale.get(Calendar.MONTH), cale.get(Calendar.DAY_OF_MONTH), 20, 0, 0);
            Date date = calendar.getTime();
            String s1 = format.format(date);
            if (nowTime.compareTo(s1) > 0) {
                mapper.updateJobStatusConfirmationReportByTask(ups);
            }
            return s;
        }).collect(Collectors.toList());
    }


    /**
     * 定时任务 返佣 代码可能有问题
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:25 2019/7/18
     * @Param []
     **/
    @Scheduled(cron = "0 0 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void task3() {
        List<HrRebaterecord> list = rebaterecordMapper.selectRebateTime();

        list.stream().map(s -> {


            if (s.getRebateTime() != null) {
                //String dateToStr = StringUtil.DateToStr(time);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //现在时间
                String nowTime = format.format(new Date());

                // creates calendar
                Calendar cal = Calendar.getInstance();
                // 返佣时间
                cal.setTime(s.getRebateTime());
                // 返佣时间 加72 小时
                cal.add(Calendar.HOUR_OF_DAY, 72);
                //72小时后的时间
                String st = format.format(cal.getTime());
                //  现在时间 > 返佣时间 + 72 小时 执行update语句
                if (nowTime.compareTo(st) > 0) {
                    mapper.waitingForCommissionToBecomeARebate();
                }

            }


            return s;
        }).collect(Collectors.toList());


    }

    /**
     * 定时任务: 已面试改为面试不通过
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:02 2019/7/19
     * @Param []
     **/
    @Scheduled(cron = "0 0 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void task4() {
        //查询简章的报道时间
        List<HrSignUp> ups = mapper.recruitmentInterviewOver();
        ups.stream().map(recruitment -> {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = format.format(new Date());
            //报到时间
            if (recruitment.getRegisterTime() != null) {
                // 取当前日期。
                Calendar cale = Calendar.getInstance();
                cale.setTime(recruitment.getRegisterTime());
                //00.00 的时间
                Calendar calendar = new GregorianCalendar(cale.get(Calendar.YEAR),
                        cale.get(Calendar.MONTH), cale.get(Calendar.DAY_OF_MONTH), 00, 0, 0);
                Date date = calendar.getTime();

                String registerTime = format.format(date);
                // 现在时间 > 报到时间当天 0.00
                if (nowTime.compareTo(registerTime) > 0) {
                    mapper.updateInterviewStatus();
                }

            }


            return recruitment;
        }).collect(Collectors.toList());
    }

    /**
     * 企业未解决求职者的投诉, 7天后
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:07 2019/7/25
     * @Param []
     **/
    @Scheduled(cron = "0 59 23 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void task5() {
        var id = complaintMapper.queryParams();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        id.stream().map(u -> {
            // 现在系统时间
            var timeNow = LocalDateTime.now();
            //格式化当前时间
            var stringTimeNow = format.format(timeNow);
            //得到投诉创建时间
            var createTime = u.getCreateTime();
            // 投诉 7天后
            var sevenDaysLater = createTime.plusDays(7);

            //格式化 投诉7天后的时间
            var format2 = format.format(sevenDaysLater);
            //如果 系统时间 > 投诉7天后的时间 平台后台处理
            if (stringTimeNow.compareTo(format2) > 0) {
                Integer complaintId = u.getId();
                //把投诉状态改为 平台后台处理
                complaintMapper.updateStatusById(complaintId);
            }
            return u;


        }).collect(Collectors.toList());


    }

    /**
     * now > 简章的面试时间 简章状态改为 已过期
     *
     * @return void
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 23:02 2019-09-02
     * @Param []
     */
    @Scheduled(cron = "0 0 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void task6() {
        briefchapterMapper.updateInterviewStatus();
    }

    /**
     * 把已结束状态下的简章 没有到面试时间的改为待面试
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:48 2019/9/3
     * @Param []
     **/
    @Scheduled(cron = "0 0 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void task7() {
        briefchapterMapper.waitingForAnInterview();
    }

    /**
     * 已结束状态下 now() > 面试时间 修改已结束状态为待报到
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:30 2019/9/3
     * @Param []
     **/
    @Scheduled(cron = "0 0 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void task8() {
        briefchapterMapper.waitForReport();
    }


    /**
     * now > 报道时间 and now < 第一笔返佣时间
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:27 2019/9/3
     * @Param []
     **/
    @Scheduled(cron = "0 0 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void test9() {
        briefchapterMapper.waitForRebate();

    }

    /**
     * 招聘端 我的发布 已结束 返佣中
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:28 2019/9/3
     * @Param []
     * @return void
     **/
    @Scheduled(cron = "0 0 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void test10() {
        List<Integer> list = briefchapterMapper.selectIdByBriefchapterId();
        list.stream()
                .map(d -> {
                    List<EntryResignation> map = resignationMapper.selectEntryStatusOver(d);
                    map.stream().map(dto -> {
                        Date rebateTimeStart = dto.getRebateTimeStart();
                        Date rebateTimeEnd = dto.getRebateTimeEnd();
                        Date date = new Date();
                        if (date.compareTo(rebateTimeStart) > 0 && date.compareTo(rebateTimeEnd) < 0) {
                            briefchapterMapper.rebating(d);
                        }
                        return dto;
                    }).collect(Collectors.toList());
                    return d;
                }).collect(Collectors.toList());
    }

    /**
     * 招聘端 我的发布 已结束 返佣结束
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:29 2019/9/3
     * @Param []
     * @return void
     **/
    @Scheduled(cron = "0 0 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void test11(){
        List<Integer> list = briefchapterMapper.selectIdByBriefchapterId();
        list.stream()
                .map(dto -> {
                    List<EntryResignation> entryResignations = resignationMapper.selectLastRebatetimeByBriefchapterId(dto);
                    entryResignations.stream()
                            .map(d -> {
                                Date rebateTimeEnd = d.getRebateTimeEnd();
                                Date date = new Date();
                                if (date.compareTo(rebateTimeEnd) > 0) {
                                    briefchapterMapper.alreadyRebate(dto);
                                }
                                return d;
                            }).collect(Collectors.toList());
                    return dto;
                }).collect(Collectors.toList());
    }


}
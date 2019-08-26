package com.nado.rlzy.config;

import com.nado.rlzy.db.mapper.HrComplaintMapper;
import com.nado.rlzy.db.mapper.HrRebaterecordMapper;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.pojo.HrSignUp;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
public class ScheduledTask {
    @Autowired
    private HrSignUpMapper mapper;

    @Autowired
    private HrComplaintMapper complaintMapper;

    @Autowired
    private HrRebaterecordMapper rebaterecordMapper;

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
     * 定时任务 返佣
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:25 2019/7/18
     * @Param []
     **/
    @Scheduled(cron = "*/1 * * * *")
    @Transactional(rollbackFor = Exception.class)
    public void task3() {
        List<HrSignUp> list = rebaterecordMapper.selectRebateTime();
        list.stream().map(s -> {
            s.getRebat().stream().map(c -> {
                Date time = c.getCreateTime();
                //String dateToStr = StringUtil.DateToStr(time);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //现在时间
                String nowTime = format.format(new Date());

                // creates calendar
                Calendar cal = Calendar.getInstance();
                // 返佣时间
                cal.setTime(time);
                // 返佣时间 加72 小时
                cal.add(Calendar.HOUR_OF_DAY, 72);
                //72小时后的时间
                String st = format.format(cal.getTime());
                //  现在时间 > 返佣时间 + 72 小时 执行update语句
                if (nowTime.compareTo(st) > 0) {
                    mapper.waitingForCommissionToBecomeARebate();
                }


                return c;
            }).collect(Collectors.toList());
            return s;
        }).collect(Collectors.toList());


    }

    /**
     * 不合适 和面试不通过
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:02 2019/7/19
     * @Param []
     **/
    @Scheduled(cron = "*/1 * * * *")
    @Transactional(rollbackFor = Exception.class)
    public void task4() {
        List<HrSignUp> ups = mapper.recruitmentInterviewOver();
        ups.stream().map(recruitment -> {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = format.format(new Date());
            //报到时间
            Date time = recruitment.getRegisterTime();
            // 取当前日期。
            Calendar cale = Calendar.getInstance();
            cale.setTime(time);


            //00.00 的时间
            Calendar calendar = new GregorianCalendar(cale.get(Calendar.YEAR),
                    cale.get(Calendar.MONTH), cale.get(Calendar.DAY_OF_MONTH), 00, 0, 0);
            Date date = calendar.getTime();

            String registerTime = format.format(date);
            // 现在时间 > 报到时间当天 0.00
            if (nowTime.compareTo(registerTime) > 0) {
                mapper.updateInterviewStatus();
            }
            return recruitment;
        }).collect(Collectors.toList());
    }

    /**
     * 企业未解决求职者的投诉, 7天后
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:07 2019/7/25
     * @Param []
     * @return void
     **/
    @Scheduled(cron = "0 59 23 * * ? *")
    @Transactional(rollbackFor = Exception.class)
    public void task5(){
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


}
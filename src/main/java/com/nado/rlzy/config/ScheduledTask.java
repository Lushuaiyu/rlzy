package com.nado.rlzy.config;

import com.nado.rlzy.bean.dto.TaskHireWayDto;
import com.nado.rlzy.db.mapper.*;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.utils.DateUtil;
import com.nado.rlzy.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    @Resource
    private HrAcctMapper acctMapper;

    @Resource
    private HrAcctDetailMapper acctDetailMapper;

    @Resource
    private HrSignupDeliveryrecordMapper signupDeliveryrecordMapper;

    @Resource
    private MessageMapper messageMapper;


    /**
     * 求职者报道那天 晚上没有报道 默认确认报道 自动返佣
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
        List<HrRebaterecord> ups = rebaterecordMapper.signUpIdByReport();
        ups.stream().map(s -> {
            Integer businessUserId = s.getBusinessUserId();
            BigDecimal rebateMale = s.getRebateMale();
            BigDecimal rebateFemale = s.getRebateFemale();
            Integer brId = s.getBrId();
            Integer sex = s.getSex();
            Date registerTime = s.getRegisterTime();
            Integer userId = s.getUserId();
            Integer hsdId = s.getHsdId();


            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //现在时间
            String nowTime = format.format(new Date());
            // 取当前日期。
            Calendar cale = Calendar.getInstance();
            //把报道时间塞到cale里 !
            cale.setTime(registerTime);
            //报道那天的晚上8点的时间
            Calendar calendar = new GregorianCalendar(cale.get(Calendar.YEAR),
                    cale.get(Calendar.MONTH), cale.get(Calendar.DAY_OF_MONTH), 20, 0, 0);
            Date date = calendar.getTime();
            String s1 = format.format(date);

            // 查询返佣记录表的信息 简章 id 报名投递表的 id 返佣类型是报道返佣的
            List<HrRebaterecord> list = rebaterecordMapper.selectAllReportInformation();
            list.stream()
                    .map(d -> {
                        Integer briefchapterId = d.getBriefchapterId();
                        Integer signupDeliveryrecordId = d.getSignupDeliveryrecordId();
                        Integer rebateType = d.getRebateType();

                        // 如果现在时间 > 晚上8点 查到的 报名投递表id和 简章 id  不等于 返佣记录表的 简章 id 和 signup_deliveryrecord_id 则执行定时任务
                        if (nowTime.compareTo(s1) > 0 && brId.compareTo(briefchapterId) < 0 && hsdId.compareTo(signupDeliveryrecordId) < 0) {
                            if (sex.equals(0)) {
                                //女
                                HrRebaterecord rebaterecord = new HrRebaterecord();
                                rebaterecord.setRebateFemale(rebateFemale);
                                rebaterecord.setBriefchapterId(brId);
                                rebaterecord.setRebateTime(new Date());
                                rebaterecord.setCreateTime(new Date());
                                rebaterecord.setSignupDeliveryrecordId(hsdId);
                                rebaterecord.setRebateType(1);
                                rebaterecord.setStatus(1);
                                List<HrRebaterecord> hrRebaterecords = new ArrayList<>();
                                hrRebaterecords.add(rebaterecord);
                                rebaterecordMapper.insertListt(hrRebaterecords);
                                //钱从企业冻结金额到求职者 或者推荐人余额 这里是求职者或者推荐人钱增加
                                acctMapper.rebateUser(rebateFemale, userId);
                                //企业冻结金额减少 消费金额增加
                                acctMapper.rebateBusiness(rebateFemale, businessUserId);
                                //acctDetail 增加数据 账户余额增加
                                //查询账户id, 账户余额
                                HrAcct hrAcct = acctMapper.selectAcctIdByUserId(userId);
                                //账户id
                                Integer acctId = hrAcct.getId();
                                //账户余额
                                BigDecimal acctbalance = hrAcct.getAcctbalance();
                                //账户详情
                                HrAcctDetail detail = new HrAcctDetail();
                                detail.setAcctid(acctId);
                                detail.setAmount(rebateFemale);
                                detail.setBeforeamount(acctbalance);
                                detail.setAfteramount(rebateFemale.add(acctbalance));
                                detail.setCreatetime(LocalDateTime.now());
                                detail.setStatus(1);
                                detail.setType(2);
                                detail.setBriefchapterid(brId);
                                //增加账户详情
                                acctDetailMapper.insertSelective(detail);
                                // 报名表投递表  待返佣金额减少 面试返佣状态改变
                                HrSignupDeliveryrecord deliveryrecord = new HrSignupDeliveryrecord();
                                deliveryrecord.setAcceptRebateAmount(rebateFemale);
                                deliveryrecord.setSignupId(userId);
                                deliveryrecord.setBriefChapterId(brId);
                                signupDeliveryrecordMapper.reducedRebateAmount(deliveryrecord);

                            } else {
                                // 男
                                HrRebaterecord rebaterecord = new HrRebaterecord();
                                rebaterecord.setRebateFemale(rebateMale);
                                rebaterecord.setBriefchapterId(brId);
                                rebaterecord.setRebateTime(new Date());
                                rebaterecord.setCreateTime(new Date());
                                rebaterecord.setSignupDeliveryrecordId(hsdId);
                                rebaterecord.setRebateType(1);
                                rebaterecord.setStatus(1);
                                List<HrRebaterecord> hrRebaterecords = new ArrayList<>();
                                hrRebaterecords.add(rebaterecord);
                                rebaterecordMapper.insertListt(hrRebaterecords);
                                //钱从企业冻结金额到求职者 或者推荐人余额 这里是求职者或者推荐人钱增加
                                acctMapper.rebateUser(rebateMale, userId);
                                //企业冻结金额减少 消费金额增加
                                acctMapper.rebateBusiness(rebateMale, businessUserId);
                                //acctDetail 增加数据 账户余额增加
                                //查询账户id, 账户余额
                                HrAcct hrAcct = acctMapper.selectAcctIdByUserId(userId);
                                //账户id
                                Integer acctId = hrAcct.getId();
                                //账户余额
                                BigDecimal acctbalance = hrAcct.getAcctbalance();
                                //账户详情
                                HrAcctDetail detail = new HrAcctDetail();
                                detail.setAcctid(acctId);
                                detail.setAmount(rebateMale);
                                detail.setBeforeamount(acctbalance);
                                detail.setAfteramount(rebateMale.add(acctbalance));
                                detail.setCreatetime(LocalDateTime.now());
                                detail.setStatus(1);
                                detail.setType(2);
                                detail.setBriefchapterid(brId);
                                //增加账户详情
                                acctDetailMapper.insertSelective(detail);
                                // 报名表投递表  待返佣金额减少 面试返佣状态改变
                                HrSignupDeliveryrecord deliveryrecord = new HrSignupDeliveryrecord();
                                deliveryrecord.setAcceptRebateAmount(rebateMale);
                                deliveryrecord.setSignupId(userId);
                                deliveryrecord.setBriefChapterId(brId);
                                signupDeliveryrecordMapper.reducedRebateAmount(deliveryrecord);
                            }
                        }

                        return d;
                    }).collect(Collectors.toList());

            return s;
        }).collect(Collectors.toList());
    }


    /**
     * 定时任务 入职返佣
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
        //查询现在时间 和返佣时间的差值 userId 商户id
        List<HrRebaterecord> list = rebaterecordMapper.selectRebateTime();

        list.stream().map(s -> {
            //rebateHour >= 72
            Integer rebateHour = s.getRebateHour();
            Integer id = s.getId();
            Integer userId = s.getUserId();
            Integer businessUserId = s.getBusinessUserId();
            BigDecimal rebateMale = s.getRebateMale();
            BigDecimal rebateFemale = s.getRebateFemale();
            Integer brId = s.getBrId();
            Integer sex = s.getSex();
            Integer signupDeliveryrecordId = s.getSignupDeliveryrecordId();
            //如果 入职返佣 72 小时后没有执行 且 简章 id 和 报名投递表 不相等 以及 返佣类型 不等于入职返佣 进 if
            List<HrRebaterecord> list1 = rebaterecordMapper.selectAllEntryInformation();
            list1.stream()
                    .map(d -> {
                        Integer briefchapterId = d.getBriefchapterId();
                        Integer hsdId = d.getSignupDeliveryrecordId();
                        Integer rebateType = d.getRebateType();
                        Integer status = d.getStatus();
                        if (rebateHour.compareTo(72) > 0 && brId.compareTo(briefchapterId) < 0 && signupDeliveryrecordId.compareTo(hsdId) < 0 && rebateType.equals(2) && status.equals(0)) {
                            if (sex.equals(0)) {
                                //女
                                //更改返佣状态
                                rebaterecordMapper.rebateOne(id, signupDeliveryrecordId);
                                //返佣金额
                                BigDecimal rebateMoney = rebateFemale != null ? rebateFemale : BigDecimal.ZERO;
                                //钱从企业冻结金额到求职者 或者推荐人余额 这里是求职者或者推荐人钱增加
                                acctMapper.rebateUser(rebateMoney, userId);
                                //企业冻结金额减少 消费金额增加
                                acctMapper.rebateBusiness(rebateMoney, businessUserId);
                                //acctDetail 增加数据 账户余额增加
                                //查询账户id, 账户余额
                                HrAcct hrAcct = acctMapper.selectAcctIdByUserId(userId);
                                //账户id
                                Integer acctId = hrAcct.getId();
                                //账户余额
                                BigDecimal acctbalance = hrAcct.getAcctbalance();
                                //账户详情
                                HrAcctDetail detail = new HrAcctDetail();
                                detail.setAcctid(acctId);
                                detail.setAmount(rebateMoney);
                                detail.setBeforeamount(acctbalance);
                                detail.setAfteramount(rebateMoney.add(acctbalance));
                                detail.setCreatetime(LocalDateTime.now());
                                detail.setStatus(1);
                                detail.setType(2);
                                detail.setBriefchapterid(brId);
                                //增加账户余额
                                acctDetailMapper.insertSelective(detail);
                            } else if (sex.equals(1)) {
                                //男
                                //更改返佣状态
                                rebaterecordMapper.rebateOne(id, signupDeliveryrecordId);
                                //返佣金额
                                BigDecimal rebateMoney = rebateMale != null ? rebateMale : BigDecimal.ZERO;
                                //钱从企业冻结金额到求职者 或者推荐人余额 这里是求职者或者推荐人钱增加
                                acctMapper.rebateUser(rebateMoney, userId);
                                //企业冻结金额减少 消费金额增加
                                acctMapper.rebateBusiness(rebateMoney, businessUserId);
                                //acctDetail 增加数据 账户余额增加
                                //查询账户id, 账户余额
                                HrAcct hrAcct = acctMapper.selectAcctIdByUserId(userId);
                                //账户id
                                Integer acctId = hrAcct.getId();
                                //账户余额
                                BigDecimal acctbalance = hrAcct.getAcctbalance();
                                //账户详情
                                HrAcctDetail detail = new HrAcctDetail();
                                detail.setAcctid(acctId);
                                detail.setAmount(rebateMoney);
                                detail.setBeforeamount(acctbalance);
                                detail.setAfteramount(rebateMoney.add(acctbalance));
                                detail.setCreatetime(LocalDateTime.now());
                                detail.setStatus(1);
                                detail.setType(2);
                                detail.setBriefchapterid(brId);
                                //增加账户余额
                                acctDetailMapper.insertSelective(detail);
                            }
                        }
                        return d;
                    }).collect(Collectors.toList());


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
    public void task9() {
        briefchapterMapper.waitForRebate();

    }

    /**
     * 招聘端 我的发布 已结束 返佣中
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:28 2019/9/3
     * @Param []
     **/
    @Scheduled(cron = "0 0 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void task10() {
        List<Integer> list = briefchapterMapper.selectIdByBriefchapterId();
        list.stream()
                .map(d -> {
                    List<EntryResignation> map = resignationMapper.selectEntryStatusOver(d);
                    if (map.size() > 0) {
                        map.stream().map(dto -> {
                            Date rebateTimeStart = dto.getRebateTimeStart();
                            Date rebateTimeEnd = dto.getRebateTimeEnd();
                            Date date = new Date();
                            if (date.compareTo(rebateTimeStart) > 0 && date.compareTo(rebateTimeEnd) < 0) {
                                briefchapterMapper.rebating(d);
                            }
                            return dto;
                        }).collect(Collectors.toList());
                    }

                    return d;
                }).collect(Collectors.toList());
    }

    /**
     * 招聘端 我的发布 已结束 返佣结束
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:29 2019/9/3
     * @Param []
     **/
    @Scheduled(cron = "0 0 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void task11() {
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


    /**
     * 查询身份是本人求职 简章是代招单位下的单位  的面试时间 > 现在时间
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:39 2019/9/5
     * @Param []
     **/
    @Scheduled(cron = "0 0 20 * * ? ")
    @Transactional(rollbackFor = Exception.class)
    public void task12() {
        List<HrBriefchapter> list = briefchapterMapper.interviewAllPerson();
        list.stream()
                .map(dto -> {
                    Integer id = dto.getId();
                    Integer signUpId = dto.getSignUpId();
                    Integer userId = dto.getUserId();
                    LocalDateTime interviewTime = dto.getInterviewTime();
                    //获得面试那天0点的时间
                    LocalDateTime zeroPoint = LocalDateTime.of(LocalDate.from(interviewTime), LocalTime.MIN);
                    String format = zeroPoint.plusHours(-4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    LocalDateTime now = LocalDateTime.now();
                    String nowTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    List<Message> allList = messageMapper.selectAllList();
                    allList.stream()
                            .map(t -> {
                                Integer briefchapterId = t.getBriefchapterId() != null ? t.getBriefchapterId() : -1;
                                Integer userId1 = t.getUserId() != null ? t.getUserId() : -1;
                                Integer type = t.getType();
                                if (nowTime.compareTo(format) > 0 && id.compareTo(briefchapterId) < 0 && userId.compareTo(userId1) < 0 && type.compareTo(1) < 0) {
                                    Message message = new Message();
                                    message.setUserId(userId);
                                    message.setSignUpId(signUpId);
                                    message.setBriefchapterId(id);
                                    message.setCreateTime(LocalDateTime.now());
                                    message.setType(1);
                                    List<Message> messages = new ArrayList<>();
                                    messages.add(message);
                                    messageMapper.insertList(messages);
                                }
                                return t;

                            }).collect(Collectors.toList());
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * 查询身份是本人求职 简章是招聘单位的简章 的面试时间 > 现在时间
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:31 2019/9/6
     * @Param []
     **/
    @Scheduled(cron = "0 0 20 * * ? ")
    @Transactional(rollbackFor = Exception.class)
    public void task13() {

        List<HrBriefchapter> list = briefchapterMapper.interviewAllPersonRecruitment();
        list.stream()
                .map(dto -> {
                    Integer id = dto.getId();
                    Integer signUpId = dto.getSignUpId();
                    Integer userId = dto.getUserId();
                    LocalDateTime interviewTime = dto.getInterviewTime();
                    //获得面试那天0点的时间
                    LocalDateTime zeroPoint = LocalDateTime.of(LocalDate.from(interviewTime), LocalTime.MIN);
                    String format = zeroPoint.plusHours(-4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    LocalDateTime now = LocalDateTime.now();
                    String nowTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    List<Message> allList = messageMapper.selectAllList();
                    allList.stream()
                            .map(t -> {
                                Integer briefchapterId = t.getBriefchapterId() != null ? t.getBriefchapterId() : -1;
                                Integer userId1 = t.getUserId() != null ? t.getUserId() : -1;
                                Integer type = t.getType();
                                if (nowTime.compareTo(format) > 0 && id.compareTo(briefchapterId) < 0 && userId.compareTo(userId1) < 0 && type.compareTo(1) < 0) {
                                    Message message = new Message();
                                    message.setUserId(userId);
                                    message.setSignUpId(signUpId);
                                    message.setBriefchapterId(id);
                                    message.setCreateTime(LocalDateTime.now());
                                    message.setType(1);
                                    List<Message> messages = new ArrayList<>();
                                    messages.add(message);
                                    messageMapper.insertList(messages);
                                }
                                return t;

                            }).collect(Collectors.toList());
                    return dto;
                }).collect(Collectors.toList());
    }


    /**
     * 查询身份是推荐人 简章是代招单位的简章的面试时间 > 现在时间 登陆身份是推荐人
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:34 2019/9/6
     * @Param []
     **/
    @Scheduled(cron = "0 0 * * * ? ")
    @Transactional(rollbackFor = Exception.class)
    public void task15() {
        List<HrBriefchapter> list = briefchapterMapper.intervieweAllPersonReferrer();
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
                                String signUpName = t.getSignUpName() != null && t.getSignUpName() != "" ? t.getSignUpName() : "-1";
                                Integer type = t.getType();
                                if (nowTime.compareTo(format) > 0 && id.compareTo(briefchapterId) < 0 && userName.compareTo(signUpName) < 0 && type.compareTo(1) < 0) {
                                    Message message = new Message();
                                    message.setUserId(userId);
                                    message.setSignUpId(signUpId);
                                    message.setBriefchapterId(id);
                                    message.setCreateTime(LocalDateTime.now());
                                    message.setType(1);
                                    message.setSignUpName(userName);
                                    List<Message> messages = new ArrayList<>();
                                    messages.add(message);
                                    messageMapper.insertList(messages);
                                }
                                return t;

                            }).collect(Collectors.toList());

                    return dto;
                }).collect(Collectors.toList());


    }

    /**
     * 查询身份是推荐人 简章是招聘单位的简章的面试时间 > 现在时间 登陆身份是推荐人
     *
     * @return void
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 21:25 2019-09-06
     * @Param []
     */
    @Scheduled(cron = "0 0 * * * ? ")
    @Transactional(rollbackFor = Exception.class)
    public void task16() {
        List<HrBriefchapter> list = briefchapterMapper.interviewAllPersonReferrerRecruitment();
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
                                String signUpName = t.getSignUpName() != null && t.getSignUpName() != "" ? t.getSignUpName() : "-1";
                                Integer type = t.getType();
                                if (nowTime.compareTo(format) > 0 && id.compareTo(briefchapterId) < 0 && userName.compareTo(signUpName) < 0 && type.compareTo(1) < 0) {
                                    Message message = new Message();
                                    message.setUserId(userId);
                                    message.setSignUpId(signUpId);
                                    message.setBriefchapterId(id);
                                    message.setCreateTime(LocalDateTime.now());
                                    message.setType(1);
                                    message.setSignUpName(userName);
                                    List<Message> messages = new ArrayList<>();
                                    messages.add(message);
                                    messageMapper.insertList(messages);
                                }
                                return t;

                            }).collect(Collectors.toList());
                    return dto;
                }).collect(Collectors.toList());


    }

    /**
     * 查询身份是本人求职 简章是代招单位下的单位 的报道时间 > 现在时间
     *
     * @return void
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 09:14 2019-09-07
     * @Param []
     */
    @Scheduled(cron = "0 0 * * * ? ")
    @Transactional(rollbackFor = Exception.class)
    public void task17() {
        List<HrBriefchapter> list = briefchapterMapper.interviewAllPerson();
        list.stream()
                .map(dto -> {
                    Integer id = dto.getId();
                    Integer signUpId = dto.getSignUpId();
                    Integer userId = dto.getUserId();
                    Date registerTime = dto.getRegisterTime();
                    LocalDateTime localDateTime = DateUtil.convertToLocalDateTimeViaInstant(registerTime);
                    //获得面试那天0点的时间
                    LocalDateTime zeroPoint = LocalDateTime.of(LocalDate.from(localDateTime), LocalTime.MIN);
                    String format = zeroPoint.plusHours(-4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    LocalDateTime now = LocalDateTime.now();
                    String nowTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    List<Message> allList = messageMapper.selectAllList();
                    allList.stream()
                            .map(t -> {
                                Integer briefchapterId = t.getBriefchapterId() != null ? t.getBriefchapterId() : -1;
                                Integer userId1 = t.getUserId() != null ? t.getUserId() : -1;
                                Integer type = t.getType();
                                if (nowTime.compareTo(format) > 0 && id.compareTo(briefchapterId) < 0 && userId.compareTo(userId1) < 0 && type.compareTo(2) < 0) {
                                    Message message = new Message();
                                    message.setUserId(userId);
                                    message.setSignUpId(signUpId);
                                    message.setBriefchapterId(id);
                                    message.setCreateTime(LocalDateTime.now());
                                    message.setType(2);
                                    List<Message> messages = new ArrayList<>();
                                    messages.add(message);
                                    messageMapper.insertList(messages);
                                }
                                return t;
                            }).collect(Collectors.toList());
                    return dto;
                }).collect(Collectors.toList());
    }


    /**
     * 查询身份是本人求职 简章是招聘单位下的单位 的报道时间 > 现在时间
     *
     * @return void
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 09:22 2019-09-07
     * @Param []
     */
    @Scheduled(cron = "0 0 * * * ? ")
    @Transactional(rollbackFor = Exception.class)
    public void task18() {
        List<HrBriefchapter> list = briefchapterMapper.intervieweAllPersonReferrer();
        list.stream()
                .map(dto -> {
                    Integer id = dto.getId();
                    Integer signUpId = dto.getSignUpId();
                    Integer userId = dto.getUserId();
                    String userName = dto.getUserName();
                    Date registerTime = dto.getRegisterTime();
                    LocalDateTime localDateTime = DateUtil.convertToLocalDateTimeViaInstant(registerTime);
                    //获得面试那天0点的时间
                    LocalDateTime zeroPoint = LocalDateTime.of(LocalDate.from(localDateTime), LocalTime.MIN);
                    //得到昨天晚上8点
                    String format = zeroPoint.plusHours(-4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    //现在时间
                    LocalDateTime now = LocalDateTime.now();
                    String nowTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


                    List<Message> allList = messageMapper.selectAllList();
                    allList.stream()
                            .map(t -> {
                                Integer briefchapterId = t.getBriefchapterId() != null ? t.getBriefchapterId() : -1;
                                Integer userId1 = t.getUserId() != null ? t.getUserId() : -1;
                                Integer type = t.getType();
                                if (nowTime.compareTo(format) > 0 && id.compareTo(briefchapterId) < 0 && userId.compareTo(userId1) < 0 && type.compareTo(2) < 0) {
                                    Message message = new Message();
                                    message.setUserId(userId);
                                    message.setSignUpId(signUpId);
                                    message.setBriefchapterId(id);
                                    message.setCreateTime(LocalDateTime.now());
                                    message.setType(2);
                                    List<Message> messages = new ArrayList<>();
                                    messages.add(message);
                                    messageMapper.insertList(messages);
                                }
                                return t;
                            }).collect(Collectors.toList());
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * 查询身份是推荐人 简章是代招单位的简章的报道时间 > 现在时间 登陆身份是推荐人
     *
     * @return void
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 09:25 2019-09-07
     * @Param []
     */
    @Scheduled(cron = "0 0 * * * ? ")
    @Transactional(rollbackFor = Exception.class)
    public void task19() {

        List<HrBriefchapter> list = briefchapterMapper.intervieweAllPersonReferrer();
        list.stream()
                .map(dto -> {
                    Integer id = dto.getId();
                    Integer signUpId = dto.getSignUpId();
                    Integer userId = dto.getUserId();
                    String userName = dto.getUserName();
                    Date registerTime = dto.getRegisterTime();
                    LocalDateTime localDateTime = DateUtil.convertToLocalDateTimeViaInstant(registerTime);
                    //获得面试那天0点的时间
                    LocalDateTime zeroPoint = LocalDateTime.of(LocalDate.from(localDateTime), LocalTime.MIN);
                    String format = zeroPoint.plusHours(-4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    LocalDateTime now = LocalDateTime.now();
                    String nowTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    List<Message> allList = messageMapper.selectAllList();
                    allList.stream()
                            .map(t -> {
                                Integer briefchapterId = t.getBriefchapterId() != null ? t.getBriefchapterId() : -1;
                                String signUpName = t.getSignUpName() != null && t.getSignUpName() != "" ? t.getSignUpName() : "-1";
                                Integer type = t.getType();
                                if (nowTime.compareTo(format) > 0 && id.compareTo(briefchapterId) < 0 && userName.compareTo(signUpName) < 0 && type.compareTo(2) < 0) {
                                    Message message = new Message();
                                    message.setUserId(userId);
                                    message.setSignUpId(signUpId);
                                    message.setBriefchapterId(id);
                                    message.setCreateTime(LocalDateTime.now());
                                    message.setType(2);
                                    message.setSignUpName(userName);
                                    List<Message> messages = new ArrayList<>();
                                    messages.add(message);
                                    messageMapper.insertList(messages);
                                }
                                return t;
                            }).collect(Collectors.toList());
                    return dto;
                }).collect(Collectors.toList());

    }

    /**
     * 查询身份是推荐人 简章是招聘单位的简章的报道时间 > 现在时间 登陆身份是推荐人
     *
     * @return void
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 09:28 2019-09-07
     * @Param []
     */
    @Scheduled(cron = "0 0 * * * ? ")
    @Transactional(rollbackFor = Exception.class)
    public void task20() {
        List<HrBriefchapter> list = briefchapterMapper.interviewAllPersonReferrerRecruitment();
        list.stream()
                .map(dto -> {
                    Integer id = dto.getId();
                    Integer signUpId = dto.getSignUpId();
                    Integer userId = dto.getUserId();
                    Date registerTime = dto.getRegisterTime();
                    LocalDateTime localDateTime = DateUtil.convertToLocalDateTimeViaInstant(registerTime);
                    String userName = dto.getUserName();
                    //获得面试那天0点的时间
                    LocalDateTime zeroPoint = LocalDateTime.of(LocalDate.from(localDateTime), LocalTime.MIN);
                    String format = zeroPoint.plusHours(-4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    LocalDateTime now = LocalDateTime.now();
                    String nowTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    List<Message> allList = messageMapper.selectAllList();
                    allList.stream()
                            .map(t -> {
                                Integer briefchapterId = t.getBriefchapterId() != null ? t.getBriefchapterId() : -1;
                                String signUpName = t.getSignUpName() != null && t.getSignUpName() != "" ? t.getSignUpName() : "-1";
                                Integer type = t.getType();
                                if (nowTime.compareTo(format) > 0 && id.compareTo(briefchapterId) < 0 && userName.compareTo(signUpName) < 0 && type.compareTo(2) < 0) {
                                    Message message = new Message();
                                    message.setUserId(userId);
                                    message.setSignUpId(signUpId);
                                    message.setBriefchapterId(id);
                                    message.setCreateTime(LocalDateTime.now());
                                    message.setType(2);
                                    message.setSignUpName(userName);
                                    List<Message> messages = new ArrayList<>();
                                    messages.add(message);
                                    messageMapper.insertList(messages);
                                }
                                return t;
                            }).collect(Collectors.toList());
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * 完全直录：报道当天00：00视为拒绝报道
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:08 2019/9/20
     * @Param []
     **/
    @Scheduled(cron = "0 0 * * * ? ")
    @Transactional(rollbackFor = Exception.class)
    public void task21() {
        List<TaskHireWayDto> taskHireWayDtos = mapper.fullyDirectRecording();
        taskHireWayDtos.stream()
                .map(dto -> {
                    //得到报道时间
                    LocalDateTime time = dto.getRegisterTime();
                    Integer id = dto.getHsdId();
                    //获得报道那天0点的时间
                    LocalDateTime zeroPoint = LocalDateTime.of(LocalDate.from(time), LocalTime.MIN);
                    //格式化那个时间
                    String format = zeroPoint.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    LocalDateTime now = LocalDateTime.now();
                    String s = StringUtil.localdatetimeToStr(now);
                    if (s.compareTo(format) > 0) {
                        signupDeliveryrecordMapper.fullyDirectRecordingNoreport(id);
                    }
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * 不可直录：面试当天00：00视为拒绝报道
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:08 2019/9/20
     * @Param []
     **/
    @Scheduled(cron = "0 0 * * * ? ")
    @Transactional(rollbackFor = Exception.class)
    public void task22() {
        List<TaskHireWayDto> taskHireWayDtos = mapper.notDirectlyRecorded();
        taskHireWayDtos.stream()
                .map(dto -> {
                    //得到面试时间
                    LocalDateTime time = dto.getInterviewTime();
                    Integer id = dto.getHsdId();
                    //获得面试那天0点的时间
                    LocalDateTime zeroPoint = LocalDateTime.of(LocalDate.from(time), LocalTime.MIN);
                    //格式化那个时间
                    String format = zeroPoint.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    LocalDateTime now = LocalDateTime.now();
                    String s = StringUtil.localdatetimeToStr(now);
                    if (s.compareTo(format) > 0) {
                        signupDeliveryrecordMapper.notDirectlyRecorded(id);
                    }
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * 可以直录：面试当天00：00视为拒绝报道
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:08 2019/9/20
     * @Param []
     **/
    @Scheduled(cron = "0 0 * * * ? ")
    @Transactional(rollbackFor = Exception.class)
    public void task23() {
        List<TaskHireWayDto> taskHireWayDtos = mapper.canRecordDirectly();
        taskHireWayDtos.stream()
                .map(dto -> {
                    //得到报道时间
                    LocalDateTime time = dto.getInterviewTime();
                    Integer id = dto.getHsdId();
                    //获得报道那天0点的时间
                    LocalDateTime zeroPoint = LocalDateTime.of(LocalDate.from(time), LocalTime.MIN);
                    //格式化那个时间
                    String format = zeroPoint.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    LocalDateTime now = LocalDateTime.now();
                    String s = StringUtil.localdatetimeToStr(now);
                    if (s.compareTo(format) > 0) {
                        signupDeliveryrecordMapper.canRecordDirectly(id);
                    }
                    return dto;
                }).collect(Collectors.toList());
    }

}
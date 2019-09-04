package com.nado.rlzy.service.impl;

import cn.hutool.core.lang.Assert;
import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.bean.query.EditBriefchapterQuery;
import com.nado.rlzy.bean.query.RebateQuery;
import com.nado.rlzy.bean.query.ReleaseBriefcharpterQuery;
import com.nado.rlzy.db.mapper.*;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.MyReleaseService;
import com.nado.rlzy.utils.CollectorsUtil;
import com.nado.rlzy.utils.StringUtil;
import com.nado.rlzy.utils.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName 我的发布 service
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/22 13:41
 * @Version 1.0
 */
@Service
public class MyReleaseServiceImpl implements MyReleaseService {

    @Resource
    private HrBriefchapterMapper mapper;

    @Resource
    private ProvinceMapper provinceMapper;

    @Resource
    private HrRebaterecordMapper rebaterecordMapper;

    @Resource
    private HrSignUpMapper signUpMapper;

    @Resource
    private HrAcctMapper acctMapper;
    @Resource
    private HrAcctDetailMapper acctDetailMapper;

    @Resource
    private HrDictionaryItemMapper dictionaryItemMapper;

    @Resource
    private HrGroupMapper groupMapper;

    @Resource
    private HrUserMapper userMapper;

    @Resource
    private ViolationRecordMapper violationRecordMapper;

    @Resource
    private EntryResignationMapper resignationMapper;

    @Resource
    private HrSignupDeliveryrecordMapper signupDeliveryrecordMapper;

    @Override
    public Map<String, Object> myRelease(Integer userId, Integer status) {
        List<HrBriefchapter> list = mapper.myRelease(userId, status);
        List<HrBriefchapter> hrBriefchapters = mapper.myReleaseRecruitment(userId, status);
        List<HrBriefchapter> collect = list.stream().map(dto -> {
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            System.out.println(map);

            BigDecimal rebateMaleInterview = dto.getRebateMaleInterview();
            BigDecimal rebateMaleReport = dto.getRebateMaleReport();
            BigDecimal rebateMaleEntry = dto.getRebateMaleEntry();
            BigDecimal rebateFemaleInterview = dto.getRebateFemaleInterview();
            BigDecimal rebateFemaleReport = dto.getRebateFemaleReport();
            BigDecimal rebateFemaleEntry = dto.getRebateFemaleEntry();
            if (null != rebateMaleInterview && null != rebateMaleReport && null != rebateMaleEntry &&
                    null != rebateFemaleInterview && null != rebateFemaleReport && null != rebateFemaleEntry) {
                dto.setRebateMaleInterview1("返" + rebateMaleInterview + "元");
                dto.setRebateMaleReport1("返" + rebateMaleReport + "元");
                dto.setRebateFemaleInterview1("返" + rebateMaleEntry + "元");
                dto.setRebateFemaleReport1("返" + rebateFemaleReport + "元");
                //入职返佣的信息
                dto.setRebateEntryResignation1(dto.getRebateEntryResignation1());
            }
            return dto;
        }).collect(Collectors.toList());
        List<HrBriefchapter> briefchapterList = hrBriefchapters.stream()
                .map(dto -> {
                    Map<Integer, BigDecimal> map = dto.getRebat().stream()
                            .collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    System.out.println(map);

                    BigDecimal rebateMaleInterview = dto.getRebateMaleInterview();
                    BigDecimal rebateMaleReport = dto.getRebateMaleReport();
                    BigDecimal rebateMaleEntry = dto.getRebateMaleEntry();
                    BigDecimal rebateFemaleInterview = dto.getRebateFemaleInterview();
                    BigDecimal rebateFemaleReport = dto.getRebateFemaleReport();
                    BigDecimal rebateFemaleEntry = dto.getRebateFemaleEntry();
                    if (null != rebateMaleInterview && null != rebateMaleReport && null != rebateMaleEntry &&
                            null != rebateFemaleInterview && null != rebateFemaleReport && null != rebateFemaleEntry) {
                        dto.setRebateMaleInterview1("返" + rebateMaleInterview + "元");
                        dto.setRebateMaleReport1("返" + rebateMaleReport + "元");
                        dto.setRebateFemaleInterview1("返" + rebateMaleEntry + "元");
                        dto.setRebateFemaleReport1("返" + rebateFemaleReport + "元");
                        //入职返佣的信息
                        dto.setRebateEntryResignation1(dto.getRebateEntryResignation1());
                    }
                    return dto;

                })
                .collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("myRelease", collect);
        map.put("myReleaseRecruitment", briefchapterList);
        return map;

    }


    @Override
    public List<Province> getPCA() {
        return provinceMapper.getPCA();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(ReleaseBriefcharpterQuery query, Integer type) {
        //招聘单位
        if (type.equals(5)) {
            //有返佣
            if (query.getRebateStatus().equals(1)) {
                //简章表
                int brId = initBriefcharpterRebate(query);
                //入职返佣表
                initEntryResignation(query, brId);
                //返佣记录表
                initRebate(query, brId);

            } else {
                //不返佣
                //简章表初始化
                initBriefcharpter(query);
            }
            //代招单位
        } else if (type.equals(6)) {

            if (query.getRebateStatus().equals(1)) {
                //返佣
                //简章表初始化
                int brId = initBriefcharpterRebate(query);
                //入职返佣表
                initEntryResignation(query, brId);
                //入职返佣表
                initRebate(query, brId);

            } else {
                //不返佣
                //简章表初始化
                initBriefcharpter(query);
            }
        }

    }

    private void initRebate(ReleaseBriefcharpterQuery query, int brId) {
        HrRebaterecord rebaterecord = new HrRebaterecord();
        List<EntryResignation> entry = query.getRebateEntry();
        entry.stream()
                .map(dto -> {
                    rebaterecord.setBriefchapterId(brId);
                    rebaterecord.setCreateTime(new Date());
                    rebaterecord.setRebateTime(dto.getRebateTime());
                    rebaterecord.setRebateMale(dto.getRebateMaleEntry());
                    rebaterecord.setRebateFemale(dto.getRebateFemaleEntry());
                    return dto;
                }).collect(Collectors.toList());
        ArrayList<HrRebaterecord> rebaterecords = new ArrayList<>();
        rebaterecords.add(rebaterecord);
        //批量添加入职返佣
        rebaterecordMapper.insertListt(rebaterecords);
    }

    private void initEntryResignation(ReleaseBriefcharpterQuery query, int brId) {
        if (query.getRebateEntry() != null) {
            //如果 入职返佣不为空 添加记录
            List<EntryResignation> rebateEntry = query.getRebateEntry();
            if (rebateEntry.size() > 0 && query.getRebType().equals(2)) {
                rebateEntry.stream()
                        .map(dto -> {
                            dto.setBriefChapterId(brId);
                            dto.setRebateTime(new Date());
                            dto.setCreateTime(new Date());
                            dto.setType(2);
                            return dto;
                        }).collect(Collectors.toList());
                resignationMapper.insertList(rebateEntry);
            }
        }
        //简章返佣表添加面试返佣

        if (query.getRebType().equals(0)) {
            //面试
            EntryResignation resignation = new EntryResignation();
            resignation.setRebateMaleEntry(query.getRebateMaleInterview());
            resignation.setRebateFemaleEntry(query.getRebateFemaleInterview());
            resignation.setType(0);
            resignation.setBriefChapterId(brId);
            resignationMapper.insertSelective(resignation);
        } else if (query.getRebType().equals(1)) {
            //报道
            EntryResignation resignation1 = new EntryResignation();
            resignation1.setRebateMaleEntry(query.getRebateMaleInterview());
            resignation1.setRebateMaleEntry(query.getRebateMaleReport());
            resignation1.setRebateFemaleEntry(query.getRebateFemaleReport());
            resignation1.setType(1);
            resignation1.setBriefChapterId(brId);
            resignationMapper.insertSelective(resignation1);


        }

    }

    @Override
    public List<HrSignUp> recruitmentDetailsOverview(Integer[] jobStatus) {
        //把数组转换成list
        List<Integer> list = Stream.of(jobStatus).collect(Collectors.toList());
        List<HrSignUp> signUps = signUpMapper.recruitmentDetailsOverview(list);
        //用stream 处理list 集合
        List<HrSignUp> collect = signUps.stream().map(dto -> {
            //如果状态是待面试 进入这个
            signUps.stream().filter(s -> s.equals(1)).map(inteview -> {
                //待面试
                Date time = inteview.getInterviewTime();

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //面试时间
                String toStr = StringUtil.DateToStr(time);
                //现在时间
                String nowTime = format.format(new Date());
                //现在时间 > 面试时间 返回 -1 前台提示 '面试时间已过，请确认是否面试，当天20：00前不确认默认已面试'
                if (nowTime.compareTo(toStr) > 0) {
                    inteview.setInterviewTimeFlag(-1);
                }
                return dto;
            }).collect(Collectors.toList());

            //待报道
            signUps.stream().filter(t -> t.equals(2)).map(report -> {
                Date registerTime = report.getRegisterTime();
                //报到时间
                String date = StringUtil.DateToStr(registerTime);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //现在时间
                String nowTime = format.format(new Date());
                if (nowTime.compareTo(date) > 0) {
                    report.setReportTimeFlag(-1);
                }
                return report;
            }).collect(Collectors.toList());

            //待返佣

            return dto;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int reportNotSuitable(Integer signUpId, Integer briefChapterId) {
        return signUpMapper.reportNotSuitable(signUpId, briefChapterId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recruitmentDetailsInvitationInterview(Integer signUpId, Integer briefChapterId) {
        ValidationUtil.ValidResult result = ValidationUtil.validateBean(signUpId);
        return signUpMapper.recruitmentDetailsInvitationInterview(signUpId, briefChapterId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recruitmentDetailsDirectAdmission(Integer signUpId, Integer briefChapterId) {
        return signUpMapper.recruitmentDetailsDirectAdmission(signUpId, briefChapterId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recruitmentInterviewd(Integer signUpId, Integer briefChapterId) {
        //改变求职状态为 已面试
        return signUpMapper.recruitmentInterviewd(signUpId, briefChapterId);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> recruitmentNoInterviewd(Integer signUpId, Integer userId, Integer briefChapterId) {
        Map<String, Object> map = new HashMap<>();
        //未面试
        int i = signUpMapper.recruitmentNoInterviewd(signUpId, briefChapterId);
        map.put("recruitmentNoInterviewd", i);
        //如果连续三次没有面试 停权6个月
        List<HrSignUp> hrSignUps = signUpMapper.threeNoInterview(signUpId);
        final boolean[] flag = {false};
        hrSignUps.stream().map(dto -> {
            Integer jobStatus = dto.getJobStatus();
            if (jobStatus != 8) {
                flag[0] = flag[0];
            } else {
                flag[0] = true;
            }
            return dto;
        }).collect(Collectors.toList());
        if (flag[0] = true) {
            //连续三次没有面试 停权推荐人 | 本人
            HrUser hrUser = new HrUser();
            hrUser.setViolationTime(LocalDateTime.now());
            userMapper.updateViolationFlag(userId);
            //返回违规转态到前台
            List<HrUser> hrUsers = userMapper.queryVilolationFlag(userId);
            map.put("queryVilolationFlag", hrUsers);

        }
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recruitmentInterviewFailed(Integer signUpId, Integer briefChapterId) {
        return signUpMapper.recruitmentInterviewFailed(signUpId, briefChapterId);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recruitmentInterviewSuccess(Integer signUpId, Integer briefChapterId, Integer sex, Integer signUpUserId,
                                           Integer busInessUserId, Integer signupDeliveryrecordId) {
        //改变求职状态
        signUpMapper.recruitmentInterviewSuccess(signUpId, briefChapterId);
        // 查询简章表 面试返佣的钱
        List<HrBriefchapter> hrBriefchapters = mapper.interviewRebateOrReportRebate(briefChapterId);
        hrBriefchapters.stream()
                .map(dto -> {
                    BigDecimal maleInterview = dto.getRebateMaleInterview();
                    BigDecimal femaleInterview = dto.getRebateFemaleInterview();
                    System.out.println(maleInterview);
                    System.out.println(femaleInterview);
                    Integer rebate = dto.getRebate();

                    //有返佣才进来
                    if (rebate.equals(1)) {
                        if (sex.equals(0)) {
                            //女
                            HrRebaterecord rebaterecord = new HrRebaterecord();
                            rebaterecord.setRebateFemale(femaleInterview);
                            rebaterecord.setBriefchapterId(briefChapterId);
                            rebaterecord.setRebateTime(new Date());
                            rebaterecord.setCreateTime(new Date());
                            rebaterecord.setSignupDeliveryrecordId(signupDeliveryrecordId);
                            rebaterecord.setRebateType(0);
                            rebaterecord.setStatus(1);
                            List<HrRebaterecord> hrRebaterecords = new ArrayList<>();
                            hrRebaterecords.add(rebaterecord);
                            rebaterecordMapper.insertListt(hrRebaterecords);
                            //钱从企业冻结金额到求职者 或者推荐人余额 这里是求职者或者推荐人钱增加
                            acctMapper.rebateUser(femaleInterview, signUpUserId);
                            //企业冻结金额减少 消费金额增加
                            acctMapper.rebateBusiness(femaleInterview, busInessUserId);
                            //acctDetail 增加数据 账户余额增加
                            //查询账户id, 账户余额
                            HrAcct hrAcct = acctMapper.selectAcctIdByUserId(signUpUserId);
                            //账户id
                            Integer acctId = hrAcct.getId();
                            //账户余额
                            BigDecimal acctbalance = hrAcct.getAcctbalance();
                            //账户详情
                            HrAcctDetail detail = new HrAcctDetail();
                            detail.setAcctid(acctId);
                            detail.setAmount(femaleInterview);
                            detail.setBeforeamount(acctbalance);
                            detail.setAfteramount(femaleInterview.add(acctbalance));
                            detail.setCreatetime(LocalDateTime.now());
                            detail.setStatus(1);
                            detail.setType(2);
                            detail.setBriefchapterid(briefChapterId);
                            //增加账户详情
                            acctDetailMapper.insertSelective(detail);
                            // 报名表投递表  待返佣金额减少 面试返佣状态改变
                            HrSignupDeliveryrecord deliveryrecord = new HrSignupDeliveryrecord();
                            deliveryrecord.setAcceptRebateAmount(femaleInterview);
                            deliveryrecord.setSignupId(signUpId);
                            deliveryrecord.setBriefChapterId(briefChapterId);
                            signupDeliveryrecordMapper.reducedRebateAmount(deliveryrecord);

                        } else if (sex.equals(1)) {
                            //男
                            HrRebaterecord rebaterecord = new HrRebaterecord();
                            rebaterecord.setRebateMale(maleInterview);
                            rebaterecord.setBriefchapterId(briefChapterId);
                            rebaterecord.setRebateTime(new Date());
                            rebaterecord.setCreateTime(new Date());
                            rebaterecord.setSignupDeliveryrecordId(signupDeliveryrecordId);
                            rebaterecord.setRebateType(0);
                            rebaterecord.setStatus(1);
                            List<HrRebaterecord> hrRebaterecords = new ArrayList<>();
                            hrRebaterecords.add(rebaterecord);
                            //面试返佣 到返佣记录表
                            rebaterecordMapper.insertListt(hrRebaterecords);
                            //钱从企业冻结金额到求职者 或者推荐人余额 这里是求职者或者推荐人钱增加
                            acctMapper.rebateUser(maleInterview, signUpUserId);
                            //企业冻结金额减少 消费金额增加
                            acctMapper.rebateBusiness(maleInterview, busInessUserId);

                            //acctDetail 增加数据 账户余额增加
                            //查询账户id, 账户余额
                            HrAcct hrAcct = acctMapper.selectAcctIdByUserId(signUpUserId);
                            //账户id
                            Integer acctId = hrAcct.getId();
                            //账户余额
                            BigDecimal acctbalance = hrAcct.getAcctbalance();
                            //账户详情
                            HrAcctDetail detail = new HrAcctDetail();
                            detail.setAcctid(acctId);
                            detail.setAmount(maleInterview);
                            detail.setBeforeamount(acctbalance);
                            detail.setAfteramount(maleInterview.add(acctbalance));
                            detail.setCreatetime(LocalDateTime.now());
                            detail.setStatus(1);
                            detail.setType(2);
                            detail.setBriefchapterid(briefChapterId);
                            //增加账户详情
                            acctDetailMapper.insertSelective(detail);
                            // 报名表投递表  待返佣金额减少 面试返佣状态改变
                            HrSignupDeliveryrecord deliveryrecord = new HrSignupDeliveryrecord();
                            deliveryrecord.setAcceptRebateAmount(femaleInterview);
                            deliveryrecord.setSignupId(signUpId);
                            deliveryrecord.setBriefChapterId(briefChapterId);
                            signupDeliveryrecordMapper.reducedRebateAmount(deliveryrecord);
                        } else {
                            Assert.isFalse((!sex.equals(0) || !sex.equals(1)), "面试返佣失败");
                        }
                    }
                    return dto;
                }).collect(Collectors.toList());
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> notReported(Integer signUpId, Integer briefChapterId, Integer userId) {
        Map<String, Object> map = new HashMap<>();
        int i = signUpMapper.notReported(signUpId, briefChapterId);
        map.put("notReported", i);
        //如果连续三次没有报道 停权6个月
        List<HrSignUp> hrSignUps = signUpMapper.threeNoInterview(signUpId);
        final boolean[] flag = {false};
        hrSignUps.stream().map(dto -> {
            Integer jobStatus = dto.getJobStatus();
            if (jobStatus != 9) {
                flag[0] = flag[0];
            } else {
                flag[0] = true;
            }
            return dto;
        }).collect(Collectors.toList());
        if (flag[0] = true) {
            //连续三次没有报道 停权推荐人 | 本人
            HrUser hrUser = new HrUser();
            hrUser.setViolationTime(LocalDateTime.now());
            userMapper.updateViolationFlag(userId);
            //返回违规转态到前台
            List<HrUser> hrUsers = userMapper.queryVilolationFlag(userId);
            map.put("queryVilolationFlag", hrUsers);

        }
        return map;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int reported(Integer signUpId, Integer briefChapterId, Integer sex,
                        Integer signUpUserId, Integer busInessUserId, Integer signupDeliveryrecordId) {

        signUpMapper.reported(signUpId, briefChapterId);


        // 查询简章表 面试返佣的钱
        List<HrBriefchapter> hrBriefchapters = mapper.interviewRebateOrReportRebate(briefChapterId);
        hrBriefchapters.stream()
                .map(dto -> {
                    BigDecimal maleReport = dto.getRebateMaleReport();
                    BigDecimal femaleReport = dto.getRebateFemaleReport();
                    Integer rebate = dto.getRebate();
                    //是否有返佣 有进去
                    if (rebate.equals(1)) {
                        if (sex.equals(0)) {
                            //女
                            HrRebaterecord rebaterecord = new HrRebaterecord();
                            rebaterecord.setRebateFemale(femaleReport);
                            rebaterecord.setBriefchapterId(briefChapterId);
                            rebaterecord.setRebateTime(new Date());
                            rebaterecord.setCreateTime(new Date());
                            rebaterecord.setSignupDeliveryrecordId(signupDeliveryrecordId);
                            rebaterecord.setRebateType(1);
                            rebaterecord.setStatus(1);
                            List<HrRebaterecord> hrRebaterecords = new ArrayList<>();
                            hrRebaterecords.add(rebaterecord);
                            rebaterecordMapper.insertListt(hrRebaterecords);
                            //钱从企业冻结金额到求职者 或者推荐人余额 这里是求职者或者推荐人钱增加
                            acctMapper.rebateUser(femaleReport, signUpUserId);
                            //企业冻结金额减少 消费金额增加
                            acctMapper.rebateBusiness(femaleReport, busInessUserId);
                            //acctDetail 增加数据 账户余额增加
                            //查询账户id, 账户余额
                            HrAcct hrAcct = acctMapper.selectAcctIdByUserId(signUpUserId);
                            //账户id
                            Integer acctId = hrAcct.getId();
                            //账户余额
                            BigDecimal acctbalance = hrAcct.getAcctbalance();
                            //账户详情
                            HrAcctDetail detail = new HrAcctDetail();
                            detail.setAcctid(acctId);
                            detail.setAmount(femaleReport);
                            detail.setBeforeamount(acctbalance);
                            detail.setAfteramount(femaleReport.add(acctbalance));
                            detail.setCreatetime(LocalDateTime.now());
                            detail.setStatus(1);
                            detail.setType(2);
                            detail.setBriefchapterid(briefChapterId);
                            //增加账户详情
                            acctDetailMapper.insertSelective(detail);
                            // 报名表投递表  待返佣金额减少 面试返佣状态改变
                            HrSignupDeliveryrecord deliveryrecord = new HrSignupDeliveryrecord();
                            deliveryrecord.setAcceptRebateAmount(femaleReport);
                            deliveryrecord.setSignupId(signUpId);
                            deliveryrecord.setBriefChapterId(briefChapterId);
                            signupDeliveryrecordMapper.reducedRebateAmount(deliveryrecord);
                        } else if (sex.equals(1)) {
                            //男
                            HrRebaterecord rebaterecord = new HrRebaterecord();
                            rebaterecord.setRebateMale(maleReport);
                            rebaterecord.setBriefchapterId(briefChapterId);
                            rebaterecord.setRebateTime(new Date());
                            rebaterecord.setCreateTime(new Date());
                            rebaterecord.setSignupDeliveryrecordId(signupDeliveryrecordId);
                            rebaterecord.setRebateType(1);
                            rebaterecord.setStatus(1);
                            List<HrRebaterecord> hrRebaterecords = new ArrayList<>();
                            hrRebaterecords.add(rebaterecord);
                            //报道返佣
                            rebaterecordMapper.insertListt(hrRebaterecords);
                            //钱从企业冻结金额到求职者 或者推荐人余额 这里是求职者或者推荐人钱增加
                            acctMapper.rebateUser(maleReport, signUpUserId);
                            //企业冻结金额减少 消费金额增加
                            acctMapper.rebateBusiness(maleReport, busInessUserId);
                            //acctDetail 增加数据 账户余额增加
                            //查询账户id, 账户余额
                            HrAcct hrAcct = acctMapper.selectAcctIdByUserId(signUpUserId);
                            //账户id
                            Integer acctId = hrAcct.getId();
                            //账户余额
                            BigDecimal acctbalance = hrAcct.getAcctbalance() != null ? hrAcct.getAcctbalance() : BigDecimal.ZERO;
                            //账户详情
                            HrAcctDetail detail = new HrAcctDetail();
                            detail.setAcctid(acctId);
                            detail.setAmount(maleReport);
                            detail.setBeforeamount(acctbalance);
                            detail.setAfteramount(maleReport.add(acctbalance));
                            detail.setCreatetime(LocalDateTime.now());
                            detail.setStatus(1);
                            detail.setType(2);
                            detail.setBriefchapterid(briefChapterId);
                            //增加账户余额
                            acctDetailMapper.insertSelective(detail);
                            // 报名表投递表  待返佣金额减少 面试返佣状态改变
                            HrSignupDeliveryrecord deliveryrecord = new HrSignupDeliveryrecord();
                            deliveryrecord.setAcceptRebateAmount(maleReport);
                            deliveryrecord.setSignupId(signUpId);
                            deliveryrecord.setBriefChapterId(briefChapterId);
                            signupDeliveryrecordMapper.reducedRebateAmount(deliveryrecord);
                        } else {
                            Assert.isFalse((!sex.equals(0) || !sex.equals(1)), "面试返佣失败");
                        }
                    }
                    return dto;
                }).collect(Collectors.toList());
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int noReportedReason(Integer reason, Integer signUpId, Integer briefChapterId) {
        int noReported = signUpMapper.noReportedReason(reason, signUpId, briefChapterId);
        return noReported >= 1 ? 1 : 0;
    }

    @Override
    public List<HrRebaterecord> rebatee(Integer signUpId, Integer briefchapterId, Integer sex) {
        List<HrRebaterecord> sign = rebaterecordMapper.rebate(signUpId, briefchapterId);
        sign.stream().map(rebat -> {
            if (sex.equals(0)) {
                BigDecimal female = rebat.getRebateFemale() != null ? rebat.getRebateFemale() : BigDecimal.ZERO;
                rebat.setRebateMon("金额: ¥" + female);
                rebat.setRebateFemale(BigDecimal.valueOf(0));
                rebat.setRebateMale(BigDecimal.valueOf(0));
            }
            if (sex.equals(1)) {
                BigDecimal male = rebat.getRebateMale() != null ? rebat.getRebateMale() : BigDecimal.ZERO;
                rebat.setRebateMon("金额: ¥" + male);
                rebat.setRebateFemale(BigDecimal.valueOf(0));
                rebat.setRebateMale(BigDecimal.valueOf(0));
            }
            return rebat;
        }).collect(Collectors.toList());
        return sign;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int noRebate(RebateQuery query) {
        List<HrRebaterecord> sign = rebaterecordMapper.rebate(query.getSignUpId(), query.getBriefchapterId());
        sign.stream()
                .map(dto -> {
                    //返佣表id前台传过来 id在查询返佣记录时传给前台了
                    rebaterecordMapper.noRebate(query.getId());
                    return dto;
                }).collect(Collectors.toList());
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int rebate(RebateQuery query) {
        // 查询返佣详情
        List<HrRebaterecord> sign = rebaterecordMapper.rebate(query.getSignUpId(), query.getBriefchapterId());
        sign.stream()
                .map(dto -> {
                    if (query.getRebateType().equals(0)) {
                        //不返佣
                        rebaterecordMapper.noRebate(query.getId());
                    } else {
                        //更改返佣状态
                        rebaterecordMapper.rebateOne(query.getRebateId(), dto.getHsdId());
                        String rebateMon = query.getRebateMon();
                        String substring = rebateMon.substring(5, 8);
                        //返佣金额
                        BigDecimal rebateMoney = StringUtil.decimal(substring) != null ? StringUtil.decimal(substring) : BigDecimal.ZERO;
                        //钱从企业冻结金额到求职者 或者推荐人余额 这里是求职者或者推荐人钱增加
                        acctMapper.rebateUser(rebateMoney, query.getUserId());
                        //企业冻结金额减少 消费金额增加
                        acctMapper.rebateBusiness(rebateMoney, query.getBusInessUserId());
                        //acctDetail 增加数据 账户余额增加
                        //查询账户id, 账户余额
                        HrAcct hrAcct = acctMapper.selectAcctIdByUserId(query.getUserId());
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
                        detail.setBriefchapterid(query.getBriefchapterId());
                        //增加账户余额
                        acctDetailMapper.insertSelective(detail);
                    }
                    return dto;
                }).collect(Collectors.toList());
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeJobStatus(Integer signUpId, Integer status, Integer currentState, Integer briefChapterId) {
        Integer signUpStatus = 4;
        //把integer 转 list
        List<Integer> list = Stream.of(signUpStatus).collect(Collectors.toList());
        List<HrSignUp> dtos = signUpMapper.recruitmentDetailsOverview(list);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //现在时间
        String nowTime = format.format(new Date());

        // 8 未面试 3 面试未通过
        if (currentState.equals(8) || currentState.equals(3)) {

            dtos.stream()
                    .map(u -> {
                        //报道时间
                        String registerTime = format.format(u.getRegisterTime());
                        //报道时间 > 现在时间
                        if (registerTime.compareTo(nowTime) > 0) {
                            signUpMapper.changeJobStatus(signUpId, status, briefChapterId);

                            //报到时间 < 现在时间
                        } else if (registerTime.compareTo(nowTime) < 0) {
                            signUpMapper.changeJobStatus(signUpId, status, briefChapterId);
                        }
                        return u;
                    }).collect(Collectors.toList());
        }
        //未报到
        if (currentState.equals(2)) {
            signUpMapper.changeJobStatus(signUpId, status, briefChapterId);
        }
        //返佣中断, 未返佣
        if (currentState.equals(4)) {
            signUpMapper.commissionRebate(signUpId);
            signUpMapper.changeJobStatus(signUpId, status, briefChapterId);
        }

        return 1;
    }

    @Override
    public List<HrBriefchapter> numberOfRecruitsFull(Integer briefchapter) {

        return mapper.queryRecruitingNo(briefchapter)
                .stream()
                .map(dto -> {
                    //如果招聘人数为0  说明报名满了
                    Integer recruitingNo = dto.getRecruitingNo();
                    if (recruitingNo <= 0) {
                        dto.setRecruitingNo(0);
                    }
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int invitationToRegister(Integer signUpId) {
        return signUpMapper.invitationToRegister(signUpId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int notSuitable(Integer signUpId) {
        return signUpMapper.notSuitable(signUpId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int invitationInterview(Integer signUpId) {
        return signUpMapper.invitationInterview(signUpId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int directAdmission(Integer signUpId, Integer userId, Integer sex, Integer briefchapter) {

        // 招聘端 直接录取 查询面试的返费金额
        HrSignUp hrSignUp = signUpMapper.SearchdirectAdmission(signUpId, sex, briefchapter);
        //查询账户id hracct 表
        HrAcct hrAcct = acctMapper.selectAcctIdByUserId(userId);

        Integer acctId = hrAcct.getId();
        //得到面试时间
        Date interviewTime = hrSignUp.getInterviewTime();
        System.out.println(interviewTime);
        String date = StringUtil.DateToStr(interviewTime);

        //格式化时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(new Date());
        //得到面试返佣金额
        BigDecimal value = hrSignUp.getValue();
        //现在时间 < 面试时间
        int i = format.compareTo(date);
        if (i < 0) {
            Assert.isFalse(acctMapper.directAdmission(userId, value) < 1, RlzyConstant.OPS_FAILED_MSG);
            HrAcctDetail detail = new HrAcctDetail();
            detail.setAcctid(acctId);
            detail.setAmount(value);
            detail.setSignupid(signUpId);
            detail.setCreatetime(LocalDateTime.now());
            Assert.isFalse(acctDetailMapper.accountDetailTableAddsReferrerRebate(detail) < 1, RlzyConstant.OPS_FAILED_MSG);
        }
        return 1;
    }

    @Override
    public List<HrDictionaryItem> selectContentByType(String type) {

        // 创建Example
        Example example = new Example(HrDictionaryItem.class);
        // 创建Criteria
        Example.Criteria criteria = example.createCriteria();
        // 添加条件
        criteria.andLike("pid", type);
        List<HrDictionaryItem> items = dictionaryItemMapper.selectByExample(example);
        List<HrDictionaryItem> collect = items.stream().collect(Collectors.toList());
        System.out.println(collect);
        return collect;
    }

/*
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int editBriefchapterFail(ReleaseBriefcharpterQuery query) {
        int num = query.getManNum() + query.getWomenNum();
        query.setRecruitingNo(num);
        Integer update = mapper.update(query);
        //修改返佣
        HrRebaterecord hrRebaterecord = new HrRebaterecord();
        ArrayList<HrRebaterecord> list = new ArrayList<>();
        List<ReleaseBriefcharpterQuery> queries = new ArrayList<>();
        for (ReleaseBriefcharpterQuery briefcharpterQuery : queries) {
            String rebateMale = briefcharpterQuery.getRebateMale();
            if (!StringUtils.isBlank(rebateMale)) {
                BigDecimal decimal = StringUtil.decimal(rebateMale);
                hrRebaterecord.setRebateMale(decimal);
            }
            String rebateFemale = briefcharpterQuery.getRebateFemale();
            BigDecimal decimal1 = StringUtil.decimal(rebateFemale);
            Assert.isFalse(null != decimal1, "女生返佣不能为空");
            hrRebaterecord.setRebateFemale(decimal1);
            //返佣时间
            list.add(hrRebaterecord);
        }
        int i = rebaterecordMapper.updateBatch(list);
        return i >= 1 && update >= 1 ? 1 : 0;
    }*/

    @Override
    public List<EntryResignation> selectEntryRebate(Integer briefchapterId) {
        List<EntryResignation> list = resignationMapper.selectEntryRebate(briefchapterId);
        return list;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer editBriefchapterMyRelease(EditBriefchapterQuery query) {
        BriefcharpterQuery briefcharpterQuery = new BriefcharpterQuery();
        briefcharpterQuery.setBriefcharpterId(query.getBriefchapter());
        //代招单位
        List<HrBriefchapter> list = mapper.queryBriefcharpterDetileByParams(briefcharpterQuery);

        //无返佣
        if (query.getRebate().equals(0)) {
            if (list.size() > 0) {
                // 有返佣到无返佣 钱从用户的冻结金额到账户余额
                list.stream()
                        .map(dto -> {
                            //入职返佣金额男 简章对应的多有返佣金额
                            Map<Integer, BigDecimal> rebateMaleEntry = dto.getRebateEntryResignation()
                                    .stream()
                                    .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                            CollectorsUtil.summingBigDecimal(EntryResignation::getRebateMaleEntry)));
                            //入职返佣金额女 简章对应的多有返佣金额
                            Map<Integer, BigDecimal> rebateFemaleEntry = dto.getRebateEntryResignation().stream()
                                    .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                            CollectorsUtil.summingBigDecimal(EntryResignation::getRebateFemaleEntry)));
                            //声明
                            final BigDecimal[] maleEntry = {null};
                            final BigDecimal[] femaleEntry = {null};
                            rebateFemaleEntry.forEach((k, v) -> {
                                maleEntry[0] = v;
                            });
                            rebateMaleEntry.forEach((k, v) -> {
                                femaleEntry[0] = v;
                            });
                            BigDecimal maleEntryTernaryOperator = maleEntry[0] != null ? maleEntry[0] : BigDecimal.ZERO;
                            BigDecimal femaleEntryTernaryOperator = femaleEntry[0] != null ? femaleEntry[0] : BigDecimal.ZERO;
                            //合并 rebateMaleEntry 和 rebateFemaleEntry 的value
                            //简章对应的入职返费 男 + 女 这笔钱退到账户余额
                            BigDecimal rebateEntry = maleEntryTernaryOperator.add(femaleEntryTernaryOperator);
                            //简章表 简章返佣的钱加起来
                            BigDecimal reMaInterview = dto.getRebateMaleInterview() != null ? dto.getRebateMaleInterview() : BigDecimal.ZERO;
                            BigDecimal reMaReport = dto.getRebateMaleReport() != null ? dto.getRebateMaleReport() : BigDecimal.ZERO;
                            BigDecimal reFemInterview = dto.getRebateFemaleInterview() != null ? dto.getRebateFemaleReport() : BigDecimal.ZERO;
                            BigDecimal reFemReport = dto.getRebateFemaleReport() != null ? dto.getRebateFemaleReport() : BigDecimal.ZERO;
                            //登录人该退的返佣的钱 冻结金额 to 账户余额
                            BigDecimal allRebateMoney = rebateEntry.add(reMaInterview)
                                    .add(reMaReport)
                                    .add(reFemInterview)
                                    .add(reFemReport);
                            //查询账户id, 账户余额
                            HrAcct hrAcct = acctMapper.selectAcctIdByUserId(query.getUserId());
                            //账户id
                            Integer acctId = hrAcct.getId();
                            //账户余额
                            BigDecimal acctbalance = hrAcct.getAcctbalance();

                            //账户详情
                            HrAcctDetail detail = new HrAcctDetail();
                            detail.setAcctid(acctId);
                            detail.setAmount(allRebateMoney);
                            detail.setBeforeamount(acctbalance);
                            detail.setAfteramount(allRebateMoney.add(acctbalance));
                            detail.setCreatetime(LocalDateTime.now());
                            detail.setStatus(1);
                            detail.setType(4);
                            detail.setBriefchapterid(query.getBriefchapter());
                            //增加账户详情
                            acctDetailMapper.insertSelective(detail);
                            //钱退到账户余额 账户余额增加钱
                            acctMapper.reimbursement(query.getUserId(), allRebateMoney);

                            //没有返佣 钱清零
                            query.setRebate(0);
                            query.setRebateMaleInterview(BigDecimal.valueOf(0));
                            query.setRebateMaleReport(BigDecimal.valueOf(0));
                            query.setRebateMaleEntry(BigDecimal.valueOf(0));
                            query.setRebateFemaleInterview(BigDecimal.valueOf(0));
                            query.setRebateFemaleReport(BigDecimal.valueOf(0));
                            query.setRebateFemaleEntry(BigDecimal.valueOf(0));
                            mapper.update2(query);
                            //把 发布简章的入职返佣的钱清0
                            query.getResignations().stream()
                                    .map(m -> {
                                        m.setRebateMaleEntry(BigDecimal.ZERO);
                                        m.setRebateFemaleEntry(BigDecimal.ZERO);
                                        m.setRebateTime(null);
                                        resignationMapper.updateRebate(m);
                                        return m;
                                    }).collect(Collectors.toList());
                            return dto;
                        }).collect(Collectors.toList());

            }
            //有返佣
        } else if (query.getRebate().equals(1)) {
            //存在简章
            if (list.size() > 0) {
                list.stream()
                        .map(dto -> {
                            //发布简章时的人数
                            Integer manNum = dto.getManNum();
                            Integer womenNum = dto.getWomenNum();
                            //编辑简章时的人数
                            Integer manNumNow = query.getManNumNow();
                            Integer womenNumNow = query.getWomenNumNow();
                            // 编辑人数男 - 招聘人数男
                            int manNumOne = manNumNow - manNum > 0 ? manNumNow - manNum : 0;
                            //...女...女...
                            int womenNumOne = womenNumNow - womenNum > 0 ? womenNumNow - womenNum : 0;

                            //招聘人数 - 编辑人数 男
                            int manNumFour = manNum - manNumNow > 0 ? manNum - manNumNow : 0;
                            //招聘人数 - 编辑人数 女
                            int womenNumFour = womenNum - womenNumNow > 0 ? womenNum - womenNumNow : 0;
                            // ========发布简章的面试 报道返佣
                            BigDecimal reMaInterview = dto.getRebateMaleInterview() != null ? dto.getRebateMaleInterview() : BigDecimal.ZERO;
                            BigDecimal reMaReport = dto.getRebateMaleReport() != null ? dto.getRebateMaleReport() : BigDecimal.ZERO;
                            BigDecimal reFemInterview = dto.getRebateFemaleInterview() != null ? dto.getRebateFemaleReport() : BigDecimal.ZERO;
                            BigDecimal reFemReport = dto.getRebateFemaleReport() != null ? dto.getRebateFemaleReport() : BigDecimal.ZERO;


                            //=========编辑简章时的面试 报道返佣
                            BigDecimal reMaleInterview = query.getRebateMaleInterview() != null ? query.getRebateMaleInterview() : BigDecimal.ZERO;
                            BigDecimal reMaleReport = query.getRebateMaleReport() != null ? query.getRebateMaleReport() : BigDecimal.ZERO;
                            BigDecimal reFemaleInterview = query.getRebateFemaleInterview() != null ? query.getRebateFemaleInterview() : BigDecimal.ZERO;
                            BigDecimal reFemaleReport = query.getRebateFemaleReport() != null ? query.getRebateFemaleReport() : BigDecimal.ZERO;

                            //发布简章的入职返佣
                            //入职返佣金额男 简章对应的多笔返佣金额
                            Map<Integer, BigDecimal> rebateMaleEntry = dto.getRebateEntryResignation()
                                    .stream()
                                    .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                            CollectorsUtil.summingBigDecimal(EntryResignation::getRebateMaleEntry)));
                            //入职返佣金额女 简章对应的多笔返佣金额
                            Map<Integer, BigDecimal> rebateFemaleEntry = dto.getRebateEntryResignation().stream()
                                    .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                            CollectorsUtil.summingBigDecimal(EntryResignation::getRebateFemaleEntry)));
                            //声明
                            final BigDecimal[] maleEntry = {null};
                            final BigDecimal[] femaleEntry = {null};
                            rebateFemaleEntry.forEach((k, v) -> {
                                maleEntry[0] = v;
                            });
                            rebateMaleEntry.forEach((k, v) -> {
                                femaleEntry[0] = v;
                            });
                            //发布简章入职返佣 男
                            BigDecimal maleEntryTernaryOperator = maleEntry[0] != null ? maleEntry[0] : BigDecimal.ZERO;
                            //发布简章入职返佣 女
                            BigDecimal femaleEntryTernaryOperator = femaleEntry[0] != null ? femaleEntry[0] : BigDecimal.ZERO;
                            //发布简章时的男生返佣
                            BigDecimal addAllMaleRebateDto = reMaInterview.add(reMaReport).add(maleEntryTernaryOperator);
                            //发布简章时的女生返佣
                            BigDecimal addAllFemaleRebateDto = (reFemInterview).add(reFemReport).add(femaleEntryTernaryOperator);


                            //入职返佣金额男 简章对应的多笔返佣金额 编辑简章时前台传过来
                            Map<Integer, BigDecimal> queryRebateMaleEntry = query.getResignations()
                                    .stream()
                                    .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                            CollectorsUtil.summingBigDecimal(EntryResignation::getRebateMaleEntry)));
                            //入职返佣金额女 简章对应的多笔返佣金额 编辑简章时前台传过来
                            Map<Integer, BigDecimal> queryRebateFemaleEntry = query.getResignations().stream()
                                    .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                            CollectorsUtil.summingBigDecimal(EntryResignation::getRebateFemaleEntry)));

                            final BigDecimal[] queryMaleEntry = {null};
                            final BigDecimal[] queryFemaleEntry = {null};
                            queryRebateMaleEntry.forEach((k, v) -> {
                                queryMaleEntry[0] = v;
                            });
                            queryRebateFemaleEntry.forEach((k, v) -> {
                                queryFemaleEntry[0] = v;
                            });

                            //编辑简章时入职返佣男
                            BigDecimal queryMaleEntryOne = queryMaleEntry[0] != null ? queryMaleEntry[0] : BigDecimal.ZERO;
                            //编辑简章时的入职返佣女
                            BigDecimal queryFemaleOne = queryFemaleEntry[0] != null ? queryFemaleEntry[0] : BigDecimal.ZERO;

                            //编辑简章时的男生返佣
                            BigDecimal addAllRebateMaleQuery = reMaleInterview.add(reMaleReport).add(queryMaleEntryOne);
                            //编辑简章时的女生返佣
                            BigDecimal addAllRebateFemaleQuery = reFemaleInterview.add(reFemaleReport).add(queryFemaleOne);

                            //男生返佣差价
                            BigDecimal subtract = addAllRebateMaleQuery.subtract(addAllMaleRebateDto);
                            //女生返佣差价
                            BigDecimal subtract1 = addAllRebateFemaleQuery.subtract(addAllFemaleRebateDto);

                            if ((manNumOne > 0) || womenNumOne > 0) {
                                //招聘人数增加时的男* 编辑返佣 + 原来的招聘人数 * 返佣差价 = 男生总共返佣
                                BigDecimal multiply1 = addAllRebateMaleQuery.multiply(BigDecimal.valueOf(manNumOne));
                                BigDecimal multiply = (BigDecimal.valueOf(manNum)).multiply(subtract);
                                //所有的男生返佣
                                BigDecimal addAllRebateMale = multiply1.add(multiply);

                                ///招聘人数增加时的女 * 编辑返佣 + 原来的招聘人数 * 返佣差价 = 女生总共返佣
                                BigDecimal multiply2 = addAllRebateFemaleQuery.multiply(BigDecimal.valueOf(womenNumOne));
                                BigDecimal multiply3 = BigDecimal.valueOf(womenNum).multiply(subtract1);
                                //所有女生的返佣
                                BigDecimal addAllRebateFemale = multiply2.add(multiply3);

                                //男生和女生增加人数时的返佣金额
                                BigDecimal addAllRebate = addAllRebateMale.add(addAllRebateFemale);

                                // 判断账户余额够不够
                                //企业账户余额
                                int money = acctMapper.selectAcct(query.getUserId());
                                BigDecimal moneyOf = BigDecimal.valueOf(money);
                                Assert.isFalse(moneyOf.compareTo(addAllRebate) < 0, "账户余额不够请去充值");


                                //查询账户id, 账户余额
                                HrAcct hrAcct = acctMapper.selectAcctIdByUserId(query.getUserId());
                                //账户id
                                Integer acctId = hrAcct.getId();
                                //账户余额
                                BigDecimal acctbalance = hrAcct.getAcctbalance();
                                //账户详情
                                HrAcctDetail detail = new HrAcctDetail();
                                detail.setAcctid(acctId);
                                detail.setAmount(addAllRebate);
                                detail.setBeforeamount(acctbalance);
                                detail.setAfteramount(acctbalance.subtract(addAllRebate));
                                detail.setCreatetime(LocalDateTime.now());
                                detail.setStatus(1);
                                detail.setType(4);
                                detail.setBriefchapterid(query.getBriefchapter());
                                //增加账户详情
                                acctDetailMapper.insertSelective(detail);
                                //招聘人数增加 钱从账户余额 到冻结金额
                                acctMapper.reimbursementAddNum(query.getUserId(), addAllRebate);
                                //简章表修改
                                query.setRebateMaleEntry(queryMaleEntry[0]);
                                query.setRebateFemaleEntry(queryFemaleEntry[0]);
                                mapper.update2(query);
                                //入职返佣表修改
                                List<EntryResignation> resignations = query.getResignations();
                                resignations.stream()
                                        .map(z -> {
                                            resignationMapper.updateRebate(z);
                                            return z;
                                        }).collect(Collectors.toList());

                            } else if (manNumFour > 0 || womenNumFour > 0) {
                                //招聘人数减少时  减少的人的返佣的钱 退到账户余额 + 剩下的人 * 返佣差价
                                //减少的人的返佣的钱 男 冻结金额到账户余额
                                BigDecimal multiply = addAllMaleRebateDto.multiply(BigDecimal.valueOf(manNumFour));
                                //减少的人的返佣的钱 女 冻结金额到账户余额
                                BigDecimal multiply2 = addAllFemaleRebateDto.multiply(BigDecimal.valueOf(manNumFour));
                                //减少的人的返佣的钱 全部 冻结金额到账户余额
                                BigDecimal allSubtractRebate = multiply.add(multiply2);

                                //剩下的人的返佣差价 男 账户余额到冻结金额
                                BigDecimal multiply1 = subtract.multiply(BigDecimal.valueOf(manNumNow));
                                //剩下的人的返佣差价 女 账户余额到冻结金额
                                BigDecimal multiply3 = subtract1.multiply(BigDecimal.valueOf(womenNumNow));
                                //剩下的人的返佣差价 全部 账户余额到冻结金额
                                BigDecimal addSubtractRebate = multiply1.add(multiply3);

                                //冻结金额到账户余额 退钱 不用判断冻结金额钱够不够

                                //查询账户id, 账户余额
                                HrAcct hrAcct = acctMapper.selectAcctIdByUserId(query.getUserId());
                                //账户id
                                Integer acctId = hrAcct.getId();
                                //账户余额
                                BigDecimal acctbalance = hrAcct.getAcctbalance();
                                //账户详情
                                HrAcctDetail detail = new HrAcctDetail();
                                detail.setAcctid(acctId);
                                detail.setAmount(allSubtractRebate.subtract(addSubtractRebate));
                                detail.setBeforeamount(acctbalance);
                                detail.setAfteramount(acctbalance.subtract(allSubtractRebate.subtract(addSubtractRebate)));
                                detail.setCreatetime(LocalDateTime.now());
                                detail.setStatus(1);
                                detail.setType(4);
                                detail.setBriefchapterid(query.getBriefchapter());

                                //账户余额到冻结金额 补上原来的返佣的钱
                                acctMapper.reimbursementAddNum(query.getUserId(), addSubtractRebate);
                                //减少的人的返佣的钱 冻结金额到账户余额 退的钱
                                acctMapper.reimbursement(query.getUserId(), allSubtractRebate);
                                //简章表修改
                                query.setRebateMaleEntry(queryMaleEntry[0]);
                                query.setRebateFemaleEntry(queryFemaleEntry[0]);
                                mapper.update2(query);
                                //入职返佣表修改
                                List<EntryResignation> resignations = query.getResignations();
                                resignations.stream()
                                        .map(z -> {
                                            resignationMapper.updateRebate(z);
                                            return z;
                                        }).collect(Collectors.toList());
                            }
                            dto.getRebateEntryResignation().stream()
                                    .map(d -> {
                                        //entry_resignation 修改入职反佣
                                        resignationMapper.updateRebate(d);
                                        return d;
                                    }).collect(Collectors.toList());
                            return dto;
                        }).collect(Collectors.toList());
            }
        } else {
            Assert.isFalse(query.getRebate() != 0 || query.getRebate() != 1, "未选择是否返佣, 请重新选择");
        }
        //编辑完了 简章状态改成待审核
        mapper.updateStatus(query.getBriefchapter());
        return 1;
    }

    @Override
    public Integer editBriefchapterMyReleaseRecruitment(EditBriefchapterQuery query) {
        BriefcharpterQuery briefcharpterQuery = new BriefcharpterQuery();
        briefcharpterQuery.setBriefcharpterId(query.getBriefchapter());
        //代招单位
        List<HrBriefchapter> list = mapper.queryBriefcharpterDetileRecruitment(briefcharpterQuery);
        //无返佣
        if (query.getRebate().equals(0)) {
            if (list.size() > 0) {
                // 有返佣到无返佣 钱从用户的冻结金额到账户余额
                list.stream()
                        .map(dto -> {
                            //入职返佣金额男 简章对应的多有返佣金额
                            Map<Integer, BigDecimal> rebateMaleEntry = dto.getRebateEntryResignation()
                                    .stream()
                                    .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                            CollectorsUtil.summingBigDecimal(EntryResignation::getRebateMaleEntry)));
                            //入职返佣金额女 简章对应的多有返佣金额
                            Map<Integer, BigDecimal> rebateFemaleEntry = dto.getRebateEntryResignation().stream()
                                    .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                            CollectorsUtil.summingBigDecimal(EntryResignation::getRebateFemaleEntry)));
                            //声明
                            final BigDecimal[] maleEntry = {null};
                            final BigDecimal[] femaleEntry = {null};
                            rebateFemaleEntry.forEach((k, v) -> {
                                maleEntry[0] = v;
                            });
                            rebateMaleEntry.forEach((k, v) -> {
                                femaleEntry[0] = v;
                            });
                            BigDecimal maleEntryTernaryOperator = maleEntry[0] != null ? maleEntry[0] : BigDecimal.ZERO;
                            BigDecimal femaleEntryTernaryOperator = femaleEntry[0] != null ? femaleEntry[0] : BigDecimal.ZERO;
                            //合并 rebateMaleEntry 和 rebateFemaleEntry 的value
                            //简章对应的入职返费 男 + 女 这笔钱退到账户余额
                            BigDecimal rebateEntry = maleEntryTernaryOperator.add(femaleEntryTernaryOperator);
                            //简章表 简章返佣的钱加起来
                            BigDecimal reMaInterview = dto.getRebateMaleInterview() != null ? dto.getRebateMaleInterview() : BigDecimal.ZERO;
                            BigDecimal reMaReport = dto.getRebateMaleReport() != null ? dto.getRebateMaleReport() : BigDecimal.ZERO;
                            BigDecimal reFemInterview = dto.getRebateFemaleInterview() != null ? dto.getRebateFemaleReport() : BigDecimal.ZERO;
                            BigDecimal reFemReport = dto.getRebateFemaleReport() != null ? dto.getRebateFemaleReport() : BigDecimal.ZERO;
                            //登录人该退的返佣的钱 冻结金额 to 账户余额
                            BigDecimal allRebateMoney = rebateEntry.add(reMaInterview)
                                    .add(reMaReport)
                                    .add(reFemInterview)
                                    .add(reFemReport);
                            //查询账户id, 账户余额
                            HrAcct hrAcct = acctMapper.selectAcctIdByUserId(query.getUserId());
                            //账户id
                            Integer acctId = hrAcct.getId();
                            //账户余额
                            BigDecimal acctbalance = hrAcct.getAcctbalance();

                            //账户详情
                            HrAcctDetail detail = new HrAcctDetail();
                            detail.setAcctid(acctId);
                            detail.setAmount(allRebateMoney);
                            detail.setBeforeamount(acctbalance);
                            detail.setAfteramount(allRebateMoney.add(acctbalance));
                            detail.setCreatetime(LocalDateTime.now());
                            detail.setStatus(1);
                            detail.setType(4);
                            detail.setBriefchapterid(query.getBriefchapter());
                            //增加账户详情
                            acctDetailMapper.insertSelective(detail);
                            //钱退到账户余额 账户余额增加钱
                            acctMapper.reimbursement(query.getUserId(), allRebateMoney);

                            //没有返佣 钱清零
                            query.setRebate(0);
                            query.setRebateMaleInterview(BigDecimal.valueOf(0));
                            query.setRebateMaleReport(BigDecimal.valueOf(0));
                            query.setRebateMaleEntry(BigDecimal.valueOf(0));
                            query.setRebateFemaleInterview(BigDecimal.valueOf(0));
                            query.setRebateFemaleReport(BigDecimal.valueOf(0));
                            query.setRebateFemaleEntry(BigDecimal.valueOf(0));
                            mapper.update2(query);
                            //把 发布简章的入职返佣的钱清0
                            query.getResignations().stream()
                                    .map(m -> {
                                        m.setRebateMaleEntry(BigDecimal.ZERO);
                                        m.setRebateFemaleEntry(BigDecimal.ZERO);
                                        m.setRebateTime(null);
                                        resignationMapper.updateRebate(m);
                                        return m;
                                    }).collect(Collectors.toList());
                            return dto;
                        }).collect(Collectors.toList());

            }
            //有返佣
        } else if (query.getRebate().equals(1)) {
            //存在简章
            if (list.size() > 0) {
                list.stream()
                        .map(dto -> {
                            //发布简章时的人数
                            Integer manNum = dto.getManNum();
                            Integer womenNum = dto.getWomenNum();
                            //编辑简章时的人数
                            Integer manNumNow = query.getManNumNow();
                            Integer womenNumNow = query.getWomenNumNow();
                            // 编辑人数男 - 招聘人数男
                            int manNumOne = manNumNow - manNum > 0 ? manNumNow - manNum : 0;
                            //...女...女...
                            int womenNumOne = womenNumNow - womenNum > 0 ? womenNumNow - womenNum : 0;

                            //招聘人数 - 编辑人数 男
                            int manNumFour = manNum - manNumNow > 0 ? manNum - manNumNow : 0;
                            //招聘人数 - 编辑人数 女
                            int womenNumFour = womenNum - womenNumNow > 0 ? womenNum - womenNumNow : 0;
                            // ========发布简章的面试 报道返佣
                            BigDecimal reMaInterview = dto.getRebateMaleInterview() != null ? dto.getRebateMaleInterview() : BigDecimal.ZERO;
                            BigDecimal reMaReport = dto.getRebateMaleReport() != null ? dto.getRebateMaleReport() : BigDecimal.ZERO;
                            BigDecimal reFemInterview = dto.getRebateFemaleInterview() != null ? dto.getRebateFemaleReport() : BigDecimal.ZERO;
                            BigDecimal reFemReport = dto.getRebateFemaleReport() != null ? dto.getRebateFemaleReport() : BigDecimal.ZERO;


                            //=========编辑简章时的面试 报道返佣
                            BigDecimal reMaleInterview = query.getRebateMaleInterview() != null ? query.getRebateMaleInterview() : BigDecimal.ZERO;
                            BigDecimal reMaleReport = query.getRebateMaleReport() != null ? query.getRebateMaleReport() : BigDecimal.ZERO;
                            BigDecimal reFemaleInterview = query.getRebateFemaleInterview() != null ? query.getRebateFemaleInterview() : BigDecimal.ZERO;
                            BigDecimal reFemaleReport = query.getRebateFemaleReport() != null ? query.getRebateFemaleReport() : BigDecimal.ZERO;

                            //发布简章的入职返佣
                            //入职返佣金额男 简章对应的多笔返佣金额
                            Map<Integer, BigDecimal> rebateMaleEntry = dto.getRebateEntryResignation()
                                    .stream()
                                    .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                            CollectorsUtil.summingBigDecimal(EntryResignation::getRebateMaleEntry)));
                            //入职返佣金额女 简章对应的多笔返佣金额
                            Map<Integer, BigDecimal> rebateFemaleEntry = dto.getRebateEntryResignation().stream()
                                    .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                            CollectorsUtil.summingBigDecimal(EntryResignation::getRebateFemaleEntry)));
                            //声明
                            final BigDecimal[] maleEntry = {null};
                            final BigDecimal[] femaleEntry = {null};
                            rebateFemaleEntry.forEach((k, v) -> {
                                maleEntry[0] = v;
                            });
                            rebateMaleEntry.forEach((k, v) -> {
                                femaleEntry[0] = v;
                            });
                            //发布简章入职返佣 男
                            BigDecimal maleEntryTernaryOperator = maleEntry[0] != null ? maleEntry[0] : BigDecimal.ZERO;
                            //发布简章入职返佣 女
                            BigDecimal femaleEntryTernaryOperator = femaleEntry[0] != null ? femaleEntry[0] : BigDecimal.ZERO;
                            //发布简章时的男生返佣
                            BigDecimal addAllMaleRebateDto = reMaInterview.add(reMaReport).add(maleEntryTernaryOperator);
                            //发布简章时的女生返佣
                            BigDecimal addAllFemaleRebateDto = (reFemInterview).add(reFemReport).add(femaleEntryTernaryOperator);


                            //入职返佣金额男 简章对应的多笔返佣金额 编辑简章时前台传过来
                            Map<Integer, BigDecimal> queryRebateMaleEntry = query.getResignations()
                                    .stream()
                                    .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                            CollectorsUtil.summingBigDecimal(EntryResignation::getRebateMaleEntry)));
                            //入职返佣金额女 简章对应的多笔返佣金额 编辑简章时前台传过来
                            Map<Integer, BigDecimal> queryRebateFemaleEntry = query.getResignations().stream()
                                    .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                            CollectorsUtil.summingBigDecimal(EntryResignation::getRebateFemaleEntry)));

                            final BigDecimal[] queryMaleEntry = {null};
                            final BigDecimal[] queryFemaleEntry = {null};
                            queryRebateMaleEntry.forEach((k, v) -> {
                                queryMaleEntry[0] = v;
                            });
                            queryRebateFemaleEntry.forEach((k, v) -> {
                                queryFemaleEntry[0] = v;
                            });

                            //编辑简章时入职返佣男
                            BigDecimal queryMaleEntryOne = queryMaleEntry[0] != null ? queryMaleEntry[0] : BigDecimal.ZERO;
                            //编辑简章时的入职返佣女
                            BigDecimal queryFemaleOne = queryFemaleEntry[0] != null ? queryFemaleEntry[0] : BigDecimal.ZERO;

                            //编辑简章时的男生返佣
                            BigDecimal addAllRebateMaleQuery = reMaleInterview.add(reMaleReport).add(queryMaleEntryOne);
                            //编辑简章时的女生返佣
                            BigDecimal addAllRebateFemaleQuery = reFemaleInterview.add(reFemaleReport).add(queryFemaleOne);

                            //男生返佣差价
                            BigDecimal subtract = addAllRebateMaleQuery.subtract(addAllMaleRebateDto);
                            //女生返佣差价
                            BigDecimal subtract1 = addAllRebateFemaleQuery.subtract(addAllFemaleRebateDto);

                            if ((manNumOne > 0) || womenNumOne > 0) {
                                //招聘人数增加时的男* 编辑返佣 + 原来的招聘人数 * 返佣差价 = 男生总共返佣
                                BigDecimal multiply1 = addAllRebateMaleQuery.multiply(BigDecimal.valueOf(manNumOne));
                                BigDecimal multiply = (BigDecimal.valueOf(manNum)).multiply(subtract);
                                //所有的男生返佣
                                BigDecimal addAllRebateMale = multiply1.add(multiply);

                                ///招聘人数增加时的女 * 编辑返佣 + 原来的招聘人数 * 返佣差价 = 女生总共返佣
                                BigDecimal multiply2 = addAllRebateFemaleQuery.multiply(BigDecimal.valueOf(womenNumOne));
                                BigDecimal multiply3 = BigDecimal.valueOf(womenNum).multiply(subtract1);
                                //所有女生的返佣
                                BigDecimal addAllRebateFemale = multiply2.add(multiply3);

                                //男生和女生增加人数时的返佣金额
                                BigDecimal addAllRebate = addAllRebateMale.add(addAllRebateFemale);

                                // 判断账户余额够不够
                                //企业账户余额
                                int money = acctMapper.selectAcct(query.getUserId());
                                BigDecimal moneyOf = BigDecimal.valueOf(money);
                                Assert.isFalse(moneyOf.compareTo(addAllRebate) < 0, "账户余额不够请去充值");


                                //查询账户id, 账户余额
                                HrAcct hrAcct = acctMapper.selectAcctIdByUserId(query.getUserId());
                                //账户id
                                Integer acctId = hrAcct.getId();
                                //账户余额
                                BigDecimal acctbalance = hrAcct.getAcctbalance();
                                //账户详情
                                HrAcctDetail detail = new HrAcctDetail();
                                detail.setAcctid(acctId);
                                detail.setAmount(addAllRebate);
                                detail.setBeforeamount(acctbalance);
                                detail.setAfteramount(acctbalance.subtract(addAllRebate));
                                detail.setCreatetime(LocalDateTime.now());
                                detail.setStatus(1);
                                detail.setType(4);
                                detail.setBriefchapterid(query.getBriefchapter());
                                //增加账户详情
                                acctDetailMapper.insertSelective(detail);
                                //招聘人数增加 钱从账户余额 到冻结金额
                                acctMapper.reimbursementAddNum(query.getUserId(), addAllRebate);
                                //简章表修改
                                query.setRebateMaleEntry(queryMaleEntry[0]);
                                query.setRebateFemaleEntry(queryFemaleEntry[0]);
                                mapper.update2(query);
                                //入职返佣表修改
                                List<EntryResignation> resignations = query.getResignations();
                                resignations.stream()
                                        .map(z -> {
                                            resignationMapper.updateRebate(z);
                                            return z;
                                        }).collect(Collectors.toList());

                            } else if (manNumFour > 0 || womenNumFour > 0) {
                                //招聘人数减少时  减少的人的返佣的钱 退到账户余额 + 剩下的人 * 返佣差价
                                //减少的人的返佣的钱 男 冻结金额到账户余额
                                BigDecimal multiply = addAllMaleRebateDto.multiply(BigDecimal.valueOf(manNumFour));
                                //减少的人的返佣的钱 女 冻结金额到账户余额
                                BigDecimal multiply2 = addAllFemaleRebateDto.multiply(BigDecimal.valueOf(manNumFour));
                                //减少的人的返佣的钱 全部 冻结金额到账户余额
                                BigDecimal allSubtractRebate = multiply.add(multiply2);

                                //剩下的人的返佣差价 男 账户余额到冻结金额
                                BigDecimal multiply1 = subtract.multiply(BigDecimal.valueOf(manNumNow));
                                //剩下的人的返佣差价 女 账户余额到冻结金额
                                BigDecimal multiply3 = subtract1.multiply(BigDecimal.valueOf(womenNumNow));
                                //剩下的人的返佣差价 全部 账户余额到冻结金额
                                BigDecimal addSubtractRebate = multiply1.add(multiply3);

                                //冻结金额到账户余额 退钱 不用判断冻结金额钱够不够

                                //查询账户id, 账户余额
                                HrAcct hrAcct = acctMapper.selectAcctIdByUserId(query.getUserId());
                                //账户id
                                Integer acctId = hrAcct.getId();
                                //账户余额
                                BigDecimal acctbalance = hrAcct.getAcctbalance();
                                //账户详情
                                HrAcctDetail detail = new HrAcctDetail();
                                detail.setAcctid(acctId);
                                detail.setAmount(allSubtractRebate.subtract(addSubtractRebate));
                                detail.setBeforeamount(acctbalance);
                                detail.setAfteramount(acctbalance.subtract(allSubtractRebate.subtract(addSubtractRebate)));
                                detail.setCreatetime(LocalDateTime.now());
                                detail.setStatus(1);
                                detail.setType(4);
                                detail.setBriefchapterid(query.getBriefchapter());

                                //账户余额到冻结金额 补上原来的返佣的钱
                                acctMapper.reimbursementAddNum(query.getUserId(), addSubtractRebate);
                                //减少的人的返佣的钱 冻结金额到账户余额 退的钱
                                acctMapper.reimbursement(query.getUserId(), allSubtractRebate);
                                //简章表修改
                                query.setRebateMaleEntry(queryMaleEntry[0]);
                                query.setRebateFemaleEntry(queryFemaleEntry[0]);
                                mapper.update2(query);
                                //入职返佣表修改
                                List<EntryResignation> resignations = query.getResignations();
                                resignations.stream()
                                        .map(z -> {
                                            resignationMapper.updateRebate(z);
                                            return z;
                                        }).collect(Collectors.toList());
                            }
                            dto.getRebateEntryResignation().stream()
                                    .map(d -> {
                                        //entry_resignation 修改入职反佣
                                        resignationMapper.updateRebate(d);
                                        return d;
                                    }).collect(Collectors.toList());
                            return dto;
                        }).collect(Collectors.toList());
            }
        } else {
            Assert.isFalse(query.getRebate() != 0 || query.getRebate() != 1, "未选择是否返佣, 请重新选择");
        }
        //编辑完了 简章状态改成待审核
        mapper.updateStatus(query.getBriefchapter());
        return 1;
    }


    @Override
    public Map<String, Object> selectGroupName(Integer type, Integer userId, Integer status) {
        Map<String, Object> map = new HashMap<>();
        if (status.equals(1)) {
            //被招聘企业名字
            List<HrGroup> collect = groupMapper.selectGroupName(type, userId)
                    .stream()
                    .collect(Collectors.toList());
            map.put("selectGroupName", collect);
        } else {
            //招聘企业名字
            List<HrGroup> hrGroups = groupMapper.selectGroupNameRecruitment(type, userId)
                    .stream()
                    .collect(Collectors.toList());
            map.put("selectGroupNameRecruitment", hrGroups);
        }
        return map;

    }


    /**
     * 有返佣的情况下发布简章
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:57 2019/8/24
     * @Param [query]
     **/
    private int initBriefcharpterRebate(ReleaseBriefcharpterQuery query) {
        HrBriefchapter dto = new HrBriefchapter();
        dto.setPostId(query.getPostId());
        //被招聘企业id
        dto.setRecruitedcompanyId(query.getRecruitedCompanyId());

        dto.setProfessionId(query.getProfessionId());
        String s = query.getAvgSalary();
        BigDecimal decimal = StringUtil.decimal(s);
        dto.setAvgSalary(decimal);
        //招聘人数
        int num = query.getManNum() + query.getWomenAgeId();
        dto.setRecruitingNo(num);
        String salary = query.getDetailSalary();
        BigDecimal decimal1 = StringUtil.decimal(salary);
        dto.setDetailSalary(decimal1);
        dto.setManAgeId(query.getManAgeId());
        dto.setWomenAgeId(query.getWomenAgeId());
        dto.setEducationId(query.getEducationId());
        dto.setExperienceId(query.getExperienceId());
        dto.setWorkWayId(query.getWorkWayId());
        dto.setWorkAddress(query.getWorkAddress());
        dto.setWorkTimeArrangeId(query.getWorkTimeArrangeId());
        dto.setClothingReguirementId(query.getClothingRequirementId());
        dto.setHobbyId(query.getHobbyId());
        dto.setOvertimeTimeId(query.getOvertimeTimeId());
        dto.setWelfareId(query.getWelfareId());
        //用人单位证明
        dto.setEmployerCertificatePhotoUrl(query.getEmployerCertificatePhotoUrl());

        //是否返佣 有返佣
        dto.setRebate(1);
        dto.setDescriptionJobPhotoUrl(query.getDescriptionJobPhotoUrl());

        //用人单位面试地址
        dto.setInterviewAddress(query.getInterviewAddress());

        //非用人单位面试地址
        dto.setNoEmployerAddress(query.getNoEmployerAddress());

        dto.setPostDetail(query.getPostDetail());
        String interviewTime = query.getInterviewTime();
        LocalDateTime dateTime = StringUtil.strToLocalDateTime(interviewTime);
        dto.setInterviewTime(dateTime);
        String time = query.getRegisterTime();
        Date date = StringUtil.StrToDate(time);
        dto.setRegisterTime(date);
        String contractTime = query.getContractTime();
        LocalDateTime localDateTime1 = StringUtil.strToLocalDateTime(contractTime);
        dto.setContractTime(localDateTime1);
        dto.setHireWay(query.getHireWay());
        dto.setAcceptRecommendedResume(query.getAcceptRecommendedResume());


        dto.setContractWay(query.getContractWay());
        dto.setManNum(query.getManNum());
        dto.setWomenNum(query.getWomenNum());
        dto.setUserId(query.getUserId());
        //面试返佣男
        dto.setRebateMaleInterview(query.getRebateMaleInterview());
        // 报道...
        dto.setRebateMaleReport(query.getRebateMaleReport());

        //面试返佣女
        dto.setRebateFemaleInterview(query.getRebateFemaleInterview());
        //报道...
        dto.setRebateFemaleReport(query.getRebateFemaleReport());
        //入职返佣男女的金钱 时间
        List<EntryResignation> rebateEntry = query.getRebateEntry();
        //发布简章时 入职返佣不为空
        if (null != rebateEntry) {
            //入职返佣男
            Map<Integer, BigDecimal> rebateMaleEntry = rebateEntry.stream()
                    .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                            CollectorsUtil.summingBigDecimal(EntryResignation::getRebateMaleEntry)));
            //入职返佣女
            Map<Integer, BigDecimal> rebateFemaleEntry = rebateEntry.stream()
                    .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                            CollectorsUtil.summingBigDecimal(EntryResignation::getRebateFemaleEntry)));
            //添加入职返佣的钱 男
            rebateMaleEntry.forEach((k, v) -> dto.setRebateMaleEntry(v));
            //添加入职返佣的钱 女
            rebateFemaleEntry.forEach((k, v) -> dto.setRebateFemaleEntry(v));
        }


        if (query.getContractWay() == 0) {
            //招聘单位
            dto.setContractWay(0);
            dto.setContractWayDetailId(Integer.valueOf(query.getContractWayDetailId()));
        } else if (query.getContractWay() == 1) {
            //用工单位
            dto.setContractWay(1);
            dto.setContractWayDetailId(Integer.valueOf(query.getContractWayDetailId()));
        } else if (query.getContractWay() == 2) {
            //厂方指定劳务公司
            dto.setContractWay(2);
            dto.setContractWayDetailId(Integer.valueOf(query.getContractWayDetailId()));
        } else {
            System.out.println(RlzyConstant.OPS_FAILED_MSG);
        }

        Assert.isFalse(mapper.save(dto) < 1, RlzyConstant.OPS_FAILED_MSG);
        return dto.getId();
    }

    /**
     * 没返佣 简章表初始化
     *
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:29 2019/8/24
     * @Param [query]
     **/
    private void initBriefcharpter(ReleaseBriefcharpterQuery query) {
        HrBriefchapter dto = new HrBriefchapter();
        dto.setPostId(query.getPostId());
        //被招聘企业id
        dto.setRecruitedcompanyId(query.getRecruitedCompanyId());

        dto.setProfessionId(query.getProfessionId());
        String s = query.getAvgSalary();
        BigDecimal decimal = StringUtil.decimal(s);
        dto.setAvgSalary(decimal);
        //招聘人数
        int num = query.getManNum() + query.getWomenAgeId();
        dto.setRecruitingNo(num);
        String salary = query.getDetailSalary();
        BigDecimal decimal1 = StringUtil.decimal(salary);
        dto.setDetailSalary(decimal1);


        dto.setManAgeId(query.getManAgeId());
        dto.setWomenAgeId(query.getWomenAgeId());
        dto.setEducationId(query.getEducationId());
        dto.setExperienceId(query.getExperienceId());
        dto.setWorkWayId(query.getWorkWayId());
        dto.setWorkAddress(query.getWorkAddress());
        dto.setWorkTimeArrangeId(query.getWorkTimeArrangeId());
        dto.setClothingReguirementId(query.getClothingRequirementId());
        dto.setHobbyId(query.getHobbyId());
        dto.setOvertimeTimeId(query.getOvertimeTimeId());
        dto.setWelfareId(query.getWelfareId());

        //用人单位证明
        dto.setEmployerCertificatePhotoUrl(query.getEmployerCertificatePhotoUrl());

        //是否返佣 没返佣
        dto.setRebate(0);

        dto.setDescriptionJobPhotoUrl(query.getDescriptionJobPhotoUrl());

        //用人单位面试地址
        dto.setInterviewAddress(query.getInterviewAddress());

        //非用人单位面试地址
        dto.setNoEmployerAddress(query.getNoEmployerAddress());

        dto.setPostDetail(query.getPostDetail());
        String interviewTime = query.getInterviewTime();
        LocalDateTime dateTime = StringUtil.strToLocalDateTime(interviewTime);
        dto.setInterviewTime(dateTime);
        String time = query.getRegisterTime();
        Date date = StringUtil.StrToDate(time);
        dto.setRegisterTime(date);
        String contractTime = query.getContractTime();
        LocalDateTime localDateTime1 = StringUtil.strToLocalDateTime(contractTime);
        dto.setContractTime(localDateTime1);
        dto.setHireWay(query.getHireWay());
        dto.setAcceptRecommendedResume(query.getAcceptRecommendedResume());


        dto.setContractWay(query.getContractWay());
        dto.setManNum(query.getManNum());
        dto.setWomenNum(query.getWomenNum());
        dto.setUserId(query.getUserId());
        if (query.getContractWay() == 0) {
            //招聘单位
            dto.setContractWay(0);
            dto.setContractWayDetailId(Integer.valueOf(query.getContractWayDetailId()));
        } else if (query.getContractWay() == 1) {
            //用工单位
            dto.setContractWay(1);
            dto.setContractWayDetailId(Integer.valueOf(query.getContractWayDetailId()));
        } else if (query.getContractWay() == 2) {
            //厂方指定劳务公司
            dto.setContractWay(2);
            dto.setContractWayDetailId(Integer.valueOf(query.getContractWayDetailId()));
        } else {
            System.out.println(RlzyConstant.OPS_FAILED_MSG);
        }

        Assert.isFalse(mapper.save(dto) < 1, RlzyConstant.OPS_FAILED_MSG);
    }
}
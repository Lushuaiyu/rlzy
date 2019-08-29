package com.nado.rlzy.service.impl;

import cn.hutool.core.lang.Assert;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

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

    @Autowired
    private HrBriefchapterMapper mapper;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private HrRebaterecordMapper rebaterecordMapper;

    @Autowired
    private HrSignUpMapper signUpMapper;

    @Autowired
    private HrAcctMapper acctMapper;
    @Autowired
    private HrAcctDetailMapper acctDetailMapper;

    @Autowired
    private HrDictionaryItemMapper dictionaryItemMapper;

    @Autowired
    private HrGroupMapper groupMapper;

    @Autowired
    private HrUserMapper userMapper;

    @Autowired
    private ViolationRecordMapper violationRecordMapper;

    @Autowired
    private EntryResignationMapper resignationMapper;

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

            } else {
                //不返佣
                //简章表初始化
                initBriefcharpter(query);
            }
        }

    }

    private void initEntryResignation(ReleaseBriefcharpterQuery query, int brId) {
        if (query.getRebateEntry() != null) {
            //如果 入职返佣不为空 添加记录
            List<EntryResignation> rebateEntry = query.getRebateEntry();
            rebateEntry.stream()
                    .map(dto -> {
                        dto.setBriefChapterId(brId);
                        dto.setRebateTime(new Date());
                        dto.setCreateTime(new Date());
                        return dto;
                    }).collect(Collectors.toList());
            resignationMapper.insertList(rebateEntry);
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
    public int recruitmentInterviewSuccess(Integer signUpId, Integer briefChapterId, Integer rebateType) {
        int signup = signUpMapper.recruitmentInterviewSuccess(signUpId, briefChapterId);
        HrRebaterecord hrRebaterecord = new HrRebaterecord();
        hrRebaterecord.setBriefchapterId(briefChapterId);
        hrRebaterecord.setRebateType(rebateType);
        //查询返佣id
        int reId = rebaterecordMapper.selectReId(hrRebaterecord);

        hrRebaterecord.setId(reId);
        hrRebaterecord.setRebateTime(new Date());
        //改变返佣状态
        int rebateStatus = rebaterecordMapper.updateRebateStatus(hrRebaterecord);
        return signup >= 1 && reId >= 1 && rebateStatus >= 1 ? RlzyConstant.OPS_SUCCESS_CODE : RlzyConstant.OPS_FAILED_CODE;
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
    public int reported(Integer signUpId, Integer briefChapterId, Integer rebateType) {
        int reported = signUpMapper.reported(signUpId, briefChapterId);
        HrRebaterecord hrRebaterecord = new HrRebaterecord();
        hrRebaterecord.setBriefchapterId(briefChapterId);
        hrRebaterecord.setRebateType(rebateType);
        int reId = rebaterecordMapper.selectReId(hrRebaterecord);

        hrRebaterecord.setId(reId);
        hrRebaterecord.setRebateTime(new Date());
        int rebateStatus = rebaterecordMapper.updateRebateStatus(hrRebaterecord);
        return reported >= 1 && reId >= 1 && rebateStatus >= 1 ? RlzyConstant.OPS_SUCCESS_CODE : RlzyConstant.OPS_FAILED_CODE;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int noReportedReason(Integer reason, Integer signUpId, Integer briefChapterId, Integer type) {
        int noReported = signUpMapper.noReportedReason(reason, signUpId, briefChapterId);
        ViolationRecord record = new ViolationRecord();
        record.setContent(reason);
        record.setSignUpId(signUpId);
        record.setCreatTime(new Date());
        record.setType(type);
        int violationRecord = violationRecordMapper.insertSelective(record);
        return noReported >= 1 && violationRecord >= 1 ? 1 : 0;
    }

    @Override
    public List<HrSignUp> rebatee(Integer signUpId, Integer sex) {
        List<HrSignUp> sign = signUpMapper.rebate(signUpId);
        List<HrSignUp> list = sign.stream().map(signUp -> {
            signUp.getRebat().stream().map(rebat -> {
                if (sex.equals(0)) {
                    BigDecimal female = rebat.getRebateFemale();
                    rebat.setRebateOne(female);
                    rebat.setRebateFemale(BigDecimal.valueOf(0));
                    rebat.setRebateMale(BigDecimal.valueOf(0));
                }
                if (sex.equals(1)) {
                    BigDecimal male = rebat.getRebateMale();
                    rebat.setRebateOne(male);
                    rebat.setRebateFemale(BigDecimal.valueOf(0));
                    rebat.setRebateMale(BigDecimal.valueOf(0));
                }

                return rebat;
            }).collect(Collectors.toList());

            return signUp;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int noRebate(Integer rebateId) {
        return signUpMapper.noRebate(rebateId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int rebate(RebateQuery query) {

        //更改返佣状态
        signUpMapper.rebateOne(query.getRebateId());
        // 本人 -- 求职者账户增加钱
        signUpMapper.rebateAdd(query);
        // 企业 -- 从企业账户冻结金额扣钱
        signUpMapper.rebateSubtraction(query);
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
        //查询账户id
        HrAcctDetail detail1 = acctDetailMapper.selectByPrimaryKey(userId);

        Integer detail1Id = detail1.getId();
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
            detail.setAcctid(detail1Id);
            detail.setAmount(value);
            detail.setSignupid(signUpId);
            detail.setCreatetime(new Date());
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
    }

    @Override
    public List<EntryResignation> selectEntryRebate(Integer briefchapterId) {
        List<EntryResignation> list = resignationMapper.selectEntryRebate(briefchapterId);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer editBriefchapterMyRelease(EditBriefchapterQuery query) {
       /* BriefcharpterQuery briefcharpterQuery = new BriefcharpterQuery();
        briefcharpterQuery.setBriefcharpterId(query.getBriefchapter());

        List<HrBriefchapter> list = mapper.queryBriefcharpterDetileByParams(briefcharpterQuery);
        List<HrBriefchapter> hrBriefchapters = mapper.queryBriefcharpterDetileRecruitment(briefcharpterQuery);

        //代招单位
        if (query.getTypp().equals(1)) {
            list.stream()
                    .map(m -> {
                        //发布简章时 招聘的男生数量
                        var ManNum = m.getManNum();
                        // 发布简章时 招聘的女生数量
                        var womenNum = m.getWomenNum();
                        //编辑简章后 少招多少人 男
                        var editManNum = ManNum - query.getManNumNow();
                        editManNum = Math.abs(editManNum);

                        // 编辑简章后 多招多少人 男
                        var editManNum1 = query.getManNumNow() - ManNum;
                        editManNum1 = Math.abs(editManNum1);

                        // 编辑简章后  少招多少人 女
                        var editWomenNum = womenNum - query.getWomenNumNow();
                        editWomenNum = Math.abs(editWomenNum);
                        //编辑简章时 多招多少人 女
                        var editWomenNum2 = query.getWomenNumNow() - womenNum;
                        editWomenNum2 = Math.abs(editWomenNum2);
                       *//* //如果用户输入的返佣金额 < 1000 增加的是用户输入的钱 用户输入的钱 > 1000 就  / 10 面试返佣男 增加的钱
                        var rebateMaleInterview = query.getRebateMaleInterview().compareTo(BigDecimal.valueOf(1000)) < 0 ?
                                query.getRebateMaleInterview() : query.getRebateMaleInterview().divide(BigDecimal.valueOf(10));
                        //...............面试返佣女 增加的钱
                        var rebateFemaleInterview = query.getRebateFemaleInterview().compareTo(BigDecimal.valueOf(1000)) < 0 ?
                                query.getRebateFemaleInterview() : query.getRebateFemaleInterview().divide(BigDecimal.valueOf(10));
                        //...............报道返佣男 增加的钱
                        var rebateMaleReport = query.getRebateMaleReport().compareTo(BigDecimal.valueOf(1000)) < 0 ?
                                query.getRebateMaleReport() : query.getRebateMaleReport().divide(BigDecimal.valueOf(10));
                        //..............报道返佣女 增加的钱
                        var rebateFemaleReport = query.getRebateFemaleReport().compareTo(BigDecimal.valueOf(1000)) < 0 ?
                                query.getRebateFemaleReport() : query.getRebateFemaleReport().divide(BigDecimal.valueOf(10));
                        //入职返佣男 增加的钱
                        var rebateMaleEntry = query.getRebateMaleEntry().compareTo(BigDecimal.valueOf(1000)) < 0 ?
                                query.getRebateMaleEntry() : query.getRebateMaleEntry().divide(BigDecimal.valueOf(10));*//*
                        //发布简章时 面试返佣男
                        BigDecimal maleInterview = m.getRebateMaleInterview() != null ? m.getRebateMaleInterview() : BigDecimal.ZERO;
                        // 发布简章时 面试返佣女
                        BigDecimal femaleInterview = m.getRebateFemaleInterview() != null ? m.getRebateFemaleInterview() : BigDecimal.ZERO;
                        // 发布简章时 报道返佣男
                        BigDecimal maleReport = m.getRebateMaleReport() != null ? m.getRebateMaleReport() : BigDecimal.ZERO;
                        //// 发布简章时 报道返佣女
                        BigDecimal femaleReport = m.getRebateFemaleReport() != null ? m.getRebateFemaleReport() : BigDecimal.ZERO;
                        //简章对应的入职返佣 男
                        BigDecimal maleEntry = m.getRebateMaleEntry() != null ? m.getRebateMaleEntry() : BigDecimal.ZERO;
                        //简章对应的入职返佣 女
                        BigDecimal femaleEntry = m.getRebateFemaleEntry() != null ? m.getRebateFemaleEntry() : BigDecimal.ZERO;
                        // 单人返佣 男 减少招聘人数时的的钱 返到账户余额 发布简章时的返佣
                        BigDecimal allRebateMale = maleInterview.add(femaleInterview)
                                .add(maleReport)
                                .add(maleEntry);
                        // 单人返佣 女 减少招聘人数时的的钱 返到账户余额 发布简章时的返佣
                        BigDecimal allRebateFemale = femaleInterview
                                .add(femaleReport)
                                .add(femaleEntry);
                        //判断入职返佣是否为空 和多招聘人的相关参数不为空才进行这个操作
                        //编辑简章时男生入职返佣的钱
                        Map<Integer, BigDecimal> resignationMaleEntry = query.getResignations()
                                .stream()
                                .filter(x -> x.getType().equals(2))
                                .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                        CollectorsUtil.summingBigDecimal()));
                        //编辑简章时女生入职返佣的钱
                        Map<Integer, BigDecimal> resignationFemaleEntry = query.getResignations()
                                .stream()
                                .filter(y -> y.getType().equals(2))
                                .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                        CollectorsUtil.summingBigDecimal(EntryResignation::getRebateFemaleEntry)));


                        //男生入职返佣的钱
                        resignationMaleEntry.forEach((k, v) ->{
                            BigDecimal decimal = v != null ? v : BigDecimal.ZERO;
                            BigDecimal reMale = decimal;
                            query.setRebateMEntry(reMale);
                        });
                        //女生入职返佣的钱
                        resignationFemaleEntry.forEach((k, v) -> {
                            BigDecimal decimal = v != null ? v : BigDecimal.ZERO;
                            BigDecimal reFemale = decimal;

                            query.setRebateFentry(reFemale);
                        } );
                        BigDecimal rebateMaleInterview = query.getRebateMaleInterview().subtract(maleInterview) != null ? query.getRebateMaleInterview().subtract(maleInterview) : BigDecimal.ZERO;
                        BigDecimal rebateMaleReport = query.getRebateMaleReport().subtract(maleReport) != null ? query.getRebateMaleReport().subtract(maleReport) : BigDecimal.ZERO;
                        BigDecimal rebateFemaleInterview = query.getRebateFemaleInterview().subtract(femaleInterview) != null ? query.getRebateFemaleInterview().subtract(femaleInterview) : BigDecimal.ZERO;
                        BigDecimal rebateFemaleReport = query.getRebateFemaleReport().subtract(femaleReport) != null ? query.getRebateFemaleReport().subtract(femaleReport) : BigDecimal.ZERO;
                        //编辑简章时男生增加的返佣的钱  修改的返佣的钱 - 原来的钱 * 增加的人数
                        BigDecimal addMaleRebate = rebateMaleInterview.add(rebateMaleReport)
                                .add(query.getRebateMEntry());
                        //编辑简章时女生增加的返佣的钱
                        BigDecimal addFemaleRebate = rebateFemaleInterview.add(rebateFemaleReport)
                                .add(query.getRebateFentry());

                        //查询账户余额 和冻结金额的余额
                        List<HrAcct> hrAccts = acctMapper.selectAcct(query.getUserId());
                        // 少招聘 n 个人的返费金额  返费 = 单人返费 * n 男生招聘数量减少
                        if (query.getManNumNow() < ManNum) {
                            //男 少招的人的返费金额 退到账户余额
                            BigDecimal man = allRebateMale.multiply(BigDecimal.valueOf(editManNum));
                            //钱退到账户余额 账户余额增加钱 冻结金额减少钱
                            acctMapper.reimbursement(query.getUserId(), man);
                        }
                        if (query.getWomenNumNow() < womenNum) {
                            //女生招聘数量减少
                            BigDecimal women = allRebateFemale.multiply(BigDecimal.valueOf(editWomenNum));
                            //女 少招的人的返费金额 退到账户余额
                            acctMapper.reimbursement(query.getUserId(), women);

                        }
                        if (query.getManNumNow() > ManNum) {
                            //男 多招的人的返佣金额 + 原来招的人的差价 返佣的钱从账户余额到 冻结金额
                            BigDecimal man = addMaleRebate.multiply(BigDecimal.valueOf(editManNum1)).add();

                            hrAccts.stream()
                                    .map(dto -> {
                                        //账户余额
                                        BigDecimal acctbalance = dto.getAcctbalance();
                                        //账户余额不足 请去充值
                                        Assert.isFalse(acctbalance.subtract(query.getRebateMoney()).compareTo(BigDecimal.valueOf(0)) <= 0,
                                                RlzyConstant.ACCOUNT_BALANCE_IS_EMPTY);
                                        //返费的金额 从账户余额到冻结金额 账户余额 - 钱 冻结金额 + 钱
                                        acctMapper.reimbursementAddNum(query.getUserId(), man);
                                        return dto;
                                    }).collect(Collectors.toList());
                        }
                        if (query.getWomenNumNow() > womenNum) {
                            //女 多招的人 返佣的钱从账户余额到冻结金额
                            BigDecimal women = addFemaleRebate.multiply(BigDecimal.valueOf(editWomenNum2));
                            hrAccts.stream()
                                    .map(dto -> {
                                        //账户余额
                                        BigDecimal acctbalance = dto.getAcctbalance();
                                        //账户余额不足 请去充值
                                        Assert.isFalse(acctbalance.subtract(query.getRebateMoney()).compareTo(BigDecimal.valueOf(0)) <= 0,
                                                RlzyConstant.ACCOUNT_BALANCE_IS_EMPTY);
                                        //返费的金额 从账户余额到冻结金额 账户余额 - 钱 冻结金额 + 钱
                                        acctMapper.reimbursementAddNum(query.getUserId(), women);
                                        return dto;
                                    }).collect(Collectors.toList());
                        }
                        //修改入职返佣的钱 简章表
                        query.setRebateMaleEntry(query.getRebateMEntry());
                        query.setRebateFemaleEntry(query.getRebateFentry());
                        //修改返费金额 面试 报道 男女返佣
                        mapper.updateRebateMoney(query);
                        //修改入职返佣的钱 发布简章时返佣的表
                        query.getResignations().stream()
                                .map(t -> {
                                    resignationMapper.resignationEntry(query);
                                    return t;
                                }).collect(Collectors.toList());
                        //修改 男生 女生的招聘人数
                        mapper.updateNumberOfRecruits(query);
                        //修改招聘状态 从正在招到待审核
                        mapper.updateStatus(query.getBriefchapter());
                        return m;
                    }).collect(Collectors.toList());
        } else {
            //招聘单位
            hrBriefchapters.stream()
                    .map(m -> {
                        //发布简章时 招聘的男生数量
                        var ManNum = m.getManNum();
                        // 发布简章时 招聘的女生数量
                        var womenNum = m.getWomenNum();
                        //编辑简章后 少招多少人 男
                        var editManNum = ManNum - query.getManNumNow();
                        editManNum = Math.abs(editManNum);

                        // 编辑简章后 多招多少人 男
                        var editManNum1 = query.getManNumNow() - ManNum;
                        editManNum1 = Math.abs(editManNum1);

                        // 编辑简章后  少招多少人 女
                        var editWomenNum = womenNum - query.getWomenNumNow();
                        editWomenNum = Math.abs(editWomenNum);
                        //编辑简章时 多招多少人 女
                        var editWomenNum2 = query.getWomenNumNow() - womenNum;
                        editWomenNum2 = Math.abs(editWomenNum2);
                       *//* //如果用户输入的返佣金额 < 1000 增加的是用户输入的钱 用户输入的钱 > 1000 就  / 10 面试返佣男 增加的钱
                        var rebateMaleInterview = query.getRebateMaleInterview().compareTo(BigDecimal.valueOf(1000)) < 0 ?
                                query.getRebateMaleInterview() : query.getRebateMaleInterview().divide(BigDecimal.valueOf(10));
                        //...............面试返佣女 增加的钱
                        var rebateFemaleInterview = query.getRebateFemaleInterview().compareTo(BigDecimal.valueOf(1000)) < 0 ?
                                query.getRebateFemaleInterview() : query.getRebateFemaleInterview().divide(BigDecimal.valueOf(10));
                        //...............报道返佣男 增加的钱
                        var rebateMaleReport = query.getRebateMaleReport().compareTo(BigDecimal.valueOf(1000)) < 0 ?
                                query.getRebateMaleReport() : query.getRebateMaleReport().divide(BigDecimal.valueOf(10));
                        //..............报道返佣女 增加的钱
                        var rebateFemaleReport = query.getRebateFemaleReport().compareTo(BigDecimal.valueOf(1000)) < 0 ?
                                query.getRebateFemaleReport() : query.getRebateFemaleReport().divide(BigDecimal.valueOf(10));
                        //入职返佣男 增加的钱
                        var rebateMaleEntry = query.getRebateMaleEntry().compareTo(BigDecimal.valueOf(1000)) < 0 ?
                                query.getRebateMaleEntry() : query.getRebateMaleEntry().divide(BigDecimal.valueOf(10));*//*
                        //发布简章时 面试返佣男
                        BigDecimal maleInterview = m.getRebateMaleInterview();
                        // 发布简章时 面试返佣女
                        BigDecimal femaleInterview = m.getRebateFemaleInterview();
                        // 发布简章时 报道返佣男
                        BigDecimal maleReport = m.getRebateMaleReport();
                        //// 发布简章时 报道返佣女
                        BigDecimal femaleReport = m.getRebateFemaleReport();
                        //简章对应的入职返佣 男
                        BigDecimal maleEntry = m.getRebateMaleEntry();
                        //简章对应的入职返佣 女
                        BigDecimal femaleEntry = m.getRebateFemaleEntry();
                        // 单人返佣 男 减少招聘人数时的的钱 返到账户余额 发布简章时的返佣
                        BigDecimal allRebateMale = maleInterview.add(femaleInterview)
                                .add(maleReport)
                                .add(maleEntry);
                        // 单人返佣 女 减少招聘人数时的的钱 返到账户余额 发布简章时的返佣
                        BigDecimal allRebateFemale = femaleInterview
                                .add(femaleReport)
                                .add(femaleEntry);
                        //编辑简章时男生入职返佣的钱
                        Map<Integer, BigDecimal> resignationMaleEntry = query.getResignations()
                                .stream()
                                .filter(x -> x.getType().equals(2))
                                .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                        CollectorsUtil.summingBigDecimal(EntryResignation::getRebateMaleEntry)));
                        //编辑简章时男生入职返佣的钱
                        Map<Integer, BigDecimal> resignationFemaleEntry = query.getResignations()
                                .stream()
                                .filter(y -> y.getType().equals(2))
                                .collect(Collectors.groupingBy(EntryResignation::getBriefChapterId,
                                        CollectorsUtil.summingBigDecimal(EntryResignation::getRebateFemaleEntry)));
                        final BigDecimal[] reMale = {null};
                        final BigDecimal[] reFemale = {null};
                        //男生入职返佣的钱
                        resignationMaleEntry.forEach((k, v) -> reMale[0] = v);
                        //女生入职返佣的钱
                        resignationFemaleEntry.forEach((k, v) -> reFemale[0] = v);
                        //编辑简章时男生增加的返佣的钱
                        BigDecimal addMaleRebate = query.getRebateMaleInterview().add(query.getRebateMaleReport())
                                .add(reMale[0]);
                        //编辑简章时女生增加的返佣的钱
                        BigDecimal addFemaleRebate = query.getRebateFemaleInterview().add(query.getRebateFemaleReport())
                                .add(reFemale[0]);

                        //查询账户余额 和冻结金额的余额
                        List<HrAcct> hrAccts = acctMapper.selectAcct(query.getUserId());
                        // 少招聘 n 个人的返费金额  返费 = 单人返费 * n 男生招聘数量减少
                        if (query.getManNumNow() < ManNum) {
                            //男 少招的人的返费金额 退到账户余额
                            BigDecimal man = allRebateMale.multiply(BigDecimal.valueOf(editManNum));
                            //钱退到账户余额 账户余额增加钱 冻结金额减少钱
                            acctMapper.reimbursement(query.getUserId(), man);
                        }
                        if (query.getWomenNumNow() < womenNum) {
                            //女生招聘数量减少
                            BigDecimal women = allRebateFemale.multiply(BigDecimal.valueOf(editWomenNum));
                            //女 少招的人的返费金额 退到账户余额
                            acctMapper.reimbursement(query.getUserId(), women);

                        }
                        if (query.getManNumNow() > ManNum) {
                            //男 多招的人 返佣的钱从账户余额到 冻结金额
                            BigDecimal man = addMaleRebate.multiply(BigDecimal.valueOf(editManNum1));

                            hrAccts.stream()
                                    .map(dto -> {
                                        //账户余额
                                        BigDecimal acctbalance = dto.getAcctbalance();
                                        //账户余额不足 请去充值
                                        Assert.isFalse(acctbalance.subtract(query.getRebateMoney()).compareTo(BigDecimal.valueOf(0)) <= 0,
                                                RlzyConstant.ACCOUNT_BALANCE_IS_EMPTY);
                                        //返费的金额 从账户余额到冻结金额 账户余额 - 钱 冻结金额 + 钱
                                        acctMapper.reimbursementAddNum(query.getUserId(), man);
                                        return dto;
                                    }).collect(Collectors.toList());
                        }
                        if (query.getWomenNumNow() > womenNum) {
                            //女 多招的人 返佣的钱从账户余额到冻结金额
                            BigDecimal women = addFemaleRebate.multiply(BigDecimal.valueOf(editWomenNum2));
                            hrAccts.stream()
                                    .map(dto -> {
                                        //账户余额
                                        BigDecimal acctbalance = dto.getAcctbalance();
                                        //账户余额不足 请去充值
                                        Assert.isFalse(acctbalance.subtract(query.getRebateMoney()).compareTo(BigDecimal.valueOf(0)) <= 0,
                                                RlzyConstant.ACCOUNT_BALANCE_IS_EMPTY);
                                        //返费的金额 从账户余额到冻结金额 账户余额 - 钱 冻结金额 + 钱
                                        acctMapper.reimbursementAddNum(query.getUserId(), women);
                                        return dto;
                                    }).collect(Collectors.toList());
                        }
                        //修改入职返佣的钱 简章表
                        query.setRebateMaleEntry(reMale[0]);
                        query.setRebateFemaleEntry(reFemale[0]);
                        //修改返费金额 面试 报道 男女返佣
                        mapper.updateRebateMoney(query);
                        //修改入职返佣的钱 发布简章时返佣的表
                        query.getResignations().stream()
                                .map(t -> {
                                    resignationMapper.resignationEntry(query);
                                    return t;
                                }).collect(Collectors.toList());
                        //修改 男生 女生的招聘人数
                        mapper.updateNumberOfRecruits(query);
                        //修改招聘状态 从正在招
                        mapper.updateStatus(query.getBriefchapter());
                        return m;
                    }).collect(Collectors.toList());
        }*/


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
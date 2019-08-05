package com.nado.rlzy.service.impl;

import cn.hutool.core.lang.Assert;
import com.nado.rlzy.bean.dto.SignUpNumberDto;
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
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    @Override
    public Map<String, Object> myRelease(Integer userId, Integer typeId, Integer status) {
        List<HrBriefchapter> list = mapper.myRelease(userId, typeId, status);
        List<HrBriefchapter> hrBriefchapters = mapper.myReleaseRecruitment(userId, typeId, status);
        List<HrBriefchapter> collect = list.stream().map(dto -> {
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            System.out.println(map);

            //对返佣金额进行 foreach 操作, set到返回的结果集里

            map.forEach((k, v) -> {
                dto.setRebateRecord(v);
            });
            return dto;
        }).collect(Collectors.toList());
        List<HrBriefchapter> briefchapterList = hrBriefchapters.stream()
                .map(dto -> {
                    Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                            CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    System.out.println(map);

                    //对返佣金额进行 foreach 操作, set到返回的结果集里

                    map.forEach((k, v) -> {
                        dto.setRebateRecord(v);
                    });
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
        //简章表
        //initBriefcharpter(query);
        //职位表
        //TODO
        HrRebaterecord rebaterecord = new HrRebaterecord();
        HrBriefchapter briefchapter = new HrBriefchapter();
        //招聘单位
        if (type.equals(5)) {
            //招聘单位 briefchapter.setRecruitedcompanyid( 组织表主键id);
           // briefchapter.setRecruitedcompanyId(query.getRecruitedCompanyId());
            //简章表
            initBriefcharpter(query);
            //职位表
            //TODO
            //有返佣 前台到后台
            if (query.getRebateStatus().equals(1)) {
                //简章表id
                rebaterecord.setBriefchapterId(briefchapter.getId());
                //返佣类型 面试
                if (query.getRebateType().equals(0)) {
                    String male = query.getRebateMale();
                    BigDecimal decimal = StringUtil.decimal(male);
                    String female = query.getRebateFemale();
                    BigDecimal decimal1 = StringUtil.decimal(female);
                    rebaterecord.setRebateType(0);
                    rebaterecord.setRebateMale(decimal);
                    rebaterecord.setRebateFemale(decimal1);
                    rebaterecord.setCreateTime(new Date());
                }
                //返佣类型 报道
                if (query.getRebateType().equals(1)) {
                    String male = query.getRebateMale();
                    BigDecimal decimal = StringUtil.decimal(male);
                    String female = query.getRebateFemale();
                    BigDecimal decimal1 = StringUtil.decimal(female);
                    rebaterecord.setRebateType(1);
                    rebaterecord.setRebateMale(decimal);
                    rebaterecord.setRebateFemale(decimal1);
                    rebaterecord.setCreateTime(new Date());
                }
                //返佣类型 入职
                if (query.getRebateType().equals(2)) {
                    String male = query.getRebateMale();
                    BigDecimal decimal = StringUtil.decimal(male);
                    String female = query.getRebateFemale();
                    BigDecimal decimal1 = StringUtil.decimal(female);
                    rebaterecord.setRebateTime(query.getRebateTime());
                    rebaterecord.setRebateType(2);
                    rebaterecord.setRebateMale(decimal);
                    rebaterecord.setRebateFemale(decimal1);
                    rebaterecord.setCreateTime(new Date());
                }
            } else {
                briefchapter.setRebateStatus(0);
            }
            //代招单位
        } else if (type.equals(6)) {
            //代招单位
            //TODO 当前登录用户的主键id
            //先获取主键
            briefchapter.setCertifierId(8);
            //简章表
            initBriefcharpter(query);
            //职位表
            //TODO
            //有返佣 前台到后台
            if (query.getRebateStatus().equals(1)) {
                //简章表id
                rebaterecord.setBriefchapterId(briefchapter.getId());
                //返佣类型
                if (query.getRebateType().equals(0)) {
                    String male = query.getRebateMale();
                    BigDecimal decimal = StringUtil.decimal(male);
                    String female = query.getRebateFemale();
                    BigDecimal decimal1 = StringUtil.decimal(female);
                    rebaterecord.setRebateType(0);
                    rebaterecord.setRebateMale(decimal);
                    rebaterecord.setRebateFemale(decimal1);
                    rebaterecord.setCreateTime(new Date());
                }
                if (query.getRebateType().equals(1)) {
                    String male = query.getRebateMale();
                    BigDecimal decimal = StringUtil.decimal(male);
                    String female = query.getRebateFemale();
                    BigDecimal decimal1 = StringUtil.decimal(female);
                    rebaterecord.setRebateType(1);
                    rebaterecord.setRebateMale(decimal);
                    rebaterecord.setRebateFemale(decimal1);
                    rebaterecord.setCreateTime(new Date());
                }
                if (query.getRebateType().equals(2)) {
                    String male = query.getRebateMale();
                    BigDecimal decimal = StringUtil.decimal(male);
                    String female = query.getRebateFemale();
                    BigDecimal decimal1 = StringUtil.decimal(female);
                    rebaterecord.setRebateTime(query.getRebateTime());
                    rebaterecord.setRebateType(2);
                    rebaterecord.setRebateMale(decimal);
                    rebaterecord.setRebateFemale(decimal1);
                    rebaterecord.setCreateTime(new Date());
                }
            } else {
                briefchapter.setRebateStatus(0);
            }
        }
        if (rebaterecordMapper.insertSelective(query) >= 1) {
            System.out.println("添加成功");
        } else {
            System.out.println("failed");
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
    public int reportNotSuitable(Integer signUpId) {
        return signUpMapper.reportNotSuitable(signUpId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recruitmentDetailsInvitationInterview(Integer signUpId) {
        ValidationUtil.ValidResult result = ValidationUtil.validateBean(signUpId);
        return signUpMapper.recruitmentDetailsInvitationInterview(signUpId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recruitmentDetailsDirectAdmission(Integer signUpId) {
        return signUpMapper.recruitmentDetailsDirectAdmission(signUpId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recruitmentInterviewd(Integer signUpId) {
        return signUpMapper.recruitmentInterviewd(signUpId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recruitmentNoInterviewd(Integer signUpId) {
        return signUpMapper.recruitmentNoInterviewd(signUpId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recruitmentInterviewFailed(Integer signUpId) {
        return signUpMapper.recruitmentInterviewFailed(signUpId);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recruitmentInterviewSuccess(Integer signUpId) {
        return signUpMapper.recruitmentInterviewSuccess(signUpId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int notReported(Integer signUpId) {
        return signUpMapper.notReported(signUpId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int reported(Integer signUpId) {
        return signUpMapper.reported(signUpId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int noReportedReason(Integer reason, Integer signUpId) {
        return signUpMapper.noReportedReason(reason, signUpId);
    }

    @Override
    public List<HrSignUp> rebate(Integer userId, Integer sex) {
        List<HrSignUp> sign = signUpMapper.rebate(userId);
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
        if (query.getTypeId().equals(1)) {
            //更改返佣状态
            signUpMapper.rebateOne(query.getRebateId());
            // 本人 -- 求职者账户增加钱
            signUpMapper.rebateAdd(query);
            // 企业 -- 从企业账户冻结金额扣钱
            signUpMapper.rebateSubtraction(query);

        } else if (query.getTypeId().equals(2)) {
            //更改返佣状态
            signUpMapper.rebateOne(query.getRebateId());
            // 本人 -- 求职者账户增加钱
            signUpMapper.rebateAdd(query);
            // 企业 -- 从企业账户冻结金额扣钱
            signUpMapper.rebateSubtraction(query);
        } else {
            Assert.isFalse(query.getTypeId() != 1 || query.getTypeId() != 2, RlzyConstant.OPS_FAILED_MSG);
        }

        signUpMapper.rebateAdd(query);
        signUpMapper.rebateSubtraction(query);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeJobStatus(Integer signUpId, Integer status, Integer currentState) {
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
                            signUpMapper.changeJobStatus(signUpId, status);

                            //报到时间 < 现在时间
                        } else if (registerTime.compareTo(nowTime) < 0) {
                            signUpMapper.changeJobStatus(signUpId, status);
                        }
                        return u;
                    }).collect(Collectors.toList());
        }
        //未报到
        if (currentState.equals(2)) {
            signUpMapper.changeJobStatus(signUpId, status);
        }
        //返佣中断, 未返佣
        if (currentState.equals(4)) {
            signUpMapper.commissionRebate(signUpId);
            signUpMapper.changeJobStatus(signUpId, status);
        }

        return 1;
    }

    @Override
    public List<SignUpNumberDto> numberOfRecruitsFull(Integer briefchapter) {
        List<SignUpNumberDto> numberDtos = signUpMapper.countSignUpNum(briefchapter);
        List<SignUpNumberDto> list = numberDtos.stream().map(dto -> {

            Integer numberOfRecruit = dto.getWomenNum() + dto.getManNum();
            Integer signUpNum = dto.getBoyNum() + dto.getGirlNum();
            if (numberOfRecruit.equals(signUpNum)) {
                dto.setFull(0);
            }
            return dto;
        }).collect(Collectors.toList());
        return list;
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
    public int directAdmission(Integer signUpId, Integer userId, Integer sex) {

        HrSignUp hrSignUp = signUpMapper.SearchdirectAdmission(signUpId, sex);
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
        return null;
    }

    @Override
    public Integer editBriefchapterMyRelease(EditBriefchapterQuery query) {
        var briefcharpterQuery = new BriefcharpterQuery();
        briefcharpterQuery.setBriefcharpterId(query.getBriefchapter());

        List<HrBriefchapter> list = mapper.queryBriefcharpterDetileByParams(briefcharpterQuery);

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
                    var finalEditManNum = editManNum1;
                    var finalEditWomenNum = editWomenNum2;
                    var finalEditManNum1 = editManNum;
                    var finalEditWomenNum1 = editWomenNum;
                    //如果用户输入的返佣金额 < 1000 那就增加 用户输入的钱 > 1000 就  / 10
                    var man = query.getRebateNowMan().compareTo(BigDecimal.valueOf(1000)) < 0 ? query.getRebateNowMan() : query.getRebateNowMan().divide(BigDecimal.valueOf(10));
                    var women = query.getRebateNowWomen().compareTo(BigDecimal.valueOf(1000)) < 0 ? query.getRebateNowWomen() : query.getRebateNowWomen().divide(BigDecimal.valueOf(10));
                    // 多招聘 n 个人  返费 = 单人返费 * n

                    if (query.getManNumNow() > ManNum && query.getWomenNumNow() > womenNum) {

                        //男 女 多招的人的返费金额
                        man = man.multiply(BigDecimal.valueOf(finalEditManNum));
                        women = women.multiply(BigDecimal.valueOf(finalEditWomenNum));
                        //返费 金额 存到  临时字段
                        rebaterecordMapper.updateRebateMoney(women.add(man), query.getBriefchapter(), null);

                    } else if (query.getManNumNow() > ManNum && query.getWomenNumNow() < womenNum) {
                        //男  多招的人的返费金额
                        man = man.multiply(BigDecimal.valueOf(finalEditManNum));
                        // 女 少招 的钱 退到账户余额
                        women = women.multiply(BigDecimal.valueOf(finalEditWomenNum));
                        //返费 金额 存到  临时字段
                        rebaterecordMapper.updateRebateMoney(man, query.getBriefchapter(), null);

                        //少招人的钱退到余额
                        acctMapper.returnMoney(query.getUserId(), women);
                    } else if (query.getWomenNumNow() > womenNum && query.getManNumNow() < ManNum) {
                        // 男 少招 的钱 退到账户余额
                        man = man.multiply(BigDecimal.valueOf(finalEditManNum));
                        //女  多招的人的返费金额
                        women = women.multiply(BigDecimal.valueOf(finalEditWomenNum));
                        //返费 金额 存到  临时字段
                        rebaterecordMapper.updateRebateMoney(women, query.getBriefchapter(), null);

                        //少招人的钱退到余额
                        acctMapper.returnMoney(query.getUserId(), man);

                    } else if (query.getManNumNow() < ManNum && query.getWomenNumNow() < womenNum) {
                        //钱退到账户余额
                        //男女 少招的人的返费金额
                        //少招聘n个人 返费 = 单人返费 * n
                        man = man.multiply(BigDecimal.valueOf(finalEditManNum1));
                        women = women.multiply(BigDecimal.valueOf(finalEditWomenNum1));
                        acctMapper.returnMoney(query.getUserId(), man.add(women));
                    }

                    m.getRebat()
                            .stream()
                            .map(u -> {
                                //返佣的钱
                                if (query.getRebateTypeOne().equals(0)) {
                                    //面试返佣
                                    rebaterecordMapper.updateRebateManAndWomen(query.getAddMoneyMan(), query.getAddMoneyWomen(), query.getRebateTypeZero(), query.getBriefchapter());
                                }
                                if (query.getRebateTypeOne().equals(1)) {
                                    //报道返佣
                                    rebaterecordMapper.updateRebateManAndWomen(query.getReportMan(), query.getReportWomen(), query.getRebateTypeZero(), query.getBriefchapter());
                                }
                                if (query.getRebateTypeTwo().equals(2)) {
                                    //入职返佣
                                    rebaterecordMapper.updateRebateManAndWomen(query.getEntryMan(), query.getEntryWomen(), query.getRebateTypeZero(), query.getBriefchapter());
                                }
                                return u;
                            }).collect(Collectors.toList());

                    mapper.updateStatus(query.getBriefchapter());
                    return m;
                }).collect(Collectors.toList());
        return 1;
    }

    @Override
    public List<HrGroup> selectGroupName(Integer type, Integer userId) {
        return groupMapper.selectGroupName(type, userId).stream()
        .collect(Collectors.toList());
    }

    @Override
    public Integer updateJobStatusInterviewed(Integer signUpId) {
        return signUpMapper.updateJobStatusInterviewed(signUpId);

    }


    private void initBriefcharpter(ReleaseBriefcharpterQuery query) {
        HrBriefchapter dto = new HrBriefchapter();
        dto.setPostId(query.getPostId());

        dto.setProfessionId(query.getProfessionId());
        dto.setManAgeId(query.getManAgeId());
        dto.setWomenAgeId(query.getWomenAgeId());
        dto.setEducationId(query.getEducationId());
        dto.setExperienceId(query.getExperienceId());
        dto.setWorkWayId(query.getWorkWayId());
        dto.setWorkTimeArrangeId(query.getWorkTimeArrangeId());
        dto.setClothingRequirementId(Integer.valueOf(query.getClothingRequirementId()));
        dto.setHobbyId(query.getHobbyId());
        dto.setOvertimeTimeId(query.getOvertimeTimeId());
        dto.setWelfareId(query.getWelfareId());
        dto.setPostDetail(query.getPostDetail());
        String interviewTime = query.getInterviewTime();
        LocalDateTime dateTime = StringUtil.strToLocalDateTime(interviewTime);
        dto.setInterviewTime(dateTime);
        String time = query.getRegisterTime();
        LocalDateTime localDateTime = StringUtil.strToLocalDateTime(time);
        dto.setRegisterTime(localDateTime);
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
            dto.setContractWayDetailId(Integer.valueOf(query.getContractWayDetailId()));
        } else if (query.getContractWay() == 1) {
            //用工单位
            dto.setContractWayDetailId(Integer.valueOf(query.getContractWayDetailId()));
        } else if(query.getContractWay() == 2) {
            //厂方指定劳务公司
            dto.setContractWayDetailId(Integer.valueOf(query.getContractWayDetailId()));
        }else {
            System.out.println("o");
        }
        if (mapper.save(query) >= 1) {
            System.out.println("添加成功");
        }
    }
}
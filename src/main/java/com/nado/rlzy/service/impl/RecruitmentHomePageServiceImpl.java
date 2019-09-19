package com.nado.rlzy.service.impl;

import com.nado.rlzy.bean.dto.JobListDto;
import com.nado.rlzy.bean.frontEnd.JobListtFront;
import com.nado.rlzy.bean.query.JobListQuery;
import com.nado.rlzy.db.mapper.*;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.RecruitmentHomePageService;
import com.nado.rlzy.utils.AssertUtil;
import com.nado.rlzy.utils.CheckParametersUtil;
import com.nado.rlzy.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName 招聘版首页service实现类
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/2 14:06
 * @Version 1.0
 */
@Service
public class RecruitmentHomePageServiceImpl implements RecruitmentHomePageService {

    @Resource
    private HrSignUpMapper mapper;

    @Resource
    private CollectMapper collectMapper;


    @Resource
    private HrBriefchapterMapper briefchapterMapper;

    @Resource
    private HrUserMapper userMapper;

    @Resource
    private HrSignupDeliveryrecordMapper signupDeliveryrecordMapper;

    @Resource
    private HrGroupMapper groupMapper;


    @Override
    public List<HrSignUp> selectJobListOverview(JobListQuery query) {

        List<HrSignUp> listDtos = mapper.selectJobListOverview(query);
        List<HrSignUp> collect = listDtos.stream().map(dto -> {
            if (dto.getType().equals(1)) {
                dto.setCommendName("本人");
            }
            return dto;
        }).collect(Collectors.toList());
        return collect;

    }

    @Override
    public List<JobListtFront> selectJobList(JobListQuery query) {
        List<JobListDto> dtos = mapper.selectJobList(query);
        return dtos.stream().map(dto -> {
            JobListtFront front = new JobListtFront();
            BeanUtils.copyProperties(dto, front);
            Integer noSignUp = dto.getNoSignUp();
            String s = StringUtil.toString(noSignUp);
            front.setNoSignUp(s);

            Integer age = dto.getAge();
            String s1 = StringUtil.toString(age);
            front.setAge(s1);

            Integer jobStatus = dto.getJobStatus();
            String s3 = StringUtil.toString(jobStatus);
            front.setJobStatus(s3);
            Integer relation = dto.getRelation();
            String s4 = StringUtil.toString(relation);
            front.setRelation(s4);

            Integer signUpId = dto.getSignUpId();
            String string = StringUtil.toString(signUpId);
            front.setSignUpId(string);
            Integer briefChapterId = dto.getBriefChapterId();
            String s2 = StringUtil.toString(briefChapterId);
            front.setBriefChapterId(s2);

            double expectedSalaryUpper = dto.getExpectedSalaryUpper().doubleValue();
            String s5 = StringUtil.decimalFormatByInt(expectedSalaryUpper);
            front.setExpectedSalaryUpper(s5);

            double expectedSalaryLower = dto.getExpectedSalaryLower().doubleValue();
            String s6 = StringUtil.decimalFormatByInt(expectedSalaryLower);
            front.setExpectedSalaryLower(s6);
            front.setExpectedSalary(s6 + "k" + "-" + s5 + "k");



            Integer sex1 = dto.getSex();
            String s7 = StringUtil.toString(sex1);
            front.setSex(s7);


            if (front.getNoSignUp().equals("0")) {
                if (front.getSex().equals("0")) {
                    String frm = front.getUserName().substring(0, 1) + "女士";
                    front.setSex(frm);
                }
                if (front.getSex().equals("1")) {
                    String man = front.getUserName().substring(0, 1) + "先生";
                    front.setSex(man);
                }
                String cardId = front.getCardId();
                String s8 = StringUtil.realName(cardId);
                front.setCardId(s8);
            }

            return front;
        }).collect(Collectors.toList());
    }

    @Override
    public List<HrSignUp> selectCollectListOverview(Integer userId) {
        List<HrSignUp> jobListDtos = mapper.selectCollectListOverview(userId);
        return jobListDtos.stream().map(dto -> {
            String signUpName = dto.getSignUpName();
            String commendName = dto.getCommendName();
            if (signUpName.compareTo(commendName) == 0) {
                dto.setRecommendName("自己");
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(Collect collect) {
        CheckParametersUtil.getInstance()
                .put(collect.getUserId(), "userId")
                .put(collect.getSignUpId(), "signUpId");


        collect.setCreateTime(new Date());
        collect.setUserId(collect.getUserId());
        collect.setSignUpId(collect.getSignUpId());

        AssertUtil.isTrue(collectMapper.insertSelective(collect) <= 0, RlzyConstant.OPS_FAILED_MSG);
        return collect.getId();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateSignUpCollectStatus(Collect collect) {
        collect.setDeleteFlag(1);
        AssertUtil.isTrue(collectMapper.updateByPrimaryKeySelecti(collect) <= 0, RlzyConstant.OPS_FAILED_MSG);
        return 1;
    }


    @Override
    public HashMap<String, Object> representativeUnitSubAccount(Integer userId) {
        HrUser hrUser = userMapper.checkUserIdentity(userId);
        Integer type = Optional.ofNullable(hrUser).orElseGet(HrUser::new).getType();
        HashMap<String, Object> map = new HashMap<>();
        if (type.equals(6)) {
            //代招企业
            List<HrBriefchapter> list = briefchapterMapper.representativeUnitSubAccount(userId);
            List<HrBriefchapter> collect = list.stream()
                    .map(dto -> {
                        Integer no = Optional.ofNullable(dto).orElseGet(HrBriefchapter::new).getRecruitingNo();
                        if (!(no.equals(0))) {
                            //剩余招聘人数 不等于0 显示
                            dto.setNo(no + "人");
                        }
                        dto.setManAgeId(1);
                        dto.setWomenAgeId(0);
                        //月综合
                        double value = dto.getAvgSalary().doubleValue();
                        String format = StringUtil.decimalFormat2(value);
                        dto.setAvgSalary1(format + "元起");
                        //计薪
                        double value1 = dto.getDetailSalary().doubleValue();
                        String s1 = StringUtil.decimalFormat2(value1);
                        String detailSalaryWay = dto.getDetailSalaryWay();
                        dto.setDetailSalry1(s1 + "元/" + detailSalaryWay);
                        BigDecimal rebateMaleInterview = dto.getRebateMaleInterview();
                        BigDecimal rebateMaleReport = dto.getRebateMaleReport();
                        BigDecimal rebateMaleEntry = dto.getRebateMaleEntry();
                        BigDecimal rebateFemaleInterview = dto.getRebateFemaleInterview();
                        BigDecimal rebateFemaleReport = dto.getRebateFemaleReport();
                        BigDecimal rebateFemaleEntry = dto.getRebateFemaleEntry();
                        if (null != rebateMaleInterview && null != rebateMaleReport && null != rebateMaleEntry &&
                                null != rebateFemaleInterview && null != rebateFemaleReport && null != rebateFemaleEntry) {
                            //男生返佣的钱
                            BigDecimal add = rebateMaleInterview.add(rebateMaleReport)
                                    .add(rebateMaleEntry);

                            BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                                    .add(rebateFemaleEntry);
                            //女生返佣的钱
                            BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                            String rebateMoney = StringUtil.decimalToString(n);
                            dto.setRebateRecord("返" + rebateMoney + "元");
                        }

                        return dto;
                    })
                    .collect(Collectors.toList());
            map.put("representativeUnit", collect);
        } else {
            //招聘企业
            List<HrBriefchapter> list = briefchapterMapper.recruitmentUnitSubAccount(userId).stream()
                    .map(dto -> {
                        Integer no = Optional.ofNullable(dto).orElseGet(HrBriefchapter::new).getRecruitingNo();
                        if (!(no.equals(0))) {
                            //剩余招聘人数 不等于0 显示
                            dto.setNo(no + "人");
                        }
                        dto.setManAgeId(1);
                        dto.setWomenAgeId(0);
                        //月综合
                        double value = dto.getAvgSalary().doubleValue();
                        String format = StringUtil.decimalFormat2(value);
                        dto.setAvgSalary1(format + "元起");
                        //计薪
                        double value1 = dto.getDetailSalary().doubleValue();
                        String s1 = StringUtil.decimalFormat2(value1);
                        String detailSalaryWay = dto.getDetailSalaryWay();
                        dto.setDetailSalry1(s1 + "元/" + detailSalaryWay);
                        BigDecimal rebateMaleInterview = dto.getRebateMaleInterview();
                        BigDecimal rebateMaleReport = dto.getRebateMaleReport();
                        BigDecimal rebateMaleEntry = dto.getRebateMaleEntry();
                        BigDecimal rebateFemaleInterview = dto.getRebateFemaleInterview();
                        BigDecimal rebateFemaleReport = dto.getRebateFemaleReport();
                        BigDecimal rebateFemaleEntry = dto.getRebateFemaleEntry();
                        if (null != rebateMaleInterview && null != rebateMaleReport && null != rebateMaleEntry &&
                                null != rebateFemaleInterview && null != rebateFemaleReport && null != rebateFemaleEntry) {
                            //男生返佣的钱
                            BigDecimal add = rebateMaleInterview.add(rebateMaleReport)
                                    .add(rebateMaleEntry);

                            BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                                    .add(rebateFemaleEntry);
                            //女生返佣的钱
                            BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                            String rebateMoney = StringUtil.decimalToString(n);
                            dto.setRebateRecord("返" + rebateMoney + "元");
                        }
                        return dto;
                    })
                    .collect(Collectors.toList());
            map.put("recruitmentUnit", list);
        }
        return map;
    }

    @Override
    public Map<String, Object> recruitmentBriefchapter(Integer userId) {
        HrUser hrUser = userMapper.checkUserIdentity(userId);
        Integer type = Optional.ofNullable(hrUser).orElseGet(HrUser::new).getType();
        HashMap<String, Object> map = new HashMap<>();
        if (type.equals(6)) {
            //代招企业
            List<HrBriefchapter> list = briefchapterMapper.representativeUnit(userId);
            List<HrBriefchapter> collect = list.stream()
                    .map(dto -> {
                        Integer no = Optional.ofNullable(dto).orElseGet(HrBriefchapter::new).getRecruitingNo();
                        if (!(no.equals(0))) {
                            //剩余招聘人数 不等于0 显示
                            dto.setNo(no + "人");
                        }
                        dto.setManAgeId(1);
                        dto.setWomenAgeId(0);
                        //月综合
                        double value = dto.getAvgSalary().doubleValue();
                        String format = StringUtil.decimalFormat2(value);
                        dto.setAvgSalary1(format + "元起");
                        //计薪
                        double value1 = dto.getDetailSalary().doubleValue();
                        String s1 = StringUtil.decimalFormat2(value1);
                        String detailSalaryWay = dto.getDetailSalaryWay();
                        dto.setDetailSalry1(s1 + "元/" + detailSalaryWay);
                        BigDecimal rebateMaleInterview = dto.getRebateMaleInterview();
                        BigDecimal rebateMaleReport = dto.getRebateMaleReport();
                        BigDecimal rebateMaleEntry = dto.getRebateMaleEntry();
                        BigDecimal rebateFemaleInterview = dto.getRebateFemaleInterview();
                        BigDecimal rebateFemaleReport = dto.getRebateFemaleReport();
                        BigDecimal rebateFemaleEntry = dto.getRebateFemaleEntry();
                        if (null != rebateMaleInterview && null != rebateMaleReport && null != rebateMaleEntry &&
                                null != rebateFemaleInterview && null != rebateFemaleReport && null != rebateFemaleEntry) {
                            //男生返佣的钱
                            BigDecimal add = rebateMaleInterview.add(rebateMaleReport)
                                    .add(rebateMaleEntry);

                            BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                                    .add(rebateFemaleEntry);
                            //女生返佣的钱
                            BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                            String rebateMoney = StringUtil.decimalToString(n);
                            dto.setRebateRecord("返" + rebateMoney + "元");
                        }

                        return dto;
                    })
                    .collect(Collectors.toList());
            map.put("representativeUnit", collect);
        } else {
            //招聘企业
            List<HrBriefchapter> list = briefchapterMapper.recruitmentUnit(userId).stream()
                    .map(dto -> {
                        Integer no = Optional.ofNullable(dto).orElseGet(HrBriefchapter::new).getRecruitingNo();
                        if (!(no.equals(0))) {
                            //剩余招聘人数 不等于0 显示
                            dto.setNo(no + "人");
                        }
                        dto.setManAgeId(1);
                        dto.setWomenAgeId(0);
                        //月综合
                        double value = dto.getAvgSalary().doubleValue();
                        String format = StringUtil.decimalFormat2(value);
                        dto.setAvgSalary1(format + "元起");
                        //计薪
                        double value1 = dto.getDetailSalary().doubleValue();
                        String s1 = StringUtil.decimalFormat2(value1);
                        String detailSalaryWay = dto.getDetailSalaryWay();
                        dto.setDetailSalry1(s1 + "元/" + detailSalaryWay);
                        BigDecimal rebateMaleInterview = dto.getRebateMaleInterview();
                        BigDecimal rebateMaleReport = dto.getRebateMaleReport();
                        BigDecimal rebateMaleEntry = dto.getRebateMaleEntry();
                        BigDecimal rebateFemaleInterview = dto.getRebateFemaleInterview();
                        BigDecimal rebateFemaleReport = dto.getRebateFemaleReport();
                        BigDecimal rebateFemaleEntry = dto.getRebateFemaleEntry();
                        if (null != rebateMaleInterview && null != rebateMaleReport && null != rebateMaleEntry &&
                                null != rebateFemaleInterview && null != rebateFemaleReport && null != rebateFemaleEntry) {
                            //男生返佣的钱
                            BigDecimal add = rebateMaleInterview.add(rebateMaleReport)
                                    .add(rebateMaleEntry);

                            BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                                    .add(rebateFemaleEntry);
                            //女生返佣的钱
                            BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                            String rebateMoney = StringUtil.decimalToString(n);
                            dto.setRebateRecord("返" + rebateMoney + "元");
                        }
                        return dto;
                    })
                    .collect(Collectors.toList());
            map.put("recruitmentUnit", list);
        }
        return map;
    }


    @Override
    public List<HrUser> referrer(JobListQuery query) {
        List<HrUser> hrUsers = userMapper.selectReferrer(query);
        List<HrUser> collect = hrUsers.stream().map(dto -> {
            Integer recommendNo = dto.getRecommendNo();
            dto.setRecommend(recommendNo + "人");
            return dto;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<HrUser> referrerDetails(Integer userId) {

        List<HrUser> list = userMapper.selectReferrerDetails(userId);
        int interviewed = userMapper.interviewed(userId);
        int arReported = userMapper.arReported(userId);
        int noReported = userMapper.noReported(userId);
        int noInterview = userMapper.noInterview(userId);
        int jobSeeker = userMapper.jobSeeker(userId);
        int numberViolations = signupDeliveryrecordMapper.selectNumberViolations(userId);


        list.stream()
                .map(dto -> {

                    Integer recommendNo = dto.getRecommendNo();
                    dto.setRecommend(recommendNo + "人");
                    dto.setInterviewed("参加面试" + interviewed + "人");
                    dto.setArReported("报道" + arReported + "人");
                    dto.setNoInterview( "未面试" + noInterview + "人");
                    dto.setNoReported("未报到" + noReported + "人");

                    dto.setJobSeeker( "拥有求职者" +jobSeeker + "人");
                    dto.setViolationRecord("有责未报到" + numberViolations + "次  ");
                    return dto;
                })
                .collect(Collectors.toList());
        return list;
    }


    @Override
    public int collectSignUPTable(Integer userId, Integer signUpId) {
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setSignUpId(signUpId);
        collect.setCreateTime(new Date());
        collectMapper.insertSelective(collect);
        return collect.getId();
    }



    @Override
    public int collectCancel(Integer id) {
        Collect collect = new Collect();
        collect.setDeleteFlag(1);
        return collectMapper.insertSelective(collect);

    }


    @Override
    public String subAccountPermission(Integer userId) {
        return userMapper.subAccountPermission(userId);
    }

    @Override
    public HrUser checkUserIdentity(Integer userId) {
        return userMapper.checkUserIdentity(userId);
    }

    @Override
    public Integer selectCoCertificationStatus(Integer userId) {
        HrUser hrUser = userMapper.checkUserIdentity(userId);
        Integer type = Optional.ofNullable(hrUser).orElseGet(HrUser::new).getType();
        HrGroup group = groupMapper.selectCoCertificationStatus(userId, type);
        Integer status = Optional.ofNullable(group).orElseGet(HrGroup::new).getStatus();
        return status;

    }

    @Override
    public int collectReferrer(Integer userId) {
        Collect collect = new Collect();
        collect.setUserId(userId);
        collectMapper.insertSelective(collect);
        return collect.getId();
    }



    public static void main(String[] args) {
        String realName = "320673199611152349";
        /*realName = realName.substring(0, 4) + replaceStr(realName.substring(4, 14)) + realName.substring(14, 18);
        System.out.println(realName);
        System.out.println("aaa");*/
        System.out.println(StringUtil.realName(realName));
    }

   /* private static String replaceStr(String realName) {
        StringBuffer stringBuffer = new StringBuffer();
        if (StringUtils.isNotBlank(realName)) {
            for (int i = 0; i < realName.length(); i++) {
                stringBuffer.append("*");
            }
        }


        return stringBuffer.toString();


    }*/


}
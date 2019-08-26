package com.nado.rlzy.service.impl;

import cn.hutool.core.lang.Assert;
import com.nado.rlzy.bean.dto.JobListDto;
import com.nado.rlzy.bean.frontEnd.JobListtFront;
import com.nado.rlzy.bean.query.JobListQuery;
import com.nado.rlzy.db.mapper.CollectMapper;
import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.mapper.HrUserMapper;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.RecruitmentHomePageService;
import com.nado.rlzy.utils.CheckParametersUtil;
import com.nado.rlzy.utils.CollectorsUtil;
import com.nado.rlzy.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private HrSignUpMapper mapper;

    @Autowired
    private CollectMapper collectMapper;


    @Autowired
    private HrBriefchapterMapper briefchapterMapper;

    @Autowired
    private HrUserMapper userMapper;


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

            double expectedSalaryLower = dto.getExpectedSalaryLower().doubleValue();
            String s5 = StringUtil.decimalFormatByInt(expectedSalaryLower);
            front.setExpectedSalaryLower(s5);

            double doubleValue = dto.getExpectedSalaryUpper().doubleValue();
            String s6 = StringUtil.decimalFormatByInt(doubleValue);
            front.setExpectedSalaryUpper(s6);

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
                cardId = cardId.substring(0, 3) + replaceStr(cardId.substring(3, 6)) + cardId.substring(6, 14) + replaceStr(cardId.substring(14, 18));
                front.setCardId(cardId);
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

   /* @Override
    public List<JobListtFront> selectCollectList() {
        List<JobListDto> dtos = mapper.selectCollectList();
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


                  Integer briefChapterId = dto.getBriefChapterId();
                  String s2 = StringUtil.toString(briefChapterId);
                  front.setBriefChapterId(s2);

                  double expectedSalaryLower = dto.getExpectedSalaryLower().doubleValue();
                  String s5 = StringUtil.decimalFormatByInt(expectedSalaryLower);
                  front.setExpectedSalaryLower(s5);

                  double doubleValue = dto.getExpectedSalaryUpper().doubleValue();
                  String s6 = StringUtil.decimalFormatByInt(doubleValue);
                  front.setExpectedSalaryUpper(s6);

                  Integer sex1 = dto.getSex();
                  String s7 = StringUtil.toString(sex1);
                  front.setSex(s7);


                  if (front.getNoSignUp().equals("0")) {
                      throw new IllegalArgumentException("error param!");
                  }
                  return front;



        }).collect(Collectors.toList());
    }*/

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(Collect collect) {
        CheckParametersUtil.getInstance()
                .put(collect.getUserId(), "userId")
                .put(collect.getSignUpId(), "signUpId");


        collect.setCreateTime(new Date());
        collect.setUserId(collect.getUserId());
        collect.setSignUpId(collect.getSignUpId());

        Assert.isFalse(collectMapper.insertSelective(collect) <= 0, RlzyConstant.OPS_FAILED_MSG);
        return collect.getId();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateSignUpCollectStatus(Collect collect) {
        collect.setDeleteFlag(1);
        Assert.isFalse(collectMapper.updateByPrimaryKeySelecti(collect) <= 0, RlzyConstant.OPS_FAILED_MSG);
        return 1;
    }


    @Override
    public Map<String, Object> recruitmentBriefchapter(Integer userId, Integer type) {
        HashMap<String, Object> map = new HashMap<>();
        if (type.equals(1)) {
            //代招企业
            List<HrBriefchapter> list = briefchapterMapper.representativeUnit(userId);
            List<HrBriefchapter> collect = list.stream()
                    .map(dto -> {
                        Integer no = dto.getRecruitingNo();
                        if (!(dto.getRecruitingNo().equals(0))) {
                            //剩余招聘人数 不等于0 显示
                            dto.setNo(no + "人");
                        }
                        //月综合
                        double value = dto.getAvgSalary().doubleValue();
                        String format = StringUtil.decimalFormat2(value);
                        dto.setAvgSalary1(format + "元起");
                        //计薪
                        double value1 = dto.getDetailSalary().doubleValue();
                        String s1 = StringUtil.decimalFormat2(value1);
                        String detailSalaryWay = dto.getDetailSalaryWay();
                        dto.setDetailSalry1(s1 + "元/" + detailSalaryWay);
                        Map<Integer, BigDecimal> mapp = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                                CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                        //对返佣金额进行 foreach 操作, set到返回的结果集里
                        mapp.forEach((k, v) -> {
                            BigDecimal mm = v;
                            double v1 = mm.doubleValue();
                            String s = StringUtil.decimalFormat2(v1);
                            if (v != null) {
                                s = "返" + s + "元";
                                dto.setRebateRecord(s);
                            } else {
                                dto.setRebateRecord("无返佣");
                            }
                        });
                        return dto;
                    })
                    .collect(Collectors.toList());
            map.put("representativeUnit", collect);
        } else {
            //招聘企业
            List<HrBriefchapter> list = briefchapterMapper.recruitmentUnit(userId).stream()
                    .map(dto -> {
                        Integer no = dto.getRecruitingNo();
                        if (!(dto.getRecruitingNo().equals(0))) {
                            //剩余招聘人数 不等于0 显示
                            dto.setNo(no + "人");
                        }
                        //月综合
                        double value = dto.getAvgSalary().doubleValue();
                        String format = StringUtil.decimalFormat2(value);
                        dto.setAvgSalary1(format + "元起");
                        //计薪
                        double value1 = dto.getDetailSalary().doubleValue();
                        String s1 = StringUtil.decimalFormat2(value1);
                        String detailSalaryWay = dto.getDetailSalaryWay();
                        dto.setDetailSalry1(s1 + "元/" + detailSalaryWay);
                        Map<Integer, BigDecimal> mapp = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                                CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                        //对返佣金额进行 foreach 操作, set到返回的结果集里
                        mapp.forEach((k, v) -> {
                            BigDecimal mm = v;
                            double v1 = mm.doubleValue();
                            String s = StringUtil.decimalFormat2(v1);
                            if (v != null) {
                                s = "返" + s + "元";
                                dto.setRebateRecord(s);
                            } else {
                                dto.setRebateRecord("无返佣");
                            }
                        });
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
            Integer recommendNoLower = dto.getRecommendNoLower();
            Integer recommendNoUpper = dto.getRecommendNoUpper();
            dto.setRecommend(recommendNoLower + "-" + recommendNoUpper + "人");
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
        list.stream()
                .map(dto -> {
                    Integer recommendNoLower = dto.getRecommendNoLower();
                    Integer recommendNoUpper = dto.getRecommendNoUpper();
                    dto.setRecommend(recommendNoLower + "-" + recommendNoUpper + "人");
                    dto.setInterviewed("参加面试" + interviewed + "人");
                    dto.setArReported("报道" + arReported + "人");
                    dto.setNoInterview( "未面试" + noInterview + "人");
                    dto.setNoReported("未报到" + noReported + "人");

                    dto.setJobSeeker( "拥有求职者" +jobSeeker + "人");
                    dto.setViolationRecord("有责未报到" + dto.getViolationRecord() + "次  ");
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
    public int collectReferrer(Integer userId) {
        Collect collect = new Collect();
        collect.setUserId(userId);
        collectMapper.insertSelective(collect);
        return collect.getId();
    }



    public static void main(String[] args) {
        String realName = "320673199611152349";
        realName = realName.substring(0, 3) + replaceStr(realName.substring(3, 6)) + realName.substring(6, 14) + replaceStr(realName.substring(14, 18));
        System.out.println(realName);
        System.out.println("aaa");
    }

    private static String replaceStr(String realName) {
        StringBuffer stringBuffer = new StringBuffer();
        if (StringUtils.isNotBlank(realName)) {
            for (int i = 0; i < realName.length(); i++) {
                stringBuffer.append("*");
            }
        }


        return stringBuffer.toString();


    }


}
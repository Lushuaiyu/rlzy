package com.nado.rlzy.service.impl;

import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.mapper.HrGroupMapper;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.mapper.HrUserMapper;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.db.pojo.HrUser;
import com.nado.rlzy.service.JobSeekingPersonalCenterService;
import com.nado.rlzy.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName 求职端个人中心实现类
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/22 14:59
 * @Version 1.0
 */
@Service
public class JobSeekingPersonalCenterServiceImpl implements JobSeekingPersonalCenterService {

    @Resource
    private HrBriefchapterMapper mapper;

    @Resource
    private HrSignUpMapper signUpMapper;

    @Resource
    private HrUserMapper userMapper;

    @Resource
    private HrGroupMapper groupMapper;

    @Override
    public List<HrBriefchapter> recruitmentBrochureCollectionRecruitment(Integer userId, Integer type) {
        return mapper.recruitmentBrochureCollectionRecruitment(userId, type)
                .stream()
                .map(dto -> {
                    //月综合
                    double value = dto.getAvgSalary().doubleValue();
                    String format = StringUtil.decimalFormat2(value);
                    dto.setAvgSalary1(format + "元起");
                    //计薪
                    double value1 = dto.getDetailSalary().doubleValue();
                    String s1 = StringUtil.decimalFormat2(value1);
                    String detailSalaryWay = dto.getDetailSalaryWay();
                    dto.setDetailSalry1(s1 + "元/" + detailSalaryWay);
                    dto.setNo(dto.getRecruitingNo() + "人");
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
    }

    @Override
    public List<HrBriefchapter> recruitmentBrochureCollection(Integer userId, Integer type) {
        return mapper.recruitmentBrochureCollection(userId, type)
                .stream()
                .map(dto -> {
                    //月综合
                    double value = dto.getAvgSalary().doubleValue();
                    String format = StringUtil.decimalFormat2(value);
                    dto.setAvgSalary1(format + "元起");
                    //计薪
                    double value1 = dto.getDetailSalary().doubleValue();
                    String s1 = StringUtil.decimalFormat2(value1);
                    String detailSalaryWay = dto.getDetailSalaryWay();
                    dto.setDetailSalry1(s1 + "元/" + detailSalaryWay);
                    dto.setNo(dto.getRecruitingNo() + "人");
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
                }).collect(Collectors.toList());

    }

    @Override
    public List<HrSignUp> selectSignUpTable(Integer signId, Integer userId) {
        return signUpMapper.selectSignUpTable(signId, userId).stream().collect(Collectors.toList());
    }


    @Override
    public List<HrSignUp> selectSignUp(Integer userId) {
        return signUpMapper.selectSignUp(userId).stream().collect(Collectors.toList());
    }


    @Override
    public List<HrUser> queryMyselfVillation(Integer userId) {
        List<HrUser> list = userMapper.queryMyselfVillation(userId);
        return list.stream()
                .map(dto -> {
                    dto.getDeliveryrecords()
                            .stream().map(x -> {
                        Integer jobStatus = x.getJobStatus();
                        if (jobStatus.equals(8)) {
                            //未面试
                            x.setInterviewFont("未面试");
                        }
                        if (jobStatus.equals(9)) {
                            //未报到
                            x.setReportFont("未报到");
                        }
                        return x;
                    }).collect(Collectors.toList());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<HrUser> queryReferrerVillation(Integer userId) {

        List<HrUser> list = userMapper.queryReferrerVillation(userId);
        List<HrUser> users = list.stream()
                .map(dto -> {
                    Integer dtoUserId = dto.getUserId();
                    Integer briefChapterId = dto.getBriefChapterId();
                    List<HrUser> hrUsers = userMapper.queryJobStatus(dtoUserId, briefChapterId);
                    hrUsers.stream()
                            .map(d -> {
                                long count = d.getDeliveryrecords().stream().filter(x -> x.getJobStatus() == 8).count();
                                Integer inteviewe = (int) count;
                                long count1 = d.getDeliveryrecords().stream().filter(xx -> xx.getJobStatus() == 9).count();
                                Integer report = (int) count1;

                                if (!(inteviewe.equals(0))) {
                                    dto.setInteview(inteviewe + "人未面试");
                                }
                                if (!(report.equals(0))) {
                                    dto.setReport(report + "人未报到");
                                }
                                return d;
                            }).collect(Collectors.toList());
                    return dto;
                }).collect(Collectors.toList());
        return users;

    }
}

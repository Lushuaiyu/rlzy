package com.nado.rlzy.service.impl;

import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.mapper.HrGroupMapper;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.mapper.HrUserMapper;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.db.pojo.HrRebaterecord;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.db.pojo.HrUser;
import com.nado.rlzy.service.JobSeekingPersonalCenterService;
import com.nado.rlzy.utils.CollectorsUtil;
import com.nado.rlzy.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private HrBriefchapterMapper mapper;

    @Autowired
    private HrSignUpMapper signUpMapper;

    @Autowired
    private HrUserMapper userMapper;

    @Autowired
    private HrGroupMapper groupMapper;

    @Override
    public List<HrBriefchapter> recruitmentBrochureCollectionRecruitment(Integer userId, Integer type) {
        return mapper.recruitmentBrochureCollectionRecruitment(userId, type)
                .stream()
                .map(dto -> {
                    Map<Integer, BigDecimal> map = dto.getRebat()
                            .stream()
                            .collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId, CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    map.forEach((k, v) -> {
                        BigDecimal m = v;
                        double v1 = m.doubleValue();
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
    }

    @Override
    public List<HrBriefchapter> recruitmentBrochureCollection(Integer userId, Integer type) {
        return mapper.recruitmentBrochureCollection(userId, type)
                .stream()
                .map(dto -> {
                    Map<Integer, BigDecimal> map = dto.getRebat()
                            .stream()
                            .collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    map.forEach((k, v) -> {
                        BigDecimal m = v;
                        double v1 = m.doubleValue();
                        String s = StringUtil.decimalFormat2(v1);
                        if (v != null) {
                            s = "返" + s + "元";
                            dto.setRebateRecord(s);
                        } else {
                            dto.setRebateRecord("无返佣");
                        }
                    });
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

package com.nado.rlzy.service.impl;

import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.db.pojo.HrRebaterecord;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.service.JobSeekingPersonalCenterService;
import com.nado.rlzy.utils.CollectorsUtil;
import com.nado.rlzy.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
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
                        }else {
                            dto.setRebateRecord("无返佣");
                        }                    });
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
                        }else {
                            dto.setRebateRecord("无返佣");
                        }                    });
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
    public Map<Object, Object> searchSignUpUserName(Integer briefChapterId, Integer userId, Integer typeId) {
        HashMap<Object, Object> map = new HashMap<>();

        if (typeId.equals(1)){

        }

        return map;
    }
}
package com.nado.rlzy.service.impl;

import com.nado.rlzy.db.mapper.HrBriefchapterMapper;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.service.JobSeekingPersonalCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private HrBriefchapterMapper mapper;

    @Autowired
    private HrSignUpMapper signUpMapper;
    @Override
    public List<HrBriefchapter> recruitmentBrochureCollection(Integer userId) {
        return mapper.recruitmentBrochureCollection(userId).stream().collect(Collectors.toList());

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
    public List<HrSignUp> searchSignUpUserName(Integer briefChapterId) {
        return signUpMapper.searchSignUpUserName(briefChapterId).stream().collect(Collectors.toList());
    }
}
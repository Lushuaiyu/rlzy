package com.nado.rlzy.service.impl;

import cn.hutool.core.lang.Assert;
import com.nado.rlzy.bean.dto.JobListDto;
import com.nado.rlzy.bean.frontEnd.JobListtFront;
import com.nado.rlzy.bean.query.JobListQuery;
import com.nado.rlzy.db.mapper.CollectMapper;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.pojo.Collect;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.RecruitmentHomePageService;
import com.nado.rlzy.utils.CheckParametersUtil;
import com.nado.rlzy.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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

    @Override
    public List<JobListtFront> selectJobListOverview(JobListQuery query) {

        List<JobListDto> listDtos = mapper.selectJobListOverview(query);
        List<JobListtFront> collect = listDtos.stream().map(dto -> {
            JobListtFront front = new JobListtFront();
            BeanUtils.copyProperties(dto, front);

            //数据处理
            Integer age = dto.getAge();
            String s = StringUtil.toString(age);
            front.setAge(s);


            Integer jobStatus = dto.getJobStatus();
            String s2 = StringUtil.toString(jobStatus);
            front.setJobStatus(s2);

            Integer relation = dto.getRelation();
            String s3 = StringUtil.toString(relation);
            front.setRelation(s3);


            return front;


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
    public List<JobListtFront> selectCollectListOverview(Integer userId) {
        List<JobListDto> jobListDtos = mapper.selectCollectListOverview(userId);
        return jobListDtos.stream().map(dto -> {
            JobListtFront front = new JobListtFront();
            BeanUtils.copyProperties(dto, front);
            //数据处理
            Integer age = dto.getAge();
            String s = StringUtil.toString(age);
            front.setAge(s);


            Integer jobStatus = dto.getJobStatus();
            String s2 = StringUtil.toString(jobStatus);
            front.setJobStatus(s2);


            Integer relation = dto.getRelation();
            String s3 = StringUtil.toString(relation);
            front.setRelation(s3);

            return front;


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
    public void save(Collect collect) {
        CheckParametersUtil.getInstance()
                .put(collect.getBriefchapterId(), "briefcharpter")
                .put(collect.getUserId(), "userId")
                .put(collect.getSignUpId(), "signUpId");

        collect.setBriefchapterId(collect.getBriefchapterId());
        collect.setCreateTime(new Date());
        collect.setUserId(collect.getUserId());
        collect.setSignUpId(collect.getSignUpId());

        Assert.isTrue(collectMapper.addBriefchapter(collect) >= 1, RlzyConstant.OPS_FAILED_MSG);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSignUpCollectStatus(Collect collect) {
        //Assert.isFalse( null == collect.getBriefchapterId(), RlzyConstant.NULL_PARAM);
        Assert.isFalse(null == collect.getUserId(), RlzyConstant.NULL_PARAM);
        Assert.isFalse(null == collect.getSignUpId(), RlzyConstant.NULL_PARAM);
        collect.setDeleteFlag(1);
        Assert.isFalse(collectMapper.updateByPrimaryKeySelective(collect) < 1, RlzyConstant.OPS_FAILED_MSG);
    }



    /*public static void main(String[] args) {
        String realName = "320673199611152349";
        realName = realName.substring(0,3) + replaceStr(realName.substring(3,6)) + realName.substring(6, 14) + replaceStr(realName.substring(14, 18));
        System.out.println(realName);
        System.out.println("aaa");
    }*/

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
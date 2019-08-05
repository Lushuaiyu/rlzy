package com.nado.rlzy.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdcardUtil;
import com.nado.rlzy.bean.dto.ComplaintDto;
import com.nado.rlzy.bean.dto.ComplaintPage;
import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.bean.query.ComplaintQuery;
import com.nado.rlzy.db.mapper.*;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.JobSearchHomePageService;
import com.nado.rlzy.utils.CollectorsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName 求职端首页
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/6/28 16:47
 * @Version 1.0
 */
@Service
public class JobSearchHomePageServiceimpl implements JobSearchHomePageService {

    @Autowired
    private HrBriefchapterMapper mapper;

    @Autowired
    private HrRebaterecordMapper rebaterecordMapper;

    @Autowired
    private HrGroupMapper groupMapper;

    @Autowired
    private HrSignUpMapper signUpMapper;

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private MySignUpTableMapper tableMapper;


    @Autowired
    private HrComplaintMapper complaintMapper;

    @Autowired
    private HrSignupDeliveryrecordMapper signupDeliveryrecordMapper;

    @Autowired
    private HrAcctMapper acctMapper;

    @Autowired
    private HrUserMapper userMapper;

    @Autowired
    private HrDictionaryItemMapper dictionaryItemMapper;

    @Override
    public List<HrGroup> coHomePage(Integer groupId) {
        List<HrGroup> list = groupMapper.coHomePage(groupId);

        return list.stream().collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> queryBriefcharpterByParams(BriefcharpterQuery query) {
        List<HrBriefchapter> dtos = mapper.queryBriefcharpterByParams(query);
        //返回结果集
        return dtos.stream().map(dto -> {
            //对返佣的简章id 进行分组, 然后对返佣金额进行sum操作
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //对返佣金额进行 foreach 操作, set到返回的结果集里
            map.forEach((k, v) -> {
                dto.setRebateRecord(v);

            });
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> queryBriefcharpterDtoByParams(BriefcharpterQuery query) {

        //查询全部招聘简章
        List<HrBriefchapter> dtos = mapper.queryBriefcharpterDtoByParams(query);
        //返回结果集
        return dtos.stream().map(dto -> {
            //对返佣的简章id 进行分组, 然后对返佣金额进行sum操作
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //对返佣金额进行 foreach 操作, set到返回的结果集里
            map.forEach((k, v) -> {
                dto.setRebateRecord(v);

            });
            return dto;
        }).collect(Collectors.toList());

    }

    @Override
    public List<HrBriefchapter> queryBriefcharpterDetileByParams(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.queryBriefcharpterDetileByParams(query);
        return list.stream().map(dto -> {
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //对返佣金额进行 foreach 操作, set到返回的结果集里
            map.forEach((k, v) -> {
                dto.setRebateRecord(v);

            });

            return dto;
        }).collect(Collectors.toList());

    }

    @Override
    public List<HrBriefchapter> queryBriefcharpterDetileRecruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.queryBriefcharpterDetileRecruitment(query);
        return list.stream()
                .map(dto -> {
                    dto.getRebat()
                            .stream()
                            .collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId, CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> queryBriefcharpterByLongLiveRecruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.queryBriefcharpterByLongLiveRecruitment(query);
        return list.stream()
                .map(dto -> {
                    dto.getRebat()
                            .stream()
                            .collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> queryBriefcharpterByLongLive(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.queryBriefcharpterByLongLive(query);
        return list.stream().map(dto -> {
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //对返佣金额进行 foreach 操作, set到返回的结果集里
            map.forEach((k, v) -> {
                dto.setRebateRecord(v);
            });
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public List<HrBriefchapter> queryBriefcharpterByLongEatRecruitment(BriefcharpterQuery query) {
        return mapper.queryBriefcharpterByLongEatRecruitment(query)
                .stream()
                .map(dto -> {
                    Map<Integer, BigDecimal> map = dto.getRebat()
                            .stream()
                            .collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    map.forEach((k, v) -> {
                        dto.setRebateRecord(v);
                    });
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> queryBriefcharpterByLongEat(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.queryBriefcharpterByLongEat(query);
        return list.stream().map(dto -> {
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //对返佣金额进行 foreach 操作, set到返回的结果集里
            map.forEach((k, v) -> {
                dto.setRebateRecord(v);
            });
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> recommendedFeeTop10Recruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.recommendedFeeTop10Recruitment(query);
        return list.stream()
                .map(dto -> {
                    Map<Integer, BigDecimal> map = dto.getRebat().stream()
                            .collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId, CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    map.forEach((k, v) -> {
                        dto.setRebateRecord(v);
                    });
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> recommendedFeeTop10(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.recommendedFeeTop10(query);
        return list
                .stream()
                .map(dto -> {
                    Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                            CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));


                    //对返佣金额进行 foreach 操作, set到返回的结果集里
                    map.forEach((k, v) -> {
                        dto.setRebateRecord(v);
                    });
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> studentDivisionRecruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.studentDivisionRecruitment(query);
        return list.stream()
                .map(dto -> {
                    Map<Integer, BigDecimal> map = dto.getRebat()
                            .stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId, CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    map.forEach((k, v) -> {
                        dto.setRebateRecord(v);
                    });
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> studentDivision(BriefcharpterQuery query) {
        List<HrBriefchapter> vals = mapper.studentDivision(query);
        return vals.stream().map(dto -> {
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //System.out.println(map);

            //对返佣金额进行 foreach 操作, set到返回的结果集里
            map.forEach((k, v) -> {
                dto.setRebateRecord(v);

            });
            return dto;

        }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> salaryLeaderboardRecruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.salaryLeaderboardRecruitment(query);
        return list
                .stream()
                .map(dto -> {
                    Map<Integer, BigDecimal> map = dto.getRebat()
                            .stream()
                            .collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    map.forEach((k, v) -> {
                        dto.setRebateRecord(v);
                    });
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> salaryLeaderboard(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.salaryLeaderboard(query);
        return list.stream().map(dto -> {
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));

            //对返佣金额进行 foreach 操作, set到返回的结果集里
            map.forEach((k, v) -> {
                dto.setRebateRecord(v);

            });
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public List<HrBriefchapter> directBusinessRecruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.directBusinessRecruitment(query);
        return list.stream()
                .map(dto -> {
                    Map<Integer, BigDecimal> collect = dto.getRebat()
                            .stream()
                            .collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId, CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    collect.forEach((k, v) -> {
                        dto.setRebateRecord(v);
                    });
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> directBusiness(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.directBusiness(query);
        return list.stream().map(dto -> {
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            System.out.println(map);

            //对返佣金额进行 foreach 操作, set到返回的结果集里
            map.forEach((k, v) -> {
                dto.setRebateRecord(v);

            });
            return dto;


        }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> directAdmissionRecruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.directAdmissionRecruitment(query);
        return list.stream().map(dto -> {
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            System.out.println(map);

            //对返佣金额进行 foreach 操作, set到返回的结果集里
            map.forEach((k, v) -> {
                dto.setRebateRecord(v);

            });
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> directAdmission(BriefcharpterQuery query) {
        return mapper.directAdmission(query).stream().map(dto -> {
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            System.out.println(map);

            //对返佣金额进行 foreach 操作, set到返回的结果集里
            map.forEach((k, v) -> {
                dto.setRebateRecord(v);

            });
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<HrSignUp> querySignUpUserName(Integer type, Integer userId) {

        List<HrSignUp> hrSignUps = signUpMapper.querySignUpUserName(type, userId);
        return hrSignUps.stream().collect(Collectors.toList());
    }

    @Override
    public List<HrSignUp> querySignUpStatus(String jobUserName) {
        List<HrSignUp> signUps = signUpMapper.querySignUpStatus(jobUserName);
        return signUps.stream().collect(Collectors.toList());
    }

    @Override
    public Map<Object, Object> queryBriefchapterBySignUpStatus(Integer signUpStatus) {
        List<HrBriefchapter> list = mapper.queryBriefchapterBySignUpStatus(signUpStatus);
        List<HrBriefchapter> hrBriefchapters = mapper.queryBriefchapterBySignUpstatusRecruitment(signUpStatus);
        //TODO
        List<HrBriefchapter> collect = list.stream().map(dto -> {
            Map<Integer, BigDecimal> map = dto.getRebat().stream().map(r -> {
                if (dto.getSex().equals(0)) {
                    r.setRebateOne(r.getRebateFemale());
                } else {
                    r.setRebateOne(r.getRebateMale());
                }
                return r;
            }).collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //System.out.println(map);

            //对返佣金额进行 foreach 操作, set到返回的结果集里
            map.forEach((k, v) -> {
                dto.setRebateRecord(v);

            });
            return dto;
        }).collect(Collectors.toList());

        List<HrBriefchapter> list1 = hrBriefchapters.stream()
                .map(dto -> {
                    Map<Integer, BigDecimal> map = dto.getRebat()
                            .stream().map(r -> {
                                if (dto.getSex().equals(0)) {
                                    r.setRebateOne(r.getRebateFemale());
                                } else {
                                    r.setRebateOne(r.getRebateMale());
                                }
                                return r;


                            }).collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    map.forEach((k, v) -> {
                        dto.setRebateRecord(v);
                    });
                    return dto;
                })
                .collect(Collectors.toList());
        HashMap<Object, Object> map = new HashMap<>();
        map.put("queryBriefchapterBySignUpStatus", collect);
        map.put("queryBriefchapterBySignUpstatusRecruitment", list1);
        return map;


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelRegistration(Integer signUpId) {
        Assert.isFalse(signUpMapper.cancelRegistration(signUpId) < 1, RlzyConstant.OPS_FAILED_MSG);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int IWantToSignUp(Integer userId, Integer briefChapterId) {
        HrSignUp signUp = new HrSignUp();
        signUp.setBriefChapterId(briefChapterId);
        return signupDeliveryrecordMapper.IWantToSignUp(userId, briefChapterId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addCancelBriefchapter(Collect collect) {
        Collect co = new Collect();
        if (collect.getFlag().equals(0)) {
        co.setUserId(collect.getUserId());
        co.setBriefchapterId(collect.getBriefchapterId());
        co.setCreateTime(new Date());
        Assert.isTrue(collectMapper.addBriefchapter(collect) >= 1, RlzyConstant.OPS_FAILED_MSG);
        } else if (collect.getFlag().equals(1)){
            co.setDeleteFlag(1);
            Assert.isTrue(collectMapper.updateByPrimaryKeySelective(collect) >= 1, RlzyConstant.OPS_FAILED_MSG);
        }



    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addSignUpTable(HrSignUp query) {
        HrSignUp up = new HrSignUp();

        //校验参数
        checkAddSignUp(query.getUserName(), query.getSex(), query.getEducation(), query.getGraduationTime(),
                query.getProfession(), query.getRegistrationPositionId(), query.getArrivalTime(), query.getExpectedSalaryLower(),
                query.getExpectedSalaryUpper(),
                query.getRelation(), query.getItIsPublic(), query.getAgreePlatformHelp(), query.getUserId(), query.getIdCard());
        //初始化参数
        Integer userId = initSignUp(query.getUserName(), query.getSex(), query.getEducation(), query.getGraduationTime(),
                query.getProfession(), query.getRegistrationPositionId(), query.getArrivalTime(), query.getExpectedSalaryLower(),
                query.getExpectedSalaryUpper(),
                query.getRelation(), query.getItIsPublic(), query.getAgreePlatformHelp(), query.getUserId(), query.getIdCard());

        if (query.getTypeId().equals(1)) {

            //初始化参数
            initSignUpDeliveryrecord(up, userId);

            Assert.isFalse(signUpMapper.insertSelective(query) < 1, "参数添加失败");
        } else {

            HrAcct acct = new HrAcct();
            acct.setSignUpId(userId);
            Assert.isFalse(acctMapper.insertSelective(acct) < 1, "参数添加失败");

        }


    }

    private void initSignUpDeliveryrecord(HrSignUp up, Integer userId) {
        HrSignupDeliveryrecord record = new HrSignupDeliveryrecord();
        record.setSignupId(userId);
        record.setCreateTime(LocalDateTime.now());
        record.setJobStatus(up.getJobStatus());
        record.setNoPassReason(up.getNoPassReason());
        record.setNoReportReason(up.getNoReportReason());
        Assert.isFalse(signupDeliveryrecordMapper.insertSelective(record) < 1, RlzyConstant.OPS_FAILED_MSG);
    }

    private Integer initSignUp(String userName, Integer sex, String education,
                               String graduationTime, String profession,
                               String registrationPositionId, String arrivalTime,
                               BigDecimal expectedSalaryLower, BigDecimal expectedSalaryUpper,
                               Integer relation, Integer itIsPublic, Integer agreePlatformHelp,
                               Integer userId, String idCard) {
        HrSignUp signUp = new HrSignUp();
        signUp.setUserName(userName);
        signUp.setSex(sex);
        signUp.setEducation(education);
        signUp.setGraduationTime(graduationTime);
        signUp.setProfession(profession);
        signUp.setRegistrationPositionId(registrationPositionId);
        signUp.setArrivalTime(arrivalTime);
        signUp.setExpectedSalaryLower(expectedSalaryLower);
        signUp.setExpectedSalaryUpper(expectedSalaryUpper);
        signUp.setRelation(relation);
        signUp.setItIsPublic(itIsPublic);
        signUp.setAgreePlatformHelp(agreePlatformHelp);
        signUp.setUserId(userId);
        signUp.setIdCard(idCard);
        Assert.isFalse(signUpMapper.insertSelective(signUp) < 1, "参数添加失败");
        return signUp.getId();
    }

    private void checkAddSignUp(String userName, Integer sex, String education, String graduationTime, String profession,
                                String registrationPositionId, String arrivalTime, BigDecimal expectedSalaryLower, BigDecimal expectedSalaryUpper,
                                Integer relation, Integer itIsPublic, Integer agreePlatformHelp, Integer userId, String idCard) {
        Assert.isFalse(StringUtils.isBlank(userName), "用户名不能为空");
        Assert.isFalse(null == sex, "性别不能为空");
        Assert.isFalse(StringUtils.isBlank(education), "学历不能为空");
        Assert.isFalse(null == graduationTime, "毕业时间不能为空");
        Assert.isFalse(StringUtils.isBlank(profession), "专业不能为空");
        Assert.isFalse(null == registrationPositionId, "意向岗位不能为空");
        Assert.isFalse(null == arrivalTime, "到岗时间不能为空");
        Assert.isFalse(null == expectedSalaryLower, "工资上限不能为空");
        Assert.isFalse(null == expectedSalaryUpper, "工资下限不能为空");
        Assert.isFalse(null == relation, "年龄不能为空");
        Assert.isFalse(null == itIsPublic, "是否公开不能为空");
        Assert.isFalse(null == agreePlatformHelp, "是否获取平台帮助不能为空");
        Assert.isFalse(null == userId, "用户id不能为空");
        //Assert.isFalse(null == briefchapterId, "简章id不能为空");
        boolean card = IdcardUtil.isValidCard(idCard);
        Assert.isFalse(false == card, "身份证输入不正确");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertSelective(MySignUpTable record) {

        Assert.isFalse(StringUtils.isBlank(record.getGroupName()), "分组名字不能为空");
        record.setGroupName(record.getGroupName());
        record.setStatus(3);
        record.setCreateTime(new Date());
        record.setSignId(record.getSignId());

        Assert.isFalse(tableMapper.insertSelective(record) < 1, RlzyConstant.OPS_FAILED_MSG);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addComplaint(ComplaintQuery query, String head) {
        checkNoNull(query.getName(),
                query.getBriefChapterId(),
                query.getComplaintTypeId(),
                query.getDescription(),
                query.getEvidence());


        HrComplaint complaint = new HrComplaint();
        //如果登录用户是推荐人
        if (query.getType().equals(2)) {
            complaint.setUserId(query.getUserId());

        }
        complaint.setName(query.getName());
        complaint.setBriefChapterId(query.getBriefChapterId());
        complaint.setComplaintTypeId(query.getComplaintTypeId());
        complaint.setDescription(query.getDescription());
        complaint.setEvidence(head);
        complaint.setUserId(query.getUserId());
        complaint.setSignUpId(query.getSignUpId());
        complaint.setCreateTime(LocalDateTime.now());


        Assert.isFalse(complaintMapper.save(complaint) < 1, RlzyConstant.OPS_FAILED_MSG);

    }

    @Override
    public List<HrBriefchapter> atThePosition(Integer groupId) {
        List<HrBriefchapter> list = mapper.atThePosition(groupId);
        return list.stream().map(dto -> {
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            System.out.println(map);
            //对返佣金额进行 foreach 操作, set到返回的结果集里
            map.forEach((k, v) -> {
                dto.setRebateRecord(v);
            });
            return dto;
        }).collect(Collectors.toList());
    }

    private void checkNoNull(String name, Integer briefChapterId, String complaintTypeId, String description, String evidence) {
        Assert.isFalse(StringUtils.isBlank(name), "投诉人不能为空");
        Assert.isFalse(null == briefChapterId, "简章不能为空");
        Assert.isFalse(StringUtils.isBlank(complaintTypeId), "投诉类型不能为空");
        Assert.isFalse(StringUtils.isBlank(description), "问题描述不能为空");
        Assert.isFalse(StringUtils.isBlank(evidence), "凭证不能为空");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void complaintWithdrawn(ComplaintQuery query) {
        HrComplaint complaint = new HrComplaint();
        complaint.setDeleteFlag(1);
        //complaint.setId(query.getId());
        Assert.isFalse(complaintMapper.update(complaint) < 1, RlzyConstant.OPS_FAILED_MSG);
    }

    @Override
    public List<MySignUpTable> searchGroupingInformation() {
        return tableMapper.searchGroupingInformation();

    }

    @Override
    public List<HrSignUp> grouper(Integer status) {
        List<HrSignUp> grouper = signUpMapper.grouper(status);
        return grouper.stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<HrSignUp> selectSignUpTableBySignUpName(String signUpName, Integer status) {
        List<HrSignUp> hrSignUps = signUpMapper.selectSignUpTableBySignUpName(signUpName, status);
        return hrSignUps.stream()
                .collect(Collectors.toList());
    }

    @Override
    public int confirmRegistration(Integer briefChapterId, Integer[] id) {
        List<Integer> collect = Stream.of(id).collect(Collectors.toList());
        return signUpMapper.confirmRegistration(briefChapterId, collect);

    }

    @Override
    public Map<String, List<ComplaintPage>> complaintPage(Integer typeId, Integer userId, Integer brieId, Integer dictionary) {
        Map<String, List<ComplaintPage>> map = new HashMap<>();
        if (typeId.equals(1)) {
            //本人
            List<ComplaintPage> users = userMapper.selectMyselfName(typeId, userId);
            List<ComplaintPage> dictionary1 = dictionaryItemMapper.dictionary(dictionary);
            map.put("selectMyselfName", users);
            map.put("dictionary", dictionary1);
        } else {
            //推荐人
            List<ComplaintPage> list = userMapper.selectMyselfName(typeId, userId);
            List<ComplaintPage> person = signUpMapper.queryComplaintPerson(typeId, userId, brieId);
            List<ComplaintPage> itemList = dictionaryItemMapper.dictionary(dictionary);
            map.put("selectMyselfName", list);
            map.put("queryComplaintPerson", person);
            map.put("dictionary", itemList);

        }
        return map;
    }

    @Override
    public List<ComplaintDto> creditCenter(Integer status) {
        return complaintMapper.creditCenter(status)
                .stream()
                .collect(Collectors.toList());
    }


}
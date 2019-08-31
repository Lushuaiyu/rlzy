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
import com.nado.rlzy.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Autowired
    private MySignUpTableSignUpMapper tableSignUpMapper;

    @Autowired
    private EntryResignationMapper resignationMapper;

    @Override
    public List<HrGroup> coHomePage(Integer groupId) {
        List<HrGroup> list = groupMapper.coHomePage(groupId);

        return list.stream().collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> queryBriefcharpterByParams(BriefcharpterQuery query) {
        Map<String, Object> map = new HashMap<>();
        //身份是本人：首页显示的是与本人求职表内容完全匹配（性别
        //、年龄、毕业时间、到岗时间、薪资要求、学历、意向岗位）
        List<HrSignUp> hrSignUps = signUpMapper.queryAll(query.getUserId());
        if (query.getType().equals(1)) {
            //本人
            hrSignUps.stream()
                    .map(dto -> {
                        Integer age = dto.getAge();
                        Date arrivalTime = dto.getArrivalTime();
                        Date graduationTime = dto.getGraduationTime();
                        BigDecimal expectedSalaryLower = dto.getExpectedSalaryLower();
                        BigDecimal expectedSalaryUpper = dto.getExpectedSalaryUpper();
                        String registrationPositionId = dto.getRegistrationPositionId();
                        SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd");
                        Date date1 = new Date();
                        String nowTime = formatt.format(date1);
                        Calendar startTime = Calendar.getInstance();
                        Calendar endTime = Calendar.getInstance();
                        Integer time = null;
                        String exTime = "";
                        try {
                            //毕业时间
                            startTime.setTime(graduationTime);
                            endTime.setTime(formatt.parse(nowTime));
                            //毕业了几年
                            time = endTime.get(Calendar.YEAR) - startTime.get(Calendar.YEAR);
                            exTime = String.valueOf(time);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (age >= 16 && age <= 22) {
                            query.setAge(1);
                        } else if (age >= 23 && age <= 30) {
                            query.setAge(2);
                        } else if (age >= 31 && age <= 40) {
                            query.setAge(3);
                        } else {
                            query.setAge(4);
                        }
                        query.setArrivalTime(arrivalTime);
                        String str = exTime;
                        int i = Integer.parseInt(str);
                        //经验
                        if (i > 0 && i <= 1) {
                            query.setExperienceId(1);
                        } else if (i >= 1 && i <= 3) {
                            query.setExperienceId(5);
                        } else if (i >= 3 && i <= 5) {
                            query.setExperienceId(2);
                        } else if (i >= 5 && i <= 10) {
                            query.setExperienceId(3);
                        } else {
                            query.setExperienceId(4);
                        }
                        query.setExpectedSalaryLower(expectedSalaryLower);
                        query.setExpectedSalaryUpper(expectedSalaryUpper);
                        String education1 = dto.getEducation();
                        query.setEducationId(education1);
                        //意向岗位
                        int[] ints = Arrays.stream(registrationPositionId.split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
                        query.setPostName(ints);
                        return dto;
                    }).collect(Collectors.toList());
            //查询全部招聘简章
            List<HrBriefchapter> dtos = mapper.queryBriefcharpterByParams(query);
            List<HrBriefchapter> collect = dtos.stream()
                    .map(d -> {
                        //月综合
                        double value = d.getAvgSalary().doubleValue();
                        String format = StringUtil.decimalFormat2(value);
                        d.setAvgSalary1(format + "元起");
                        //计薪
                        double value1 = d.getDetailSalary().doubleValue();
                        String s1 = StringUtil.decimalFormat2(value1);
                        String detailSalaryWay = d.getDetailSalaryWay();
                        d.setDetailSalry1(s1 + "元/" + detailSalaryWay);
                        d.setNo(d.getRecruitingNo() + "人");
                        BigDecimal rebateMaleInterview = d.getRebateMaleInterview();
                        BigDecimal rebateMaleReport = d.getRebateMaleReport();
                        BigDecimal rebateMaleEntry = d.getRebateMaleEntry();
                        BigDecimal rebateFemaleInterview = d.getRebateFemaleInterview();
                        BigDecimal rebateFemaleReport = d.getRebateFemaleReport();
                        BigDecimal rebateFemaleEntry = d.getRebateFemaleEntry();
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
                            d.setRebateRecord("返" + rebateMoney + "元");
                        }
                        return d;
                    }).collect(Collectors.toList());

            map.put("queryBriefcharpterByParamsMyselfDto", collect);
        } else {
            //身份是推荐人
            hrSignUps.stream()
                    .map(dto -> {
                        String postIdStr = dto.getPostIdStr();
                        //意向岗位
                        int[] ints = Arrays.stream(postIdStr.split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
                        query.setPostName(ints);
                        return dto;
                    }).collect(Collectors.toList());
            List<HrBriefchapter> dtos = mapper.queryBriefcharpterByParams(query);
            List<HrBriefchapter> coll = dtos.stream()
                    .map(dt -> {
                        //月综合
                        double value = dt.getAvgSalary().doubleValue();
                        String format = StringUtil.decimalFormat2(value);
                        dt.setAvgSalary1(format + "元起");
                        //计薪
                        double value1 = dt.getDetailSalary().doubleValue();
                        String s1 = StringUtil.decimalFormat2(value1);
                        String detailSalaryWay = dt.getDetailSalaryWay();
                        dt.setDetailSalry1(s1 + "元/" + detailSalaryWay);
                        dt.setNo(dt.getRecruitingNo() + "人");
                        BigDecimal rebateMaleInterview = dt.getRebateMaleInterview();
                        BigDecimal rebateMaleReport = dt.getRebateMaleReport();
                        BigDecimal rebateMaleEntry = dt.getRebateMaleEntry();
                        BigDecimal rebateFemaleInterview = dt.getRebateFemaleInterview();
                        BigDecimal rebateFemaleReport = dt.getRebateFemaleReport();
                        BigDecimal rebateFemaleEntry = dt.getRebateFemaleEntry();
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
                            dt.setRebateRecord("返" + rebateMoney + "元");
                        }
                       /* //对返佣的简章id 进行分组, 然后对返佣金额进行sum操作
                        Map<Integer, BigDecimal> ma = dt.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                                CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                        //对返佣金额进行 foreach 操作, set到返回的结果集里
                        ma.forEach((k, v) -> {
                            BigDecimal m = v;
                            double v1 = m.doubleValue();
                            String s = StringUtil.decimalFormat2(v1);
                            if (v != null) {
                                s = "返" + s + "元";
                                dt.setRebateRecord(s);
                            } else {
                                dt.setRebateRecord("无返佣");
                            }
                        });*/
                        return dt;
                    }).collect(Collectors.toList());
            map.put("referrerQueryBriefcharpterByParams", coll);
        }
        return map;
    }

    @Override
    public Map<String, Object> queryBriefcharpterDtoByParams(BriefcharpterQuery query) {
        Map<String, Object> map = new HashMap<>();
        //身份是本人：首页显示的是与本人求职表内容完全匹配（性别
        //、年龄、毕业时间、到岗时间、薪资要求、学历、意向岗位）
        List<HrSignUp> hrSignUps = signUpMapper.queryAll(query.getUserId());

        if (query.getType().equals(1)) {
            //本人
            hrSignUps.stream()
                    .map(dto -> {
                        Integer age = dto.getAge();
                        Date arrivalTime = dto.getArrivalTime();
                        Date graduationTime = dto.getGraduationTime();
                        BigDecimal expectedSalaryLower = dto.getExpectedSalaryLower();
                        BigDecimal expectedSalaryUpper = dto.getExpectedSalaryUpper();
                        String education = dto.getEducation();
                        String registrationPositionId = dto.getRegistrationPositionId();
                        SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd");
                        Date date1 = new Date();
                        String nowTime = formatt.format(date1);
                        Calendar startTime = Calendar.getInstance();
                        Calendar endTime = Calendar.getInstance();
                        Integer time = null;
                        String exTime = "";
                        try {
                            //毕业时间
                            startTime.setTime(graduationTime);
                            endTime.setTime(formatt.parse(nowTime));
                            //毕业了几年
                            time = endTime.get(Calendar.YEAR) - startTime.get(Calendar.YEAR);
                            exTime = String.valueOf(time);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (age >= 16 && age <= 22) {
                            query.setAge(1);
                        } else if (age >= 23 && age <= 30) {
                            query.setAge(2);
                        } else if (age >= 31 && age <= 40) {
                            query.setAge(3);
                        } else {
                            query.setAge(4);
                        }
                        query.setArrivalTime(arrivalTime);
                        String str = exTime;
                        int i = Integer.parseInt(str);
                        //经验
                        if (i > 0 && i <= 1) {
                            query.setExperienceId(1);
                        } else if (i >= 1 && i <= 3) {
                            query.setExperienceId(5);
                        } else if (i >= 3 && i <= 5) {
                            query.setExperienceId(2);
                        } else if (i >= 5 && i <= 10) {
                            query.setExperienceId(3);
                        } else {
                            query.setExperienceId(4);
                        }
                        query.setExpectedSalaryLower(expectedSalaryLower);
                        query.setExpectedSalaryUpper(expectedSalaryUpper);
                        String education1 = dto.getEducation();
                        query.setEducationId(education1);
                        //意向岗位
                        int[] ints = Arrays.stream(registrationPositionId.split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
                        query.setPostName(ints);
                        return dto;
                    }).collect(Collectors.toList());
            //查询全部招聘简章
            List<HrBriefchapter> dtos = mapper.queryBriefcharpterDtoByParams(query);
            List<HrBriefchapter> collect = dtos.stream()
                    .map(d -> {
                        //月综合
                        double value = d.getAvgSalary().doubleValue();
                        String format = StringUtil.decimalFormat2(value);
                        d.setAvgSalary1(format + "元起");
                        //计薪
                        double value1 = d.getDetailSalary().doubleValue();
                        String s1 = StringUtil.decimalFormat2(value1);
                        String detailSalaryWay = d.getDetailSalaryWay();
                        d.setDetailSalry1(s1 + "元/" + detailSalaryWay);
                        d.setNo(d.getRecruitingNo() + "人");
                        BigDecimal rebateMaleInterview = d.getRebateMaleInterview();
                        BigDecimal rebateMaleReport = d.getRebateMaleReport();
                        BigDecimal rebateMaleEntry = d.getRebateMaleEntry();
                        BigDecimal rebateFemaleInterview = d.getRebateFemaleInterview();
                        BigDecimal rebateFemaleReport = d.getRebateFemaleReport();
                        BigDecimal rebateFemaleEntry = d.getRebateFemaleEntry();
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
                            d.setRebateRecord("返" + rebateMoney + "元");
                        }
                        return d;
                    }).collect(Collectors.toList());

            map.put("myselfDto", collect);
        } else {
            //身份是推荐人
            hrSignUps.stream()
                    .map(dto -> {
                        String postIdStr = dto.getPostIdStr();
                        //意向岗位
                        int[] ints = Arrays.stream(postIdStr.split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
                        query.setPostName(ints);
                        return dto;
                    }).collect(Collectors.toList());
            List<HrBriefchapter> dtos = mapper.queryBriefcharpterDtoByParams(query);
            List<HrBriefchapter> coll = dtos.stream()
                    .map(dt -> {
                        //月综合
                        double value = dt.getAvgSalary().doubleValue();
                        String format = StringUtil.decimalFormat2(value);
                        dt.setAvgSalary1(format + "元起");
                        //计薪
                        double value1 = dt.getDetailSalary().doubleValue();
                        String s1 = StringUtil.decimalFormat2(value1);
                        String detailSalaryWay = dt.getDetailSalaryWay();
                        dt.setDetailSalry1(s1 + "元/" + detailSalaryWay);
                        dt.setNo(dt.getRecruitingNo() + "人");
                        BigDecimal rebateMaleInterview = dt.getRebateMaleInterview();
                        BigDecimal rebateMaleReport = dt.getRebateMaleReport();
                        BigDecimal rebateMaleEntry = dt.getRebateMaleEntry();
                        BigDecimal rebateFemaleInterview = dt.getRebateFemaleInterview();
                        BigDecimal rebateFemaleReport = dt.getRebateFemaleReport();
                        BigDecimal rebateFemaleEntry = dt.getRebateFemaleEntry();

                        if (null != rebateMaleInterview && null != rebateMaleReport && null != rebateMaleEntry &&
                                null != rebateFemaleInterview && null != rebateFemaleReport && null != rebateFemaleEntry) {
                            //男生返佣的钱
                            BigDecimal add = rebateMaleInterview.add(rebateMaleReport)
                                    .add(rebateMaleEntry);

                            //女生返佣的钱
                            BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                                    .add(rebateFemaleEntry);
                            BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                            String rebateMoney = StringUtil.decimalToString(n);
                            dt.setRebateRecord("返" + rebateMoney + "元");
                        }
                        return dt;
                    }).collect(Collectors.toList());
            map.put("referrerQueryBriefcharpterDtoByParams", coll);
        }
        return map;
    }

    @Override
    public List<HrBriefchapter> queryBriefcharpterDetileByParams(BriefcharpterQuery query) {
        List<HrBriefchapter> val = mapper.queryBriefcharpterDetileByParams(query);
        return val.stream().map(dto -> {
            Integer manNum1 = dto.getManNum();
            dto.setManNum1(manNum1 + "人");
            Integer womenNum1 = dto.getWomenNum();
            dto.setWomenNum1(womenNum1 + "人");
            String manAge2 = dto.getManAge();
            String womenAge2 = dto.getWomenAge();
            String experienceId1 = dto.getExperienceId();
            if (dto.getExperienceId().compareTo("1") == 0) {
                dto.setExperience(experienceId1 + "年内");
                dto.setExperienceId(null);
            } else if (experienceId1.compareTo("3-5") == 0 ||
                    experienceId1.compareTo("1-3") == 0 ||
                    experienceId1.compareTo("5-10") == 0) {
                dto.setExperience(experienceId1 + "年");
                dto.setExperienceId(null);
            } else if (experienceId1.compareTo("10") == 0) {
                dto.setExperience(experienceId1 + "年以上");
                dto.setExperienceId(null);
            } else {
                System.out.println("============");
            }

            dto.setManAge1(manAge2 + "岁");
            dto.setWomenAge1(womenAge2 + "岁");
            Integer overtimeTime = dto.getOvertimeTimeId();
            if (overtimeTime == 1) {
                dto.setOvertimeTime1("较少");
            } else if (overtimeTime == 2) {
                dto.setOvertimeTime1("多");
            } else if (overtimeTime == 3) {
                dto.setOvertimeTime1("较多");
            } else {
                System.out.println("====");
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

    }

    @Override
    public Map<String, Object> queryBriefcharpterListDetileByParams(BriefcharpterQuery query) {
        Map<String, Object> map = new HashMap<>();
        List<HrBriefchapter> list = mapper.queryBriefcharpterDetileByParams(query);
        List<HrBriefchapter> hrBriefchapters = mapper.queryBriefcharpterDetileRecruitment(query);
        //代招单位
        List<HrBriefchapter> briefcharpterDetile = list.stream()
                .map(dto -> {
                    Integer manNum1 = dto.getManNum();
                    dto.setManNum1(manNum1 + "人");
                    Integer womenNum1 = dto.getWomenNum();
                    dto.setWomenNum1(womenNum1 + "人");
                    String manAge2 = dto.getManAge();
                    String womenAge2 = dto.getWomenAge();
                    dto.setManAge1(manAge2 + "岁");
                    dto.setWomenAge1(womenAge2 + "岁");
                    Integer overtimeTime = dto.getOvertimeTimeId();
                    if (overtimeTime == 1) {
                        dto.setOvertimeTime1("较少");
                    } else if (overtimeTime == 2) {
                        dto.setOvertimeTime1("多");
                    } else if (overtimeTime == 3) {
                        dto.setOvertimeTime1("较多");
                    } else {
                        System.out.println("====");
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

                    String experienceId1 = dto.getExperienceId();
                    if (dto.getExperienceId().compareTo("1") == 0) {
                        dto.setExperience(experienceId1 + "年内");
                    } else if (experienceId1.compareTo("3-5") == 0 ||
                            experienceId1.compareTo("1-3") == 0 ||
                            experienceId1.compareTo("5-10") == 0) {
                        dto.setExperience(experienceId1 + "年");
                    } else if (experienceId1.compareTo("10") == 0) {
                        dto.setExperience(experienceId1 + "年以上");
                    } else {
                        System.out.println("============");
                    }
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
        //招聘单位
        List<HrBriefchapter> collect = hrBriefchapters.stream()
                .map(dto -> {
                    Integer manNum1 = dto.getManNum();
                    dto.setManNum1(manNum1 + "人");
                    Integer womenNum1 = dto.getWomenNum();
                    dto.setWomenNum1(womenNum1 + "人");
                    String manAge2 = dto.getManAge();
                    String womenAge2 = dto.getWomenAge();
                    dto.setManAge1(manAge2 + "岁");
                    dto.setWomenAge1(womenAge2 + "岁");
                    Integer overtimeTime = dto.getOvertimeTimeId();
                    if (overtimeTime == 1) {
                        dto.setOvertimeTime1("较少");
                    } else if (overtimeTime == 2) {
                        dto.setOvertimeTime1("多");
                    } else if (overtimeTime == 3) {
                        dto.setOvertimeTime1("较多");
                    } else {
                        System.out.println("====");
                    }
                    String experienceId1 = dto.getExperienceId();
                    if (dto.getExperienceId().compareTo("1") == 0) {
                        dto.setExperience(experienceId1 + "年内");
                        dto.setExperienceId(null);
                    } else if (experienceId1.compareTo("3-5") == 0 ||
                            experienceId1.compareTo("1-3") == 0 ||
                            experienceId1.compareTo("5-10") == 0) {
                        dto.setExperience(experienceId1 + "年");
                        dto.setExperienceId(null);
                    } else if (experienceId1.compareTo("10") == 0) {
                        dto.setExperience(experienceId1 + "年以上");
                        dto.setExperienceId(null);
                    } else {
                        System.out.println("============");
                    }

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

        //代招单位
        map.put("queryBriefcharpterDetileByParams", briefcharpterDetile);
        //招聘单位
        map.put("queryBriefcharpterDetileRecruitment", collect);
        return map;
    }

    @Override
    public List<HrBriefchapter> recommendAPositionRecruitment(String recruitedCompany) {
        List<HrBriefchapter> list1 = mapper.recommendAPositionRecruitment(recruitedCompany);
        List<HrBriefchapter> collect = list1.stream()
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
                        //女生返佣的钱
                        BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                                .add(rebateFemaleEntry);

                        BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                        String rebateMoney = StringUtil.decimalToString(n);
                        dto.setRebateRecord("返" + rebateMoney + "元");
                    }
                    return dto;
                })
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<HrBriefchapter> recommendAPosition(String recruitedCompany) {
        List<HrBriefchapter> list = mapper.recommendAPosition(recruitedCompany);
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
                        //女生返佣的钱
                        BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                                .add(rebateFemaleEntry);

                        BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                        String rebateMoney = StringUtil.decimalToString(n);
                        dto.setRebateRecord("返" + rebateMoney + "元");
                    }
                    return dto;
                })
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<HrBriefchapter> queryBriefcharpterDetileRecruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> val = mapper.queryBriefcharpterDetileRecruitment(query);
        return val.stream().map(dto -> {
            Integer manNum1 = dto.getManNum();
            dto.setManNum1(manNum1 + "人");
            Integer womenNum1 = dto.getWomenNum();
            dto.setWomenNum1(womenNum1 + "人");
            String manAge2 = dto.getManAge();
            String womenAge2 = dto.getWomenAge();
            dto.setManAge1(manAge2 + "岁");
            dto.setWomenAge1(womenAge2 + "岁");
            Integer overtimeTime = dto.getOvertimeTimeId();
            if (overtimeTime == 1) {
                dto.setOvertimeTime1("较少");
            } else if (overtimeTime == 2) {
                dto.setOvertimeTime1("多");
            } else if (overtimeTime == 3) {
                dto.setOvertimeTime1("较多");
            } else {
                System.out.println("====");
            }
            String experienceId1 = dto.getExperienceId();
            if (dto.getExperienceId().compareTo("1") == 0) {
                dto.setExperience(experienceId1 + "年内");
                dto.setExperienceId(null);
            } else if (experienceId1.compareTo("3-5") == 0 ||
                    experienceId1.compareTo("1-3") == 0 ||
                    experienceId1.compareTo("5-10") == 0) {
                dto.setExperience(experienceId1 + "年");
                dto.setExperienceId(null);
            } else if (experienceId1.compareTo("10") == 0) {
                dto.setExperience(experienceId1 + "年以上");
                dto.setExperienceId(null);
            } else {
                System.out.println("============");
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

    }

    @Override
    public List<HrBriefchapter> queryBriefcharpterByLongLiveRecruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.queryBriefcharpterByLongLiveRecruitment(query);
        return list.stream()
                .map(dto -> {
                    Integer no = dto.getRecruitingNo();
                    dto.setNo(String.valueOf(no + "人"));
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
                        //女生返佣的钱
                        BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                                .add(rebateFemaleEntry);

                        BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                        String rebateMoney = StringUtil.decimalToString(n);
                        dto.setRebateRecord("返" + rebateMoney + "元");
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<HrBriefchapter> queryBriefcharpterByLongLive(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.queryBriefcharpterByLongLive(query);

        return list.stream().map(dto -> {
            Integer no = dto.getRecruitingNo();
            dto.setNo(String.valueOf(no + "人"));
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
                //女生返佣的钱
                BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                        .add(rebateFemaleEntry);

                BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                String rebateMoney = StringUtil.decimalToString(n);
                dto.setRebateRecord(rebateMoney);
            }
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public List<HrBriefchapter> queryBriefcharpterByLongEatRecruitment(BriefcharpterQuery query) {
        return mapper.queryBriefcharpterByLongEatRecruitment(query)
                .stream()
                .map(dto -> {
                    Integer no = dto.getRecruitingNo();
                    dto.setNo(String.valueOf(no + "人"));
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
                        //女生返佣的钱
                        BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                                .add(rebateFemaleEntry);

                        BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                        String rebateMoney = StringUtil.decimalToString(n);
                        dto.setRebateRecord("返" + rebateMoney + "元");
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> queryBriefcharpterByLongEat(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.queryBriefcharpterByLongEat(query);
        return list.stream().map(dto -> {
            Integer no = dto.getRecruitingNo();
            dto.setNo(String.valueOf(no + "人"));
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
                //女生返佣的钱
                BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                        .add(rebateFemaleEntry);

                BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                String rebateMoney = StringUtil.decimalToString(n);
                dto.setRebateRecord("返" + rebateMoney + "元");
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> recommendedFeeTop10Recruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.recommendedFeeTop10Recruitment(query);
        return list.stream()
                .map(dto -> {
                    Integer no = dto.getRecruitingNo();
                    dto.setNo(String.valueOf(no + "人"));
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
                        //女生返佣的钱
                        BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                                .add(rebateFemaleEntry);

                        BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                        String rebateMoney = StringUtil.decimalToString(n);
                        dto.setRebateRecord("返" + rebateMoney + "元");
                    }
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> recommendedFeeTop10(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.recommendedFeeTop10(query);
        return list
                .stream()
                .map(dto -> {
                    Integer no = dto.getRecruitingNo();
                    dto.setNo(String.valueOf(no + "人"));
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
                        //女生返佣的钱
                        BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                                .add(rebateFemaleEntry);

                        BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                        String rebateMoney = StringUtil.decimalToString(n);
                        dto.setRebateRecord("返" + rebateMoney + "元");
                    }
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> studentDivisionRecruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.studentDivisionRecruitment(query);
        return list.stream()
                .map(dto -> {
                    Integer no = dto.getRecruitingNo();
                    dto.setNo(String.valueOf(no + "人"));
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
                        //女生返佣的钱
                        BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                                .add(rebateFemaleEntry);

                        BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                        String rebateMoney = StringUtil.decimalToString(n);
                        dto.setRebateRecord("返" + rebateMoney + "元");
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> studentDivision(BriefcharpterQuery query) {
        List<HrBriefchapter> vals = mapper.studentDivision(query);
        return vals.stream().map(dto -> {
            Integer no = dto.getRecruitingNo();
            dto.setNo(String.valueOf(no + "人"));
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
                //女生返佣的钱
                BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                        .add(rebateFemaleEntry);

                BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                String rebateMoney = StringUtil.decimalToString(n);
                dto.setRebateRecord("返" + rebateMoney + "元");
            }
            return dto;

        }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> salaryLeaderboardRecruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.salaryLeaderboardRecruitment(query);
        return list
                .stream()
                .map(dto -> {
                    Integer no = dto.getRecruitingNo();
                    dto.setNo(String.valueOf(no + "人"));
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
                        //女生返佣的钱
                        BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                                .add(rebateFemaleEntry);

                        BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                        String rebateMoney = StringUtil.decimalToString(n);
                        dto.setRebateRecord("返" + rebateMoney + "元");
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> salaryLeaderboard(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.salaryLeaderboard(query);
        return list.stream().map(dto -> {
            Integer no = dto.getRecruitingNo();
            dto.setNo(String.valueOf(no + "人"));
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
                //女生返佣的钱
                BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                        .add(rebateFemaleEntry);

                BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                String rebateMoney = StringUtil.decimalToString(n);
                dto.setRebateRecord("返" + rebateMoney + "元");
            }
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public List<HrBriefchapter> directBusinessRecruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.directBusinessRecruitment(query);
        return list.stream()
                .map(dto -> {
                    Integer no = dto.getRecruitingNo();
                    dto.setNo(String.valueOf(no + "人"));
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
                        //女生返佣的钱
                        BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                                .add(rebateFemaleEntry);

                        BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                        String rebateMoney = StringUtil.decimalToString(n);
                        dto.setRebateRecord("返" + rebateMoney + "元");
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> directBusiness(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.directBusiness(query);
        return list.stream().map(dto -> {
            Integer no = dto.getRecruitingNo();
            dto.setNo(String.valueOf(no + "人"));
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
                //女生返佣的钱
                BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                        .add(rebateFemaleEntry);

                BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                String rebateMoney = StringUtil.decimalToString(n);
                dto.setRebateRecord("返" + rebateMoney + "元");
            }
            return dto;


        }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> directAdmissionRecruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.directAdmissionRecruitment(query);
        return list.stream().map(dto -> {
            Integer no = dto.getRecruitingNo();
            dto.setNo(String.valueOf(no + "人"));
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
                //女生返佣的钱
                BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                        .add(rebateFemaleEntry);

                BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                String rebateMoney = StringUtil.decimalToString(n);
                dto.setRebateRecord("返" + rebateMoney + "元");
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> directAdmission(BriefcharpterQuery query) {
        return mapper.directAdmission(query).stream().map(dto -> {
            Integer no = dto.getRecruitingNo();
            dto.setNo(String.valueOf(no + "人"));
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
                //女生返佣的钱
                BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                        .add(rebateFemaleEntry);

                BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                String rebateMoney = StringUtil.decimalToString(n);
                dto.setRebateRecord("返" + rebateMoney + "元");
            }
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public Map<Object, Object> queryBriefchapterBySignUpStatus(Integer type, Integer userId, Integer[] jobStatus) {

        //查求职者名字
        List<HrSignUp> hrSignUps = signUpMapper.querySignUpUserName(type, userId);
        String userName = null;
        Integer sex = null;
        Integer id = null;

        List<HrBriefchapter> collect = null;
        List<HrBriefchapter> list1 = null;
        //求职状态 已结束转态的可能有多个 求职状态是前台传过来的
        List<Integer> list2 = Stream.of(jobStatus).collect(Collectors.toList());

        for (HrSignUp hrSignUp : hrSignUps) {
            userName = hrSignUp.getSignUpName();
            //性别 0 女 1 男
            sex = hrSignUp.getSex();
            Integer finalSex = sex;
            id = hrSignUp.getId();


            //查简章 代招单位
            List<HrBriefchapter> list = mapper.queryBriefchapterBySignUpStatus(list2, id);
            //判断集合是否为空
            if (list.size() > 0) {
                collect = list.stream().map(dto -> {
                    //显示男生的全部返费金额
                    Map<Integer, BigDecimal> allMaleRebate = dto.getRebat().stream()
                            .collect(Collectors.groupingBy(HrRebaterecord::getSignUpId, CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateMale)));
                    //显示女生的全部返费金额
                    Map<Integer, BigDecimal> allFemaleRebate = dto.getRebat()
                            .stream()
                            .collect(Collectors.groupingBy(HrRebaterecord::getSignUpId, CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateMale)));
                    if (finalSex.equals(0)) {
                        //女生的全部返费
                        allMaleRebate.forEach((k, v) -> {
                            double vv = v.doubleValue();
                            String s = StringUtil.decimalFormat2(vv);
                            dto.setRebateRecord(s);
                        });
                    }
                    if (finalSex.equals(1)) {
                        //男生的全部返费
                        allFemaleRebate.forEach((k, v) -> {
                            double v1 = v.doubleValue();
                            String format2 = StringUtil.decimalFormat2(v1);
                            dto.setRebateRecord("返费" + format2);
                        });
                    }
                    dto.getRebat().stream()
                            .map(d -> {
                                if (finalSex.equals(0)) {
                                    //女生返费
                                    Integer status1 = d.getStatusTwo();
                                    if (status1.equals(0)) {
                                        d.setRebateStatus("待返佣");
                                    } else if (status1.equals(1)) {
                                        d.setRebateStatus("给付已结束");
                                    } else if (status1.equals(2)) {
                                        d.setRebateStatus("给付失败");
                                    } else if (status1.equals(3)) {
                                        d.setRebateStatus("给付中");
                                    }
                                    d.setRebateMale(BigDecimal.ZERO);
                                    double v = d.getRebateFemale().doubleValue();
                                    String s = StringUtil.decimalFormat2(v);
                                    d.setRebateMaleStr("金额: ¥" + s);
                                } else if (finalSex.equals(1)) {
                                    //男生返费
                                    Integer status1 = d.getStatusTwo();
                                    if (status1.equals(0)) {
                                        d.setRebateStatus("待返佣");
                                    } else if (status1.equals(1)) {
                                        d.setRebateStatus("给付已结束");
                                    } else if (status1.equals(2)) {
                                        d.setRebateStatus("给付失败");
                                    } else if (status1.equals(3)) {
                                        d.setRebateStatus("给付中");
                                    }
                                    d.setRebateFemale(BigDecimal.ZERO);
                                    double v = d.getRebateMale().doubleValue();
                                    String s = StringUtil.decimalFormat2(v);
                                    d.setRebateMaleStr("金额: ¥" + s);
                                }
                                return d;
                            }).collect(Collectors.toList());
                    return dto;
                }).collect(Collectors.toList());

            }


            //招聘单位
            //招聘单位
            List<HrBriefchapter> hrBriefchapters = mapper.queryBriefchapterBySignUpstatusRecruitment(list2, id);
            if (hrBriefchapters.size() > 0) {
                list1 = hrBriefchapters.stream()
                        .map(dto -> {
                            //显示男生的全部返费金额
                            Map<Integer, BigDecimal> allMaleRebate = dto.getRebat().stream()
                                    .collect(Collectors.groupingBy(HrRebaterecord::getSignUpId, CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateMale)));
                            //显示女生的全部返费金额
                            Map<Integer, BigDecimal> allFemaleRebate = dto.getRebat()
                                    .stream()
                                    .collect(Collectors.groupingBy(HrRebaterecord::getSignUpId, CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateMale)));
                            if (finalSex.equals(0)) {
                                //女生的全部返费
                                allMaleRebate.forEach((k, v) -> {
                                    double vv = v.doubleValue();
                                    String s = StringUtil.decimalFormat2(vv);
                                    dto.setRebateRecord(s);
                                });
                            }
                            if (finalSex.equals(1)) {
                                //男生的全部返费
                                allFemaleRebate.forEach((k, v) -> {
                                    double v1 = v.doubleValue();
                                    String format2 = StringUtil.decimalFormat2(v1);
                                    dto.setRebateRecord("返费" + format2);
                                });
                            }

                            dto.getRebat().stream()
                                    .map(d -> {
                                        if (finalSex.equals(0)) {
                                            //女生返费
                                            Integer status1 = d.getStatusTwo();
                                            if (status1.equals(0)) {
                                                d.setRebateStatus("待返佣");
                                            } else if (status1.equals(1)) {
                                                d.setRebateStatus("给付已结束");
                                            } else if (status1.equals(2)) {
                                                d.setRebateStatus("给付失败");
                                            } else if (status1.equals(3)) {
                                                d.setRebateStatus("给付中");
                                            }
                                            d.setRebateMale(BigDecimal.ZERO);
                                            double v = d.getRebateFemale().doubleValue();
                                            String s = StringUtil.decimalFormat2(v);
                                            d.setRebateMaleStr("金额: ¥" + s);
                                        } else if (finalSex.equals(1)) {
                                            //男生返费
                                            Integer status1 = d.getStatusTwo();
                                            if (status1.equals(0)) {
                                                d.setRebateStatus("待返佣");
                                            } else if (status1.equals(1)) {
                                                d.setRebateStatus("给付已结束");
                                            } else if (status1.equals(2)) {
                                                d.setRebateStatus("给付失败");
                                            } else if (status1.equals(3)) {
                                                d.setRebateStatus("给付中");
                                            }
                                            d.setRebateFemale(BigDecimal.ZERO);
                                            double v = d.getRebateMale().doubleValue();
                                            String s = StringUtil.decimalFormat2(v);
                                            d.setRebateMaleStr("金额: ¥" + s);
                                        }
                                        return d;
                                    }).collect(Collectors.toList());
                            return dto;
                        })
                        .collect(Collectors.toList());
            }
        }
        HashMap<Object, Object> map = new HashMap<>();
        //查求职者名字
        map.put("querySignUpUserName", hrSignUps);
        //map.put("querySignUpStatus", signUps);
        map.put("queryBriefchapterBySignUpStatus", collect);
        map.put("queryBriefchapterBySignUpstatusRecruitment", list1);
        return map;


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int IWantToSignUp(HrSignupDeliveryrecord deliveryrecord) {
        HrSignupDeliveryrecord deliveryrecord1 = new HrSignupDeliveryrecord();
        deliveryrecord1.setSignupId(deliveryrecord.getSignupId());
        deliveryrecord1.setBriefChapterId(deliveryrecord.getBriefChapterId());
        deliveryrecord1.setJobStatus(0);
        deliveryrecord.setStatus(0);
        deliveryrecord1.setCreateTime(LocalDateTime.now());
        return signupDeliveryrecordMapper.insertSelective(deliveryrecord1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int referrerToSIgnUp(HrSignupDeliveryrecord deliveryrecord) {
        List<HrSignupDeliveryrecord> deliveryrecords = new ArrayList<>();
        List<HrSignupDeliveryrecord> list1 = new ArrayList<>();
        list1.add(deliveryrecord);
        list1.stream()
                .map(dto -> {
                    dto.setSignupId(deliveryrecord.getSignupId());
                    dto.setBriefChapterId(deliveryrecord.getBriefChapterId());
                    dto.setJobStatus(0);
                    dto.setStatus(0);
                    dto.setCreateTime(LocalDateTime.now());
                    deliveryrecords.add(dto);
                    return dto;
                })
                .collect(Collectors.toList());
        Assert.isFalse(signupDeliveryrecordMapper.referrerToSIgnUp(deliveryrecords) < 1, RlzyConstant.OPS_FAILED_MSG);
        //招聘人数 -
        Integer[] number = deliveryrecord.getNumber();
        List<Integer> list = Arrays.asList(number);
        long count = list.stream().count();
        Integer count1 = (int) count;

        Assert.isFalse(mapper.remainingQuota(count1, deliveryrecord.getBriefChapterId()) < 1, RlzyConstant.OPS_FAILED_MSG);

        return 1;
    }

    @Override
    public int updateCollect(Collect collect) {
        collect.setDeleteFlag(1);
        return collectMapper.updateByPrimaryKeySelecti(collect);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addCancelBriefchapter(Collect collect) {
        Collect co = new Collect();
        co.setUserId(collect.getUserId());
        co.setBriefchapterId(collect.getBriefchapterId());
        co.setCreateTime(new Date());
        Assert.isTrue(collectMapper.addBriefchapter(collect) >= 1, RlzyConstant.OPS_FAILED_MSG);
        return collect.getId();
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
        //初始化报名表参数
        Integer userId = initSignUp(query.getUserName(), query.getSex(), query.getEducation(), query.getGraduationTime(),
                query.getProfession(), query.getRegistrationPositionId(), query.getArrivalTime(), query.getExpectedSalaryLower(),
                query.getExpectedSalaryUpper(),
                query.getRelation(), query.getItIsPublic(), query.getAgreePlatformHelp(), query.getUserId(), query.getIdCard());

        //初始化报名表投递记录表参数
        initSignUpDeliveryrecord(up, userId);
        Assert.isFalse(signUpMapper.insertSelective(query) < 1, "参数添加失败");

        //初始化我的求职表和报名表的中间表参数
        initMySignUpTable(query.getMySignUpTableId(), userId);


    }

    private void initMySignUpTable(Integer mySignUpTableId, Integer userId) {
        MySignUpTableSignUp up = new MySignUpTableSignUp();
        up.setMySignUpTableId(mySignUpTableId);
        up.setSignUpId(userId);
        up.setCreateTime(LocalDateTime.now());
        Assert.isFalse(tableSignUpMapper.insertSelective(up) < 1, RlzyConstant.OPS_FAILED_MSG);
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
                               Date graduationTime, String profession,
                               String registrationPositionId, Date arrivalTime,
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

    private void checkAddSignUp(String userName, Integer sex, String education, Date graduationTime, String profession,
                                String registrationPositionId, Date arrivalTime, BigDecimal expectedSalaryLower, BigDecimal expectedSalaryUpper,
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
    public int insertSelective(MySignUpTable record) {

        Assert.isFalse(StringUtils.isBlank(record.getGroupName()), "分组名字不能为空");
        record.setGroupName(record.getGroupName());
        record.setStatus(3);
        record.setCreateTime(new Date());
        record.setUserId(record.getUserId());
        boolean flag = tableMapper.insertSelective(record) < 1;
        Assert.isFalse(flag, RlzyConstant.OPS_FAILED_MSG);
        int a = flag == false ? 0 : 1;
        return a;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addComplaint(ComplaintQuery query, String head) {
        checkNoNull(query.getName(),
                query.getBriefChapterId(),
                query.getComplaintTypeId(),
                query.getDescription(),
                query.getEvidence());
        HrComplaint complaint = new HrComplaint();
        complaint.setName(query.getName());
        complaint.setBriefChapterId(query.getBriefChapterId());
        complaint.setComplaintTypeId(query.getComplaintTypeId());
        complaint.setDescription(query.getDescription());
        complaint.setEvidence(head);
        complaint.setUserId(query.getUserId());
        //complaint.setSignUpId(query.getSignUpId());
        complaint.setCreateTime(LocalDateTime.now());
        complaint.setContactPerson(query.getContactPerson());
        complaint.setPhone(query.getPhone());


        Assert.isFalse(complaintMapper.save(complaint) < 1, RlzyConstant.OPS_FAILED_MSG);
        return 1;

    }


    @Override
    public Map<String, Object>
    interviewReportEntry(Integer briefchapterId) {
        HashMap<String, Object> map = new HashMap<>();
        //报名 no
        int signUpNo = signUpMapper.briefchaterSignUpNo(briefchapterId);
        map.put("briefchaterSignUpNo", signUpNo);
        //面试
        int interviewNo = signUpMapper.briefchapterInterviewNo(briefchapterId);
        map.put("briefchapterInterviewNo", interviewNo);
        //报道后就是待返佣
        int reportNo = signUpMapper.briefchapterReportNo(briefchapterId);
        map.put("briefchapterReportNo", reportNo);

        int rebateInterview = signUpMapper.rebateInterview(briefchapterId);
        map.put("rebateInterview", rebateInterview);
        int rebateReport = signUpMapper.rebateReport(briefchapterId);
        map.put("rebateReport", rebateReport);
        int rebateEntry = signUpMapper.rebateEntry(briefchapterId);
        map.put("rebateEntry", rebateEntry);
        return map;
    }

    @Override
    public List<HrBriefchapter> companyHomeHistory(Integer groupId) {
        List<HrBriefchapter> list = mapper.companyHomeHistory(groupId);
        return list.stream()
                .map(dto -> {
                    Integer no = dto.getRecruitingNo();
                    dto.setNo(String.valueOf(no + "人"));
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
                    BigDecimal add = rebateMaleInterview.add(rebateMaleReport)
                            .add(rebateMaleEntry);
                    BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                            .add(rebateFemaleEntry);
                    BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                    String rebateMoney = StringUtil.decimalToString(n);
                    dto.setRebateRecord(rebateMoney);
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> compayHomeHistoryRecruitment(Integer groupId) {
        List<HrBriefchapter> hrBriefchapters = mapper.companyHomeHistoryRecruitment(groupId);
        return hrBriefchapters.stream()
                .map(dto -> {
                    Integer no = dto.getRecruitingNo();
                    dto.setNo(String.valueOf(no + "人"));
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
                    BigDecimal add = rebateMaleInterview.add(rebateMaleReport)
                            .add(rebateMaleEntry);

                    BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                            .add(rebateFemaleEntry);
                    BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                    String rebateMoney = StringUtil.decimalToString(n);
                    dto.setRebateRecord(rebateMoney);
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<HrComplaint> violationRecord(Integer groupId) {
        List<HrComplaint> list = complaintMapper.violationRecord(groupId);
        return list;
    }


    @Override
    public List<HrBriefchapter> atThePosition(Integer groupId) {
        List<HrBriefchapter> list = mapper.atThePosition(groupId);
        return list.stream().map(dto -> {
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
            BigDecimal rebateMaleInterview = dto.getRebateMaleInterview();
            BigDecimal rebateMaleReport = dto.getRebateMaleReport();
            BigDecimal rebateMaleEntry = dto.getRebateMaleEntry();
            BigDecimal rebateFemaleInterview = dto.getRebateFemaleInterview();
            BigDecimal rebateFemaleReport = dto.getRebateFemaleReport();
            BigDecimal rebateFemaleEntry = dto.getRebateFemaleEntry();
            if (null != rebateMaleInterview && null != rebateMaleReport && null != rebateMaleEntry &&
                    null != rebateFemaleInterview && null != rebateFemaleReport && null != rebateFemaleEntry) {
                BigDecimal add = rebateMaleInterview.add(rebateMaleReport)
                        .add(rebateMaleEntry);

                BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                        .add(rebateFemaleEntry);
                BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                String rebateMoney = StringUtil.decimalToString(n);
                dto.setRebateRecord(rebateMoney);
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<HrBriefchapter> atThePositionRecruitment(Integer groupId) {
        List<HrBriefchapter> list = mapper.atThePositionRecruitment(groupId);
        return list.stream().map(dto -> {
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
            BigDecimal rebateMaleInterview = dto.getRebateMaleInterview();
            BigDecimal rebateMaleReport = dto.getRebateMaleReport();
            BigDecimal rebateMaleEntry = dto.getRebateMaleEntry();
            BigDecimal rebateFemaleInterview = dto.getRebateFemaleInterview();
            BigDecimal rebateFemaleReport = dto.getRebateFemaleReport();
            BigDecimal rebateFemaleEntry = dto.getRebateFemaleEntry();
            if (null != rebateMaleInterview && null != rebateMaleReport && null != rebateMaleEntry &&
                    null != rebateFemaleInterview && null != rebateFemaleReport && null != rebateFemaleEntry) {
                BigDecimal add = rebateMaleInterview.add(rebateMaleReport)
                        .add(rebateMaleEntry);

                BigDecimal add1 = rebateFemaleInterview.add(rebateFemaleReport)
                        .add(rebateFemaleEntry);
                BigDecimal n = add.compareTo(add1) >= 0 ? add : add1;
                String rebateMoney = StringUtil.decimalToString(n);
                dto.setRebateRecord(rebateMoney);
            }
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
    public int complaintWithdrawn(ComplaintQuery query) {
        Assert.isFalse(complaintMapper.updateCom(query) < 1, RlzyConstant.OPS_FAILED_MSG);
        return 1;
    }

    @Override
    public Map<String, Object> searchGroupingInformation(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        List<MySignUpTable> tables = tableMapper.searchGroupingdefault();
        List<MySignUpTable> mySignUpTables = tableMapper.searchGroupingInformation(userId);
        map.put("searchGroupingdefault", tables);
        map.put("searchGroupingInformation", mySignUpTables);
        return map;
    }

    @Override
    public Map<String, Object> grouper(String groupName, String signUpName, Integer type, Integer userId) {
        Map<String, Object> map = new HashMap<>();
        if (type.equals(1)) {
            //我的求职表下的被推荐人的报名表
            List<HrSignUp> hrSignUps = signUpMapper.selectSignUpTableBySignUpName(signUpName, userId);
            List<HrSignUp> coll = hrSignUps.stream()
                    .collect(Collectors.toList());
            map.put("selectSignUpTableBySignUpName", coll);
        }
        if (type.equals(2)) {
            //分组下的被推荐人的报名表
            List<HrSignUp> grouper = signUpMapper.grouper(groupName, signUpName, userId);
            List<HrSignUp> collect = grouper.stream()
                    .collect(Collectors.toList());
            map.put("grouper", collect);
        }
        return map;
    }

    @Override
    public int confirmRegistration(Integer briefChapterId, Integer[] id) {
        List<Integer> collect = Stream.of(id).collect(Collectors.toList());
        return signUpMapper.confirmRegistration(briefChapterId, collect);

    }


    @Override
    public Map<String, Object> complaintPage(Integer typeId, Integer userId, Integer brieId, Integer dictionary) {
        Map<String, Object> map = new HashMap<>();
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
    public List<ComplaintDto> creditCenter(Integer[] status, Integer userId) {
        List<Integer> list = Stream.of(status).collect(Collectors.toList());

        return complaintMapper.creditCenter(list, userId)
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<HrComplaint> selectComplaint(Integer coId) {
        List<HrComplaint> list = complaintMapper.selectComplaint(coId);
        return list.stream()
                .collect(Collectors.toList());
    }


}
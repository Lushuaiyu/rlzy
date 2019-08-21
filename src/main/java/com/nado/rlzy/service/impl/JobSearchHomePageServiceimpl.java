package com.nado.rlzy.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdcardUtil;
import com.nado.rlzy.bean.dto.ComplaintDto;
import com.nado.rlzy.bean.dto.ComplaintPage;
import com.nado.rlzy.bean.frontEnd.BriefcharpterFront;
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

            if (query.getType().equals(1)) {
                // 身份是本人
                List<HrSignUp> sign = signUpMapper.queryAll(query.getUserId(), 1);
                //遍历
                sign.stream()
                        .map(va -> {
                            //本人意向岗位
                            String postName = dto.getPostName();
                           /* Integer sex = dto.getSex();
                            Integer manAgeId = dto.getManAgeId();
                            Integer womenAgeId = dto.getWomenAgeId();
                            //经验
                            Integer experienceId = dto.getExperienceId();
                            //到岗时间
                            LocalDateTime registerTime = dto.getRegisterTime();*/
                            String registrationPositionId = va.getRegistrationPositionId();
                            //逗号分割的 string 转 int array
                            int[] ints = Arrays.stream(registrationPositionId.split(",")).mapToInt(str -> Integer.parseInt(str)).toArray();
                            //int array 转integer list
                            List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
                            list.stream()
                                    .forEach(s -> {
                                        if (postName.equals(s)) {
                                            dto.setPostName(dto.getPostName());
                                        }
                                    });
                            return va;
                        })
                        .collect(Collectors.toList());
            } else {
                //身份是推荐人
                List<HrSignUp> upList = signUpMapper.queryAll(query.getUserId(), 2);
                upList.stream()
                        .map(v -> {
                            //本人意向岗位
                            String postName = dto.getPostName();


                            String registrationPositionId = v.getRegistrationPositionId();
                            //逗号分割的 string 转 int array
                            int[] ints = Arrays.stream(registrationPositionId.split(",")).mapToInt(str -> Integer.parseInt(str)).toArray();
                            //int array 转integer list
                            List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
                            list.stream()
                                    .forEach(s -> {
                                        if (postName.equals(s)) {
                                            dto.setPostName(dto.getPostName());
                                        }
                                    });
                            return v;
                        })
                        .collect(Collectors.toList());
            }
            //对返佣的简章id 进行分组, 然后对返佣金额进行sum操作
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //对返佣金额进行 foreach 操作, set到返回的结果集里
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
    public List<HrBriefchapter> queryBriefcharpterDtoByParams(BriefcharpterQuery query) {

        //查询全部招聘简章
        List<HrBriefchapter> dtos = mapper.queryBriefcharpterDtoByParams(query);


        //返回结果集
        return dtos.stream().map(dto -> {
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
            if (query.getType().equals(1)) {
                // 身份是本人
                List<HrSignUp> sign = signUpMapper.queryAll(query.getUserId(), query.getType());
                //遍历
                sign.stream()
                        .map(va -> {
                            //本人意向岗位
                            String postName = dto.getPostName();

                            String registrationPositionId = va.getRegistrationPositionId();
                            //逗号分割的 string 转 int array
                            int[] ints = Arrays.stream(registrationPositionId.split(",")).mapToInt(str -> Integer.parseInt(str)).toArray();
                            //int array 转integer list
                            List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
                            list.stream()
                                    .forEach(s -> {
                                        if (postName.equals(s)) {
                                            dto.setPostName(dto.getPostName());
                                        }
                                    });
                            return va;
                        })
                        .collect(Collectors.toList());
            } else {
                //身份是推荐人
                List<HrSignUp> upList = signUpMapper.queryAll(query.getUserId(), 2);
                upList.stream()
                        .map(v -> {
                            //本人意向岗位
                            String postName = dto.getPostName();


                            String registrationPositionId = v.getRegistrationPositionId();
                            //逗号分割的 string 转 int array
                            int[] ints = Arrays.stream(registrationPositionId.split(",")).mapToInt(str -> Integer.parseInt(str)).toArray();
                            //int array 转integer list
                            List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
                            list.stream()
                                    .forEach(s -> {
                                        if (postName.equals(s)) {
                                            dto.setPostId(dto.getPostId());
                                        }
                                    });
                            return v;
                        })
                        .collect(Collectors.toList());
            }
            //对返佣的简章id 进行分组, 然后对返佣金额进行sum操作
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //对返佣金额进行 foreach 操作, set到返回的结果集里
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

            if (query.getType().equals(1)) {
                // 身份是本人
                List<HrSignUp> sign = signUpMapper.queryAll(query.getUserId(), 1);
                //遍历
                sign.stream()
                        .map(va -> {
                            //本人意向岗位
                            String postName = dto.getPostName();
                            //性别
                            Integer sex = va.getSex();
                            Integer manNum = dto.getManNum();
                            Integer womenNum = dto.getWomenNum();
                            String manAge = dto.getManAge();
                            String womenAge = dto.getWomenAge();
                            Integer age = va.getAge();
                            //到岗时间
                            Date date = dto.getRegisterTime();
                            //报名表 到岗时间
                            LocalDateTime arrivalTime = va.getArrivalTime();
                            String s2 = StringUtil.localdatetimeToStr(arrivalTime);

                            Date strToDate = StringUtil.StrToDate(s2);
                            //综合工资
                            BigDecimal avgSalary = dto.getAvgSalary();
                            //期望工资下限
                            BigDecimal salaryLower = va.getExpectedSalaryLower();
                            //期望工资上限
                            BigDecimal salaryUpper = va.getExpectedSalaryUpper();
                            //学历
                            String educationId = dto.getEducationId();
                            String education = va.getEducation();

                            //毕业时间
                            String graduationTime = va.getGraduationTime();


                            //经验
                            String experienceId = dto.getExperienceId();
                            SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd");
                            Date date1 = new Date();
                            String nowTime = formatt.format(date1);
                            Calendar startTime = Calendar.getInstance();
                            Calendar endTime = Calendar.getInstance();
                            Integer time = null;
                            String exTime = "";
                            try {
                                startTime.setTime(formatt.parse(graduationTime));
                                endTime.setTime(formatt.parse(nowTime));
                                //毕业了几年
                                time = endTime.get(Calendar.YEAR) - startTime.get(Calendar.YEAR);
                                exTime = String.valueOf(time);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            //意向岗位
                            String registrationPositionId = va.getRegistrationPositionId();

                            //男生年龄
                            int[] maAge = Arrays.stream(manAge.split("-")).mapToInt(str -> Integer.parseInt(str)).toArray();
                            List<Integer> manAge1 = Arrays.stream(maAge).boxed().collect(Collectors.toList());
                            Integer manAgeStart = null;
                            Integer manAgeEnd = null;


                            //女生年龄
                            int[] woAge = Arrays.stream(womenAge.split("-")).mapToInt(st -> Integer.parseInt(st)).toArray();
                            List<Integer> womenAge1 = Arrays.stream(woAge).boxed().collect(Collectors.toList());
                            Integer womenAgeStart = null;
                            Integer womenAgeEnd = null;

                            //对经验的处理
                            String regex = ",";
                            String[] split = experienceId.split(regex);
                            List<String> ex = Arrays.asList(split);
                            String experienceStart = "";
                            String experienceEnd = "";

                            //如果本人性别为男 显示招聘男生的数量
                            if (sex.equals(1)) {
                                dto.setManNum(manNum);
                                //到岗时间
                                if (date.equals(strToDate)) {
                                    dto.setRegisterTime(dto.getRegisterTime());
                                }
                                if (education.equals(educationId)) {
                                    dto.setEducationId(dto.getEducationId());
                                }
                                //经验要求
                                if (ex.size() == 1) {
                                    experienceStart = ex.get(0);
                                    if (exTime.compareTo(experienceStart) > 0 || exTime.compareTo(experienceStart) <= 0) {
                                        dto.setExperienceId(experienceStart);
                                    }
                                }
                                if (ex.size() == 2) {
                                    experienceStart = ex.get(0);
                                    experienceEnd = ex.get(1);
                                    if (exTime.compareTo(experienceStart) > 0 && exTime.compareTo(experienceEnd) <= 0) {

                                    }
                                }

                                //综合工资
                                if (avgSalary.compareTo(salaryLower) > 0 && avgSalary.compareTo(salaryUpper) <= 0) {
                                    dto.setAvgSalary(dto.getAvgSalary());
                                }

                                //年龄要求

                                if (manAge1.size() == 1) {
                                    manAgeStart = manAge1.get(0);
                                    if (age >= manAgeStart) {
                                        dto.setManAge(manAgeStart + "以上");
                                    }
                                }
                                if (manAge1.size() == 2) {
                                    manAgeStart = manAge1.get(0);
                                    manAgeEnd = manAge1.get(1);
                                    if (age > manAgeStart && age <= manAgeEnd) {
                                        dto.setManAge(dto.getManAge());
                                    }
                                }
                            } else if (sex.equals(0)) {
                                //如果本人性别为女 显示招聘女生的数量
                                dto.setWomenNum(womenNum);
                                if (arrivalTime.equals(strToDate)) {
                                    dto.setRegisterTime(dto.getRegisterTime());
                                }
                                //综合工资
                                if (avgSalary.compareTo(salaryLower) > 0 && avgSalary.compareTo(salaryUpper) <= 0) {
                                    dto.setAvgSalary(dto.getAvgSalary());
                                }

                                // 女生年龄
                                if (womenAge1.size() == 1) {
                                    womenAgeStart = womenAge1.get(0);
                                    if (age >= womenAgeStart) {
                                        dto.setWomenAge(String.valueOf(womenAgeStart));
                                    }
                                }
                                if (womenAge1.size() == 2) {
                                    womenAgeStart = womenAge1.get(0);
                                    womenAgeEnd = womenAge1.get(1);
                                    if (age > womenAgeStart && age <= womenAgeEnd) {
                                        dto.setWomenAge(dto.getWomenAge());
                                    }
                                }
                            } else {
                                dto.setWomenNum(womenNum);
                                dto.setManNum(manNum);
                            }


                            //意向岗位
                            //逗号分割的 string 转 int array
                            int[] ints = Arrays.stream(registrationPositionId.split(",")).mapToInt(str -> Integer.parseInt(str)).toArray();
                            //int array 转integer list
                            List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
                            list.stream()
                                    .forEach(s -> {
                                        if (postName.equals(s)) {
                                            dto.setPostId(dto.getPostId());
                                        }
                                    });
                            return va;
                        })
                        .collect(Collectors.toList());
            } else {
                //身份是推荐人
                List<HrSignUp> upList = signUpMapper.queryAll(query.getUserId(), 2);
                upList.stream()
                        .map(v -> {
                            //推荐人意向岗位
                            Integer postId = dto.getPostId();
                            String registrationPositionId = v.getRegistrationPositionId();
                            //逗号分割的 string 转 int array
                            int[] ints = Arrays.stream(registrationPositionId.split(",")).mapToInt(str -> Integer.parseInt(str)).toArray();
                            //int array 转integer list
                            List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
                            list.stream()
                                    .forEach(s -> {
                                        if (postId.equals(s)) {
                                            dto.setPostId(dto.getPostId());
                                        }
                                    });
                            return v;
                        })
                        .collect(Collectors.toList());
            }
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //对返佣金额进行 foreach 操作, set到返回的结果集里
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
    public Map<String, Object> queryBriefcharpterListDetileByParams(BriefcharpterQuery query) {
        Map<String, Object> map = new HashMap<>();
        List<HrBriefchapter> list = mapper.queryBriefcharpterDetileByParams(query);
        List<HrBriefchapter> hrBriefchapters = mapper.queryBriefcharpterDetileRecruitment(query);
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
                    return dto;
                })
                .collect(Collectors.toList());
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

                    return dto;
                })
                .collect(Collectors.toList());

        map.put("queryBriefcharpterDetileByParams", briefcharpterDetile);
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

                    Map<Integer, BigDecimal> ma = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                            CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    //对返佣金额进行 foreach 操作, set到返回的结果集里
                    ma.forEach((k, v) -> {
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


            if (query.getType().equals(1)) {
                // 身份是本人
                List<HrSignUp> sign = signUpMapper.queryAll(query.getUserId(), 1);
                //遍历
                sign.stream()
                        .map(va -> {
                            //本人意向岗位
                            String postName = dto.getPostName();
                            //性别
                            Integer sex = va.getSex();
                            Integer manNum = dto.getManNum();
                            Integer womenNum = dto.getWomenNum();
                            String manAge = dto.getManAge();
                            String womenAge = dto.getWomenAge();
                            Integer age = va.getAge();
                            //到岗时间
                            Date date = dto.getRegisterTime();
                            //报名表 到岗时间
                            LocalDateTime arrivalTime = va.getArrivalTime();
                            String s2 = StringUtil.localdatetimeToStr(arrivalTime);
                            Date strToDate = StringUtil.StrToDate(s2);
                            //综合工资
                            BigDecimal avgSalary = dto.getAvgSalary();
                            //期望工资下限
                            BigDecimal salaryLower = va.getExpectedSalaryLower();
                            //期望工资上限
                            BigDecimal salaryUpper = va.getExpectedSalaryUpper();
                            //学历
                            String educationId = dto.getEducationId();
                            String education = va.getEducation();

                            //毕业时间
                            String graduationTime = va.getGraduationTime();
                            //经验
                            String experienceId = dto.getExperienceId();

                            SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd");
                            Date date1 = new Date();
                            String nowTime = formatt.format(date1);
                            Calendar startTime = Calendar.getInstance();
                            Calendar endTime = Calendar.getInstance();
                            Integer time = null;
                            String exTime = "";

                            try {
                                startTime.setTime(formatt.parse(graduationTime));
                                endTime.setTime(formatt.parse(nowTime));
                                //毕业了几年
                                time = endTime.get(Calendar.YEAR) - startTime.get(Calendar.YEAR);
                                exTime = String.valueOf(time);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            //意向岗位
                            String registrationPositionId = va.getRegistrationPositionId();

                            //男生年龄
                            int[] maAge = Arrays.stream(manAge.split("-")).mapToInt(str -> Integer.parseInt(str)).toArray();
                            List<Integer> manAge1 = Arrays.stream(maAge).boxed().collect(Collectors.toList());
                            Integer manAgeStart = null;
                            Integer manAgeEnd = null;


                            //女生年龄
                            int[] woAge = Arrays.stream(womenAge.split("-")).mapToInt(st -> Integer.parseInt(st)).toArray();
                            List<Integer> womenAge1 = Arrays.stream(woAge).boxed().collect(Collectors.toList());
                            Integer womenAgeStart = null;
                            Integer womenAgeEnd = null;

                            //对经验的处理

                            String[] split = experienceId.split("-");
                            List<String> ex = Arrays.asList(split);
                            String experienceStart = "";
                            String experienceEnd = "";

                            //如果本人性别为男 显示招聘男生的数量
                            if (sex.equals(1)) {
                                dto.setManNum(manNum);
                                //到岗时间
                                if (date.equals(strToDate)) {
                                    dto.setRegisterTime(dto.getRegisterTime());
                                }
                                if (education.equals(educationId)) {
                                    dto.setEducationId(dto.getEducationId());
                                }
                                //经验要求 ===========================================================
                                if (ex.size() == 1) {
                                    experienceStart = ex.get(0);
                                    if (exTime.compareTo(experienceStart) > 0 || exTime.compareTo(experienceStart) <= 0) {
                                        dto.setExperienceId(experienceStart);
                                    }
                                }

                                if (ex.size() == 2) {
                                    experienceStart = ex.get(0);
                                    experienceEnd = ex.get(1);
                                    if (exTime.compareTo(experienceStart) > 0 && exTime.compareTo(experienceEnd) <= 0) {
                                        dto.setExperienceId(dto.getExperienceId());
                                    }
                                }
                                //综合工资
                                if (avgSalary.compareTo(salaryLower) > 0 && avgSalary.compareTo(salaryUpper) <= 0) {
                                    dto.setAvgSalary(dto.getAvgSalary());
                                }

                                //年龄要求

                                if (manAge1.size() == 1) {
                                    manAgeStart = manAge1.get(0);
                                    if (age >= manAgeStart) {
                                        dto.setManAge(String.valueOf(manAgeStart));
                                    }
                                }
                                if (manAge1.size() == 2) {
                                    manAgeStart = manAge1.get(0);
                                    manAgeEnd = manAge1.get(1);
                                    if (age > manAgeStart && age <= manAgeEnd) {
                                        dto.setManAge(dto.getManAge());
                                    }
                                }
                            } else if (sex.equals(0)) {
                                //如果本人性别为女 显示招聘女生的数量
                                dto.setWomenNum(womenNum);
                                if (arrivalTime.equals(strToDate)) {
                                    dto.setRegisterTime(dto.getRegisterTime());
                                }
                                //综合工资
                                if (avgSalary.compareTo(salaryLower) > 0 && avgSalary.compareTo(salaryUpper) <= 0) {
                                    dto.setAvgSalary(dto.getAvgSalary());
                                }

                                // 女生年龄
                                if (womenAge1.size() == 1) {
                                    womenAgeStart = womenAge1.get(0);
                                    if (age >= womenAgeStart) {
                                        dto.setWomenAge(String.valueOf(womenAgeStart));
                                    }
                                }
                                if (womenAge1.size() == 2) {
                                    womenAgeStart = womenAge1.get(0);
                                    womenAgeEnd = womenAge1.get(1);
                                    if (age > womenAgeStart && age <= womenAgeEnd) {
                                        dto.setWomenAge(dto.getWomenAge());
                                    }
                                }
                            } else {
                                dto.setWomenNum(womenNum);
                                dto.setManNum(manNum);
                            }


                            //意向岗位
                            //逗号分割的 string 转 int array
                            int[] ints = Arrays.stream(registrationPositionId.split(",")).mapToInt(str -> Integer.parseInt(str)).toArray();
                            //int array 转integer list
                            List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
                            list.stream()
                                    .forEach(s -> {
                                        if (postName.equals(s)) {
                                            dto.setPostId(dto.getPostId());
                                        }
                                    });
                            return va;
                        })
                        .collect(Collectors.toList());
            } else {
                //身份是推荐人
                List<HrSignUp> upList = signUpMapper.queryAll(query.getUserId(), 2);
                upList.stream()
                        .map(v -> {
                            Integer postId = dto.getPostId();
                            String registrationPositionId = v.getRegistrationPositionId();
                            //逗号分割的 string 转 int array
                            int[] ints = Arrays.stream(registrationPositionId.split(",")).mapToInt(str -> Integer.parseInt(str)).toArray();
                            //int array 转integer list
                            List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
                            list.stream()
                                    .forEach(s -> {
                                        if (postId.equals(s)) {
                                            dto.setPostId(dto.getPostId());
                                        }
                                    });
                            return v;
                        })
                        .collect(Collectors.toList());
            }
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //对返佣金额进行 foreach 操作, set到返回的结果集里
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
    public List<HrBriefchapter> queryBriefcharpterByLongLiveRecruitment(BriefcharpterQuery query) {
        List<HrBriefchapter> list = mapper.queryBriefcharpterByLongLiveRecruitment(query);
        BriefcharpterFront front = new BriefcharpterFront();
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
                    Map<Integer, BigDecimal> collect = dto.getRebat()
                            .stream()
                            .collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    collect.forEach((k, v) -> {
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

            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //对返佣金额进行 foreach 操作, set到返回的结果集里
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
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //对返佣金额进行 foreach 操作, set到返回的结果集里
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
                    Map<Integer, BigDecimal> map = dto.getRebat().stream()
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
                    Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                            CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));


                    //对返佣金额进行 foreach 操作, set到返回的结果集里
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
                    Map<Integer, BigDecimal> map = dto.getRebat()
                            .stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId, CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
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
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //System.out.println(map);

            //对返佣金额进行 foreach 操作, set到返回的结果集里
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
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));

            //对返佣金额进行 foreach 操作, set到返回的结果集里
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
                    Map<Integer, BigDecimal> collect = dto.getRebat()
                            .stream()
                            .collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId, CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
                    collect.forEach((k, v) -> {
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
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            System.out.println(map);

            //对返佣金额进行 foreach 操作, set到返回的结果集里
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
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            System.out.println(map);

            //对返佣金额进行 foreach 操作, set到返回的结果集里
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
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            System.out.println(map);

            //对返佣金额进行 foreach 操作, set到返回的结果集里
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
    public Map<Object, Object> queryBriefchapterBySignUpStatus(Integer type, Integer userId) {
        //查求职者名字
        List<HrSignUp> hrSignUps = signUpMapper.querySignUpUserName(type, userId);
        final String[] userName = {""};
        hrSignUps.stream().map(dto -> {
            userName[0] = dto.getUserName();
            return dto;
        }).collect(Collectors.toList());

        //查求职状态
        List<HrSignUp> signUps = signUpMapper.querySignUpStatus(userName[0]);
        List<HrSignUp> collect1 = signUps.stream().collect(Collectors.toList());
        final Integer[] status = {null};
        collect1.stream().map(dto -> {
            status[0] = dto.getJobStatus();
            return dto;
        }).collect(Collectors.toList());
        //查简章 代招单位
        List<HrBriefchapter> list = mapper.queryBriefchapterBySignUpStatus(status[0]);
        //招聘单位
        List<HrBriefchapter> hrBriefchapters = mapper.queryBriefchapterBySignUpstatusRecruitment(status[0]);
        //TODO
        List<HrBriefchapter> collect = list.stream().map(dto -> {
            Map<Integer, BigDecimal> map = dto.getRebat().stream().map(r -> {
                double doubleValue = r.getRebateFemale().doubleValue();
                String s = StringUtil.decimalFormat2(doubleValue);
                if (dto.getSex().equals(0)) {
                    r.setRebateOne(r.getRebateFemale());
                    r.setRebateMon("金额: ¥" + s);
                } else {
                    r.setRebateOne(r.getRebateMale());
                    r.setRebateMon("金额: ¥" + s);
                }
                return r;
            }).collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            //System.out.println(map);

            //对返佣金额进行 foreach 操作, set到返回的结果集里
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
        HashMap<Object, Object> map = new HashMap<>();
        //查求职者名字
        map.put("querySignUpUserName", hrSignUps);
        map.put("querySignUpStatus", signUps);
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
                               String graduationTime, String profession,
                               String registrationPositionId, LocalDateTime arrivalTime,
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
                                String registrationPositionId, LocalDateTime arrivalTime, BigDecimal expectedSalaryLower, BigDecimal expectedSalaryUpper,
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
    public List<HrBriefchapter> atThePosition(Integer groupId, String groupName) {
        List<HrBriefchapter> list = mapper.atThePosition(groupId, groupName);
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
            Map<Integer, BigDecimal> map = dto.getRebat().stream().collect(Collectors.groupingBy(HrRebaterecord::getBriefchapterId,
                    CollectorsUtil.summingBigDecimal(HrRebaterecord::getRebateOne)));
            System.out.println(map);
            //对返佣金额进行 foreach 操作, set到返回的结果集里
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
        Assert.isFalse( complaintMapper.updateCom(query) < 1, RlzyConstant.OPS_FAILED_MSG);
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
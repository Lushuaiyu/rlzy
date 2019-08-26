package com.nado.rlzy.service.impl;

import cn.hutool.core.lang.Assert;
import com.nado.rlzy.bean.dto.ComplaintDto;
import com.nado.rlzy.bean.dto.PersonCoDto;
import com.nado.rlzy.bean.query.AddCoQuery;
import com.nado.rlzy.bean.query.EditPersonDataQuery;
import com.nado.rlzy.db.mapper.*;
import com.nado.rlzy.db.pojo.Feedback;
import com.nado.rlzy.db.pojo.HrGroup;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.db.pojo.HrUser;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.platform.exception.ImgException;
import com.nado.rlzy.service.PersonCenterService;
import com.nado.rlzy.utils.CheckParametersUtil;
import com.nado.rlzy.utils.OSSClientUtil;
import com.nado.rlzy.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName 个人中心service实现类
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/1 14:50
 * @Version 1.0
 */
@Service
public class PersonCenterServiceImpl implements PersonCenterService {

    @Autowired
    private HrBriefchapterMapper mapper;

    @Autowired
    private HrGroupMapper hrGroupMapper;

    @Autowired
    private HrSignUpMapper signUpMapper;

    @Autowired
    private HrUserMapper userMapper;

    @Autowired
    private HrComplaintMapper complaintMapper;

    @Autowired
    private FeedbackMapper feedbackMapper;

    public static final Logger logger = LoggerFactory.getLogger(PersonCenterServiceImpl.class);


    @Override
    public int myFeedback(String content, Integer userId, String name, String phone) {
        //校验参数
        checkParams(content, userId, name, phone);
        Feedback feedback = new Feedback();
        feedback.setUserId(userId);
        feedback.setContent(content);
        feedback.setName(name);
        feedback.setPhone(phone);
        feedback.setCreateTime(LocalDateTime.now());

        return feedbackMapper.insertSelective(feedback);
    }

    private void checkParams(String content, Integer userId, String name, String phone) {
        Assert.isFalse(StringUtils.isBlank(content), RlzyConstant.FEEDBACK_NOT_NULL);
        Assert.isFalse(null == userId, RlzyConstant.USERID_NOT_NULL);
        Assert.isFalse(StringUtils.isBlank(name), RlzyConstant.FEEDBACK_NAME_NOT_NULL);
        Assert.isFalse(StringUtils.isBlank(phone), RlzyConstant.FEEDBACK_PHONE_NOT_NULL);

    }

    @Override
    public Map<String, Object> queryPersonCo(Integer userId, Integer type) {
        HashMap<String, Object> map = new HashMap<>(16);
        if (type.equals(1)) {
            //招聘单位
            List<PersonCoDto> list = hrGroupMapper.queryPersonCORecruitment(userId);
            List<PersonCoDto> dtos = list.stream().collect(Collectors.toList());
            map.put("queryPersonCORecruitment", dtos);
        } else {
            //代招单位
            List<PersonCoDto> coDtos = hrGroupMapper.queryPersonCo(userId);
            List<PersonCoDto> collect = coDtos.stream().collect(Collectors.toList());
            map.put("queryPersonCo", collect);
        }
        return map;


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addCo(AddCoQuery query) {
        Map<String, Object> map = new HashMap<>();
        //参数校验
        checkNullParams(query.getCoName(), query.getCoAddress(),
                query.getCompanyProfile(), query.getBusLicense());

        if (query.getType().equals(1)) {
            Integer groupId = addCoParams(query);
            map.put("queryAgentEnterprisePid", groupId);
        } else {
            List<HrGroup> coDtos = hrGroupMapper.queryTheAuditFailed(query.getGroupId());
            map.put("queryTheAuditFailed", coDtos);
        }
        return map;


    }

    private int addCoParams(AddCoQuery query) {
        List<HrGroup> hrGroups = hrGroupMapper.queryAgentEnterprisePid(query.getUserId());
        Integer id = hrGroups.get(0).getId();
        //添加
        HrGroup hrGroup = new HrGroup();
        hrGroup.setGroupname(query.getCoName());
        hrGroup.setGroupaddress(query.getCoAddress());
        hrGroup.setGroupinfo(query.getCompanyProfile());
        hrGroup.setBusinesslicense(query.getBusLicense());
        hrGroup.setPid(id);
        hrGroup.setCertifierid(query.getUserId());
        boolean b = hrGroupMapper.insertSelective(hrGroup) < 1;
        Assert.isFalse(b, RlzyConstant.OPS_FAILED_MSG);
        return hrGroup.getId();

    }


    private void checkNullParams(String coName, String coAddress, String companyProfile, String busLicense) {
        CheckParametersUtil.getInstance()
                .put(coName, "coName")
                .put(coAddress, "coAddress")
                .put(companyProfile, "companyProfile")
                .put(busLicense, "busLicense");

    }

    @Override
    public String updateHead(MultipartFile file) throws ImgException {

        if (file == null || file.getSize() <= 0) {
            throw new ImgException("file不能为空");
        }
        OSSClientUtil ossClient = new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];
    }

    @Override
    public List<HrUser> personalInformation(Integer userId) {
        return userMapper.personalInformation(userId)
                .stream()
                .map(dto -> {
                    Double salaryLower = dto.getExpectedSalaryLower().doubleValue();
                    Double salaryUpper = dto.getExpectedSalaryUpper().doubleValue();
                    String format = StringUtil.decimalFormat2(salaryLower);
                    String format1 = StringUtil.decimalFormat2(salaryUpper);
                    String s = format + "k" + "-" + format1 + "k";
                    dto.setExpectedSalary(s);
                    return dto;
                }).collect(Collectors.toList());

    }

    @Override
    public List<HrUser> personalInformationReferrer(Integer userId) {
        List<HrUser> hrSignUps = userMapper.personalInformationReferrer(userId);
        List<HrUser> collect = hrSignUps.stream().map(dto -> {
            Integer lower = dto.getRecommendNoLower();
            Integer noUpper = dto.getRecommendNoUpper();
            String number = lower + "-" + noUpper + "人";
            dto.setRecommendedNumber(number);
            return dto;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editPersonData(EditPersonDataQuery query, String url) {

        //编辑资料
        editInformation(query, url);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editPersonDataRecommend(EditPersonDataQuery query) {
        HrUser user = new HrUser();
        user.setHeadImage(query.getUrl());
        user.setRecommenderId(query.getUserId());
        user.setPostIdStr(query.getPostIdStr());
        user.setRecommendNoLower(query.getRecommendNoLower());
        user.setRecommendNoUpper(query.getRecommendNoUpper());
        user.setRecommendInfo(query.getRecommendInfo());
        user.setPublicIs(query.getItIsPublic());
        user.setAgreeHelp(query.getAgreePlatformHelp());
        Assert.isFalse(userMapper.editPersonData(user) < 1, RlzyConstant.OPS_FAILED_MSG);

    }

    @Override
    public HashMap<Object, Object> searchComplaintRecord(Integer userId) {
        List<ComplaintDto> collect = complaintMapper.searchComplaintRecord(userId).stream().collect(Collectors.toList());
        List<ComplaintDto> dtos = complaintMapper.searchComplaintRecordMyself(userId)
                .stream()
                .collect(Collectors.toList());
        HashMap<Object, Object> map = new HashMap<>();
        map.put("searchComplaintRecord", collect);
        map.put("searchComplaintRecordMyself", dtos);
        return map;
    }

    @Override
    public HashMap<String, Object> complaint(Integer complaintId, Integer type) {
        HashMap<String, Object> map = new HashMap<>();
        if (type.equals(1)) {
            //代招单位
            List<ComplaintDto> collect = complaintMapper.complaint(complaintId).stream().collect(Collectors.toList());
            map.put("complaint", collect);
        } else if (type.equals(2)) {
            //招聘单位
            List<ComplaintDto> list = complaintMapper.complaintRecruitment(complaintId);
            map.put("complaintRecruitment", list);
        }

        return map;
    }


    private void editInformationSignUp(EditPersonDataQuery query) {
        HrSignUp up = new HrSignUp();
        up.setUserId(query.getUserId());
        up.setSex(query.getSex());
        up.setEducation(query.getEducation());
        String graduationTime = query.getGraduationTime();
        Date date = StringUtil.StrToDate(graduationTime);
        up.setGraduationTime(date);
        up.setProfession(query.getProfession());
        up.setRegistrationPositionId(query.getRegistrationPositionId());
        String arrivalTime = query.getArrivalTime();
        Date date1 = StringUtil.StrToDate(arrivalTime);
        up.setArrivalTime(date1);
        up.setExpectedSalaryLower(query.getExpectedSalaryLower());
        up.setExpectedSalaryUpper(query.getExpectedSalaryUpper());
        up.setItIsPublic(query.getItIsPublic());
        up.setAgreePlatformHelp(query.getAgreePlatformHelp());
        Assert.isFalse(signUpMapper.updatePesronInformation(up) < 1, RlzyConstant.ADD_FAILED);

    }

    @Override
    public List<Feedback> feedback() {
        List<Feedback> list = feedbackMapper.feedbck();
        return list.stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<HrUser> selectReferrerInfo(Integer userId, Integer typeId) {
        List<HrUser> list = userMapper.selectReferrerInfo(userId, typeId);
        return list.stream().collect(Collectors.toList());

    }

    @Override
    public List<HrUser> collectReferrer(Integer userId) {
        List<HrUser> hrUsers = userMapper.collectReferrer(userId);
        return hrUsers.stream()
                .map(dto -> {
                    Integer recommendNoLower = dto.getRecommendNoLower();
                    Integer recommendNoUpper = dto.getRecommendNoUpper();
                    dto.setRecommend(recommendNoLower + "-" + recommendNoUpper + "人");
                    return dto;
                }).collect(Collectors.toList());
    }

    private void editInformation(EditPersonDataQuery query, String url) {
        HrUser hrUser = new HrUser();
        hrUser.setHeadImage(url);
        hrUser.setSex(query.getSex());
        hrUser.setEducation(query.getEducation());
        String graduationTime = query.getGraduationTime();
        Date date = StringUtil.StrToDate(graduationTime);
        hrUser.setGraduationTime(date);
        hrUser.setProfession(query.getProfession());
        hrUser.setPostIdStr(query.getPostIdStr());
        hrUser.setArrivalTime(query.getArrivalTime());
        hrUser.setExpectedSalaryLower(query.getExpectedSalaryLower());
        hrUser.setExpectedSalaryUpper(query.getExpectedSalaryUpper());
        hrUser.setPublicIs(query.getItIsPublic());
        hrUser.setAgreeHelp(query.getAgreePlatformHelp());
        Assert.isFalse(userMapper.editPersonData(hrUser) < 1, RlzyConstant.ADD_FAILED);
    }


}
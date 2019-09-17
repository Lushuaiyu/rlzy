package com.nado.rlzy.service.impl;

import com.nado.rlzy.bean.dto.ComplaintDto;
import com.nado.rlzy.bean.dto.PersonCoDto;
import com.nado.rlzy.bean.query.AddCoQuery;
import com.nado.rlzy.bean.query.EditPersonDataQuery;
import com.nado.rlzy.db.mapper.*;
import com.nado.rlzy.db.pojo.*;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.platform.exception.ImgException;
import com.nado.rlzy.service.PersonCenterService;
import com.nado.rlzy.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
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

    @Resource
    private HrBriefchapterMapper mapper;

    @Resource
    private HrGroupMapper hrGroupMapper;

    @Resource
    private HrSignUpMapper signUpMapper;

    @Resource
    private HrUserMapper userMapper;

    @Resource
    private HrComplaintMapper complaintMapper;

    @Resource
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
        AssertUtil.isTrue(StringUtils.isBlank(content), RlzyConstant.FEEDBACK_NOT_NULL);
        AssertUtil.isTrue(null == userId, RlzyConstant.USERID_NOT_NULL);
        AssertUtil.isTrue(StringUtils.isBlank(name), RlzyConstant.FEEDBACK_NAME_NOT_NULL);
        AssertUtil.isTrue(StringUtils.isBlank(phone), RlzyConstant.FEEDBACK_PHONE_NOT_NULL);

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
        String busLicense = OssUtilOne.picUpload(query.getBusLicense(), "0");
        hrGroup.setBusinesslicense(busLicense);
        hrGroup.setPid(id);
        hrGroup.setCertifierid(query.getUserId());
        boolean b = hrGroupMapper.insertSelective(hrGroup) < 1;
        AssertUtil.isTrue(b, RlzyConstant.OPS_FAILED_MSG);
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
                    double v = dto.getExpectedSalaryUpper().doubleValue();
                    String format1 = StringUtil.decimalFormat2(v);
                    double v1 = dto.getExpectedSalaryLower().doubleValue();
                    String s1 = StringUtil.decimalFormat2(v1);
                    String s = s1 + "k-" + format1 + "k";
                    dto.setExpectedSalaryy(s);
                    return dto;
                }).collect(Collectors.toList());

    }

    @Override
    public List<HrUser> personalInformationReferrer(Integer userId) {
        List<HrUser> hrSignUps = userMapper.personalInformationReferrer(userId);
        List<HrUser> collect = hrSignUps.stream().map(dto -> {

            Integer noUpper = dto.getRecommendNo();
            String number = noUpper + "人";
            dto.setRecommendedNumber(number);
            return dto;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editPersonData(EditPersonDataQuery query) {

        //编辑资料
        editInformation(query);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editPersonDataRecommend(EditPersonDataQuery query) {
        HrUser user = new HrUser();
        user.setId(String.valueOf(query.getUserId()));
        String s = OssUtilOne.picUpload(query.getHeadImage(), "0");
        user.setHeadImage(s);
        user.setRecommenderId(query.getUserId());
        user.setPostIdStr(query.getPostIdStr());
        user.setRecommendNo(query.getRecommendNo());
        user.setRecommendInfo(query.getRecommendInfo());
        user.setPublicIs(query.getItIsPublic());
        user.setAgreeHelp(query.getAgreePlatformHelp());
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1, RlzyConstant.OPS_FAILED_MSG);

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
        up.setExpectedSalaryUpper(query.getExpectedSalaryUpper());
        up.setExpectedSalaryLower(query.getExpectedSalaryLower());
        up.setItIsPublic(query.getItIsPublic());
        up.setAgreePlatformHelp(query.getAgreePlatformHelp());
        //
        AssertUtil.isTrue(signUpMapper.updateByPrimaryKeySelective(up) < 1, RlzyConstant.ADD_FAILED);

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
    @Transactional(rollbackFor = Exception.class)
    public int updateHeadImage(String userId, String headImage, String userName, Integer type) {
        HrUser user = new HrUser();
        user.setId(userId);
        String s = OssUtilOne.picUpload(headImage, userId);
        if (type.equals(1)) {
            //总账号
            if (null != headImage && "" != headImage) {
                user.setHeadImage(s);
            }
        } else {
            //子账号
            if (null != userName && "" != userName) {
                user.setUserName(userName);
            }
            if (null != headImage && "" != headImage) {
                user.setHeadImage(s);
            }

        }

        userMapper.updateByPrimaryKeySelective(user);
        return 1;
    }

    @Override
    public List<HrUser> collectReferrer(Integer userId) {
        List<HrUser> hrUsers = userMapper.collectReferrer(userId);
        return hrUsers.stream()
                .map(dto -> {
                    Integer recommendNo = dto.getRecommendNo();
                    dto.setRecommend(recommendNo + "人");
                    return dto;
                }).collect(Collectors.toList());
    }

    private void editInformation(EditPersonDataQuery query) {
        HrUser hrUser = new HrUser();
        HrUser user = userMapper.selectAllInformation(query.getUserId());
        if (query.getHeadImage() != null && query.getHeadImage() != "") {
            String s = OssUtilOne.picUpload(query.getHeadImage(), "0");
            hrUser.setHeadImage(s);
        }
        if (query.getUserId() != null || query.getSex() != null || query.getEducation() != null ||
                query.getGraduationTime() != null || query.getProfession() != null || query.getPostIdStr() != null ||
                query.getArrivalTime() != null || query.getExpectedSalaryLower() != null || query.getExpectedSalaryUpper() != null ||
                query.getItIsPublic() != null || query.getAgreePlatformHelp() != null) {
            hrUser.setId(String.valueOf(query.getUserId()));
            Integer sex = query.getSex() != null ? query.getSex() : user.getSex();
            hrUser.setSex(sex);
            String education = query.getEducation() != null ? query.getEducation() : user.getEducation();
            hrUser.setEducation(education);

            String graduationTime = StringUtil.DateToStr(user.getGraduationTime());
            String s = query.getGraduationTime() != null ? query.getGraduationTime() : graduationTime;
            Date date = StringUtil.StrToDate(s);
            hrUser.setGraduationTime(date);
            String s1 = query.getProfession() != null ? query.getProfession() : user.getProfession();
            hrUser.setProfession(s1);
            String s2 = query.getPostIdStr() != null ? query.getPostIdStr() : user.getPostIdStr();
            hrUser.setPostIdStr(s2);
            String s3 = StringUtil.DateToStr(user.getArrivalTime());
            String arrivalTime = query.getArrivalTime() != null ? query.getArrivalTime() : s3;
            Date date1 = StringUtil.StrToDate(arrivalTime);
            hrUser.setArrivalTime(date1);
            BigDecimal bigDecimal = query.getExpectedSalaryUpper() != null ? query.getExpectedSalaryUpper() : user.getExpectedSalaryLower();
            hrUser.setExpectedSalaryUpper(bigDecimal);
            BigDecimal bigDecimal1 = query.getExpectedSalaryUpper() != null ? query.getExpectedSalaryUpper() : user.getExpectedSalaryUpper();
            hrUser.setExpectedSalaryLower(bigDecimal1);
            Integer integer = query.getItIsPublic() != null ? query.getItIsPublic() : user.getPublicIs();
            hrUser.setPublicIs(integer);
            Integer integer1 = query.getAgreePlatformHelp() != null ? query.getAgreePlatformHelp() : user.getAgreeHelp();
            hrUser.setAgreeHelp(integer1);
            AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(hrUser) < 1, RlzyConstant.ADD_FAILED);
        }
    }


}
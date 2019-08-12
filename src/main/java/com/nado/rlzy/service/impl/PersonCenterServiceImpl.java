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
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
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
    public Map<String, List<PersonCoDto>> queryTheAuditFailed(Integer userId) {
        return hrGroupMapper.queryTheAuditFailed(userId);

    }

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
    public Map<String, Object> queryPersonCo(Integer userId) {

        List<PersonCoDto> coDtos = hrGroupMapper.queryPersonCo(userId);
        List<PersonCoDto> list = hrGroupMapper.queryPersonCORecruitment(userId);
        List<PersonCoDto> collect = coDtos.stream().collect(Collectors.toList());
        List<PersonCoDto> dtos = list.stream().collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("queryPersonCo", collect);
        map.put("queryPersonCORecruitment", dtos);
        return map;


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCo(AddCoQuery query) {

        List<HrGroup> hrGroups = hrGroupMapper.queryAgentEnterprisePid(8);
        //参数校验
        checkNullParams(query.getCoName(), query.getCoAddress(),
                query.getCompanyProfile(), query.getBusLicense());
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
        //添加成功 返回1 失败 返回0
        Integer result = b == false ? 1 : 0;
        return result;
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
    public HashMap<Object, Object> searchComplaintRecord(Integer userId, Integer typeId) {
        List<ComplaintDto> collect = complaintMapper.searchComplaintRecord(userId, typeId).stream().collect(Collectors.toList());
        List<ComplaintDto> dtos = complaintMapper.searchComplaintRecordMyself(userId, typeId)
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
            //代招单位下的单位
            List<ComplaintDto> collect = complaintMapper.complaint(complaintId).stream().collect(Collectors.toList());
            map.put("complaint", collect);
        } else if (type.equals(2)){
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
        up.setGraduationTime(query.getGraduationTime());
        up.setProfession(query.getProfession());
        up.setRegistrationPositionId(query.getRegistrationPositionId());
        up.setArrivalTime(query.getArrivalTime());
        up.setExpectedSalaryLower(query.getExpectedSalaryLower());
        up.setExpectedSalaryUpper(query.getExpectedSalaryUpper());
        up.setItIsPublic(query.getItIsPublic());
        up.setAgreePlatformHelp(query.getAgreePlatformHelp());
        Assert.isFalse(signUpMapper.updatePesronInformation(up) < 1, RlzyConstant.ADD_FAILED);

    }

    @Override
    public List<Feedback> feedback() {
        //创建Example
        Example example = new Example(Feedback.class);

        //创建Criteria
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteFlag", 0);
        List<Feedback> list = feedbackMapper.selectByExample(example);
        return list.stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<HrUser> selectReferrerInfo(Integer userId, Integer typeId) {
        List<HrUser> list = userMapper.selectReferrerInfo(userId, typeId);
        return list.stream().collect(Collectors.toList());

    }

    private void editInformation(EditPersonDataQuery query, String url) {
        HrUser hrUser = new HrUser();
        hrUser.setHeadImage(url);
        hrUser.setSex(query.getSex());
        hrUser.setEducation(query.getEducation());
        hrUser.setGraduationTime(query.getGraduationTime());
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
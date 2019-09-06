package com.nado.rlzy.service.impl;

import cn.hutool.core.util.IdcardUtil;
import com.nado.rlzy.RlzyApplication;
import com.nado.rlzy.bean.query.RecruitmentSideRegisterHobHuntingQuery;
import com.nado.rlzy.bean.query.RecruitmentSideRegisterQuery;
import com.nado.rlzy.db.mapper.HrGroupMapper;
import com.nado.rlzy.db.mapper.HrSignUpMapper;
import com.nado.rlzy.db.mapper.HrSignupDeliveryrecordMapper;
import com.nado.rlzy.db.mapper.HrUserMapper;
import com.nado.rlzy.db.pojo.HrGroup;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.db.pojo.HrSignupDeliveryrecord;
import com.nado.rlzy.db.pojo.HrUser;
import com.nado.rlzy.platform.constants.RlzyConstant;
import com.nado.rlzy.service.UserService;
import com.nado.rlzy.utils.AssertUtil;
import com.nado.rlzy.utils.MD5;
import com.nado.rlzy.utils.PhoneUtil;
import com.nado.rlzy.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName 招聘端和求职端 登录注册 修改密码啊
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/6/29 10:55
 * @Version 1.0
 */
@Service
@Import(RlzyApplication.class)
public class UserServiceImpl implements UserService {
    @Resource
    private HrUserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Value("${yuntongxun.templateId}")
    private String templateId;

    @Resource
    private HrGroupMapper groupMapper;

    @Resource
    private HrSignUpMapper signUpMapper;

    @Resource
    private HrSignupDeliveryrecordMapper signupDeliveryrecordMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changePasswoed(String phone, String code, String passWord, Integer userId) {
        //校验参数
        checkParams(phone, code, passWord);

        String key = "phone::" + phone + "templateCode::" + templateId;
        //判断 key 过期时间
        AssertUtil.isTrue(!(redisTemplate.hasKey(key)), RlzyConstant.SMS_CODE_STALEDATED);
        AssertUtil.isTrue(!(redisTemplate.opsForValue().get(key).toString().equals(code)), RlzyConstant.SMS_MESSAGE_FAILED);
        passWord = MD5.getMD5(passWord + RlzyConstant.PASSWORD_SALT);
        return userMapper.changePassword(userId, passWord);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registerUser(RecruitmentSideRegisterQuery query) {
        //校验参数
        check(query.getUnitType(),
                query.getImageHead(),
                query.getUserName(),
                query.getIdCard(),
                query.getGroupName(),
                query.getGroupAddress(),
                query.getGroupInfo(),
                query.getBusinessLicense(),
                query.getEnterpriseLicense(),
                query.getRegistrantCertificate(),
                query.getSocialCreditCode(),
                query.getLegalPerson(),
                query.getRegistrationPlace());

        //初始化主表信息, 返回主键
        initUser(query.getId(), query.getImageHead(), query.getUserName(), query.getIdCard(), query.getUnitType());

        if (query.getUnitType().equals(5)) {
            //初始化 企业信息 招聘单位
            initGroup(query.getUnitType(), query.getGroupName(), query.getGroupAddress(), query.getGroupInfo(),
                    query.getBusinessLicense(), query.getEnterpriseLicense(), query.getRegistrantCertificate(),
                    query.getSocialCreditCode(), query.getLegalPerson(), query.getRegistrationPlace(), query.getUnitType());
        } else {
            //代招单位
            initGroup(query.getUnitType(), query.getGroupName(), query.getGroupAddress(), query.getGroupInfo(),
                    query.getBusinessLicense(), query.getEnterpriseLicense(), query.getRegistrantCertificate(),
                    query.getSocialCreditCode(), query.getLegalPerson(), query.getRegistrationPlace(), query.getUnitType());
        }
        return 1;
    }


    private void initGroup(Integer userId, String groupName, String groupAddress, String groupInfo,
                           String businessLicense, String enterpriseLicense, String registrantCertificate,
                           String socialCreditCode, String legalPerson, String registrationPlace, Integer unitType) {
        HrGroup group = new HrGroup();
        group.setGroupname(groupName);
        group.setGroupaddress(groupAddress);
        group.setGroupinfo(groupInfo);
        group.setBusinesslicense(businessLicense);
        group.setEnterpriseLicense(enterpriseLicense);
        group.setRegistrantCertificate(registrantCertificate);
        group.setSocialcreditcode(socialCreditCode);
        group.setLegalperson(legalPerson);
        group.setRegistrationplace(registrationPlace);
        group.setCertifierid(userId);
        group.setStatus(1);
        group.setCreatetime(LocalDateTime.now());
        if (unitType.equals(5)) {
            group.setType(1);
        } else {
            group.setType(0);
        }
        group.setGroupStatus(0);
        AssertUtil.isTrue(groupMapper.insertSelective(group) < 1, RlzyConstant.OPS_FAILED_MSG);
    }


    private void initUser(String id, String imageHead, String userName, String idCard, Integer unitType) {
        HrUser user = new HrUser();
        user.setId(id);
        user.setUserName(userName);
        user.setType(unitType);
        user.setHeadImage(imageHead);
        // 身份证应该正则匹配
        user.setIdCard(idCard);
        //TODO
        // 身份证 实名认证
        user.setStatus(0);
        user.setRegisterTime(LocalDateTime.now());
        AssertUtil.isTrue(userMapper.updateByPrimaryKey(user) < 1, RlzyConstant.OPS_FAILED_MSG);
    }

    private void check(Integer unitType, String imageHead,
                       String userName, String idCard,
                       String groupName, String groupAddress,
                       String groupInfo, String businessLicense,
                       String enterpriseLicense, String registrantCertificate,
                       String socialCreditCode, String legalPerson, String registrationPlace) {
        AssertUtil.isTrue(null == unitType, "身份类型不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userName), "姓名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(imageHead), "头像不能为空");

        AssertUtil.isTrue(StringUtils.isBlank(idCard), "身份证不能为空");

        boolean card = IdcardUtil.isValidCard(idCard);
        AssertUtil.isTrue(false == card, "身份证号码输入有误");
        AssertUtil.isTrue(StringUtils.isBlank(groupName), "公司名字不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(groupAddress), "公司地址不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(groupInfo), "公司简介不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(businessLicense), "企业许可证不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(socialCreditCode), "统一社会信用码");
        AssertUtil.isTrue(StringUtils.isBlank(enterpriseLicense), "企业许可证");
        AssertUtil.isTrue(StringUtils.isBlank(registrantCertificate), "注册人委托证明");
        AssertUtil.isTrue(StringUtils.isBlank(registrationPlace), "注册地");
        AssertUtil.isTrue(StringUtils.isBlank(legalPerson), "法人");
    }

    @Override
    public HrUser findByPhone(String phone) {
        HrUser one = userMapper.selectPhoneByPhone(phone);
        return one;


    }

    @Override
    public HrUser findUserById(String userId) {

        HrUser hrUser = new HrUser();
        hrUser.setId(userId);
        hrUser.setStatus(0);
        HrUser one = userMapper.selectOne(hrUser);

        return one;
    }

    @Override
    public HrUser queryUser(String phone, String password) {
        String md5 = MD5.getMD5(password + RlzyConstant.PASSWORD_SALT);
        return userMapper.queryUser(phone, md5);
    }

    @Override
    public HrUser login(String phone, String password) {
        String md5 = MD5.getMD5(password + RlzyConstant.PASSWORD_SALT);

        HrUser login = userMapper.login(phone, md5);
        HrUser user = new HrUser();
        user.setUserId(Integer.valueOf(login.getId()));
        user.setId(null);
        user.setImproveInformation(login.getImproveInformation());
        user.setViolationFlag(login.getViolationFlag());
        user.setViolationTime(login.getViolationTime());
        return login;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registerJobHunting(RecruitmentSideRegisterHobHuntingQuery query) {
        if (query.getUnitType().equals(1)) {
            //本人
            initUserJobHunt(query.getId(),
                    query.getImageHead(), query.getUserName(), query.getIdCard(), query.getUnitType(),
                    query.getSex(), query.getEducation(), query.getGraduationTime(), query.getRegistrationPositionId(), query.getProfession(), query.getArrivalTime(),
                    query.getExpectedSalaryUpper(), query.getExpectedSalaryLower());

            //报名表
           /* Integer signUpId =*/ initSignUp(query.getId(), query.getSex(), query.getUserName(), query.getIdCard(),
                    query.getEducation(), query.getGraduationTime(), query.getRegistrationPositionId(),
                    query.getProfession(), query.getArrivalTime(),
                    query.getExpectedSalaryUpper(), query.getExpectedSalaryLower(), query.getItIsPublic(), query.getAgreePlatformHelp());
            //报名表投递记录表
            //initSignUpDeliveryrecord(signUpId);

        } else {
            //推荐人
            initUserReferrer(query.getId(), query.getImageHead(), query.getUserName(), query.getIdCard(), query.getPostIdStr(),
                    query.getRecommendNo(), query.getRecommendInfo(), query.getItIsPublic(), query.getAgreePlatformHelp(), query.getUnitType());

        }
        return 1;
    }

    @Override
    public int register(RecruitmentSideRegisterHobHuntingQuery query) {
        //校验参数
        checkregister(query.getPhone(), query.getCode(), query.getPassword(), query.getConfirmPassword());
        //查缓存 判断验证码
        String key = "phone::" + query.getPhone() + "templateCode::" + templateId;
        //判断 key 过期时间
        AssertUtil.isTrue(!(redisTemplate.hasKey(key)), RlzyConstant.SMS_CODE_STALEDATED);

        AssertUtil.isTrue(!(redisTemplate.opsForValue().get(key).toString().equals(query.getCode())), RlzyConstant.SMS_MESSAGE_FAILED);
        //有没有注册过
        HrUser user = new HrUser();
        user.setMobile(query.getPhone());
        HrUser one = userMapper.selectOne(user);
        AssertUtil.isTrue(null != one, "用户已存在, 不要重复注册");
        //if (query.getUnitType().equals(1)) {
        //本人
        Integer userId = initregisterUser(query.getPhone(), MD5.getMD5(query.getPassword() + RlzyConstant.PASSWORD_SALT));
        return userId;
        //} else {
            /*//推荐人
            Integer userId = initRegisterReferrer(query.getPhone(), MD5.getMD5(query.getPassword() + RlzyConstant.PASSWORD_SALT));*/
        //return userId;
        //}
    }

    private Integer initRegisterReferrer(String phone, String md5) {
        HrUser user = new HrUser();
        user.setMobile(phone);
        user.setPassword(md5);
        user.setRegisterTime(LocalDateTime.now());
        user.setImproveInformation(1);
        userMapper.insertSelective(user);
        return Integer.valueOf(user.getId());

    }

    private Integer initregisterUser(String phone, String md5) {
        HrUser user = new HrUser();
        user.setMobile(phone);
        user.setPassword(md5);
        user.setRegisterTime(LocalDateTime.now());
        user.setImproveInformation(1);
        userMapper.insertSelective(user);
        return Integer.valueOf(user.getId());
    }

    private void checkregister(String phone, String code, String password, String confirmPassword) {
        PhoneUtil.phone(phone);
        AssertUtil.isTrue(StringUtils.isBlank(code), "验证码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(password), "密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword), "确认密码不能为空");
        AssertUtil.isTrue(!password.equals(confirmPassword), "密码与确认密码不一致");


    }

    private void initSignUpDeliveryrecord(Integer signUpId) {
        HrSignupDeliveryrecord deliveryrecord = new HrSignupDeliveryrecord();
        deliveryrecord.setSignupId(signUpId);
        deliveryrecord.setCreateTime(LocalDateTime.now());
        deliveryrecord.setDeleteFlag(0);
        signupDeliveryrecordMapper.insertSelective(deliveryrecord);
        AssertUtil.isTrue(signupDeliveryrecordMapper.insertSelective(deliveryrecord) < 1, RlzyConstant.OPS_FAILED_MSG);
    }

    private void initUserReferrer(String id, String imageHead, String userName, String idCard,
                                  String postIdStr, Integer recommendNo,
                                  String recommendInfo, Integer itIsPublic, Integer agreePlatformHelp, Integer unitType) {
        HrUser user = new HrUser();
        user.setId(id);
        user.setHeadImage("afdsafaf");
        user.setUserName(userName);
        user.setIdCard(idCard);
        user.setPostIdStr(postIdStr);
        user.setRecommendNo(recommendNo);
        user.setType(unitType);
        user.setRecommendInfo(recommendInfo);
        user.setPublicIs(itIsPublic);
        user.setAgreeHelp(agreePlatformHelp);
        user.setRegisterTime(LocalDateTime.now());
        AssertUtil.isTrue(userMapper.updateByPrimaryKey(user) < 1, RlzyConstant.OPS_FAILED_MSG);
    }

    private Integer initSignUp(String userId, Integer sex, String userName, String idCard, String education, String graduationTime,
                               String registrationPositionId, String profession, String arrivalTime,
                               String expectedSalaryUpper, String expectedSalaryLower, Integer itIsPublic, Integer agreePlatformHelp) {
        HrSignUp signUp = new HrSignUp();
        signUp.setSex(sex);
        signUp.setUserName(userName);
        boolean b = IdcardUtil.isvalidCard18(idCard);
        AssertUtil.isTrue(b == false, "身份证输入有误, 请重新输入");
        signUp.setIdCard(idCard);
        int ageByIdCard = IdcardUtil.getAgeByIdCard(idCard);
        signUp.setAge(ageByIdCard);
        signUp.setEducation(education);
        signUp.setUserId(Integer.valueOf(userId));
        String graduationTime1 = graduationTime;
        Date date1 = StringUtil.StrToDate(graduationTime1);
        signUp.setGraduationTime(date1);
        signUp.setRegistrationPositionId(registrationPositionId);
        signUp.setProfession(profession);
        Date date = StringUtil.StrToDate(arrivalTime);
        signUp.setArrivalTime(date);
        BigDecimal lowerExpectedSalary = StringUtil.decimal(expectedSalaryLower);
        signUp.setExpectedSalaryLower(lowerExpectedSalary);

        BigDecimal upperExpectedSalary = StringUtil.decimal(expectedSalaryUpper);
        signUp.setExpectedSalaryUpper(upperExpectedSalary);
        signUp.setItIsPublic(itIsPublic);
        signUp.setAgreePlatformHelp(agreePlatformHelp);
        signUp.setCreateTime(LocalDateTime.now());
        AssertUtil.isTrue(signUpMapper.insertSelective(signUp) < 1, RlzyConstant.OPS_FAILED_MSG);
        return signUp.getId();
    }

    private void initUserJobHunt(String id, String imageHead, String userName, String idCard, Integer unitType,
                                 Integer sex, String education, String graduationTime,
                                 String registrationPositionId, String profession, String arrivalTime,
                                 String expectedSalaryUpper, String expectedSalaryLower) {
        HrUser user = new HrUser();
        user.setId(id);
        user.setHeadImage("affadf");
        user.setUserName(userName);
        boolean b = IdcardUtil.isvalidCard18(idCard);
        AssertUtil.isTrue(b == false, "身份证输入有误, 请重新输入");
        user.setIdCard(idCard);
        user.setType(unitType);
        user.setSex(sex);
        user.setRegisterTime(LocalDateTime.now());
        user.setEducation(education);
        String graduationTime1 = graduationTime;
        Date date = StringUtil.StrToDate(graduationTime1);
        user.setGraduationTime(date);
        user.setPostIdStr(registrationPositionId);
        user.setProfession(profession);
        String time = arrivalTime;
        Date date1 = StringUtil.StrToDate(time);
        user.setArrivalTime(date1);
        BigDecimal lowerExpectedSalaryUpper = StringUtil.decimal(expectedSalaryUpper);
        user.setExpectedSalaryUpper(lowerExpectedSalaryUpper);
        BigDecimal lowerExpectedSalary = StringUtil.decimal(expectedSalaryLower);
        user.setExpectedSalaryLower(lowerExpectedSalary);
        AssertUtil.isTrue(userMapper.updateByPrimaryKey(user) < 1, RlzyConstant.OPS_FAILED_MSG);
    }

    private void checkJobHunting(Integer unitType, String imageHead, String userName, Integer sex,
                                 String idCard, String education, Date graduationTime,
                                 String profession, String registrationPositionId, String arrivalTime,
                                 String expectedSalaryUpper, String expectedSalaryLower, Integer itIsPublic,
                                 Integer agreePlatformHelp, String postIdStr,
                                 Integer recommendNo, String recommendInfo) {
        AssertUtil.isTrue(null == unitType, "身份类型不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(imageHead), "头像不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空");
        AssertUtil.isTrue(null == sex, "性别不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(idCard), "身份证不能为空");
        boolean validCard = IdcardUtil.isValidCard(idCard);
        AssertUtil.isTrue(false == validCard, "身份证输入有误");
        AssertUtil.isTrue(StringUtils.isBlank(education), "学历不能为空");
        AssertUtil.isTrue(null == graduationTime, "毕业时间不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(profession), "专业不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(registrationPositionId), "意向岗位不能为空");
        AssertUtil.isTrue(null == arrivalTime, "到岗时间不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(expectedSalaryUpper), "期望薪资上限不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(expectedSalaryLower), "期望薪资下限不能为空");
        AssertUtil.isTrue(null == itIsPublic, "是否公开");
        AssertUtil.isTrue(null == agreePlatformHelp, "是否获取平台帮助");
        AssertUtil.isTrue(null == postIdStr, "意向岗位不能为空");
        AssertUtil.isTrue(null == recommendNo, "推荐人数上限");
        AssertUtil.isTrue(StringUtils.isBlank(recommendInfo), "推荐说明不能为空");


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int switchIdentity(Integer userId, Integer type) {
        return userMapper.switchIdentity(userId, type);
    }


    private void checkParams(String phone, String code, String password) {
        AssertUtil.isTrue(StringUtils.isBlank(phone), RlzyConstant.PHONE_NULL);
        AssertUtil.isTrue(StringUtils.isBlank(code), RlzyConstant.CODE_NULL);
        AssertUtil.isTrue(StringUtils.isBlank(password), RlzyConstant.PASSWORD_NULL);

    }
}
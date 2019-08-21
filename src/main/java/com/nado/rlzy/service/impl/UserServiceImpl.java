package com.nado.rlzy.service.impl;

import cn.hutool.core.lang.Assert;
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
import com.nado.rlzy.utils.MD5;
import com.nado.rlzy.utils.PhoneUtil;
import com.nado.rlzy.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @Autowired
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
        Assert.isFalse(!(redisTemplate.hasKey(key)), RlzyConstant.SMS_CODE_STALEDATED);
        Assert.isFalse(!(redisTemplate.opsForValue().get(key).toString().equals(code)), RlzyConstant.SMS_MESSAGE_FAILED);
        passWord = MD5.getMD5(passWord + RlzyConstant.PASSWORD_SALT);
        return userMapper.changePassword(userId, passWord);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registerUser(RecruitmentSideRegisterQuery query) {
        //校验参数
        check(query.getPhone(),
                query.getCode(),
                query.getPassword(),
                query.getConfirmPassword(),
                query.getUnitType(),
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

        //查缓存 判断验证码
        String key = "phone::" + query.getPhone() + "templateCode::" + templateId;
        //判断密码与确认密码是否一致
        Assert.isFalse(!(query.getPassword().equals(query.getConfirmPassword())), "确认密码与密码不一致");
        //判断 key 过期时间
        Assert.isFalse(!(redisTemplate.hasKey(key)), RlzyConstant.SMS_CODE_STALEDATED);
        Assert.isFalse(!(redisTemplate.opsForValue().get(key).toString().equals(query.getCode())), RlzyConstant.SMS_MESSAGE_FAILED);

        HrUser user = new HrUser();
        user.setMobile(query.getPhone());
        HrUser one = userMapper.selectOne(user);
        Assert.isFalse(null != one, "用户已存在, 不要重复注册");

        //初始化主表信息, 返回主键
        Integer userId = initUser(query.getPhone(), query.getPassword(),
                query.getImageHead(), query.getUserName(), query.getIdCard(), query.getUnitType());

        if (query.getUnitType().equals(5)) {
            //初始化 企业信息 招聘单位
            initGroup(userId, query.getGroupName(), query.getGroupAddress(), query.getGroupInfo(),
                    query.getBusinessLicense(), query.getEnterpriseLicense(), query.getRegistrantCertificate(),
                    query.getSocialCreditCode(), query.getLegalPerson(), query.getRegistrationPlace(), query.getUnitType());
        } else {
            //代招单位
            initGroup(userId, query.getGroupName(), query.getGroupAddress(), query.getGroupInfo(),
                    query.getBusinessLicense(), query.getEnterpriseLicense(), query.getRegistrantCertificate(),
                    query.getSocialCreditCode(), query.getLegalPerson(), query.getRegistrationPlace(), query.getUnitType());

        }


        return 0;
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
        Assert.isFalse(groupMapper.insertSelective(group) < 1, RlzyConstant.OPS_FAILED_MSG);
    }


    private Integer initUser(String phone, String password, String imageHead, String userName, String idCard, Integer unitType) {
        HrUser user = new HrUser();
        user.setUserName(userName);
        user.setMobile(phone);
        password = MD5.getMD5(password + RlzyConstant.PASSWORD_SALT);
        user.setPassword(password);
        user.setType(unitType);
        user.setHeadImage(imageHead);
        // 身份证应该正则匹配
        user.setIdCard(idCard);
        //TODO
        // 身份证 实名认证
        user.setStatus(0);
        user.setRegisterTime(LocalDateTime.now());
        // insertSelective 保存一个实体，null的属性不会保存，会使用数据库默认值
        Assert.isFalse(userMapper.insertSelective(user) < 1, RlzyConstant.OPS_FAILED_MSG);
        return Integer.valueOf(user.getId());
    }

    private void check(String phone, String code,
                       String password, String confirmPassword,
                       Integer unitType, String imageHead,
                       String userName, String idCard,
                       String groupName, String groupAddress,
                       String groupInfo, String businessLicense,
                       String enterpriseLicense, String registrantCertificate,
                       String socialCreditCode, String legalPerson, String registrationPlace) {
        PhoneUtil.phone(phone);
        Assert.isFalse(StringUtils.isBlank(code), "验证码不能为空");
        Assert.isFalse(StringUtils.isBlank(password), "密码不能为空");
        Assert.isFalse(StringUtils.isBlank(confirmPassword), "确认密码不能为空");
        Assert.isFalse(null == unitType, "身份类型不能为空");
        Assert.isFalse(StringUtils.isBlank(userName), "姓名不能为空");
        Assert.isFalse(StringUtils.isBlank(imageHead), "头像不能为空");

        Assert.isFalse(StringUtils.isBlank(idCard), "身份证不能为空");

        boolean card = IdcardUtil.isValidCard(idCard);
        Assert.isFalse(false == card, "身份证号码输入有误");
        Assert.isFalse(StringUtils.isBlank(groupName), "公司名字不能为空");
        Assert.isFalse(StringUtils.isBlank(groupAddress), "公司地址不能为空");
        Assert.isFalse(StringUtils.isBlank(groupInfo), "公司简介不能为空");
        Assert.isFalse(StringUtils.isBlank(businessLicense), "企业许可证不能为空");
        Assert.isFalse(StringUtils.isBlank(socialCreditCode), "统一社会信用码");
        Assert.isFalse(StringUtils.isBlank(enterpriseLicense), "企业许可证");
        Assert.isFalse(StringUtils.isBlank(registrantCertificate), "注册人委托证明");
        Assert.isFalse(StringUtils.isBlank(registrationPlace), "注册地");
        Assert.isFalse(StringUtils.isBlank(legalPerson), "法人");
    }

    @Override
    public HrUser findByPhone(String phone) {
        HrUser hrUser = new HrUser();
        hrUser.setMobile(phone);
        hrUser.setDeleteFlag(0);
        HrUser one = userMapper.selectOne(hrUser);
        return one;


    }

    @Override
    public HrUser findUserById(String userId) {

        HrUser hrUser = new HrUser();
        hrUser.setId("1");
        hrUser.setDeleteFlag(0);
        HrUser one = userMapper.selectOne(hrUser);

        return one;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registerJobHunting(RecruitmentSideRegisterHobHuntingQuery query) {
        //校验参数
        checkJobHunting(query.getPhone(), query.getCode(), query.getPassword(), query.getConfirmPassword(),
                query.getUnitType(), query.getImageHead(), query.getUserName(), query.getSex(), query.getIdCard(),
                query.getEducation(), query.getGraduationTime(), query.getProfession(), query.getRegistrationPositionId(), query.getArrivalTime(),
                query.getExpectedSalaryLower(), query.getExpectedSalaryUpper(), query.getItIsPublic(), query.getAgreePlatformHelp(),
                query.getPostIdStr(), query.getRecommendNoLower(), query.getRecommendNoUpper(), query.getRecommendInfo());
        //查缓存 判断验证码
        String key = "phone::" + query.getPhone() + "templateCode::" + templateId;
        //判断 key 过期时间
        Assert.isFalse(!(redisTemplate.hasKey(key)), RlzyConstant.SMS_CODE_STALEDATED);
        Assert.isFalse(!(redisTemplate.opsForValue().get(key).toString().equals(query.getCode())), RlzyConstant.SMS_MESSAGE_FAILED);

        //有没有注册过
        HrUser user = new HrUser();
        user.setMobile(query.getPhone());
        HrUser one = userMapper.selectOne(user);
        Assert.isFalse(null != one, "用户已存在, 不要重复注册");

        if (query.getUnitType().equals(1)) {
            //本人
            Integer userId = initUserJobHunt(query.getPhone(), query.getPassword(),
                    query.getImageHead(), query.getUserName(), query.getIdCard(), query.getUnitType(),
                    query.getSex(), query.getEducation(), query.getGraduationTime(), query.getRegistrationPositionId(), query.getProfession(), query.getArrivalTime(),
                    query.getExpectedSalaryLower(), query.getExpectedSalaryUpper());

            //报名表
            Integer signUpId = initSignUp(userId, query.getEducation(), query.getGraduationTime(), query.getRegistrationPositionId(), query.getProfession(), query.getArrivalTime(),
                    query.getExpectedSalaryLower(), query.getExpectedSalaryUpper(), query.getItIsPublic(), query.getAgreePlatformHelp());
            //报名表投递记录表
            initSignUpDeliveryrecord(signUpId, query.getJobStatus());

        } else {
            //推荐人
            initUserReferrer(query.getImageHead(), query.getUserName(), query.getIdCard(), query.getPostIdStr(), query.getRecommendNoUpper(),
                    query.getRecommendNoLower(), query.getRecommendInfo(), query.getItIsPublic(), query.getAgreePlatformHelp());

        }
        return 1;
    }

    private void initSignUpDeliveryrecord(Integer signUpId, Integer jobStatus) {
        HrSignupDeliveryrecord deliveryrecord = new HrSignupDeliveryrecord();
        deliveryrecord.setSignupId(signUpId);
        deliveryrecord.setCreateTime(LocalDateTime.now());
        deliveryrecord.setDeleteFlag(0);
        signupDeliveryrecordMapper.insertSelective(deliveryrecord);
        Assert.isFalse(signupDeliveryrecordMapper.insertSelective(deliveryrecord) < 1, RlzyConstant.OPS_FAILED_MSG);
    }

    private void initUserReferrer(String imageHead, String userName, String idCard,
                                  String postIdStr, Integer recommendNoUpper, Integer recommendNoLower,
                                  String recommendInfo, Integer itIsPublic, Integer agreePlatformHelp) {
        HrUser user = new HrUser();
        user.setHeadImage(imageHead);
        user.setUserName(userName);
        user.setIdCard(idCard);
        user.setPostIdStr(postIdStr);
        user.setRecommendNoUpper(recommendNoUpper);
        user.setRecommendNoLower(recommendNoLower);
        user.setRecommendInfo(recommendInfo);
        user.setPublicIs(itIsPublic);
        user.setAgreeHelp(agreePlatformHelp);
        user.setRegisterTime(LocalDateTime.now());
        Assert.isFalse(userMapper.insertSelective(user) < 1, RlzyConstant.OPS_FAILED_MSG);
    }

    private Integer initSignUp(Integer userId, String education, String graduationTime,
                               String registrationPositionId, String profession, String arrivalTime,
                               String expectedSalaryLower, String expectedSalaryUpper, Integer itIsPublic, Integer agreePlatformHelp) {
        HrSignUp signUp = new HrSignUp();
        signUp.setEducation(education);
        signUp.setUserId(userId);
        signUp.setGraduationTime(graduationTime);
        signUp.setRegistrationPositionId(registrationPositionId);
        signUp.setProfession(profession);
        LocalDateTime localDateTime = StringUtil.strToLocalDateTime(arrivalTime);
        signUp.setArrivalTime(localDateTime);
        BigDecimal lowerExpectedSalary = StringUtil.decimal(expectedSalaryLower);
        signUp.setExpectedSalaryLower(lowerExpectedSalary);
        BigDecimal upperExpectedSalary = StringUtil.decimal(expectedSalaryUpper);
        signUp.setExpectedSalaryUpper(upperExpectedSalary);
        signUp.setItIsPublic(itIsPublic);
        signUp.setAgreePlatformHelp(agreePlatformHelp);
        signUp.setCreateTime(LocalDateTime.now());
        Assert.isFalse(signUpMapper.insertSelective(signUp) < 1, RlzyConstant.OPS_FAILED_MSG);
        return signUp.getId();
    }

    private Integer initUserJobHunt(String phone, String password, String imageHead, String userName, String idCard, Integer unitType,
                                    Integer sex, String education, String graduationTime,
                                    String registrationPositionId, String profession, String arrivalTime,
                                    String expectedSalaryLower, String expectedSalaryUpper) {
        HrUser user = new HrUser();
        user.setMobile(phone);
        user.setPassword(password);
        user.setHeadImage(imageHead);
        user.setUserName(userName);
        user.setIdCard(idCard);
        user.setType(unitType);
        user.setSex(sex);
        user.setRegisterTime(LocalDateTime.now());
        user.setEducation(education);
        user.setGraduationTime(graduationTime);
        user.setPostIdStr(registrationPositionId);
        user.setProfession(profession);
        user.setArrivalTime(arrivalTime);
        BigDecimal lowerExpectedSalary = StringUtil.decimal(expectedSalaryLower);
        user.setExpectedSalaryLower(lowerExpectedSalary);
        BigDecimal upperExpectedSalary = StringUtil.decimal(expectedSalaryUpper);
        user.setExpectedSalaryUpper(upperExpectedSalary);

        Assert.isFalse(userMapper.insertSelective(user) < 1, RlzyConstant.OPS_FAILED_MSG);
        return Integer.valueOf(user.getId());
    }

    private void checkJobHunting(String phone, String code, String password, String confirmPassword,
                                 Integer unitType, String imageHead, String userName, Integer sex,
                                 String idCard, String education, String graduationTime,
                                 String profession, String registrationPositionId, String arrivalTime,
                                 String expectedSalaryLower, String expectedSalaryUpper, Integer itIsPublic,
                                 Integer agreePlatformHelp, String postIdStr, Integer recommendNoLower, Integer recommendNoUpper, String recommendInfo) {
        PhoneUtil.phone(phone);
        Assert.isFalse(StringUtils.isBlank(code), "验证码不能为空");
        Assert.isFalse(StringUtils.isBlank(password), "密码不能为空");
        Assert.isFalse(StringUtils.isBlank(confirmPassword), "确认密码不能为空");
        Assert.isFalse(!password.equals(confirmPassword), "密码与确认密码不一致");
        Assert.isFalse(null == unitType, "身份类型不能为空");
        Assert.isFalse(StringUtils.isBlank(imageHead), "头像不能为空");
        Assert.isFalse(StringUtils.isBlank(userName), "用户名不能为空");
        Assert.isFalse(null == sex, "性别不能为空");
        Assert.isFalse(StringUtils.isBlank(idCard), "身份证不能为空");
        boolean validCard = IdcardUtil.isValidCard(idCard);
        Assert.isFalse(false == validCard, "身份证输入有误");
        Assert.isFalse(StringUtils.isBlank(education), "学历不能为空");
        Assert.isFalse(null == graduationTime, "毕业时间不能为空");
        Assert.isFalse(StringUtils.isBlank(profession), "专业不能为空");
        Assert.isFalse(StringUtils.isBlank(registrationPositionId), "意向岗位不能为空");
        Assert.isFalse(null == arrivalTime, "到岗时间不能为空");
        Assert.isFalse(StringUtils.isBlank(expectedSalaryLower), "期望薪资下限");
        Assert.isFalse(StringUtils.isBlank(expectedSalaryUpper), "期望薪资上限");
        Assert.isFalse(null == itIsPublic, "是否公开");
        Assert.isFalse(null == agreePlatformHelp, "是否获取平台帮助");
        Assert.isFalse(null == postIdStr, "意向岗位不能为空");
        Assert.isFalse(null == recommendNoLower, "推荐人数下限");
        Assert.isFalse(null == recommendNoUpper, "推荐人数上限");
        Assert.isFalse(StringUtils.isBlank(recommendInfo), "推荐说明不能为空");


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int switchIdentity(Integer userId, Integer type) {
        return userMapper.switchIdentity(userId, type);
    }


    private void checkParams(String phone, String code, String password) {
        Assert.isFalse(StringUtils.isBlank(phone), RlzyConstant.PHONE_NULL);
        Assert.isFalse(StringUtils.isBlank(code), RlzyConstant.CODE_NULL);
        Assert.isFalse(StringUtils.isBlank(password), RlzyConstant.PASSWORD_NULL);

    }
}
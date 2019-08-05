package com.nado.rlzy.service;

import com.nado.rlzy.bean.query.RecruitmentSideRegisterHobHuntingQuery;
import com.nado.rlzy.bean.query.RecruitmentSideRegisterQuery;
import com.nado.rlzy.db.pojo.HrUser;

/**
 * @ClassName 招聘端和求职端 登陆注册 service
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/6/29 10:52
 * @Version 1.0
 */
public interface UserService {

    /**
     * 修改密码 || 忘记密码
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:06 2019/7/16
     * @Param [phone, code, passWord, userId]
     * @return int
     **/
    int changePasswoed(String phone, String code, String passWord, Integer userId);

    /**
     *  用户切换身份
     * @param userId 用户id
     * @param type 用户 身份
     * @return
     */
    int switchIdentity(Integer userId, Integer type);

    /**
     * 根据用户名查询用户信息
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:53 2019/7/26
     * @Param [userId]
     * @return com.nado.rlzy.db.pojo.HrUser
     **/
    HrUser findUserById(String userId);

    /**
     * 通过手机号码 确认用户有没有注册
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:18 2019/7/26
     * @Param [phone]
     * @return com.nado.rlzy.db.pojo.HrUser
     **/
    HrUser findByPhone(String phone);

    /**
     * 招聘端注册
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:37 2019/7/26
     * @Param [query]
     * @return int
     **/
    int registerUser(RecruitmentSideRegisterQuery query);

    /**
     * 求职端注册 完善信息
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:56 2019/7/29
     * @Param [query]
     * @return int
     **/
    int registerJobHunting(RecruitmentSideRegisterHobHuntingQuery query);













}
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
     * 修改密码
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:06 2019/7/16
     * @Param [phone, code, passWord, userId]
     * @return int
     **/
    int changePasswoed(String phone, String code, String passWord, Integer userId);

    /**
     * 忘记密码
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 13:46 2019-09-17
     * @Param [phone, code, password]
     * @return int
     */
    int forgetPassword(String phone, String code, String password);

    /**
     *  用户切换身份
     * @return
     */
    int switchIdentity(RecruitmentSideRegisterHobHuntingQuery query);

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
     * 招聘端注册 完善信息
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

    /**
     * 求职端注册
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 21:11 2019-09-04
     * @Param [query]
     * @return int
     */
    int register(RecruitmentSideRegisterHobHuntingQuery query);

    /**
     * 登录
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:44 2019/9/4
     * @Param [phone, password]
     * @return com.nado.rlzy.db.pojo.HrUser
     **/
    HrUser login(String phone, String password);

    /**
     * 子账号登录
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 17:51 2019-09-17
     * @Param [userId]
     * @return com.nado.rlzy.db.pojo.HrUser
     */
    HrUser loginSonAccountNumber(String phone, String password);

    /**
     * 查询用户是否被禁用
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:19 2019/9/4
     * @Param [phone, password]
     * @return com.nado.rlzy.db.pojo.HrUser
     **/
    HrUser queryUser(String phone, String password);

    /**
     * 查询拉黑的用户
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 19:52 2019-09-07
     * @Param [userId]
     * @return int
     */
    int selectEnterPriseBlacakList( Integer userId);

    /**
     * 平台拉黑求职端
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 21:06 2019-09-11
     * @Param [userId]
     * @return int
     */
    int selectPlatformlack(Integer userId);

    /**
     * 平台拉黑招聘端
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 21:06 2019-09-11
     * @Param [userId]
     * @return int
     */
    int selectplatformBlackRecruitmentEnd(Integer userId);

















}
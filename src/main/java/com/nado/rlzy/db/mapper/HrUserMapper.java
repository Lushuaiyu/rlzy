package com.nado.rlzy.db.mapper;

import com.nado.rlzy.bean.dto.ComplaintPage;
import com.nado.rlzy.bean.query.JobListQuery;
import com.nado.rlzy.db.pojo.HrUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

/**
 * 招聘端 注册登录mapper
 *
 * @Author lushuaiyu
 * @Description //TODO
 * @Date 10:51 2019/6/29
 **/
public interface HrUserMapper extends Mapper<HrUser>, MySqlMapper<HrUser> {


    HrUser finrdUserById(String userId);


    /**
     * 编辑个人资料 本人
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:44 2019/7/9
     **/
    int editPersonData(HrUser user);

    /**
     * 获取手机号码
     *
     * @return com.nado.rlzy.db.pojo.HrUser
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:47 2019/6/29
     * @Param [query]
     **/
    HrUser getUserByMobile(Map<String, Object> map);


    String getPassword(@Param("phone") String phone);

    /**
     * 查询用户是否已经登录
     *
     * @return com.nado.rlzy.db.pojo.HrUser
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:04 2019/9/4
     * @Param [phone]
     **/
    HrUser selectPhoneByPhone(@Param("phone") String phone);


    /**
     * 查询用户是否被禁用
     *
     * @return
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:16 2019/9/4
     * @Param
     **/
    HrUser queryUser(@Param("phone") String phone, @Param("password") String password);

    /**
     * 注册
     *
     * @param phone 手机号
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:01 2019/6/29
     **/
    HrUser queryUserByPhone(@Param("phone") String phone);

    /**
     * 登录
     *
     * @return com.nado.rlzy.db.pojo.HrUser
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:39 2019/9/4
     * @Param [phone, password]
     **/
    HrUser login(@Param("phone") String phone, @Param("password") String password);

    /**
     * 招聘端子账号登录
     *
     * @return com.nado.rlzy.db.pojo.HrUser
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 16:49 2019-09-17
     * @Param [userId]
     */
    HrUser loginSonAccountNumber(@Param("phone") String phone, @Param("password") String password);

    /**
     * 登陆
     *
     * @return com.nado.rlzy.db.pojo.HrUser
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 16:51 2019-06-29
     * @Param [phone, password]
     */
    HrUser getUserByMobileAndPwd(Map<String, Object> map);

    /**
     * 修改密码
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:39 2019/7/16
     * @Param [userId]
     **/
    int changePassword(@Param("userId") Integer userId, @Param("password") String password, @Param("phone") String phone);

    /**
     * 根据手机号修改密码
     *
     * @return int
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 13:48 2019-09-17
     * @Param [phone, password]
     */

    int forgetPassword(@Param("phone") String phone, @Param("password") String password);

    /**
     * 切换身份时禁掉原来的身份  for example 原来是本人 切换到推荐人 则禁掉本人
     *
     * @param userId 用户 id
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:58 2019/7/16
     **/
    int switchIdentity(@Param("userId") Integer userId);


    /**
     * 招聘端 首页 查询推荐人信息
     *
     * @param userId 推荐人id
     * @param typeId 推荐人 身份id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:29 2019/7/25
     **/
    List<HrUser> selectReferrerInfo(@Param("userId") Integer userId, @Param("typeId") Integer typeId);

    /**
     * 查询推荐人 || 求职者名字
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 12:48 2019/8/4
     * @Param [type, id]
     **/
    @Select(value = "select UserName as userName from hr_user where type = #{type} and id = #{userId}")
    List<ComplaintPage> selectMyselfName(@Param("type") Integer type, @Param("userId") Integer userId);


    /**
     * 求职端 本人 个人中心
     *
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:27 2019/7/9
     * @Param [userId]
     **/
    HrUser personalInformation(@Param("userId") Integer userId);

    /**
     * 招聘端个人资料 推荐人
     * @param userId 用户id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:11 2019/7/9
     **/
    HrUser personalInformationReferrer(@Param("userId") Integer userId);



    /**
     * 招聘端 首页 查询推荐人概览
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.JobListDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:01 2019/8/14
     * @Param [query]
     **/
    List<HrUser> selectReferrer(JobListQuery query);

    /**
     * 招聘端 首页 查询推荐人详情
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:35 2019/8/15
     * @Param [query]
     **/
    HrUser selectReferrerDetails(@Param("userId") Integer userId);

    /**
     * 招聘端 首页 查询推荐人详情 历史记录 推荐者下的求职者
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:29 2019/8/15
     * @Param [userId]
     **/
    int jobSeeker(@Param("userId") Integer userId);

    /**
     * 招聘端首页推荐人列表详情 历史记录 已面试
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:08 2019/8/15
     * @Param [userId]
     **/
    int interviewed(@Param("userId") Integer userId);

    /**
     * 招聘端首页推荐人列表详情 历史记录 已报到
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:13 2019/8/15
     * @Param [userId]
     **/
    int arReported(@Param("userId") Integer userId);

    /**
     * 招聘端首页推荐人列表详情 历史记录 未面试
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:16 2019/8/15
     * @Param [userId]
     **/
    int noInterview(@Param("userId") Integer userId);

    /**
     * 招聘端首页推荐人列表详情  历史记录 未报到
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:17 2019/8/15
     * @Param [userId]
     **/
    int noReported(@Param("userId") Integer userId);

    /**
     * 招聘端个人中心 我的搜藏 推荐人信息概览
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:49 2019/8/16
     * @Param [query]
     **/
    List<HrUser> collectReferrer(@Param("userId") Integer userId);

    /**
     * update 违规 user表
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:47 2019/8/16
     * @Param [userId]
     **/
    int updateViolationFlag(@Param("userId") Integer userId);

    /**
     * 连续三次没有面试或者报道 停权 推荐人 | 本人
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:31 2019/8/19
     * @Param [userId]
     **/
    @Select(value = "select violation_flag from hr_user where status = 0 and Id = #{userId}")
    List<HrUser> queryVilolationFlag(Integer userId);

    /**
     * 求职端 个人中心 查询本人的违规
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:33 2019/8/19
     * @Param [userId]
     **/
    List<HrUser> queryMyselfVillation(@Param("userId") Integer userId);


    /**
     * 求职端 个人中心  推荐人推荐的求职者违规
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:18 2019/8/19
     * @Param [userId]
     **/
    List<HrUser> queryReferrerVillation(@Param("userId") Integer userId);

    /**
     * 求职端 个人中心  查询报名状态
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:24 2019/8/19
     * @Param [userId, briefChapterId]
     **/
    List<HrUser> queryJobStatus(@Param("userId") Integer userId, @Param("briefChapterId") Integer briefChapterId);


    /**
     * 查询拉黑的用户
     *
     * @return int
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 19:51 2019-09-07
     * @Param [userId]
     */
    @Select(value = "select count(*) from hr_user u, hr_enterpriseblacklist e where u.IdCard = e.idCard\n" +
            "and u.status = 0 and u.id = #{userId}")
    int selectEnterPriseBlacakList(@Param("userId") Integer userId);

    /**
     * 根据 userId 更新网易云信的token
     *
     * @return int
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 14:03 2019-09-09
     * @Param [userId]
     */
    int updateNetEaseTokenByserId(@Param("userId") Integer userId, @Param("netEaseToekn") String netEaseToekn);

    /**
     * 平台拉黑求职端
     *
     * @param userId 用户 id
     * @return int
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 20:27 2019-09-11
     */
    int selectPlatformlack(@Param("userId") Integer userId);

    /**
     * 平台拉黑招聘端
     *
     * @return
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 20:58 2019-09-11
     * @Param
     */
    int selectplatformBlackRecruitmentEnd(@Param("userId") Integer userId);

    /**
     * 推荐人意向岗位 求职端首页 简章列表
     *
     * @return java.lang.String
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 15:43 2019-09-13
     * @Param [userId]
     */
    String selectReferrerIntentionalPost(@Param("userId") Integer userId);

    /**
     * 查询所有
     *
     * @return com.nado.rlzy.db.pojo.HrUser
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 17:27 2019-09-16
     * @Param [userId]
     */
    HrUser selectAllInformation(@Param("userId") Integer userId);

    /**
     * 查询头像 昵称 身份证
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:31 2019/9/18
     * @Param [userId]
     * @return com.nado.rlzy.db.pojo.HrUser
     **/
    HrUser selectHeadUserNameIdCard(@Param("userId") Integer userId);

    /**
     * 查询身份
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:26 2019/9/19
     * @Param [userId]
     * @return int
     **/
    HrUser checkUserIdentity(@Param("userId") Integer userId);

    /**
     * 子账号| 总账号权限查询
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:26 2019/9/19
     * @Param []
     * @return java.lang.String
     **/
    HrUser subAccountPermission(@Param("userId") Integer userId);


}
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


    int save(HrUser record);

    HrUser finrdUserById(String userId);


    /**
     * 编辑个人资料 本人
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:44 2019/7/9

     * @return int
     **/
    int editPersonData( HrUser user);

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
     * 注册
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:01 2019/6/29
     * @param phone 手机号
     **/
    HrUser queryUserByPhone(@Param("phone") String phone);


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
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:39 2019/7/16
     * @Param [userId]
     * @return int
     **/
    int changePassword(@Param("userId") Integer userId, @Param("password") String password);

    /**
     *  切换身份
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:58 2019/7/16
     * @param userId 用户 id
     * @param type 用户身份
     * @return int
     **/
    int switchIdentity(@Param("userId") Integer userId, @Param("type") Integer type);


    /**
     * 招聘端 首页 查询推荐人信息
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:29 2019/7/25
     * @param userId 推荐人id
     * @param typeId 推荐人 身份id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     **/
    List<HrUser> selectReferrerInfo(@Param("userId") Integer userId, @Param("typeId") Integer typeId);

    /**
     * 查询推荐人 || 求职者名字
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 12:48 2019/8/4
     * @Param [type, id]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     **/
    @Select(value = "select UserName as userName from hr_user where type = #{type} and id = #{userId}")
    List<ComplaintPage> selectMyselfName(@Param("type") Integer type, @Param("userId") Integer userId);


    /**
     * 求职端 本人 个人中心
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:27 2019/7/9
     * @Param [userId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    List<HrUser> personalInformation(@Param("userId") Integer userId);

    /**
     * 招聘端个人资料 推荐人
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:11 2019/7/9
     * @param  userId 用户id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrUser> personalInformationReferrer(@Param("userId") Integer userId);

    /**
     * 招聘端 首页 查询推荐人概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:01 2019/8/14
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.bean.dto.JobListDto>
     **/
    List<HrUser> selectReferrer(JobListQuery query);

    /**
     * 招聘端 首页 查询推荐人详情
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:35 2019/8/15
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     **/
    List<HrUser> selectReferrerDetails(@Param("userId") Integer userId);

    /**
     * 招聘端 首页 查询推荐人详情 历史记录 推荐者下的求职者
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:29 2019/8/15
     * @Param [userId]
     * @return int
     **/
    int jobSeeker (@Param("userId") Integer userId);

    /**
     * 招聘端首页推荐人列表详情 历史记录 已面试
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:08 2019/8/15
     * @Param [userId]
     * @return int
     **/
    int interviewed(@Param("userId") Integer userId);

    /**
     * 招聘端首页推荐人列表详情 历史记录 已报到
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:13 2019/8/15
     * @Param [userId]
     * @return int
     **/
    int arReported(@Param("userId") Integer userId);

    /**
     * 招聘端首页推荐人列表详情 历史记录 未面试
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:16 2019/8/15
     * @Param [userId]
     * @return int
     **/
    int noInterview(@Param("userId") Integer userId);
    /**
     * 招聘端首页推荐人列表详情  历史记录 未报到
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:17 2019/8/15
     * @Param [userId]
     * @return int
     **/
    int noReported(@Param("userId") Integer userId);

    /**
     * 招聘端个人中心 我的搜藏 推荐人信息概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:49 2019/8/16
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     **/
    List<HrUser> collectReferrer(@Param("userId") Integer userId);

    /**
     * update 违规 user表
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:47 2019/8/16
     * @Param [userId]
     * @return int
     **/
    int updateViolationFlag(@Param("userId") Integer userId);

    /**
     * 连续三次没有面试或者报道 停权 推荐人 | 本人
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:31 2019/8/19
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     **/
    @Select(value = "select violation_flag from hr_user where DeleteFlag = 0 and Id = #{userId}")
    List<HrUser> queryVilolationFlag(Integer userId);

    /**
     * 求职端 个人中心 查询本人的违规
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:33 2019/8/19
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     **/
    List<HrUser> queryMyselfVillation(@Param("userId") Integer userId);


    /**
     * 求职端 个人中心  推荐人推荐的求职者违规
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:18 2019/8/19
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     **/
    List<HrUser> queryReferrerVillation(@Param("userId") Integer userId);

    /**
     * 求职端 个人中心  查询报名状态
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:24 2019/8/19
     * @Param [userId, briefChapterId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrUser>
     **/
    List<HrUser> queryJobStatus(@Param("userId") Integer userId, @Param("briefChapterId") Integer briefChapterId);







}
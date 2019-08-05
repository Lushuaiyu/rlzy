package com.nado.rlzy.service;

import com.nado.rlzy.bean.dto.ComplaintDto;
import com.nado.rlzy.bean.dto.ComplaintPage;
import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.bean.query.ComplaintQuery;
import com.nado.rlzy.db.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName 求职端首页
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/6/28 16:45
 * @Version 1.0
 */
public interface JobSearchHomePageService {
    /**
     * 求职端 公司主页
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrGroup>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:43 2019/7/10
     * @Param [groupId]
     **/
    List<HrGroup> coHomePage(Integer groupId);

    /**
     * 招聘简章查询接口 代招单位  全部职位
     *
     * @return java.util.List<com.nado.rlzy.bean.frontEnd.BriefcharpterFront>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:59 2019/6/27
     * @Param [query] 入参
     **/
    List<HrBriefchapter> queryBriefcharpterDtoByParams(BriefcharpterQuery query);


    /**
     * 招聘简章查询接口 招聘单位 全部职位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:06 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterByParams(BriefcharpterQuery query);

    /**
     * 求职端 首页 报名表 查询报名者的名字
     *
     * @param type   求职端身份
     * @param userId 登录用户 id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:38 2019/7/11
     **/
    List<HrSignUp> querySignUpUserName(Integer type, Integer userId);

    /**
     * 查询招聘简章详情 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:20 2019/7/4
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterDetileByParams(BriefcharpterQuery query);

    /**
     * 查询招聘简章详情 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:44 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterDetileRecruitment(BriefcharpterQuery query);

    /**
     * 长白班按返费高低排 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:06 2019/7/4
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterByLongLive(BriefcharpterQuery query);

    /**
     * 长白班按返费高低排 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:46 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterByLongLiveRecruitment(BriefcharpterQuery query);



    /**
     * 企业直招专区 代招企业
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:02 2019/7/5
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> directBusiness(BriefcharpterQuery query);

    /**
     * 企业直招专区 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:41 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> directBusinessRecruitment(BriefcharpterQuery query);

    /**
     * 直接录取专区 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:25 2019/7/5
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> directAdmission (BriefcharpterQuery query);

    /**
     * 直接录取专区 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:56 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> directAdmissionRecruitment(BriefcharpterQuery query);


    /**
     * 有吃住按返费高低排 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:15 2019/7/4
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterByLongEat(BriefcharpterQuery query);

    /**
     * 有吃住按返费高低排 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:18 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterByLongEatRecruitment(BriefcharpterQuery query);

    /**
     * 推荐费top10 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:07 2019/7/4
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> recommendedFeeTop10(BriefcharpterQuery query);

    /**
     * 推荐费 toop10 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:17 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> recommendedFeeTop10Recruitment(BriefcharpterQuery query);

    /**
     * 学生专区 代招企业
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:47 2019/7/4
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> studentDivision(BriefcharpterQuery query);

    /**
     * 学生专区 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:49 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> studentDivisionRecruitment(BriefcharpterQuery query);

    /**
     * 工资排行榜 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:57 2019/7/5
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> salaryLeaderboard(BriefcharpterQuery query);

    /**
     * 工资排行榜 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:16 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> salaryLeaderboardRecruitment(BriefcharpterQuery query);

    /**
     * 求职端 首页 我的工作 根据求职者的名字查询求职状态
     *
     * @param jobUserName 求职者姓名
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:19 2019/7/11
     **/
    List<HrSignUp> querySignUpStatus(String jobUserName);


    /**
     *  求职端 首页 我的工作 根据求职状态查询简章
     *
     * @param signUpStatus 求职状态
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:40 2019/7/11
     **/
    Map<Object, Object> queryBriefchapterBySignUpStatus(Integer signUpStatus);


    /**
     *  求职端 首页 我的工作  取消报名
     *
     * @param signUpId 报名id
     * @return void
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:37 2019/7/12
     **/
    void cancelRegistration(Integer signUpId);


    /**
     * 求职端 首页 简章详情 我要报名 身份是本人
     *
     * @param userId         用户 id
     * @param briefChapterId 简章id
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:08 2019/7/14
     **/
    int IWantToSignUp(Integer userId, Integer briefChapterId);

    /**
     *  添加收藏 和取消收藏
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:56 2019/7/8
     * @Param [collect]
     * @return void
     **/
    void addCancelBriefchapter(Collect collect);



    /**
     * 新增求职表
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:04 2019/7/8
     * @Param [signUp]
     * @return void
     **/
    void addSignUpTable(HrSignUp  query);

    /**
     * 添加自定义分组
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:06 2019/7/8
     * @Param [record, signUpId]
     * @return void
     **/
    void insertSelective(MySignUpTable record);



    /**
     * 查询投诉 求职端
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:05 2019/7/10
     * @Param [userId, typeId, briefchapterId]
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     **/



    /**
     * 添加投诉 求职端
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:21 2019/7/10
     * @Param [query, head]
     * @return void
     **/
    void addComplaint(ComplaintQuery query, String head);

    /**
     * 在招职位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:22 2019/7/10
     * @Param [groupId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> atThePosition( Integer groupId);
    /**
     * 撤销投诉
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:11 2019/7/10
     * @Param []
     * @return void
     **/
    void complaintWithdrawn(ComplaintQuery query);


    /**
     * 查询我的求职表分组信息
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:36 2019/7/22
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.MySignUpTable>
     **/
    List<MySignUpTable> searchGroupingInformation();

    /**
     * 查询 每一个分组的求职者
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:16 2019/7/22
     * @param status 分组状态
     * @return java.util.List<com.nado.rlzy.db.pojo.MySignUpTable>
     **/
    List<HrSignUp> grouper(Integer status);

    /**
     * 求职端 求职表 我的求职表 通过姓名 搜索 报名表
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:43 2019/7/22
     * @param signUpName 求职者姓名
     * @param status 分组状态
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
     List<HrSignUp> selectSignUpTableBySignUpName( String signUpName, Integer status);

     /**
      * 求职端 求职表 我的求职表 确认报名
      * @Author lushuaiyu
      * @Description //TODO
      * @Date 19:46 2019/7/22
      * @Param [briefChapterId, id]
      * @return int
      **/
    int confirmRegistration(Integer briefChapterId, Integer [] id);

    /**
     * 招聘端 信用中心 投诉待处理 已撤销
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:35 2019/7/25
     * @Param [status]
     * @return java.util.List<com.nado.rlzy.bean.dto.ComplaintDto>
     **/
    List<ComplaintDto> creditCenter(Integer status);

    /** 投诉页面 前端选择内容
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:15 2019/8/4
     * @Param [typeId, userId, brieId, dictionary]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String, List<ComplaintPage>> complaintPage(Integer typeId, Integer userId, Integer brieId, Integer dictionary);













}
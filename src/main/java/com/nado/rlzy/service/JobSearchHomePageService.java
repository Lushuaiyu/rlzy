package com.nado.rlzy.service;

import com.nado.rlzy.bean.dto.ComplaintDto;
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
     * 求职端 首页 简章列表查询招聘简章详情 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:20 2019/7/4
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterDetileByParams(BriefcharpterQuery query) ;

    /**
     * 求职端 首页 简章列表 查询招聘简章详情 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:44 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterDetileRecruitment(BriefcharpterQuery query);

    /**
     * 推荐职位 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:17 2019/8/9
     * @Param [recruitedCompany]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    List<HrBriefchapter> recommendAPosition(String recruitedCompany);

    /**
     * 推荐职位 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:17 2019/8/9
     * @Param [recruitedCompany]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    List<HrBriefchapter> recommendAPositionRecruitment(String recruitedCompany);

    /**
     * query 除了 求职端首页 简章列表以外的简章详情
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:17 2019/8/9
     * @Param [query]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String, Object> queryBriefcharpterListDetileByParams(BriefcharpterQuery query);

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
     *  求职端 首页 我的工作 根据求职状态和求职者名字查询简章
     *
     * @param type 登录用户的身份
     * @param userId 用户id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:40 2019/7/11
     **/
    Map<Object, Object> queryBriefchapterBySignUpStatus(Integer type, Integer userId);



    /**
     * 求职端 首页 简章详情 我要报名 身份是本人
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:08 2019/7/14
     **/
    int IWantToSignUp(HrSignupDeliveryrecord deliveryrecord);

    /**
     * 求职端 首页 我的报名表 推荐人给被推荐人报名
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:26 2019/8/7
     * @param
     * @return int
     **/
    int referrerToSIgnUp(HrSignupDeliveryrecord deliveryrecord);

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
    int insertSelective(MySignUpTable record);

    /**
     * 添加投诉 求职端
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:21 2019/7/10
     * @Param [query, head]
     * @return void
     **/
    int addComplaint(ComplaintQuery query, String head);

    /**
     * 在招职位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:22 2019/7/10
     * @Param [groupId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> atThePosition( Integer groupId, String groupName);
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
     * 查询我的求职表分组 推荐人身份才能添加分组
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:36 2019/7/22
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.MySignUpTable>
     **/
    Map<String, Object> searchGroupingInformation(Integer userId);

    /**
     * 查询 求职端 身份是推荐人 首页 我的求职表 分组下的 被推荐人的报名表 | 查询求职端身份是推荐人 首页 我的求职表下被推荐人的求职表
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:16 2019/7/22
     * @param groupName 自定义分组名字
     * @param signUpName 被推荐人姓名
     * @param type 1 我的求职表下的被推荐人的报名表 2 自定义分组下的被推荐人的报名表
     * @return java.util.List<com.nado.rlzy.db.pojo.MySignUpTable>
     **/
    Map<String, Object> grouper(String groupName, String signUpName, Integer type);



     /**
      * 求职端 求职表 我的求职表 确认报名 废弃
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
    List<ComplaintDto> creditCenter(Integer status, Integer type);

    /** 投诉页面 前端选择内容
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:15 2019/8/4
     * @Param [typeId, userId, brieId, dictionary]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String, Object> complaintPage(Integer typeId, Integer userId, Integer brieId, Integer dictionary);













}
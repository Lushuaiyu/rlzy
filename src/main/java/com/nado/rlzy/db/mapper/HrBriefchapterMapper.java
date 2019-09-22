package com.nado.rlzy.db.mapper;

import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.bean.query.EditBriefchapterQuery;
import com.nado.rlzy.bean.query.ReleaseBriefcharpterQuery;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface HrBriefchapterMapper {


    /**
     * 招聘简章查询接口 代招单位  全部职位
     *
     * @param query
     * @return
     */
    List<HrBriefchapter> queryBriefcharpterDtoByParams(BriefcharpterQuery query);

    /**
     * 招聘简章查询接口 招聘单位 全部职位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:29 2019/8/2
     * @Param [query]
     **/
    List<HrBriefchapter> queryBriefcharpterByParams(BriefcharpterQuery query);


    /**
     * 招聘简章详情查询 代招单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:04 2019/7/4
     * @Param [query]
     **/
    HrBriefchapter queryBriefcharpterDetileByParams(BriefcharpterQuery query);


    /**
     * 招聘简章祥情查询 招聘单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:53 2019/8/2
     * @Param [query]
     **/
    HrBriefchapter queryBriefcharpterDetileRecruitment(BriefcharpterQuery query);

    /**
     * 推荐职位 代招单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:04 2019/8/9
     * @Param [query]
     **/
    List<HrBriefchapter> recommendAPosition(@Param("recruitedCompany") String recruitedCompany);

    /**
     * 推荐职位 招聘单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:05 2019/8/9
     * @Param [query]
     **/
    List<HrBriefchapter> recommendAPositionRecruitment(@Param("recruitedCompany") String recruitedCompany);


    /**
     * 推荐费top10 代招单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:04 2019/7/4
     * @Param []
     **/
    List<HrBriefchapter> recommendedFeeTop10(BriefcharpterQuery query);

    /**
     * 推荐费top10 招聘单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:07 2019/8/2
     * @Param [query]
     **/
    List<HrBriefchapter> recommendedFeeTop10Recruitment(BriefcharpterQuery query);

    /**
     * 学生专区 代招单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:44 2019/7/4
     * @Param [query]
     **/
    List<HrBriefchapter> studentDivision(BriefcharpterQuery query);

    /**
     * 学生专区 招聘单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:26 2019/8/2
     * @Param [query]
     **/
    List<HrBriefchapter> studentDivisionRecruitment(BriefcharpterQuery query);


    /**
     * 工资排行榜 代招单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:56 2019/7/5
     * @Param [query]
     **/
    List<HrBriefchapter> salaryLeaderboard(BriefcharpterQuery query);

    /**
     * 工资排行榜 招聘单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:04 2019/8/2
     * @Param [query]
     **/
    List<HrBriefchapter> salaryLeaderboardRecruitment(BriefcharpterQuery query);

    /**
     * 企业直招 代招单位
     *
     * @return
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:43 2019/7/5
     * @Param
     **/
    List<HrBriefchapter> directBusiness(BriefcharpterQuery query);

    /**
     * 企业直招 招聘单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:36 2019/8/2
     * @Param [query]
     **/
    List<HrBriefchapter> directBusinessRecruitment(BriefcharpterQuery query);

    /**
     * 直接录取专区 代招单位
     *
     * @return
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:23 2019/7/5
     * @Param
     **/
    List<HrBriefchapter> directAdmission(BriefcharpterQuery query);

    /**
     * 直接录取专区 招聘单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:53 2019/8/2
     * @Param [query]
     **/
    List<HrBriefchapter> directAdmissionRecruitment(BriefcharpterQuery query);

    /**
     * 招聘简章收藏 概览 代招单位
     *
     * @return
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:47 2019/7/5
     * @Param
     **/
    List<HrBriefchapter> recruitmentBrochureCollection(@Param("userId") Integer userId, @Param("type") Integer type);

    /**
     * 招聘简章收藏 概览 招聘单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:16 2019/8/2
     * @Param [userId]
     **/
    List<HrBriefchapter> recruitmentBrochureCollectionRecruitment(@Param("userId") Integer userId, @Param("type") Integer type);

    /**
     * 求职端 公司主页在招职位查询 代招单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:21 2019/7/10
     * @Param [groupId]
     **/
    List<HrBriefchapter> atThePosition(@Param("groupId") Integer groupId);

    /**
     * 求职端 公司主页在招职位查询 招聘单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:49 2019/8/26
     * @Param [groupId]
     **/
    List<HrBriefchapter> atThePositionRecruitment(@Param("groupId") Integer groupId);

    /**
     * 长白班按返费高低排  代招单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:33 2019/7/4
     * @Param [query]
     **/
    List<HrBriefchapter> queryBriefcharpterByLongLive(BriefcharpterQuery query);

    /**
     * 长白班按返费高低排  招聘单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:41 2019/8/2
     * @Param [query]
     **/
    List<HrBriefchapter> queryBriefcharpterByLongLiveRecruitment(BriefcharpterQuery query);

    /**
     * 有吃住按返费高低排 代招单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:14 2019/7/4
     * @Param [query]
     **/
    List<HrBriefchapter> queryBriefcharpterByLongEat(BriefcharpterQuery query);

    /**
     * 有吃住按返费高低排 招聘单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:16 2019/8/2
     * @Param [query]
     **/
    List<HrBriefchapter> queryBriefcharpterByLongEatRecruitment(BriefcharpterQuery query);

    /**
     * 查询 招聘简章总数 废弃
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:52 2019/6/27
     * @Param [query]
     **/
    int queryCountByparams(BriefcharpterQuery query);

    /**
     * 发布简章
     *
     * @return java.lang.Integer
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:21 2019/7/1
     * @Param [query]
     **/
    Integer save(HrBriefchapter query);

    /**
     * 更新发布简章
     *
     * @return java.lang.Integer
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:21 2019/7/1
     * @Param [query]
     **/
    Integer update(ReleaseBriefcharpterQuery query);

    int update2(EditBriefchapterQuery query);

    /**
     * 修改招聘人数
     *
     * @return java.lang.Integer
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:53 2019/8/20
     * @Param [query]
     **/
    Integer updateNumberOfRecruits(EditBriefchapterQuery query);

    /**
     * 根据求职状态和求职者id查询简章 代招单位
     *
     * @param signUpStatus 求职状态
     * @param id           求职id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:39 2019/7/11
     **/
    List<HrBriefchapter> queryBriefchapterBySignUpStatus(@Param("signUpStatus") List<String> signUpStatus, @Param("id") Integer id);

    /**
     * 根据求职状态和求职者id查询简章 招聘单位
     *
     * @param id 求职id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:33 2019/8/2
     * @Param [signUpStatus]
     **/
    List<HrBriefchapter> queryBriefchapterBySignUpstatusRecruitment(@Param("signUpStatus") List<String> signUpStatus, @Param("id") Integer id);

    /**
     * 招聘端 我的发布 代招单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:12 2019/7/15
     * @Param [userId, typeId, status]
     **/
    List<HrBriefchapter> myRelease(@Param("userId") Integer userId, @Param("status") Integer status);

    /**
     * 招聘端 我的发布 招聘单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 12:06 2019/8/3
     * @Param [userId, typeId, status]
     **/
    List<HrBriefchapter> myReleaseRecruitment(@Param("userId") Integer userId, @Param("status") Integer status);

    /**
     * 招聘端 我的发布 代招单位 子账号
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:01 2019/9/19
     * @Param [userId, status]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> myReleaseSubAccount(@Param("userId") Integer userId, @Param("status") Integer status);

    /**
     * 招聘端 我的发布 招聘单位 子账号
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:03 2019/9/19
     * @Param [userId, status]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> myReleaseRecruitmentSubAccount(@Param("userId") Integer userId, @Param("status") Integer status);

    /**
     * 修改招聘人数
     *
     * @param status       状态
     * @param briefchapter 简章id
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:26 2019/7/23
     **/
    int updateNumberRecruits(@Param("status") Integer status,
                             @Param("briefchapter") Integer briefchapter,
                             @Param("manNum") Integer manNum,
                             @Param("womenNum") Integer womenNum);


    /**
     * 招聘端 我的发布 编辑简章后 status 改为 未提交
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:29 2019/7/24
     * @Param [briefchapter]
     **/
    int updateStatus(@Param("briefchapter") Integer briefchapter);

    /**
     * 报名简章成功后 招聘人数减去相应的数
     *
     * @param briefchapter 简章id
     * @param number       招聘数量
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:09 2019/8/9
     **/
    int remainingQuota(@Param("number") Integer number, @Param("briefchapter") Integer briefchapter);

    /**
     * 查询某个简章的招聘人数
     *
     * @param briefchapter
     * @return
     */
    Integer queryRecruitingNo(@Param("briefchapter") Integer briefchapter);

    /**
     * 招聘端首页简章 代招单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:08 2019/8/14
     * @Param []
     **/
    List<HrBriefchapter> representativeUnit(@Param("userId") Integer userId);
    /**
     * 招聘端首页简章 招聘单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:10 2019/8/14
     * @Param []
     **/
    List<HrBriefchapter> recruitmentUnit(@Param("userId") Integer userId);

    /**
     * 招聘端首页简章 代招单位 子账号
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:42 2019/9/19
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> representativeUnitSubAccount(@Param("userId") Integer userId);

    /**
     * 招聘端首页简章 招聘单位 子账号
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:43 2019/9/19
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> recruitmentUnitSubAccount(@Param("userId") Integer userId);

    /**
     * 求职端 公司主页 历史记录 代招单位下的简章
     *
     * @param groupId 代招单位
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:03 2019/8/26
     **/
    List<HrBriefchapter> companyHomeHistory(@Param("groupId") Integer groupId);

    /**
     * 求职端 公司主页 历史记录 招聘单位的简章
     *
     * @param groupId 招聘单位
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:07 2019/8/26
     **/
    List<HrBriefchapter> companyHomeHistoryRecruitment(@Param("groupId") Integer groupId);

    /**
     * 修改发布简章时的入职返佣的金额 男女 没用上
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:32 2019/8/27
     * @Param [briefchapterId]
     **/
    int updateRebateEntry(@Param("rebateMaleEntry") BigDecimal rebateMaleEntry,
                          @Param("rebateFemaleEntry") BigDecimal rebateFemaleEntry,
                          @Param("briefchapterId") Integer briefchapterId);

    /**
     * 修改返费金额 面试 报道 入职 男女返佣 和招聘人数
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:15 2019/8/28
     * @Param [query]
     **/
    int updateRebateMoney(EditBriefchapterQuery query);

    /***
     * 由有返佣改成无返佣
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:55 2019/8/30
     * @Param [query]
     * @return int
     **/
    int updateRebateToNoRebate(EditBriefchapterQuery query);

    /**
     * 查询面试返佣 报道返佣
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:44 2019/9/1
     * @Param [briechapterId]
     **/
    List<HrBriefchapter> interviewRebateOrReportRebate(@Param("briechapterId") Integer briechapterId);

    /**
     * 查询面试 报道 入职返佣
     *
     * @return com.nado.rlzy.db.pojo.HrBriefchapter
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:15 2019/9/2
     * @Param [bridfchapterId]
     **/
    HrBriefchapter selectRebateByBriefcapterId(@Param("id") Integer id);

    /**
     * 修改简章状态
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:02 2019/9/2
     * @Param [id]
     **/
    @Update(value = "update hr_briefchapter set Status = #{status} where briefChapterStatus = 0 and Id = #{id}")
    int updateBriefchapterStatus(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * now > 简章的面试时间 简章状态改为 已过期
     *
     * @return int
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 23:06 2019-09-02
     * @Param []
     */
    @Update(value = "update hr_briefchapter set Status = 4 where now() > interviewTime")
    int updateInterviewStatus();

    /**
     * 把已结束状态下的简章 没有到面试时间的改为待面试
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:51 2019/9/3
     * @Param []
     **/
    @Update(value = "update hr_briefchapter\n" +
            "set over_status = 0\n" +
            "where briefChapterStatus = 0\n" +
            "  and Status = 4\n" +
            "  and now() < interviewTime")
    int waitingForAnInterview();

    /**
     * 已结束状态下 now() > 面试时间 修改已结束状态为待报到
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:37 2019/9/3
     * @Param []
     **/
    @Update(value = "update hr_briefchapter\n" +
            "set over_status = 1\n" +
            "where briefChapterStatus = 0\n" +
            "  and Status = 4\n" +
            "  and now() > interviewTime")
    int waitForReport();

    /**
     * now > 报道时间 and now < 第一笔返佣时间
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:09 2019/9/3
     * @Param []
     **/
    @Update(value = "update hr_briefchapter br, entry_resignation er\n" +
            "set br.over_status = 2\n" +
            "where now() > br.registerTime\n" +
            "  and now() < er.rebate_time\n" +
            "  and br.briefChapterStatus = 0\n" +
            "  and er.delete_flag = 0\n" +
            "  and br.Status = 4 and br.id = er.brief_chapter_id")
    int waitForRebate();

    /**
     * 招聘端 我的发布 已结束 返佣中
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:35 2019/9/3
     * @Param []
     **/
    @Update(value = "update hr_briefchapter set over_status = 3 where briefChapterStatus = 0 and Status = 4 and Id = #{briefchapterId}")
    int rebating(@Param("briefchapterId") Integer briefchapterId);

    /**
     * 查询简章id
     *
     * @return java.lang.Integer[]
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:30 2019/9/3
     * @Param [briefchapterId]
     **/
    List<Integer> selectIdByBriefchapterId();

    /**
     * 招聘端 我的发布 已结束 返佣结束
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:45 2019/9/3
     * @Param [briefchapterId]
     **/
    @Update(value = "update hr_briefchapter set over_status = 4 where briefChapterStatus = 0 and Status = 4 and Id = #{briefchapterId}")
    int alreadyRebate(@Param("briefchapterId") Integer briefchapterId);

    /**
     * 查询身份是本人求职 简章是代招单位下的单位 的面试时间 | 报道时间 > 现在时间
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:34 2019/9/6
     * @Param []
     **/
    List<HrBriefchapter> interviewAllPerson();

    /**
     * 查询身份是本人求职 简章是招聘单位的简章 的面试时间 | 报道时间 > 现在时间
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:50 2019/9/6
     * @Param []
     **/
    List<HrBriefchapter> interviewAllPersonRecruitment();

    /**
     * 推荐人给被推荐人报名 简章是代招单位下的单位 的面试时间 | 报道时间 > 现在时间
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:44 2019/9/6
     * @Param []
     **/
    List<HrBriefchapter> intervieweAllPersonReferrer();

    /**
     * 推荐人给被推荐人报名 简章是招聘单位下的单位 的面试时间 | 报道时间 > 现在时间
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 21:05 2019-09-06
     * @Param []
     */
    List<HrBriefchapter> interviewAllPersonReferrerRecruitment();

    /**
     * 浏览接口的人数 每浏览一次 加一
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:11 2019/9/18
     * @Param []
     **/
    int browsePerson(@Param("briefchapterId") Integer briefchapterId);

    /**
     * 编辑简章时回显 代招单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:02 2019/9/18
     * @Param [briefchapter]
     **/
    List<HrBriefchapter> editBriefchapterEcho(@Param("briefchapter") Integer briefchapter);

    /**
     * 编辑简章时回显 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:05 2019/9/18
     * @Param [briefchapter]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> editBriefchapterEchoRecruitment(@Param("briefchapter") Integer briefchapter);


}
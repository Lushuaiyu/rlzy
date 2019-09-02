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
     * @param query
     * @return
     */
    List<HrBriefchapter> queryBriefcharpterDtoByParams(BriefcharpterQuery query);

    /**
     * 招聘简章查询接口 招聘单位 全部职位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:29 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterByParams(BriefcharpterQuery query);


    /**
     * 招聘简章详情查询 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:04 2019/7/4
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterDetileByParams(BriefcharpterQuery query);

    /**
     * 招聘简章祥情查询 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:53 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterDetileRecruitment(BriefcharpterQuery query);

    /**
     * 推荐职位 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:04 2019/8/9
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> recommendAPosition(@Param("recruitedCompany") String recruitedCompany );

    /**
     * 推荐职位 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:05 2019/8/9
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> recommendAPositionRecruitment(@Param("recruitedCompany") String recruitedCompany);


    /**
     * 推荐费top10 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:04 2019/7/4
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> recommendedFeeTop10( BriefcharpterQuery query);

    /**
     * 推荐费top10 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:07 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> recommendedFeeTop10Recruitment(BriefcharpterQuery query);

    /**
     * 学生专区 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:44 2019/7/4
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> studentDivision(BriefcharpterQuery query);

    /**
     * 学生专区 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:26 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> studentDivisionRecruitment(BriefcharpterQuery query);



    /**
     * 工资排行榜 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:56 2019/7/5
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> salaryLeaderboard(BriefcharpterQuery query);

    /**
     * 工资排行榜 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:04 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> salaryLeaderboardRecruitment(BriefcharpterQuery query);

    /**
     * 企业直招 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:43 2019/7/5
     * @Param
     * @return
     **/
    List<HrBriefchapter> directBusiness(BriefcharpterQuery query);

    /**
     * 企业直招 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:36 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> directBusinessRecruitment(BriefcharpterQuery query);

    /**
     * 直接录取专区 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:23 2019/7/5
     * @Param
     * @return
     **/
    List<HrBriefchapter> directAdmission (BriefcharpterQuery query);

    /**
     * 直接录取专区 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:53 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> directAdmissionRecruitment(BriefcharpterQuery query);

    /**
     * 招聘简章收藏 概览 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:47 2019/7/5
     * @Param
     * @return
     **/
    List<HrBriefchapter> recruitmentBrochureCollection(@Param("userId") Integer userId, @Param("type") Integer type);
    /**
     * 招聘简章收藏 概览 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:16 2019/8/2
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> recruitmentBrochureCollectionRecruitment(@Param("userId") Integer userId, @Param("type") Integer type);

    /**
     * 求职端 公司主页在招职位查询 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:21 2019/7/10
     * @Param [groupId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> atThePosition(@Param("groupId") Integer groupId);

    /**
     * 求职端 公司主页在招职位查询 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:49 2019/8/26
     * @Param [groupId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> atThePositionRecruitment(@Param("groupId") Integer groupId);

    /**
     * 长白班按返费高低排  代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:33 2019/7/4
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterByLongLive(BriefcharpterQuery query);

    /**
     * 长白班按返费高低排  招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:41 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterByLongLiveRecruitment(BriefcharpterQuery query);

    /**
     * 有吃住按返费高低排 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:14 2019/7/4
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterByLongEat(BriefcharpterQuery query);

    /**
     * 有吃住按返费高低排 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:16 2019/8/2
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
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
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:21 2019/7/1
     * @Param [query]
     * @return java.lang.Integer
     **/
    Integer save(HrBriefchapter query);

    /**
     * 更新发布简章
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:21 2019/7/1
     * @Param [query]
     * @return java.lang.Integer
     **/
    Integer update(ReleaseBriefcharpterQuery query);

    int update2(EditBriefchapterQuery query);

    /**
     * 修改招聘人数
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:53 2019/8/20
     * @Param [query]
     * @return java.lang.Integer
     **/
    Integer updateNumberOfRecruits(EditBriefchapterQuery query);

    /**
     * 根据求职状态和求职者id查询简章 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:39 2019/7/11
     * @param signUpStatus 求职状态
     * @param id 求职id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefchapterBySignUpStatus(@Param("signUpStatus") List<Integer> signUpStatus, @Param("id") Integer id);

    /**
     * 根据求职状态和求职者id查询简章 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:33 2019/8/2
     * @Param [signUpStatus]
     * @param id 求职id
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefchapterBySignUpstatusRecruitment(@Param("signUpStatus") List<Integer> signUpStatus, @Param("id") Integer id);

    /**
     * 招聘端 我的发布 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:12 2019/7/15
     * @Param [userId, typeId, status]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> myRelease(@Param("userId") Integer userId, @Param("status") Integer status);

    /**
     * 招聘端 我的发布 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 12:06 2019/8/3
     * @Param [userId, typeId, status]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> myReleaseRecruitment(@Param("userId") Integer userId, @Param("status") Integer status);

    /**
     * 修改招聘人数
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:26 2019/7/23
     * @param status 状态
     * @param briefchapter 简章id
     * @return int
     **/
    int updateNumberRecruits(@Param("status") Integer status,
                             @Param("briefchapter") Integer briefchapter,
                             @Param("manNum") Integer manNum,
                             @Param("womenNum") Integer womenNum);


    /**
     * 招聘端 我的发布 编辑简章后 status 改为 待审核
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:29 2019/7/24
     * @Param [briefchapter]
     * @return int
     **/
    int updateStatus(@Param("briefchapter") Integer briefchapter);

    /**
     * 报名简章成功后 招聘人数减去相应的数
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:09 2019/8/9
     * @param briefchapter 简章id
     * @param number 招聘数量
     * @return int
     **/
    int remainingQuota( @Param("number") Integer number, @Param("briefchapter") Integer briefchapter);

    /**
     * 查询某个简章的招聘人数
     * @param briefchapter
     * @return
     */
    List<HrBriefchapter> queryRecruitingNo(@Param("briefchapter") Integer briefchapter);

    /**
     * 招聘端首页简章 代招单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:08 2019/8/14
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> representativeUnit(@Param("userId") Integer userId);

    /**
     * 招聘端首页简章 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:10 2019/8/14
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> recruitmentUnit(@Param("userId") Integer userId);

    /**
     * 求职端 公司主页 历史记录 代招单位下的简章
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:03 2019/8/26
     * @param groupId 代招单位
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> companyHomeHistory(@Param("groupId") Integer groupId);

    /**
     * 求职端 公司主页 历史记录 招聘单位的简章
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:07 2019/8/26
     * @param groupId 招聘单位
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> companyHomeHistoryRecruitment(@Param("groupId") Integer groupId);

    /**
     * 修改发布简章时的入职返佣的金额 男女 没用上
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:32 2019/8/27
     * @Param [briefchapterId]
     * @return int
     **/
    int updateRebateEntry(@Param("rebateMaleEntry") BigDecimal rebateMaleEntry,
                          @Param("rebateFemaleEntry") BigDecimal rebateFemaleEntry,
                          @Param("briefchapterId") Integer briefchapterId);

    /**
     * 修改返费金额 面试 报道 入职 男女返佣 和招聘人数
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:15 2019/8/28
     * @Param [query]
     * @return int
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
    int updateRebateToNoRebate (EditBriefchapterQuery query);

    /**
     * 查询面试返佣 报道返佣
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:44 2019/9/1
     * @Param [briechapterId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> interviewRebateOrReportRebate(@Param("briechapterId") Integer briechapterId);

    /**
     * 查询面试 报道 入职返佣
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:15 2019/9/2
     * @Param [bridfchapterId]
     * @return com.nado.rlzy.db.pojo.HrBriefchapter
     **/
    HrBriefchapter selectRebateByBriefcapterId(@Param("id") Integer id);

    /**
     * 修改简章状态
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 20:02 2019/9/2
     * @Param [id]
     * @return int
     **/
    @Update(value = "update hr_briefchapter set Status = #{status} where briefChapterStatus = 0 and Id = #{id}")
    int updateBriefchapterStatus(@Param("id") Integer id, @Param("status") Integer status);


















}
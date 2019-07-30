package com.nado.rlzy.db.mapper;

import com.nado.rlzy.bean.dto.PersonCoDto;
import com.nado.rlzy.bean.dto.ReleaseBriefcharpterDto;
import com.nado.rlzy.bean.query.BriefcharpterQuery;
import com.nado.rlzy.bean.query.ReleaseBriefcharpterQuery;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HrBriefchapterMapper {


    /**
     * 招聘简章查询接口 全部职位
     *
     * @param query
     * @return
     */
    List<HrBriefchapter> queryBriefcharpterDtoByParams(BriefcharpterQuery query);


    /**
     * 招聘简章详情查询
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:04 2019/7/4
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterDetileByParams(BriefcharpterQuery query);


    /**
     * 推荐费top10
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:04 2019/7/4
     * @Param []
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> recommendedFeeTop10( BriefcharpterQuery query);



    /**
     * 学生专区
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:44 2019/7/4
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> studentDivision(BriefcharpterQuery query);

    /**
     * 工资排行榜
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 9:56 2019/7/5
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> salaryLeaderboard(BriefcharpterQuery query);

    /**
     * 企业直招
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:43 2019/7/5
     * @Param
     * @return
     **/
    List<HrBriefchapter> directBusiness(BriefcharpterQuery query);

    /**
     * 直接录取专区
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:23 2019/7/5
     * @Param
     * @return
     **/
    List<HrBriefchapter> directAdmission (BriefcharpterQuery query);

    /**
     * 招聘简章收藏 概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:47 2019/7/5
     * @Param
     * @return
     **/
    List<HrBriefchapter> recruitmentBrochureCollection(@Param("userId") Integer userId);

    /**
     * 在招职位查询
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 19:21 2019/7/10
     * @Param [groupId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> atThePosition(@Param("groupId") Integer groupId);

    /**
     * 长白班按返费高低排 概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:33 2019/7/4
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterByLongLive(BriefcharpterQuery query);

    /**
     * 有吃住按返费高低排 概览
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:14 2019/7/4
     * @Param [query]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefcharpterByLongEat(BriefcharpterQuery query);

    /**
     * 查询 招聘简章总数
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:52 2019/6/27
     * @Param [query]
     **/
    int queryCountByparams(BriefcharpterQuery query);

    /**
     * 查询发布简章
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.ReleaseBriefcharpterDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:40 2019/6/28
     * @Param []
     **/
    List<ReleaseBriefcharpterDto> queryReleaseBriefcharpterByparams(ReleaseBriefcharpterQuery query);

    /**
     * 发布简章
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:21 2019/7/1
     * @Param [query]
     * @return java.lang.Integer
     **/
    Integer save(ReleaseBriefcharpterQuery query);

    /**
     * 更新发布简章
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:21 2019/7/1
     * @Param [query]
     * @return java.lang.Integer
     **/
    Integer update(ReleaseBriefcharpterQuery query);

    /**
     * 查询个人企业
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:37 2019/7/3
     * @Param status 企业状态
     * @return java.util.List<com.nado.rlzy.bean.dto.PersonCoDto>
     **/
    List<PersonCoDto> queryPersonCo(@Param("status") Integer status);














    /**
     * 男生年龄
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:56 2019/7/3
     * @Param [manAgeId]
     * @return java.util.List<com.nado.rlzy.bean.dto.ReleaseBriefcharpterDto>
     **/
    List<ReleaseBriefcharpterDto> checkManAge(@Param("manAgeId") Integer manAgeId);

    /**
     * 女生年龄
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:00 2019/7/3
     * @Param [womenAgeId]
     * @return java.util.List<com.nado.rlzy.bean.dto.ReleaseBriefcharpterDto>
     **/
    List<ReleaseBriefcharpterDto> checkWomenAge(@Param("womenAgeId") Integer womenAgeId);

    /**
     * 加班时长
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:16 2019/7/3
     * @Param [overtimeTime]
     * @return java.util.List<com.nado.rlzy.bean.dto.ReleaseBriefcharpterDto>
     **/
    List<ReleaseBriefcharpterDto> checkOvertimeTime(@Param("overtimeTimeId") Integer overtimeTimeId);




    /**
     * 根据求职状态查询简章
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:39 2019/7/11
     * @param signUpStatus 求职状态
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> queryBriefchapterBySignUpStatus(@Param("signUpStatus") Integer signUpStatus);


    /**
     * 招聘端 我的发布
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:12 2019/7/15
     * @Param [userId, typeId, status]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> myRelease(@Param("userId") Integer userId, @Param("typeId") Integer typeId, @Param("status") Integer status);

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














}
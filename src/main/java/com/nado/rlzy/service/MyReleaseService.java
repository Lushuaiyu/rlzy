package com.nado.rlzy.service;

import com.nado.rlzy.bean.query.EditBriefchapterQuery;
import com.nado.rlzy.bean.query.RebateQuery;
import com.nado.rlzy.bean.query.ReleaseBriefcharpterQuery;
import com.nado.rlzy.db.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName 招聘端 我的发布
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/22 13:33
 * @Version 1.0
 */
public interface MyReleaseService {
    /**
     * 招聘端 我的发布
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:39 2019/7/22
     * @Param [userId, typeId, status]
     **/
    Map<String, Object> myRelease(Integer userId, Integer status);


    /**
     * 得到省市区
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.ProvinceDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 18:37 2019/6/28
     * @Param []
     **/
    List<Province> getPCA();

    /**
     * 发布简章
     *
     * @return void
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 15:24 2019-06-29
     * @Param [query]
     */
    void saveUser(ReleaseBriefcharpterQuery query, Integer type);

    /**
     * 招聘详情 概览
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:15 2019/7/18
     * @Param [list]
     **/
    List<HrSignUp> recruitmentDetailsOverview(Integer[] jobStatus);


    /**
     * 招聘详情 已报名 不合适
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:04 2019/7/19
     * @Param [signUpId]
     **/
    int reportNotSuitable(Integer signUpId, Integer briefChapterId);

    /**
     * 招聘详情 已报名 邀请面试
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:18 2019/7/19
     * @Param [signUpId]
     **/
    int recruitmentDetailsInvitationInterview(Integer signUpId, Integer briefChapterId);

    /**
     * 招聘详情 已报名 直接录取
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:01 2019/7/19
     * @Param [signUpId]
     **/
    int recruitmentDetailsDirectAdmission(Integer signUpId, Integer briefChapterId);

    /**
     * 招聘详情 待面试 已面试
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:26 2019/7/19
     * @Param [signUpId]
     **/
    int recruitmentInterviewd(Integer signUpId, Integer briefChapterId);

    /**
     * 招聘详情 待面试 未面试
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:29 2019/7/19
     * @Param [signUpId]
     **/
    Map<String, Object> recruitmentNoInterviewd(Integer signUpId, Integer userId, Integer briefChapterId);

    /**
     * 招聘详情 待面试 已面试 面试未通过
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:51 2019/7/19
     * @Param [signUpId]
     **/
    int recruitmentInterviewFailed(Integer signUpId, Integer briefChapterId);

    /**
     * 招聘详情 待面试 已面试 面试已通过 面试返佣
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:50 2019/7/19
     * @Param [signUpId]
     **/
    int recruitmentInterviewSuccess(Integer signUpId, Integer briefChapterId, Integer sex, Integer signUpUserid, Integer busInessUserId);

    /**
     * 招聘详情 待报道 未报到
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:31 2019/7/19
     * @Param [signUpId]
     **/
    Map<String, Object> notReported(Integer signUpId, Integer briefChapterId, Integer userId);

    /**
     * 招聘详情 待报道 确认报道(已报到) 报道返佣
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:40 2019/7/19
     * @Param [signUpId]
     **/
    int reported(Integer signUpId, Integer briefChapterId, Integer sex, Integer signUpUserid, Integer busInessUserId);


    /**
     * 未报到原因
     *
     * @param reason   未报到原因
     * @param signUpId 报名者id
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:59 2019/7/19
     **/
    int noReportedReason(Integer reason, Integer signUpId, Integer briefChapterId, Integer type);


    /**
     * 招聘详情 待返佣 返佣
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:01 2019/7/19
     * @Param [userId]
     **/
    List<HrSignUp> rebatee(Integer signUpId, Integer sex);

    /**
     * 招聘详情 待返佣 没有返佣
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:45 2019/7/19
     * @Param [rebateId]
     **/
    int noRebate(Integer rebateId);

    /**
     * 招聘详情 待返佣 返佣计划
     *
     * @param query 入参
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:05 2019/7/19
     **/
    int rebate(RebateQuery query);

    /**
     * 重置求职者报名状态
     *
     * @param signUpId     报名id
     * @param status       求职状态
     * @param currentState 当前状态
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:07 2019/7/22
     **/
    int changeJobStatus(Integer signUpId, Integer status, Integer currentState, Integer briefChapterId);

    /**
     * 判断报名人数是否满了
     *
     * @param briefchapter 简章id
     * @return java.util.List<com.nado.rlzy.bean.dto.SignUpNumberDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:15 2019/7/17
     **/
    List<HrBriefchapter> numberOfRecruitsFull(Integer briefchapter);

    /**
     * 招聘端 邀请报名
     *
     * @param signUpId 报名id
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:07 2019/7/17
     **/
    int invitationToRegister(Integer signUpId);

    /**
     * 招聘端 不合适
     *
     * @param signUpId 报名id
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:21 2019/7/17
     **/
    int notSuitable(Integer signUpId);

    /**
     * 邀请面试
     *
     * @param signUpId 报名id
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:57 2019/7/17
     **/
    int invitationInterview(Integer signUpId);

    /**
     * 直接录取 招聘端
     *
     * @param signUpId 报名id
     * @param userId   推荐人id
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:12 2019/7/17
     **/
    int directAdmission(Integer signUpId, Integer userId, Integer sex, Integer briefchapter);


    /**
     * 根据配置表类型查 内容
     *
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:15 2019/7/8
     * @Param [type]
     **/
    List<HrDictionaryItem> selectContentByType(String type);

    /**
     * 招聘端 我的发布  正在招 编辑简章 代招单位
     *
     * @return java.lang.Integer
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:22 2019/7/24
     * @Param [query]
     **/
    Integer editBriefchapterMyRelease(EditBriefchapterQuery query);

    /**
     * 招聘端 我的发布  正在招 编辑简章 招聘单位
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:41 2019/9/1
     * @Param [query]
     * @return java.lang.Integer
     **/
    Integer editBriefchapterMyReleaseRecruitment(EditBriefchapterQuery query);

    /**
     * 编辑简章时查询入职返佣 查到的入职返佣信息 随着编辑简章的提交 传到后台 ======== 这个没啥用 入职返佣信息 发布简章时就有了
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:52 2019/8/28
     * @Param [briefchapterId]
     * @return java.util.List<com.nado.rlzy.db.pojo.EntryResignation>
     **/
    List<EntryResignation> selectEntryRebate(Integer briefchapterId);

    /**
     * 招聘端 我的发布 不通过 编辑简章
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:18 2019/8/20
     * @Param [query]
     **/
    int editBriefchapterFail(ReleaseBriefcharpterQuery query);


    /**
     * 查询招聘企业名称
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrGroup>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:08 2019/8/1
     * @Param [type, userId]
     **/
    Map<String, Object> selectGroupName(Integer type, Integer userId, Integer status);
}
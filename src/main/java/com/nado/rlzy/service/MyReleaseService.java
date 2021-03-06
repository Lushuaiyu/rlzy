package com.nado.rlzy.service;

import com.alibaba.fastjson.JSONObject;
import com.nado.rlzy.bean.query.*;
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
     * 招聘端 我的发布 子账号
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:39 2019/7/22
     * @Param [userId, typeId, status]
     **/
    Map<String, Object> myReleaseSubAccount(Integer userId, Integer status);


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
    void saveUser(ReleaseBriefcharpterQuery query, Integer type, JSONObject rebateEntry);

    /**
     * 招聘详情 概览
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:15 2019/7/18
     * @Param [list]
     **/
    List<HrSignUp> recruitmentDetailsOverview(String jobStatus);


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
    int recruitmentInterviewSuccess(Integer signUpId, Integer briefChapterId, Integer userId, Integer signupDeliveryrecordId);

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
    int reported(Integer signUpId, Integer briefChapterId, Integer userId, Integer signupDeliveryrecordId);


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
    int noReportedReason(Integer reason, Integer signUpId, Integer briefChapterId);


    /**
     * 招聘详情 待返佣 返佣
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:01 2019/7/19
     * @Param [userId]
     **/
    List<HrRebaterecord> rebatee(Integer signUpId, Integer briefchapterId);

    /**
     * 招聘详情 待返佣 不返佣
     *
     * @param query
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:45 2019/7/19
     **/
    int noRebate(RebateQuery query);

    /**
     * 招聘详情 待返佣 具体是入职返佣 返佣
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
     * @param hsdId  报名求职id
     * @param status 求职状态
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:07 2019/7/22
     **/
    int changeJobStatus(String hsdId, String status, String currentState);

    /**
     * 判断报名人数是否满了
     *
     * @param briefchapter 简章id
     * @return java.util.List<com.nado.rlzy.bean.dto.SignUpNumberDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:15 2019/7/17
     **/
    Map<String, Object> numberOfRecruitsFull(Integer briefchapter);

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
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:12 2019/7/17
     **/
    int directAdmission(Integer signUpId, Integer briefchapter);


    /**
     * 根据配置表类型查 内容
     *
     * @return
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:15 2019/7/8
     * @Param [type]
     */
    Map<String, Object> selectContentByType(DictionaryQuery query);

    /**
     * 前台选择页面 通用模板
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrDictionaryItem>
     * @Author chengpunan
     * @Description lushuaiyu
     * @Date 09:47 2019-09-09
     * @Param [type]
     */
    List<HrDictionaryItem> selectFrontEnd(Integer type);

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
     *
     * @return java.lang.Integer
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:41 2019/9/1
     * @Param [query]
     **/
    Integer editBriefchapterMyReleaseRecruitment(EditBriefchapterQuery query);

    /**
     * 编辑简章时查询入职返佣 查到的入职返佣信息 随着编辑简章的提交 传到后台 ======== 这个没啥用 入职返佣信息 发布简章时就有了
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.EntryResignation>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:52 2019/8/28
     * @Param [briefchapterId]
     **/
    List<EntryResignation> selectEntryRebate(Integer briefchapterId);


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

    /**
     * 编辑简章时回显 代招单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:22 2019/9/18
     * @Param [briefchapter]
     **/
    List<HrBriefchapter> editBriefchapterEcho(Integer briefchapter);

    /**
     * 编辑简章时回显 代招单位
     *
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:04 2019/9/18
     * @Param [briefchapter]
     **/
    List<HrBriefchapter> editBriefchapterEchoRecruitment(Integer briefchapter);
}
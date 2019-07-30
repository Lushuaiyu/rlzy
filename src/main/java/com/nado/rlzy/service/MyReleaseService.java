package com.nado.rlzy.service;

import com.nado.rlzy.bean.dto.ReleaseBriefcharpterDto;
import com.nado.rlzy.bean.dto.SignUpNumberDto;
import com.nado.rlzy.bean.frontEnd.ReleaseBriefcharpterFront;
import com.nado.rlzy.bean.query.EditBriefchapterQuery;
import com.nado.rlzy.bean.query.ReleaseBriefcharpterQuery;
import com.nado.rlzy.db.pojo.HrBriefchapter;
import com.nado.rlzy.db.pojo.HrSignUp;
import com.nado.rlzy.db.pojo.Options;
import com.nado.rlzy.db.pojo.Province;

import java.math.BigDecimal;
import java.util.List;

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
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:39 2019/7/22
     * @Param [userId, typeId, status]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrBriefchapter>
     **/
    List<HrBriefchapter> myRelease(Integer userId, Integer typeId, Integer status);

    /**
     * 查询发布简章的内容
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.ReleaseBriefcharpterDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:46 2019/6/28
     * @Param [query] 入参
     **/
    List<ReleaseBriefcharpterFront> queryReleaseBriefcharpterByparams(ReleaseBriefcharpterQuery query);


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
     * 男生年龄
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.ReleaseBriefcharpterDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:30 2019/7/3
     * @Param [manAgeId]
     **/
    List<ReleaseBriefcharpterDto> checkManAge(Integer manAgeId);

    /**
     * 女生年龄
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.ReleaseBriefcharpterDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:31 2019/7/3
     * @Param [womenAgeId]
     **/
    List<ReleaseBriefcharpterDto> checkWomenAge(Integer womenAgeId);

    /**
     * 加班时长
     *
     * @return java.util.List<com.nado.rlzy.bean.dto.ReleaseBriefcharpterDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:31 2019/7/3
     * @Param [overtimeTimeId]
     **/
    List<ReleaseBriefcharpterDto> checkOvertimeTime(Integer overtimeTimeId);

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
    int reportNotSuitable(Integer signUpId);

    /**
     * 招聘详情 已报名 邀请面试
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:18 2019/7/19
     * @Param [signUpId]
     **/
    int recruitmentDetailsInvitationInterview(Integer signUpId);

    /**
     * 招聘详情 已报名 直接录取
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:01 2019/7/19
     * @Param [signUpId]
     **/
    int recruitmentDetailsDirectAdmission(Integer signUpId);

    /**
     * 招聘详情 待面试 已面试
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:26 2019/7/19
     * @Param [signUpId]
     **/
    int recruitmentInterviewd(Integer signUpId);

    /**
     * 招聘详情 待面试 未面试
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:29 2019/7/19
     * @Param [signUpId]
     **/
    int recruitmentNoInterviewd(Integer signUpId);

    /**
     * 招聘详情 待面试 已面试 面试未通过
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:51 2019/7/19
     * @Param [signUpId]
     **/
    int recruitmentInterviewFailed(Integer signUpId);

    /**
     * 招聘详情 待面试 已面试 面试已通过
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:50 2019/7/19
     * @Param [signUpId]
     * @return int
     **/
    int recruitmentInterviewSuccess(Integer signUpId);

    /**
     * 招聘详情 待报道 未报到
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:31 2019/7/19
     * @Param [signUpId]
     * @return int
     **/
    int notReported(Integer signUpId);

    /**
     * 招聘详情 待报道 已报道
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:40 2019/7/19
     * @Param [signUpId]
     * @return int
     **/
    int reported(Integer signUpId);


    /**
     * 未报到原因
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:59 2019/7/19
     * @param reason 未报到原因
     * @param signUpId 报名者id
     * @return int
     **/
    int noReportedReason( Integer reason, Integer signUpId);


    /**
     * 招聘详情 待返佣 返佣计划
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:01 2019/7/19
     * @Param [userId]
     * @return java.util.List<com.nado.rlzy.db.pojo.HrSignUp>
     **/
    List<HrSignUp> rebate(Integer userId, Integer sex);

    /**
     * 招聘详情 待返佣 没有返佣
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:45 2019/7/19
     * @Param [rebateId]
     * @return int
     **/
    int noRebate(Integer rebateId);

    /**
     * 招聘详情 待返佣 返佣计划
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:05 2019/7/19
     * @param userId 招聘公司的id
     * @param signUpUserId 求职者 或者推荐人的id
     * @param addMoney 返佣多少钱
     * @param subtraction 减掉多少钱
     * @return int
     **/
    int rebate(Integer userId, Integer signUpUserId, BigDecimal addMoney, BigDecimal subtraction);

    /**
     * 重置求职者报名状态
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:07 2019/7/22
     * @param signUpId 报名id
     * @param status 求职状态
     * @param currentState 当前状态
     * @return int
     **/
    int changeJobStatus(Integer signUpId, Integer status, Integer currentState);

    /**
     * 判断报名人数是否满了
     *
     * @param briefchapter 简章id
     * @return java.util.List<com.nado.rlzy.bean.dto.SignUpNumberDto>
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:15 2019/7/17
     **/
    List<SignUpNumberDto> numberOfRecruitsFull(Integer briefchapter);

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
    int directAdmission(Integer signUpId, Integer userId, Integer sex);


    /**
     * 根据配置表类型查 内容
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:15 2019/7/8
     * @Param [type]
     * @return com.nado.rlzy.db.pojo.Options
     **/
    List<Options> selectContentByType(Integer type);

    /**
     * 招聘端 我的发布 编辑简章
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 14:22 2019/7/24
     * @Param [query]
     * @return java.lang.Integer
     **/
    Integer editBriefchapterMyRelease(EditBriefchapterQuery query);

    /**
     * 把待面试盖为未面试
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 11:40 2019/7/30
     * @Param [signUpId]
     * @return int
     **/
    Integer updateJobStatusInterviewed(Integer signUpId);
}